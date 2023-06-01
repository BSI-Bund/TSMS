"""
object definition to store data types of the Rest-API
used to create schemas-section in OpenApi YAML
"""

import re

from utilityFunctions import (
    returnTextLineFromList,
    getColumnFromTable,
    makeStringCamelCase,
)
from exampleValues import FixedValueExamples as ExampleValues


class DataType:
    """
    definition of DataType Class
    """

    # names of all existing schemas
    _schema_titles = []

    @property
    def schema_titles(self):
        """schema_titles for all dataTypes"""
        return self.__class__._schema_titles

    @schema_titles.setter
    def schema_titles(self, value: list):
        self.__class__._schema_titles = value

    # define default value regular expression
    _re_default = re.compile('default value')

    @property
    def re_default(self):
        """re_default for all dataTypes"""
        return self.__class__._re_default

    def __init__(self, data_type_text: list):
        """
        initialize object
        """
        # check for additional table to be ignored
        # probably in flavor chapter (4.1.3.4)
        # line beginning keywords: 'The following table'
        for i, line in enumerate(data_type_text):
            if 'The following table' in line:
                del data_type_text[i:]
                break  # for safety

        self.data_type_text = data_type_text
        # look for first occurrence only
        self.line_att1 = returnTextLineFromList(self.data_type_text, 'Attribute\n')
        self.line_att5ed = returnTextLineFromList(self.data_type_text, 'Ed.\n')
        # delete consecutive table header if existing
        while returnTextLineFromList(
            self.data_type_text, 'Attribute\n', self.line_att1
        ) and returnTextLineFromList(self.data_type_text, 'Ed.\n', self.line_att5ed):
            line_att1_consecutive = returnTextLineFromList(
                self.data_type_text, 'Attribute\n', self.line_att1
            )
            line_att5ed_consecutive = returnTextLineFromList(
                self.data_type_text, 'Ed.\n', self.line_att5ed
            )
            if (line_att1_consecutive) and (line_att5ed_consecutive):
                del self.data_type_text[
                    line_att1_consecutive : line_att5ed_consecutive + 1
                ]
        # exit if format is not found ... == ERROR and no obj is created
        if (self.line_att5ed - self.line_att1) != 4:
            return None
        # pull long descriptions into one line
        self.concatMultiLineDescriptions()

        # look for first occurrence only
        self.line_key1 = returnTextLineFromList(self.data_type_text, 'Key\n')
        self.line_key2type = returnTextLineFromList(
            self.data_type_text, 'ReturnType of Link\n'
        )
        # delete second table header if existing
        line_key1_second = returnTextLineFromList(
            self.data_type_text, 'Key\n', self.line_key1
        )
        line_key2type_second = returnTextLineFromList(
            self.data_type_text, 'ReturnType of Link\n', self.line_key2type
        )
        if (line_key1_second) and (line_key2type_second):
            del self.data_type_text[line_key1_second : line_key2type_second + 2]

        [self.num, self.title] = self.data_type_text[0].split(' ', 1)
        self.title = self.title.strip()
        self.description = ''
        # combine description
        for i in range(1, self.line_att1):
            # if ('following attributes:' not in self.data_type_text[i]) or ('A CAP is a ' in self.data_type_text[i]):
            self.description += self.data_type_text[i].strip() + ' '
        self.description = self.description.strip()
        # extract table contents
        self.key1 = []
        self.key2type = []
        self.key3desc = []
        # set end of table 1 depending on table 2
        table1_end = 0
        if self.line_key1:
            table1_end = self.line_key1 - 1
            # handle table 2 (optional)
            self.key1 = getColumnFromTable(
                self.data_type_text, self.line_key1 + 3, len(self.data_type_text) - 2, 3
            )
            self.key2type = getColumnFromTable(
                self.data_type_text, self.line_key1 + 4, len(self.data_type_text) - 1, 3
            )
            self.key3desc = getColumnFromTable(
                self.data_type_text, self.line_key1 + 5, len(self.data_type_text) - 0, 3
            )
            if self.data_type_text[-1].strip() not in self.key3desc[-1]:
                for i in range(len(self.data_type_text) - 5, len(self.data_type_text)):
                    if self.data_type_text[i].strip() in self.key3desc[-1]:
                        for j in range(i + 1, len(self.data_type_text)):
                            self.key3desc[-1] += ' ' + self.data_type_text[j].strip()
                        break
        else:
            table1_end = len(self.data_type_text)
        # handle table 1
        self.att1 = getColumnFromTable(
            self.data_type_text, self.line_att5ed + 1, table1_end - 4, 5
        )
        self.att2type = getColumnFromTable(
            self.data_type_text, self.line_att5ed + 2, table1_end - 3, 5
        )
        self.att3desc = getColumnFromTable(
            self.data_type_text, self.line_att5ed + 3, table1_end - 2, 5
        )
        self.att4mand = getColumnFromTable(
            self.data_type_text, self.line_att5ed + 4, table1_end - 1, 5
        )
        self.att5ed = getColumnFromTable(
            self.data_type_text, self.line_att5ed + 5, table1_end - 0, 5
        )
        # free space, keep when debugging
        del self.data_type_text

    def refer_to_schema_titles(self, ext_list) -> None:
        """
        needs to be executed before output, to refer to other schemas
        """
        self.schema_titles = ext_list

    def concatMultiLineDescriptions(self) -> None:
        """
        combine description lines into one single line
        """
        self.line_att3desc = int((self.line_att1 + self.line_att5ed) / 2)
        line_table1_end_local = 0
        for i, line in enumerate(self.data_type_text):
            if line == 'Key\n':
                line_table1_end_local = i - 1
                break
        if line_table1_end_local == 0:
            line_table1_end_local = len(self.data_type_text)
        i = self.line_att3desc + 5
        baseline = i
        while i < line_table1_end_local:
            if self.data_type_text[i + 1] in ('M\n', 'O\n', 'A\n', 'C\n'):
                i += 5
                baseline = i
            else:
                i += 1
                self.data_type_text[baseline] = (
                    self.data_type_text[baseline].replace('\n', ' ')
                    + self.data_type_text[i]
                )
                self.data_type_text[i] = ''
        # keep only not-empty entries
        self.data_type_text[:] = [x for x in self.data_type_text if x]

    def printContent(self) -> None:
        """
        function to print object content
        """
        if self.line_att1:
            print(self.num, self.title)
            print('  description:')
            print('    ' + str(self.description))
            print('  Attribute Table: (len = ' + str(len(self.att1)) + ')')
            for i, att in enumerate(self.att1):
                print(
                    '    ' + att,
                    '|',
                    self.att2type[i],
                    '|',
                    self.att3desc[i],
                    '|',
                    self.att4mand[i],
                    '|',
                    self.att5ed[i],
                )
            if len(self.key1) > 0:
                print('  Key Table: (len = ' + str(len(self.key1)) + ')')
                for i, key in enumerate(self.key1):
                    print('    ' + key, '|', self.key2type[i], '|', self.key3desc[i])
            print()
        else:
            print('INCORRECT INPUT TO DATATYPE, OBJ BUILD FAILED')

    def __str__(self) -> str:
        """
        convert contents to openapi yaml-format
        """
        if self.line_att1 == 0:
            return f'INCORRECT INPUT TO DATATYPE {self.data_type_text[0]}, OBJ BUILD FAILED'
        # head
        result = ''
        result += f'### {self.num} ###\n'
        result += f'    {self.title}:\n'
        result += f'      description: "{self.description}"\n'
        if self.title == 'CAP':
            result += '      allOf:\n'
            result += '        - $ref: "#/components/schemas/ExecutableLoadFile"\n'
        result += '      type: object\n'
        mandatory = ''
        for i, att4mand in enumerate(self.att4mand):
            if att4mand == 'M':
                mandatory += f'{self.att1[i]}, '
        # required
        if len(mandatory) > 0:
            result += f'      required: [{mandatory[:-2]}]\n'
        else:
            result += '      #required: []\n'
        result += '      properties:\n'
        for i, att1 in enumerate(self.att1):
            result += f'        {att1}: # {self.att4mand[i]}, {self.att5ed[i]}\n'
            if self.att2type[i] in self.schema_titles:
                # schema
                result += f'          $ref: "#/components/schemas/{self.att2type[i]}" # "{self.att3desc[i]}"\n'
            else:
                # description
                result += (
                    f'          description: "{self.att3desc[i].replace(":","")}"\n'
                )
                type_ = ""
                # string
                if self.att2type[i] == 'string':
                    result += '          type: string\n'
                    # version
                    if (
                        'gpSpec' == self.att1[i]
                        or 'javaCardVersion' == self.att1[i]
                        or 'javaCard' == self.att1[i]
                    ):
                        type_ = 'version'
                        result += '          format: version\n'
                        result += '          maxLength: 11\n'
                        result += '          minLength: 5\n'
                        result += (
                            r"          pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n"
                        )
                    # versionMM
                    elif (
                        ("('major.minor')" in self.att3desc[i])
                        or 'gpApi' == self.att1[i]
                        or 'gpApiVersion' == self.att1[i]
                    ):
                        type_ = 'versionMM'
                        result += '          format: version\n'
                        result += '          maxLength: 7\n'
                        result += '          minLength: 3\n'
                        result += r"          pattern: '^\d{1,3}.\d{1,3}$" + "'\n"
                    # aid
                    elif ('AID' in self.att3desc[i]) or ('aid' == self.att1[i]):
                        type_ = "aid"
                        result += '          format: aid\n'
                    # uuid
                    elif self.att1[i][-2:].lower() == 'id':
                        type_ = "uuid"
                        result += '          format: uuid\n'
                    # date-time
                    elif "Date" in self.att1[i]:
                        type_ = "date-time"
                        result += '          format: date-time\n'
                    # uri
                    elif "URL" in self.att3desc[i]:
                        type_ = "uri"
                        result += '          format: uri\n'
                        result += '          maxLength: 500\n'
                    # tlv
                    elif "TLV" in self.att3desc[i]:
                        type_ = "tlv"
                        result += '          format: tlv\n'
                        result += '          maxLength: 255\n'
                    # hardwarePlatform
                    elif 'hardwarePlatform' == self.att1[i]:
                        type_ = 'platform'
                        result += '          maxLength: 255\n'
                    # operatingSystem
                    elif 'os' == self.att1[i]:
                        type_ = 'os'
                        result += '          maxLength: 255\n'
                    # osVersion
                    elif 'osVersion' == self.att1[i]:
                        type_ = 'osVersion'
                        result += '          maxLength: 255\n'
                        result += '          minLength: 1\n'
                        # result += '          maxLength: 7\n'
                        # result += '          minLength: 3\n'
                        # result += r"          pattern: '^\d{1,3}.\d{1,3}$'" + "\n"
                    # operatingSystem
                    elif 'operatingSystemCode' == self.att1[i]:
                        type_ = 'osCode'
                        result += '          maxLength: 255\n'
                    # platformCertification
                    elif 'certifications' == self.att1[i]:
                        type_ = 'platformCert'
                        result += '          maxLength: 255\n'
                    # cspVendor
                    elif 'cspVendor' == self.att1[i]:
                        type_ = 'cspVendor'
                        result += '          maxLength: 255\n'
                    # cspVendor
                    elif 'cspApiVersion' == self.att1[i]:
                        result += '          maxLength: 7\n'
                        result += '          minLength: 0\n'
                        result += r"          pattern: '^\d{1,3}.\d{1,3}$'" + "\n"
                    # name
                    elif "name" in self.att1[i].lower():
                        type_ = "name"
                        result += '          maxLength: 255\n'
                    # name
                    elif "description" in self.att1[i].lower():
                        type_ = "description"
                        result += '          maxLength: 255\n'
                    # general
                    else:
                        type_ = "generalString"
                        result += '          maxLength: 255\n'
                # version
                elif '<Major>.<Minor>.<Revision>' in self.att3desc[i]:
                    type_ = "version"
                    result += '          type: string\n'
                    result += '          format: version\n'
                    result += '          maxLength: 11\n'
                    result += '          minLength: 5\n'
                    result += r"          pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n"
                # string-vektor
                elif self.att2type[i] == 'string[]':
                    result += '          type: array\n'
                    result += '          items:\n'
                    result += '            type: string\n'
                    if 'Versions' in self.att3desc[i]:
                        type_ = "version[]"
                        result += '            format: version\n'
                        result += '            maxLength: 11\n'
                        result += '            minLength: 5\n'
                        result += (
                            r"            pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n"
                        )
                    elif 'accessAuthorizedDeviceApps' == self.att1[i]:
                        type_ = "aid[]"
                        result += '            format: aid\n'
                    elif 'List of IDs' in self.att3desc[i]:
                        type_ = 'uuid[]'
                        result += '            format: uuid\n'
                    else:
                        result += '            maxLength: 255\n'
                # general vector
                elif self.att2type[i][-2:] == '[]':
                    result += '          type: array\n'
                    result += '          items:\n'
                    if self.att2type[i][:-2] in self.schema_titles:
                        result += f'            $ref: "#/components/schemas/{self.att2type[i][:-2]}"\n'
                    # pair
                    elif 'cspELFsVersions' == self.att1[i]:
                        type_ = 'pair[]'
                        result += '            type: array\n'
                        result += '            minLength: 2\n'
                        result += '            maxLength: 2 # == Pair\n'
                        result += '            items:\n'
                        result += '              type: string\n'
                        result += '              maxLength: 11\n'
                        result += '              minLength: 5\n'
                        result += (
                            r"              pattern: '^\d{1,3}.\d{1,3}.\d{1,3}$'" + "\n"
                        )
                # integer
                elif self.att2type[i][:3] == 'int':
                    type_ = 'int'
                    result += '          type: integer\n'
                    if 'priority' == self.att1[i]:
                        result += '          minimum: 1\n'
                        result += '          maximum: 255\n'
                    elif 'errorCategory' == self.att1[i]:
                        result += '          minimum: 1000\n'
                        result += '          maximum: 9999\n'
                    elif 'scType' == self.att1[i]:
                        type_ = 'scType'
                        result += '          minimum: 1\n'
                        result += '          maximum: 4\n'
                # boolean
                elif self.att2type[i] == 'boolean':
                    type_ = 'boolean'
                    result += '          type: boolean\n'
                    # default values, especially relevant for boolean
                    default_result = self.re_default.search(self.att3desc[i].lower())
                    if default_result:
                        _, default_end = default_result.span()
                        if (
                            'true'
                            in self.att3desc[i][default_end : default_end + 9].lower()
                        ):
                            result += '          default: true\n'
                        elif (
                            'false'
                            in self.att3desc[i].lower()[default_end : default_end + 9]
                        ):
                            result += '          default: false\n'
                        else:
                            print('EXCEPTION AT DEFAULT HANDLING:')
                            print(self.att1[i])
                            print(self.att3desc[i])
                            print(
                                self.att3desc[i][default_end : default_end + 9].lower()
                            )
                            print()
                # Map<string, string>
                elif self.att2type[i] == 'Map<string, string>':
                    type_ = 'stringMap'
                    result += '          type: object\n'
                    result += '          additionalProperties:\n'
                    result += '            type: string\n'
                    result += '            maxLength: 255\n'
                    map_mapping = {
                        'spParameters': 'spParametersMap',
                        'contextSpecificAttributes': 'spParametersMap',
                        'gpSpecVersions': 'gpSpecVersionsMap',
                        'gpApiVersions': 'gpApiVersionsMap',
                        'csp': 'cspMap',
                        'certifications': 'certificationsMap',
                    }
                    type_ = map_mapping[self.att1[i]]
                # Map<string, string[]>
                elif self.att2type[i] == 'Map<string, string[]>':
                    type_ = 'stringVecMap'
                    result += '          type: object\n'
                    result += '          additionalProperties:\n'
                    result += '            type: array\n'
                    result += '            items:\n'
                    result += '              type: string\n'
                    result += '              maxLength: 255\n'
                    if self.att1[i] == 'features':
                        type_ = 'featuresMap'
                # Map<string, string[]>
                elif self.att2type[i] == 'Map<string, boolean>':
                    type_ = 'booleanMap'
                    result += '          type: object\n'
                    result += '          additionalProperties:\n'
                    result += '            type: boolean\n'
                    if self.att1[i] == 'genericOptions':
                        type_ = 'genericOptions'
                        result += '          default: {}\n'
                # _links
                elif self.att2type[i] == 'Map<string, Link>':
                    type_ = '_links'
                    result += '          type: object\n'
                    result += '          additionalProperties:\n'
                    result += '            type: string\n'
                    result += '            format: uri\n'
                    result += '            maxLength: 500\n'
                # key-value map
                elif 'Map<string,' in self.att2type[i]:
                    result += '          type: object\n'
                    result += '          additionalProperties:\n'
                    # values are string-lists
                    if 'string[]>' in self.att2type[i]:
                        result += '            type: array\n'
                        result += '            items:\n'
                        result += '              type: string\n'
                        result += '              maxLength: 255\n'
                if self.att5ed[i] == 'No':
                    # readOnly
                    result += '          readOnly: true\n'
                # example section
                if type_ in ['version', 'versionMM', 'uuid', 'aid', 'uri', 'tlv']:
                    result += f'          example: "{ExampleValues.get(type_)}"\n'
                elif type_ == 'boolean':
                    if not default_result:
                        if self.att1[i] == 'csp':
                            result += '          example: true\n'
                        else:
                            result += (
                                f'          example: {ExampleValues.get("bool")}\n'
                            )
                elif type_ == 'scType':
                    result += '          example: 1\n'
                elif type_ == 'platform':
                    result += '          example: "P62G98"\n'
                elif type_ == 'os':
                    result += '          example: "JCOP"\n'
                elif type_ == 'osVersion':
                    result += '          example: "4.7"\n'
                elif type_ == 'featuresMap':
                    result += '          example: { "cypher": [ "alg1", "alg2" ], "signature": [ "alg3" ], "checksum": [ "alg4", "alg5", "alg6" ] }\n'
                elif type_ == 'gpSpecVersionsMap':
                    result += '          example: { "card": "2.3.1", "contactlessServices": "2.3" }\n'
                elif type_ == 'gpApiVersionsMap':
                    result += (
                        '          example: { "card": "1.7", "contactless": "1.6" }\n'
                    )
                elif type_ == 'cspMap':
                    result += (
                        '          example: { "apiVersion": "1.0", "vendor": "nxp" }\n'
                    )
                elif type_ == 'certificationsMap':
                    result += '          example: { "BSI-CC-PP-0084-2014": "link/to/letter/of/approval/1", "BSI-CC-PP-0100-2018": "link/to/letter/of/approval/2" }\n'
                elif type_ == 'osCode':
                    result += '          example: "H145G"\n'
                elif type_ == 'platformCert':
                    result += '          example: "CC EAL5"\n'
                elif type_ == 'cspVendor':
                    result += '          example: "nxp"\n'
                elif type_ == 'cspApiVersion':
                    result += '          example: "1.0"\n'
                elif type_ == 'name':
                    result += f'          example: "{makeStringCamelCase(self.att3desc[i]," ").replace(".","")}"\n'
                elif type_ == 'description':
                    result += f'          example: "{self.att3desc[i]}"\n'
                elif type_ == 'spParametersMap':
                    examples = ''
                    for i in range(1, 4):
                        examples += f'param{str(i)}: "Value{str(i)} which can be evaluated by the App.", '
                    result += '          example: { ' + examples[:-2] + ' }\n'
                elif type_ == 'uuid[]':
                    examples = ''
                    for i in range(1, 4):
                        examples += f'"{ExampleValues.get("uuid")}", '
                    result += f'          example: [ {examples[:-2]} ]\n'
                elif type_ == 'aid[]':
                    examples = ''
                    for i in range(1, 4):
                        examples += f'"{ExampleValues.get("aid")}", '
                    result += f'          example: [ {examples[:-2]} ]\n'
                elif type_ == 'pair[]':
                    examples = ''
                    for i in range(1, 4):
                        examples += f'["{ExampleValues.get("version")}", "{ExampleValues.get("version")}"], '
                    result += f'          example: [ {examples[:-2]} ]\n'
        if self.att1[i] == '_links':
            if self.key1:
                result += '          example:\n'
                for i, key1 in enumerate(self.key1):
                    result += f'            {key1}: {self.key3desc[i]}\n'
            else:
                result += '          # KEINE ANGABEN FÜR LINKS, DAHER KEINE BEISPIELE\n'
        result += '\n'
        return result


###########
# Testing #
###########

if __name__ == '__main__':
    certificate = [
        '4.1.3.16 Certificate\n',
        'A Certificate is used for the communication between TSM and SP Online System and also in PersonalizationScripts for applet provisioning and personalization.\n',
        'Certificate has the following attributes:\n',
        'Attribute\n',
        'Type\n',
        'Description\n',
        'Mand.\n',
        'Ed.\n',
        'id\n',
        'string\n',
        'Unique identification of this Certificate. Automatically assigned when created.\n',
        'A\n',
        'No\n',
        'creationDate\n',
        'string\n',
        'A datetime string (creation of Certificate).\n',
        'A\n',
        'No\n',
        'uploadDate\n',
        'string\n',
        'A datetime string (upload of Certificate binary).\n',
        'A\n',
        'No\n',
        'fileName\n',
        'string\n',
        'Name of the file the Certificate was created from.\n',
        'A\n',
        'Yes\n',
        '_links\n',
        'Map<string, Link>\n',
        'Map containing REST-URLs to related entities.\n',
        'A\n',
        'No\n',
        'The _links attribute contains the following REST-URLs:\n',
        'Key\n',
        'ReturnType of Link\n',
        'Description\n',
        'self\n',
        'Certificate\n',
        'Link to resource itself.\n',
        'binary\n',
        'byte[]\n',
        'Link to binary file of this Certificate.\n',
        'serviceProvider\n',
        'ServiceProvider\n',
        'Link to the SP of this Certificate.\n',
        'applicationConfigs\n',
        'EntityList<ApplicationConfig>\n',
        'Link to all ApplicationConfigs using this Certificate.\n',
        'sposConfigs\n',
        'EntityList<SposConfig>\n',
        'Link to all SposConfigs using this Certificate.\n',
        'services\n',
        'EntityList<Services>\n',
        'Link to all Services using this Certificate.\n',
        'flavors\n',
        'EntityList<Flavor>\n',
        'Link to all Flavors using this Certificate.\n',
    ]

    sposConfig = [
        '4.1.3.17 SposConfig\n',
        'SposConfig is used to configure communication with the Service Provider Online System. The aim is to enable a service provider to receive process success and process error messages sent by the TSM.\n',
        'SposConfig has the following attributes:\n',
        'Attribute\n',
        'Type\n',
        'Description\n',
        'Mand.\n',
        'Ed.\n',
        'url\n',
        'string\n',
        'URL of the SP’s Online Service.\n',
        'M\n',
        'Yes\n',
        'certificateId\n',
        'string\n',
        'ID of the Certificate that is used for the backend communication.\n',
        'M\n',
        'Yes\n',
        '_links\n',
        'Map<string, Link>\n',
        'Map containing REST-URLs to related entities.\n',
        'C\n',
        'No\n',
        'The _links attribute contains the following REST-URLs:\n',
        'Key\n',
        'ReturnType of Link\n',
        'Description\n',
        'self\n',
        'SposConfig\n',
        'Link to resource itself.\n',
        'serviceProvider\n',
        'ServiceProvider\n',
        'Link to SP owner of this SposConfig.\n',
        'certificate\n',
        'Certificate\n',
        'Link to the Certificate used for this SposConfig.\n',
        'services\n',
        'EntityList<Service>\n',
        'Link to list of all Services using this SposConfig.\n',
    ]

    # wrong input without linebreaks
    # results in obj without content, only source
    sposConfigwrong = [x.replace('\n', '') for x in sposConfig]

    dT_certificate = DataType(certificate)
    dT_sposConfig = DataType(sposConfig)
    dT_sposConfigwrong = DataType(sposConfigwrong)

    dT_certificate.printContent()
    dT_sposConfig.printContent()
    dT_sposConfigwrong.printContent()

    print(dT_certificate)
    print(dT_sposConfig)
    print(dT_sposConfigwrong)
