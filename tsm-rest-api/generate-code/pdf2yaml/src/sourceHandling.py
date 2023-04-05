"""
handles objects that contain relevant information of the source material
behaves indifferent to chosen source method
no direct call to pdf2txt, because the resulting TXT is saved by the parser and loaded later
sequence coordination is carried out by pdf2yaml
"""

import re
import os
from datetime import datetime

# from functools import partialmethod

from dataType import DataType
from interfaceMethod import InterfaceMethod

# choose 'fixedValueExamples' OR 'randomValueExamples' here
from exampleValues import FixedValueExamples as ExampleValues
from utilityFunctions import (
    getOperationIdsWithoutDoubles,
    renameOperationIdsWithOneUnderscore,
    pathSplitL,
    returnTextLineFromList,
    makeStringCamelCase,
    upperFirstChar,
)
from requestBodyDict import generateRequestBodyExamples


class Source:
    """definition of DataType Class"""

    def __init__(self, source_path: str, destination_name: str):
        """initialize object"""

        # take optional ending away
        destination_name = destination_name.split('.')[0]
        # current date and time
        now = datetime.now()
        # define paths, including archive path
        self.source_path = source_path
        if 'tsm-rest-api' in os.path.abspath(os.getcwd()):
            self.destination_path = os.path.join(
                os.path.abspath(os.getcwd()).split('tsm-rest-api')[0],
                'tsm-rest-api',
                'tsm-rest-api.yaml',
            )
            self.markdown_path = os.path.join(
                os.path.abspath(os.getcwd()).split('tsm-rest-api')[0],
                'tsm-rest-api',
                'README.md',
            )
        else:
            self.destination_path = os.path.join('.', f'{destination_name}.yaml')
            self.markdown_path = os.path.join('.', 'ref_table.md')
        archive_path = os.path.join('.', 'archive')
        if not os.path.exists(archive_path):
            os.mkdir(archive_path)
        self.destination_path_archive = os.path.join(
            archive_path, f'{destination_name}_{now.strftime("%Y%m%d")}.yaml'
        )
        # read source, replace headers/footers, normalize chars
        self.readSourceContent()
        # split source into relevant chapters
        self.splitSourceContent()
        # define data_types, based upon '4.1.3 Data Types'
        self.defDataTypeObj()
        ###self.data_types[0].printContent()
        # define data_types, based upon '4.1.5 Interface Methods'
        self.defInterfaceMethodObj()
        ###self.interface_methods[1].printContent()
        # generate dict for request body examples
        self.request_body_examples = generateRequestBodyExamples(self.destination_path)
        # define yaml-variable, that will contain the yaml-result later on
        self.yaml = []
        # define markdown-variable, that will contain the markdown-result later on
        self.markdown = []

    #################
    # Class Utility #
    #################

    def readSourceContent(self) -> None:
        """
        delete uninteresting lines of content
        manipulated source remains
        """
        # read from previous specified source
        with open(self.source_path, 'rt', encoding='utf-8') as file:
            self.source_text = file.readlines()
        # save source contents to general source
        with open(
            os.path.join('.', 'pdf', 'tr_03165_source.txt'), 'wt', encoding='utf-8'
        ) as file:
            file.writelines(self.source_text)
        # regular expressions
        re_kopfzeile = re.compile(r'4 Interfaces\n')
        re_fusszeile = re.compile(
            r'\d{0,3}[ ]?Federal Office for Information Security[ ]?\d{0,3}\n'
        )
        # use while, because list-elements are being deleted
        i = 0
        while i < len(self.source_text):
            # delete header-lines
            if re_kopfzeile.search(self.source_text[i]):
                self.source_text.pop(i)
                continue
            # delete footer-lines
            if re_fusszeile.search(self.source_text[i]):
                self.source_text.pop(i)
                continue
            self.source_text[i] = self.source_text[i].replace('”', "'")
            self.source_text[i] = self.source_text[i].replace('“', "'")
            self.source_text[i] = self.source_text[i].replace('’', "'")
            self.source_text[i] = self.source_text[i].replace('‘', "'")
            self.source_text[i] = self.source_text[i].replace(
                'called with:\n', 'called with\n'
            )
            self.source_text[i] = self.source_text[i].replace('•', '->')
            i += 1

    def splitSourceContent(self) -> None:
        """
        split source into section
        """
        re_chap_a = re.compile(r'^4.1.[34] Data Types$')
        re_chap_b = re.compile(r'^4.1.[45] Common Definitions$')
        re_chap_c = re.compile(r'^4.1.[56] Interface Methods$')
        re_chap_d = re.compile(r'^4.2 TSM-API$')
        # list of chapter lines
        chapter_lines = []
        for i, line in enumerate(self.source_text):
            if re_chap_a.search(line):  # line == '4.1.3 Data Types\n'):
                chapter_lines.append(i)
            elif re_chap_b.search(line):  # line == '4.1.4 Common Definitions\n'):
                chapter_lines.append(i)
            elif re_chap_c.search(line):  # line == '4.1.5 Interface Methods\n'):
                chapter_lines.append(i)
            elif re_chap_d.search(line):  # line == '4.2 TSM-API\n'):
                chapter_lines.append(i)
        # save source parts as class attributes
        self.source_data_types = self.source_text[chapter_lines[0] : chapter_lines[1]]
        self.source_common_definitions = self.source_text[
            chapter_lines[1] : chapter_lines[2]
        ]
        self.source_interface_methods = self.source_text[
            chapter_lines[2] : chapter_lines[3]
        ]
        # free space, keep when debugging
        del self.source_text

    def defInterfaceMethodObj(self) -> None:
        """define interface method objs"""
        lines = []
        re_kapitel = re.compile(r'^4.1.[56].[\d.]+ [\w /]+$')
        for i, line in enumerate(self.source_interface_methods):
            if re_kapitel.search(line.strip()):
                lines.append(i)
        lines.append(len(self.source_interface_methods))
        self.interface_methods = []
        for i in range(len(lines) - 1):
            self.interface_methods.append(
                InterfaceMethod(self.source_interface_methods[lines[i] : lines[i + 1]])
            )
        for i, method in enumerate(self.interface_methods):
            if method.type_ == 'supkapitel':
                self.interface_methods[i].tag = self.interface_methods[i + 1].tag

    def defDataTypeObj(self) -> None:
        """define data type objs"""
        lines = []
        re_chap_num = re.compile(r'^4.1.[34].[\d]{1,2} ')
        for i, line in enumerate(self.source_data_types):
            if re_chap_num.search(line):
                lines.append(i)
        lines.append(len(self.source_data_types))
        self.data_types = []
        for i in range(len(lines) - 1):
            self.data_types.append(
                DataType(self.source_data_types[lines[i] : lines[i + 1]])
            )

    def appendFileToYAML(self, source_file: str, ignore_comments=False) -> None:
        """append input to specified yaml file"""
        file_path = os.path.join('.', 'pdf', source_file)
        if os.path.isfile(file_path):
            with open(file_path, 'rt', encoding='utf-8') as file:
                for i in file.readlines():
                    if i[0] == '#':
                        if not ignore_comments:
                            self.yaml.append(i)
                    else:
                        self.yaml.append(i)

    ##########################
    # Content Section        #
    # Beginning with Header  #
    # cannot be read from TR #
    ##########################
    def addHeader(self) -> None:
        """add header to yaml"""
        self.yaml = []
        self.appendFileToYAML('source_header.yaml', True)
        # ignore 'tags:' from header, can be generated
        i = returnTextLineFromList(self.yaml, 'tags:\n')
        if i:
            self.yaml = self.yaml[:i]

    ################
    # Tags Section #
    ################
    def addTags(self) -> None:
        """add tags section to yaml"""
        tags = []
        tags_description = []
        for method in self.interface_methods:
            if method.type_ == 'supkapitel':
                tags_description.append(
                    method.title.replace('Manage ', 'methods for management of ')
                    .replace('Authentication', 'authentication methods')
                    .replace('of SP Account', 'of SP')
                    .replace('ApplicationConfigs', 'ApplicationConfigurations')
                )
            elif method.type_ == 'kapitel':
                if method.tag not in tags:
                    tags.append(method.tag)
        self.yaml.append('tags:\n')
        for i, tag in enumerate(tags):
            self.yaml.append(f'- name: {tag}\n  description: "{tags_description[i]}"\n')
        self.yaml.append('\n')

    ################################
    # Paths Section                #
    # depends on Interface Methods #
    ################################
    def addPaths(self) -> None:
        """add paths section to yaml"""
        self.yaml.append('paths:\n')
        self.yaml.append('\n')
        last_url = ''
        # get operation_ids, no doubles should be in this list
        operation_ids = getOperationIdsWithoutDoubles(self.interface_methods)
        # rename operation_ids for better readable names
        operation_ids = renameOperationIdsWithOneUnderscore(operation_ids)
        # handle every interfaceMethod to generate /paths
        for i, interface_method_ in enumerate(self.interface_methods):
            self.interface_methods[i].operation_id = operation_ids[i]
            self.yaml.append(
                f'### {interface_method_.num} {interface_method_.title} ###\n'
            )
            if interface_method_.type_ == 'supkapitel':
                self.yaml.append('#')
                for j in interface_method_.description:
                    self.yaml.append(f' {j}')
                    if j[-1] == '.':
                        self.yaml.append('\n#')
                self.yaml[-1] = self.yaml[-1].replace('#', '')
                self.yaml.append('\n')
            if interface_method_.type_ == 'kapitel':
                if interface_method_.rest_url != last_url:
                    self.yaml.append(f'  {interface_method_.rest_url}:\n')
                    last_url = interface_method_.rest_url
                else:
                    self.yaml.append(f'  #{interface_method_.rest_url}:\n')
                self.yaml.append(f'    {interface_method_.request_method}:\n')
                self.yaml.append('      tags:\n')
                self.yaml.append(f'      - {interface_method_.tag}\n')
                if len(interface_method_.description) > 70:
                    self.yaml.append(
                        f'      summary: "{interface_method_.description[:65]}..."\n'
                    )
                else:
                    self.yaml.append(
                        f'      summary: "{interface_method_.description}"\n'
                    )
                self.yaml.append(
                    f'      description: "{interface_method_.description}"\n'
                )
                self.yaml.append(f'      operationId: {operation_ids[i]}\n')
                # headers
                if '4.1.6.5.4' in interface_method_.num:
                    pass
                self.yaml.append('      ##### Request Headers:\n')
                if 'Authorization' in interface_method_.request_headers:
                    self.yaml.append('      security:\n')
                    self.yaml.append(
                        f'        - {interface_method_.request_headers["Authorization"].replace("<<","").replace(">>","").replace("-","")}: []\n'
                    )
                if 'Content-Type' in interface_method_.request_headers:
                    self.yaml.append(
                        f'      ##### Content-Type: {interface_method_.request_headers["Content-Type"]}\n'
                    )
                if 'Accept' in interface_method_.request_headers:
                    self.yaml.append(
                        f'      ##### Accept: {interface_method_.request_headers["Accept"]}\n'
                    )
                # request body
                if interface_method_.request_file:
                    self.yaml.append('      requestBody:\n')
                    if interface_method_.tag == '/executable-load-files':
                        self.yaml.append(
                            '        $ref: "#/components/requestBodies/binaryELF"\n'
                        )
                    elif interface_method_.tag == '/personalization-scripts':
                        self.yaml.append(
                            '        $ref: "#/components/requestBodies/binaryPersoScript"\n'
                        )
                    elif interface_method_.tag == '/certificates':
                        self.yaml.append(
                            '        $ref: "#/components/requestBodies/binaryCertificate"\n'
                        )
                elif interface_method_.request_body:
                    self.yaml.append('      requestBody:\n')
                    if len(interface_method_.request_body) == 1:
                        self.yaml.append('        required: true\n')
                        self.yaml.append('        content:\n')
                        self.yaml.append('          application/json:\n')
                        self.yaml.append('            schema:\n')
                        self.yaml.append(
                            f'              $ref: "#/components/schemas/{interface_method_.request_body[0].strip()}"\n'
                        )
                        key = interface_method_.request_body[0].strip()
                        if key in self.request_body_examples:
                            self.yaml.append('            example:\n')
                            for request_body_example in self.request_body_examples[key]:
                                self.yaml.append(
                                    f'              {request_body_example}\n'
                                )
                    elif len(interface_method_.request_body) == 2:
                        self.yaml.append(
                            f'        description: "{interface_method_.request_body[1].strip()}"\n'
                        )
                        self.yaml.append('        required: true\n')
                        self.yaml.append('        content:\n')
                        self.yaml.append(
                            f'          {interface_method_.request_headers["Accept"]}:\n'
                        )
                        self.yaml.append('            schema:\n')
                        if interface_method_.request_body[0].strip()[-2:] == '[]':
                            self.yaml.append('              type: array\n')
                            self.yaml.append('              items:\n')
                            self.yaml.append(
                                f'                type: {interface_method_.request_body[0].strip()[:-2]}\n'
                            )
                        elif 'Map<string,' in interface_method_.request_body[0]:
                            self.yaml.append('              type: object\n')
                            self.yaml.append('              additionalProperties:\n')
                            if 'string[]>' in interface_method_.request_body[0]:
                                self.yaml.append('                type: array\n')
                                self.yaml.append('                items:\n')
                                self.yaml.append('                  type: string\n')
                                self.yaml.append('                  maxLength: 255\n')
                    else:
                        for body in interface_method_.request_body:
                            self.yaml.append(f'        {body}\n')
                else:
                    self.yaml.append('      ##### Request Body: -\n')
                # parameters
                if interface_method_.parameters:
                    self.yaml.append('      parameters:\n')
                    for param in interface_method_.parameters:
                        self.yaml.append(f'      {param}\n')
                # response on success
                self.yaml.append('      responses:\n')
                if interface_method_.response_headers_success['Status Code'] == '200':
                    self.yaml.append('        200:\n')
                    if interface_method_.response_body_success == 'byte[]':
                        if interface_method_.tag == '/executable-load-files':
                            self.yaml.append(
                                '          $ref: "#/components/responses/200Binary_ELF"\n'
                            )
                        elif interface_method_.tag == '/personalization-scripts':
                            self.yaml.append(
                                '          $ref: "#/components/responses/200Binary_Script"\n'
                            )
                        elif interface_method_.tag == '/certificates':
                            self.yaml.append(
                                '          $ref: "#/components/responses/200Binary_Cert"\n'
                            )
                    else:
                        self.yaml.append('          description: Ok\n')
                        self.yaml.append('          content:\n')
                        self.yaml.append('            application/json:\n')
                        self.yaml.append('              schema:\n')
                        if interface_method_.response_body_success[-2:] == '[]':
                            self.yaml.append('                type: array\n')
                            self.yaml.append('                items:\n')
                            self.yaml.append(
                                f'                  $ref: "#/components/schemas/{interface_method_.response_body_success.replace("[]","")}"\n'
                            )
                        else:
                            self.yaml.append(
                                f'                $ref: "#/components/schemas/{interface_method_.response_body_success}"\n'
                            )
                elif interface_method_.response_headers_success['Status Code'] == '204':
                    self.yaml.append('        204:\n')
                    self.yaml.append(
                        '          description: "item deleted successfully"\n'
                    )
                # response on failure
                if '400' in interface_method_.response_headers_failure['Status Code']:
                    self.yaml.append(
                        f'        400:\n          {interface_method_.ref400}\n'
                    )  # .replace('__','_')
                if '401' in interface_method_.response_headers_failure['Status Code']:
                    self.yaml.append(
                        f'        401:\n          {interface_method_.ref401}\n'
                    )  # .replace('__','_')
                if '500' in interface_method_.response_headers_failure['Status Code']:
                    self.yaml.append(
                        f'        500:\n          {interface_method_.ref500}\n'
                    )  # .replace('__','_')
                self.yaml.append('\n')

    ############################################
    # add path-parameter section               #
    # those are read from InterfaceMethod-URLs #
    ############################################
    def addParameters(self) -> None:
        """add parameters section to yaml"""
        self.yaml.append('components:\n')
        self.yaml.append('\n')
        self.yaml.append('  parameters:\n')
        self.yaml.append('  \n')
        parameter_list = []
        for i in self.source_interface_methods:
            re_result = re.findall(r'/\{[\w]+\}', i)
            for j in re_result:
                parameter_list.append(j[2:-1])
        parameter_list = list(dict.fromkeys(parameter_list))
        for i in parameter_list:
            self.yaml.append(f'    {i}:\n')
            self.yaml.append('      in: path\n')
            self.yaml.append(f'      name: {i}\n')
            self.yaml.append('      required: true\n')
            self.yaml.append('      schema:\n')
            self.yaml.append('        type: string\n')
            if 'Id' in i:
                self.yaml.append('        format: uuid\n')
                # uuid-length shall not be specified
                # self.yaml.append('        maxLength: 36\n')
                # self.yaml.append('        minLength: 36\n')
                self.yaml.append(f'        example: "{ExampleValues.get("uuid")}"\n')
            else:
                self.yaml.append('        format: version\n')
                self.yaml.append('        maxLength: 11\n')
                self.yaml.append('        minLength: 5\n')
                self.yaml.append(r"        pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n")
                self.yaml.append(f'        example: "{ExampleValues.get("version")}"\n')
            self.yaml.append(
                f'      description: identifier of the referred {i[0].upper()+i[1:].replace("Id","")}\n'
            )
            self.yaml.append('    \n')

    #####################################################
    # add requestBodies section (from pre-defined file) #
    #####################################################
    def addRequestBodies(self) -> None:
        """add request bodies section to yaml"""
        self.yaml.append('  requestBodies:\n')
        self.yaml.append('  \n')
        self.appendFileToYAML('source_components_requestBodies.yaml')

    ################################################
    # add response section (from pre-defined file) #
    ################################################
    def addResponses(self) -> None:
        """add responses section to yaml"""
        self.yaml.append('  responses:\n')
        self.yaml.append('  \n')
        self.appendFileToYAML('source_components_responses.yaml')

    #######################################################
    # add securitySchemes section (from pre-defined file) #
    #######################################################
    def addSecuritySchemes(self) -> None:
        """add security schemes section to yaml"""
        self.yaml.append('  securitySchemes:\n')
        self.yaml.append('  \n')
        self.appendFileToYAML('source_components_securitySchemes.yaml')

    #######################
    # add schemas section #
    #######################
    def addSchemas(self) -> None:
        """add schemas section to yaml"""
        # compile regular expressions
        re_default = re.compile('default value')
        # append to result
        self.yaml.append('### 4.1.4 ###\n')
        self.yaml.append('  schemas:\n')
        self.yaml.append('  \n')
        self.appendFileToYAML('source_components_schemas_separate.yaml')
        schema_titles = []
        for i in self.data_types:
            schema_titles.append(i.title)
        for d_type in self.data_types:
            # head
            self.yaml.append(f'### {d_type.num} ###\n')
            self.yaml.append(f'    {d_type.title}:\n')
            self.yaml.append(f'      description: "{d_type.description}"\n')
            if d_type.title == 'ExecutableLoadFile':
                self.yaml.append('      discriminator:\n')
                self.yaml.append('        propertyName: type\n')
                self.yaml.append('        mapping:\n')
                self.yaml.append('          cap: "#/components/schemas/CAP"\n')
            if d_type.title == 'CAP':
                self.yaml.append('      allOf:\n')
                self.yaml.append(
                    '        - $ref: "#/components/schemas/ExecutableLoadFile"\n'
                )
            self.yaml.append('      type: object\n')
            mandatory = ''
            for i, att4mand in enumerate(d_type.att4mand):
                if att4mand == 'M':
                    mandatory += f'{d_type.att1[i]}, '
            # required
            if len(mandatory) > 0:
                self.yaml.append(f'      required: [{mandatory[:-2]}]\n')
            else:
                self.yaml.append('      #required: []\n')
            self.yaml.append('      properties:\n')
            for i, att1 in enumerate(d_type.att1):
                self.yaml.append(
                    f'        {att1}: # {d_type.att4mand[i]}, {d_type.att5ed[i]}\n'
                )
                if d_type.att2type[i] in schema_titles:
                    # schema
                    self.yaml.append(
                        f'          $ref: "#/components/schemas/{d_type.att2type[i]}" # "{d_type.att3desc[i]}"\n'
                    )
                else:
                    # description
                    self.yaml.append(
                        f'          description: "{d_type.att3desc[i].replace(":","")}"\n'
                    )
                    type_ = ""
                    # string
                    if d_type.att2type[i] == 'string':
                        self.yaml.append('          type: string\n')
                        # version
                        if (
                            'gpSpec' == d_type.att1[i]
                            or 'javaCardVersion' == d_type.att1[i]
                            or 'javaCard' == d_type.att1[i]
                        ):
                            type_ = 'version'
                            self.yaml.append('          format: version\n')
                            self.yaml.append('          maxLength: 11\n')
                            self.yaml.append('          minLength: 5\n')
                            self.yaml.append(
                                r"          pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n"
                            )
                        # versionMM
                        elif (
                            ("('major.minor')" in d_type.att3desc[i])
                            or 'gpApi' == d_type.att1[i]
                            or 'gpApiVersion' == d_type.att1[i]
                        ):
                            type_ = 'versionMM'
                            self.yaml.append('          format: version\n')
                            self.yaml.append('          maxLength: 7\n')
                            self.yaml.append('          minLength: 3\n')
                            self.yaml.append(
                                r"          pattern: '^\d{1,3}.\d{1,3}$" + "'\n"
                            )
                        # aid
                        elif ('AID' in d_type.att3desc[i]) or ('aid' == d_type.att1[i]):
                            type_ = "aid"
                            self.yaml.append('          format: aid\n')
                        # uuid
                        elif d_type.att1[i][-2:].lower() == 'id':
                            type_ = "uuid"
                            self.yaml.append('          format: uuid\n')
                        # date-time
                        elif "Date" in d_type.att1[i]:
                            type_ = "date-time"
                            self.yaml.append('          format: date-time\n')
                        # uri
                        elif "URL" in d_type.att3desc[i]:
                            type_ = "uri"
                            self.yaml.append('          format: uri\n')
                            self.yaml.append('          maxLength: 500\n')
                        # tlv
                        elif "TLV" in d_type.att3desc[i]:
                            type_ = "tlv"
                            self.yaml.append('          format: tlv\n')
                            self.yaml.append('          maxLength: 255\n')
                        # hardwarePlatform
                        elif 'hardwarePlatform' == d_type.att1[i]:
                            type_ = 'platform'
                            self.yaml.append('          maxLength: 255\n')
                        # operatingSystem
                        elif 'os' == d_type.att1[i]:
                            type_ = 'os'
                            self.yaml.append('          maxLength: 255\n')
                        # osVersion
                        elif 'osVersion' == d_type.att1[i]:
                            type_ = 'osVersion'
                            self.yaml.append('          maxLength: 7\n')
                            self.yaml.append('          minLength: 3\n')
                            self.yaml.append(
                                r"          pattern: '^\d{1,3}.\d{1,3}$'" + "\n"
                            )
                        # operatingSystem
                        elif 'operatingSystemCode' == d_type.att1[i]:
                            type_ = 'osCode'
                            self.yaml.append('          maxLength: 255\n')
                        # platformCertification
                        elif 'certifications' == d_type.att1[i]:
                            type_ = 'platformCert'
                            self.yaml.append('          maxLength: 255\n')
                        # cspVendor
                        elif 'cspVendor' == d_type.att1[i]:
                            type_ = 'cspVendor'
                            self.yaml.append('          maxLength: 255\n')
                        # cspVendor
                        elif 'cspApiVersion' == d_type.att1[i]:
                            self.yaml.append('          maxLength: 7\n')
                            self.yaml.append('          minLength: 0\n')
                            self.yaml.append(
                                r"          pattern: '^\d{1,3}.\d{1,3}$'" + "\n"
                            )
                        # name
                        elif "name" in d_type.att1[i].lower():
                            type_ = "name"
                            self.yaml.append('          maxLength: 255\n')
                        # name
                        elif "description" in d_type.att1[i].lower():
                            type_ = "description"
                            self.yaml.append('          maxLength: 255\n')
                        # general
                        else:
                            type_ = "generalString"
                            self.yaml.append('          maxLength: 255\n')
                    # version
                    elif '<Major>.<Minor>.<Revision>' in d_type.att3desc[i]:
                        type_ = "version"
                        self.yaml.append('          type: string\n')
                        self.yaml.append('          format: version\n')
                        self.yaml.append('          maxLength: 11\n')
                        self.yaml.append('          minLength: 5\n')
                        self.yaml.append(
                            r"          pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n"
                        )
                    # string-vektor
                    elif d_type.att2type[i] == 'string[]':
                        self.yaml.append('          type: array\n')
                        self.yaml.append('          items:\n')
                        self.yaml.append('            type: string\n')
                        if 'Versions' in d_type.att3desc[i]:
                            type_ = "version[]"
                            self.yaml.append('            format: version\n')
                            self.yaml.append('            maxLength: 11\n')
                            self.yaml.append('            minLength: 5\n')
                            self.yaml.append(
                                r"            pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'"
                                + "\n"
                            )
                        elif 'accessAuthorizedDeviceApps' == d_type.att1[i]:
                            type_ = "aid[]"
                            self.yaml.append('            format: aid\n')
                        elif 'List of IDs' in d_type.att3desc[i]:
                            type_ = 'uuid[]'
                            self.yaml.append('            format: uuid\n')
                        else:
                            self.yaml.append('            maxLength: 255\n')
                    # general vector
                    elif d_type.att2type[i][-2:] == '[]':
                        self.yaml.append('          type: array\n')
                        self.yaml.append('          items:\n')
                        if d_type.att2type[i][:-2] in schema_titles:
                            self.yaml.append(
                                f'            $ref: "#/components/schemas/{d_type.att2type[i][:-2]}"\n'
                            )
                        # pair
                        elif 'cspELFsVersions' == d_type.att1[i]:
                            type_ = 'pair[]'
                            self.yaml.append('            type: array\n')
                            self.yaml.append('            minLength: 2\n')
                            self.yaml.append('            maxLength: 2 # == Pair\n')
                            self.yaml.append('            items:\n')
                            self.yaml.append('              type: string\n')
                            self.yaml.append('              maxLength: 11\n')
                            self.yaml.append('              minLength: 5\n')
                            self.yaml.append(
                                r"              pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'"
                                + "\n"
                            )
                    # integer
                    elif d_type.att2type[i][:3] == 'int':
                        type_ = 'int'
                        self.yaml.append('          type: integer\n')
                        if 'priority' == d_type.att1[i]:
                            self.yaml.append('          minimum: 1\n')
                            self.yaml.append('          maximum: 255\n')
                        elif 'errorCategory' == d_type.att1[i]:
                            self.yaml.append('          minimum: 1000\n')
                            self.yaml.append('          maximum: 9999\n')
                        elif 'scType' == d_type.att1[i]:
                            type_ = 'scType'
                            self.yaml.append('          minimum: 1\n')
                            self.yaml.append('          maximum: 4\n')
                    # boolean
                    elif d_type.att2type[i] == 'boolean':
                        type_ = 'boolean'
                        self.yaml.append('          type: boolean\n')
                        # default values, especially relevant for boolean
                        default_result = re_default.search(d_type.att3desc[i].lower())
                        if default_result:
                            _, default_end = default_result.span()
                            if (
                                'true'
                                in d_type.att3desc[i][
                                    default_end : default_end + 9
                                ].lower()
                            ):
                                self.yaml.append('          default: true\n')
                            elif (
                                'false'
                                in d_type.att3desc[i].lower()[
                                    default_end : default_end + 9
                                ]
                            ):
                                self.yaml.append('          default: false\n')
                            else:
                                print('EXCEPTION AT DEFAULT HANDLING:')
                                print(d_type.att1[i])
                                print(d_type.att3desc[i])
                                print(
                                    d_type.att3desc[i][
                                        default_end : default_end + 9
                                    ].lower()
                                )
                                print()
                    # Map<string, string>
                    elif d_type.att2type[i] == 'Map<string, string>':
                        type_ = 'stringMap'
                        self.yaml.append('          type: object\n')
                        self.yaml.append('          additionalProperties:\n')
                        self.yaml.append('            type: string\n')
                        self.yaml.append('            maxLength: 255\n')
                        if d_type.att1[i] == 'spParameters':
                            type_ = 'spParametersMap'
                        elif d_type.att1[i] == 'contextSpecificAttributes':
                            type_ = 'spParametersMap'
                        elif d_type.att1[i] == 'gpSpecVersions':
                            type_ = 'gpSpecVersionsMap'
                        elif d_type.att1[i] == 'gpApiVersions':
                            type_ = 'gpApiVersionsMap'
                        elif d_type.att1[i] == 'csp':
                            type_ = 'cspMap'
                        elif d_type.att1[i] == 'certifications':
                            type_ = 'certificationsMap'
                    # Map<string, string[]>
                    elif d_type.att2type[i] == 'Map<string, string[]>':
                        type_ = 'stringVecMap'
                        self.yaml.append('          type: object\n')
                        self.yaml.append('          additionalProperties:\n')
                        self.yaml.append('            type: array\n')
                        self.yaml.append('            items:\n')
                        self.yaml.append('              type: string\n')
                        self.yaml.append('              maxLength: 255\n')
                        if d_type.att1[i] == 'features':
                            type_ = 'featuresMap'
                    # Map<string, string[]>
                    elif d_type.att2type[i] == 'Map<string, boolean>':
                        type_ = 'booleanMap'
                        self.yaml.append('          type: object\n')
                        self.yaml.append('          additionalProperties:\n')
                        self.yaml.append('            type: boolean\n')
                        if d_type.att1[i] == 'genericOptions':
                            type_ = 'genericOptions'
                            self.yaml.append('          default: {}\n')
                    # _links
                    elif d_type.att2type[i] == 'Map<string, Link>':
                        type_ = '_links'
                        self.yaml.append('          type: object\n')
                        self.yaml.append('          additionalProperties:\n')
                        self.yaml.append('            type: string\n')
                        self.yaml.append('            format: uri\n')
                        self.yaml.append('            maxLength: 500\n')
                    # key-value map
                    elif 'Map<string,' in d_type.att2type[i]:
                        self.yaml.append('          type: object\n')
                        self.yaml.append('          additionalProperties:\n')
                        # values are string-lists
                        if 'string[]>' in d_type.att2type[i]:
                            self.yaml.append('            type: array\n')
                            self.yaml.append('            items:\n')
                            self.yaml.append('              type: string\n')
                            self.yaml.append('              maxLength: 255\n')
                    if d_type.att5ed[i] == 'No':
                        # readOnly
                        self.yaml.append('          readOnly: true\n')
                    # example section
                    if type_ == 'version':
                        self.yaml.append(
                            f'          example: "{ExampleValues.get("version")}"\n'
                        )
                    elif type_ == 'versionMM':
                        self.yaml.append(
                            f'          example: "{ExampleValues.get("versionMM")}"\n'
                        )
                    elif type_ == 'uuid':
                        self.yaml.append(
                            f'          example: "{ExampleValues.get("uuid")}"\n'
                        )
                    elif type_ == 'aid':
                        self.yaml.append(
                            f'          example: "{ExampleValues.get("aid")}"\n'
                        )
                    elif type_ == 'uri':
                        self.yaml.append(
                            f'          example: "{ExampleValues.get("uri")}"\n'
                        )
                    elif type_ == 'tlv':
                        self.yaml.append(
                            f'          example: "{ExampleValues.get("tlv")}"\n'
                        )
                    elif type_ == 'boolean':
                        if not default_result:
                            if d_type.att1[i] == 'csp':
                                self.yaml.append('          example: true\n')
                            else:
                                self.yaml.append(
                                    f'          example: {ExampleValues.get("bool")}\n'
                                )
                    elif type_ == 'scType':
                        self.yaml.append('          example: 1\n')
                    elif type_ == 'platform':
                        self.yaml.append('          example: "P62G98"\n')
                    elif type_ == 'os':
                        self.yaml.append('          example: "JCOP"\n')
                    elif type_ == 'osVersion':
                        self.yaml.append('          example: "4.7"\n')
                    elif type_ == 'featuresMap':
                        self.yaml.append(
                            '          example: { "cypher": [ "alg1", "alg2" ], "signature": [ "alg3" ], "checksum": [ "alg4", "alg5", "alg6" ] }\n'
                        )
                    elif type_ == 'gpSpecVersionsMap':
                        self.yaml.append(
                            '          example: { "card": "2.3.1", "contactlessServices": "2.3" }\n'
                        )
                    elif type_ == 'gpApiVersionsMap':
                        self.yaml.append(
                            '          example: { "card": "1.7", "contactless": "1.6" }\n'
                        )
                    elif type_ == 'cspMap':
                        self.yaml.append(
                            '          example: { "apiVersion": "1.0", "vendor": "nxp" }\n'
                        )
                    elif type_ == 'certificationsMap':
                        self.yaml.append(
                            '          example: { "BSI-CC-PP-0084-2014": "link/to/letter/of/approval/1", "BSI-CC-PP-0100-2018": "link/to/letter/of/approval/2" }\n'
                        )
                    elif type_ == 'osCode':
                        self.yaml.append('          example: "H145G"\n')
                    elif type_ == 'platformCert':
                        self.yaml.append('          example: "CC EAL5"\n')
                    elif type_ == 'cspVendor':
                        self.yaml.append('          example: "nxp"\n')
                    elif type_ == 'cspApiVersion':
                        self.yaml.append('          example: "1.0"\n')
                    elif type_ == 'name':
                        self.yaml.append(
                            f'          example: "{makeStringCamelCase(d_type.att3desc[i]," ").replace(".","")}"\n'
                        )
                    elif type_ == 'description':
                        self.yaml.append(f'          example: "{d_type.att3desc[i]}"\n')
                    elif type_ == 'spParametersMap':
                        examples = ''
                        for i in range(1, 4):
                            examples += f'param{str(i)}: "Value{str(i)} which can be evaluated by the App.", '
                        self.yaml.append(
                            '          example: { ' + examples[:-2] + ' }\n'
                        )
                    elif type_ == 'uuid[]':
                        examples = ''
                        for i in range(1, 4):
                            examples += f'"{ExampleValues.get("uuid")}", '
                        self.yaml.append(f'          example: [ {examples[:-2]} ]\n')
                    elif type_ == 'aid[]':
                        examples = ''
                        for i in range(1, 4):
                            examples += f'"{ExampleValues.get("aid")}", '
                        self.yaml.append(f'          example: [ {examples[:-2]} ]\n')
                    elif type_ == 'pair[]':
                        examples = ''
                        for i in range(1, 4):
                            examples += f'["{ExampleValues.get("version")}", "{ExampleValues.get("version")}"], '
                        self.yaml.append(f'          example: [ {examples[:-2]} ]\n')
            if d_type.att1[i] == '_links':
                if d_type.key1:
                    self.yaml.append('          example:\n')
                    for i, key1 in enumerate(d_type.key1):
                        self.yaml.append(f'            {key1}: {d_type.key3Desc[i]}\n')
                else:
                    self.yaml.append(
                        '          # KEINE ANGABEN FÜR LINKS, DAHER KEINE BEISPIELE\n'
                    )

            self.yaml.append('\n')

    def markdownTable(self) -> None:
        """
        generate reference table, written in markdown
        added to manually written README.md in main folder
        """
        # construct reference table out of extracted TR-information
        self.markdown = []
        self.markdown.append(
            'The following table provides an overview about all available API methods. Section numbers refer to TR-03165.\n'
        )
        self.markdown.append(
            '\n|*Section*|*Description*|\n|:----------|:-------------|'
        )
        last_type_ = ''
        for i in self.interface_methods:
            if i.type_ == 'supkapitel':
                if last_type_ == 'kapitel':
                    self.markdown.append('</ul> | ')
                last_type_ = i.type_
                self.markdown.append(
                    f'\n|[{i.title}](./docs/{upperFirstChar(makeStringCamelCase(i.tag[1:],"-"))}Api.md) | '
                )
                if '/auth' == i.tag:
                    self.markdown.append(
                        'This section lists methods for authentication to the TSM Backend. '
                    )
                elif '/secure-component-profiles' == i.tag:
                    self.markdown.append(
                        'This section provides methods to retrieve information about by the TSMS supported hardware platforms. '
                    )
                else:
                    for des in i.description:
                        self.markdown.append(f'{des} ')
                self.markdown.append('<ul>')
            elif i.type_ == 'kapitel':
                last_type_ = i.type_
                self.markdown.append(
                    f'<li> {i.request_method.upper()} [**{i.operation_id}**](./docs/{upperFirstChar(makeStringCamelCase(i.tag[1:],"-"))}Api.md#{i.operation_id})</li>'
                )
        self.markdown.append('</ul> | \n\n\n')
        self.markdown.append('[Back to Overview](../README.md)\n')

        # open original README text
        with open(self.markdown_path, 'rt', encoding='utf-8') as file:
            readme_text = file.readlines()

        # identify reference table
        i_start = 0
        i_end = 0

        for i, line in enumerate(readme_text):
            if line == self.markdown[0]:
                i_start = i
            elif line == self.markdown[-1]:
                i_end = i

        # replace reference table
        if i_start and i_end:
            readme_text[i_start : i_end + 1] = self.markdown

        # write modified README text
        with open(self.markdown_path, 'wt', encoding='utf-8') as file:
            file.writelines(readme_text)

    def saveResult(self) -> None:
        """write self.yaml to file for later use"""
        # lates version with current date
        with open(self.destination_path_archive, 'wt', encoding='utf-8') as file:
            file.writelines(self.yaml)

        # actual used output without date
        # save to previous specified destination
        with open(self.destination_path, 'wt', encoding='utf-8') as file:
            file.writelines(self.yaml)

    def process(self) -> None:
        """process function of necessary steps"""
        self.addHeader()
        self.addTags()
        self.addPaths()
        self.addParameters()
        self.addRequestBodies()
        self.addResponses()
        self.addSecuritySchemes()
        self.addSchemas()
        # reset example counter for re-run
        ExampleValues.reset()
        # create markdown table
        self.markdownTable()

    def generate(self) -> None:
        """generate result by combining functions: load AND save"""
        self.process()
        # out
        self.saveResult()

    def generateReturn(self) -> list:
        """generate result by combining functions: load AND return (instead of save)"""
        self.process()
        # out
        return self.yaml


# test environment
if __name__ == '__main__':
    # change folder to projfolder
    os.chdir(pathSplitL(__file__, 2))
    # Path to source.txt
    source_path_test = os.path.join('.', 'pdf', 'tr_03165_source.txt')
    # Path to source.txt
    DESTINATION_NAME = 'sourceHandling.yaml'
    # generate example
    example = Source(source_path_test, DESTINATION_NAME)

    example.generate()

    for num in range(int(len(example.interface_methods) / 3)):
        example.interface_methods[num].printContent()

    for num in range(int(len(example.data_types) / 3)):
        example.data_types[num].printContent()
