"""
handles objects that contain relevant information of the source material
behaves indifferent to chosen source method
no direct call to pdf2txt, because the resulting TXT is saved by the parser and loaded later
sequence coordination is carried out by pdf2yaml
"""

import re
from pathlib import Path
from datetime import datetime

from dataType import DataType
from interfaceMethod import InterfaceMethod

# choose 'fixedValueExamples' OR 'randomValueExamples' here
from exampleValues import FixedValueExamples as ExampleValues
from utilityFunctions import (
    getOperationIdsWithoutDoubles,
    renameOperationIdsWithOneUnderscore,
    returnTextLineFromList,
    makeStringCamelCase,
    upperFirstChar,
)
from requestBodyDict import generateRequestBodyExamples


class Source:
    """definition of DataType Class"""

    def __init__(self, source_path: Path, destination_name: str):
        """initialize object"""

        # take optional ending away
        destination_name = destination_name.split('.')[0]
        # current date and time
        now = datetime.now()
        f_date = f'{now.year:04d}{now.month:02d}{now.day:02d}'
        # define paths, including archive path
        self.source_path = source_path

        dest_folder = Path.cwd()
        if 'tsm-rest-api' in Path.cwd().parts:
            while dest_folder.parts[-1] != 'tsm-rest-api':
                dest_folder = dest_folder.parents[0]
            self.destination_path = dest_folder / 'tsm-rest-api.yaml'
            self.markdown_path = dest_folder / 'README.md'
        else:
            self.destination_path = Path(dest_folder, destination_name + '.yaml')
            self.markdown_path = Path(dest_folder, 'ref_table.md')
        archive_path = Path(dest_folder, 'generate-code', 'pdf2yaml', 'archive')
        if not archive_path.is_dir():
            archive_path.mkdir()
        self.destination_path_archive = (
            archive_path / f'{destination_name}_{f_date}.yaml'
        )
        # read source, replace headers/footers, normalize chars
        self.readSourceContent()
        # split source into relevant chapters
        self.splitSourceContent()
        # define data_types, based upon '4.1.3 Data Types'
        self.defDataTypeObj()
        # define data_types, based upon '4.1.5 Interface Methods'
        self.defInterfaceMethodObj()
        # generate dict for request body examples, announce to all interface methods
        InterfaceMethod.request_body_examples = generateRequestBodyExamples(
            self.destination_path
        )
        # define yaml-variable, that will contain the yaml-result later on
        self.yaml = []
        # define markdown-variable, that will contain the markdown-result later on
        self.markdown = []
        # output for testing purposes (experimental / testing)
        with open('test_interface','wt',encoding='utf-8') as fobj:
            Text = []
            Text.append('chapter\trest_url\trequest_method\ttitle\trequest_headers\trequest_elf\trequest_file\trequest_body\tresponse_headers_success\tresponse_body_success\tresponse_headers_failure\tresponse_body_failure\n')
            for i in self.interface_methods:
                if i.type_ == 'kapitel':
                    if not hasattr(i, 'request_body'):
                        i.request_body = None
                    if not hasattr(i, 'request_elf'):
                        i.request_elf = None
                    if not hasattr(i, 'request_file'):
                        i.request_file = None
                    Text.append(f'{i.num}\t' \
                        f'{i.rest_url}\t' \
                        f'{i.request_method}\t' \
                        f'{i.title}\t' \
                        f'{i.request_headers}\t' \
                        f'{i.request_elf}\t' \
                        f'{i.request_file}\t' \
                        f'{i.request_body}\t' \
                        f'{i.response_headers_success}\t' \
                        f'{i.response_body_success}\t' \
                        f'{i.response_headers_failure}\t' \
                        f'{i.response_body_failure}\n')
            fobj.writelines(Text)
        pass

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
            Path(Path(__file__).parents[1], 'pdf', 'tr_03165_source.txt'),
            'wt',
            encoding='utf-8',
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
        file_path = Path(__file__).parents[1] / 'pdf' / source_file
        if file_path.is_file():
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
        # get operation_ids, no doubles should be in this list
        operation_ids = getOperationIdsWithoutDoubles(self.interface_methods)
        # rename operation_ids for better readable names
        renameOperationIdsWithOneUnderscore(operation_ids)
        # refer to operation ids in all interface Methods
        for i, _ in enumerate(self.interface_methods):
            self.interface_methods[i].refer_to_operation_id(operation_ids)
        # handle every interfaceMethod to generate /paths
        for interface_method_ in self.interface_methods:
            self.yaml.append(str(interface_method_))

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
        # append to result
        self.yaml.append('### 4.1.4 ###\n')
        self.yaml.append('  schemas:\n')
        self.yaml.append('  \n')
        self.appendFileToYAML('source_components_schemas_separate.yaml')
        schema_titles = []
        for i, item in enumerate(self.data_types):
            self.data_types[i].refer_to_schema_titles(schema_titles)
            schema_titles.append(item.title)
        for d_type in self.data_types:
            self.yaml.append(str(d_type))

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
                    f'<li> {i.request_method.upper()} [**{i.operation_ids[i.num]}**](./docs/{upperFirstChar(makeStringCamelCase(i.tag[1:],"-"))}Api.md#{i.operation_ids[i.num]})</li>'
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
    cwd = Path(__file__).parents[1]
    # Path to source.txt
    source_path_test = Path(cwd, 'pdf', 'tr_03165_source.txt')
    # Path to source.txt
    DESTINATION_NAME = 'sourceHandling.yaml'
    # generate example
    example = Source(source_path_test, DESTINATION_NAME)

    example.generate()

    for num in range(int(len(example.interface_methods) / 3)):
        example.interface_methods[num].printContent()

    for num in range(int(len(example.data_types) / 3)):
        example.data_types[num].printContent()
