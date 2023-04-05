"""
tba
"""

import re
import os

from sourceHandling import Source
from requestBodyDict import makeStringCamelCase
from utilityFunctions import pathSplitL


class Components:
    """definition of Components Class"""

    def __init__(self, source_file: str, destination_file: str):
        """initialize object"""

        # define relative paths from src subfolder to pdf subfolder
        source_path = os.path.join('.', 'pdf', source_file)
        self.destination_path = os.path.join('.', 'pdf', destination_file)
        # from source separated parts
        self.source_obj = Source(source_path, '')
        # whole result
        self.result = ['components:\n', '\n']
        # fixed examples
        self.uuid_examples = [
            'e59d3d32-6756-76ac-ffff-feeba10c081a',
            'ff686d37-4674-1456-26fa-c9ed658bb41c',
            'c73e8ddc-0318-7940-dc7d-ae5bab5ce549',
            '4079f402-4e86-b4b0-0c4f-a21681ece9c8',
            'a0f59df9-7ee2-161f-9325-46e1ecc0ecae',
            '5bd3cb36-3688-e41b-45b4-24abb956e2c5',
            'ffe6404d-0589-e66c-f837-3e89cfe3d19a',
            'f071b74c-ea66-b415-9a66-4ba06c637d13',
            '6c4a3aa8-5240-ad98-4673-a820be2df3b0',
            'fde02274-914f-b360-c679-74970a9d6cef',
            'de10f784-d526-618f-443f-2a98cb2c9893',
            '5dc9a676-847d-2606-bc15-fc8eea036be0',
            '00f5cc8a-f5c7-3929-682f-8eef5972f55a',
            '615c1678-895b-ff21-7c6c-bcd9fed11b84',
            '167f43e0-2ada-d0d0-ba18-b23fd6accd18',
            'bb5c8384-f856-ca00-2b52-bcf9f45e1f39',
            '39fb1716-4a34-87ab-18be-8ee81e7f2d67',
            'bb451440-fdc1-1983-43f2-df17104a4715',
            '96f3baa5-d50c-84e4-b285-07b679aa6953',
            '69c1eede-6767-ddbc-6c84-3903126e51b4',
            'a38d4f8a-a4d5-d5c9-b134-0657c74a9853',
            '26dffd3f-59fd-80b3-bc31-29dca2795831',
            '47db1f00-d894-af9a-a27b-1303d61fa3f4',
            '8fa10eff-19a2-7c4b-1102-2e8b867d3fda',
            '8339dcd0-b9e1-6b73-b5f0-33793d4e2307',
            '060dc3ac-71dd-3bae-e00a-a8fe85ceb022',
            '2355d041-c1c6-4ab1-e0d2-a15a41e85351',
            '6cda80a1-efb3-acf3-8b18-14a10ff2ad24',
            'f36d5f2e-0950-6e80-6367-fe7e6250a713',
        ]
        self.aid_examples = [
            'A00000025128620C1110',
            'A00000039998960C1111',
            'A00000086243583C0111',
            'A00000052220986C0110',
            'A00000030027697C0110',
            'A00000065553856C1001',
            'A00000037797423C1100',
        ]
        self.version_examples = ['4.31.005', '3.1.4', '5.12', '2.2', '1.2', '3.1']
        self.name_examples = [
            'nameOfTheSP',
            'nameOfTheService',
            'nameOfTheFlavor',
            'nameOfTheELF',
            'nameOfTheCAP',
            'nameOfThisApplicationConfig',
            'A description of this ApplicationConfig is stored here.',
            'nameOfThisPersonalizationScript',
            'nameOfTheFileTheCertificateWasCreatedFrom',
        ]
        self.uri_examples = ['/relative/link/to/resource']

    ############################################
    def addParameters(self) -> None:
        """
        add path-parameter section
        those are read from InterfaceMethod-URLs
        """
        self.result.append('  parameters:\n')
        self.result.append('  \n')
        parameter_list = []
        for i in self.source_obj.source_interface_methods:
            re_result = re.findall(r'/\{[\w]+\}', i)
            for j in re_result:
                parameter_list.append(j[2:-1])
        parameter_list = list(dict.fromkeys(parameter_list))
        for i in parameter_list:
            self.result.append('    ' + i + ':\n')
            self.result.append('      in: path\n')
            self.result.append('      name: ' + i + '\n')
            self.result.append('      required: true\n')
            self.result.append('      schema:\n')
            self.result.append('        type: string\n')
            if 'Id' in i:
                self.result.append('        format: uuid\n')
                # uuid-length shall not be specified
                # self.result.append('        maxLength: 36\n')
                # self.result.append('        minLength: 36\n')
                self.result.append(
                    '        example: "' + self.uuid_examples.pop(0) + '"\n'
                )
            else:
                self.result.append('        format: version\n')
                self.result.append('        maxLength: 11\n')
                self.result.append('        minLength: 5\n')
                self.result.append(
                    r"        pattern: '^\d{1:3}.\d{1:3}.\d{1:3}$'" + "\n"
                )
                self.result.append(
                    '        example: "' + self.version_examples.pop(0) + '"\n'
                )
            self.result.append(
                '      description: identifier of the referred '
                + i[0].upper()
                + i[1:].replace('Id', '')
                + '\n'
            )
            self.result.append('    \n')

    def appendFileToResult(self, source_file: str) -> None:
        """append content of file to result"""
        with open(
            os.path.join('.', 'pdf', source_file), 'rt', encoding='utf-8'
        ) as file:
            for i in file.readlines():
                self.result.append(i)

    #####################################################
    def addRequestBodies(self) -> None:
        """add requestBodies section (from pre-defined file)"""
        self.result.append('  requestBodies:\n')
        self.result.append('  \n')
        self.appendFileToResult('source_components_requestBodies.yaml')

    ################################################
    def addResponses(self) -> None:
        """add response section (from pre-defined file)"""
        self.result.append('  responses:\n')
        self.result.append('  \n')
        self.appendFileToResult('source_components_responses.yaml')

    #######################################################
    def addSecuritySchemes(self) -> None:
        """add securitySchemes section (from pre-defined file)"""
        self.result.append('  securitySchemes:\n')
        self.result.append('  \n')
        self.appendFileToResult('source_components_securitySchemes.yaml')

    #######################
    def addSchemas(self) -> None:
        """add schemas section"""
        self.result.append('### 4.1.3 ###\n')
        self.result.append('  schemas:\n')
        self.result.append('  \n')
        self.appendFileToResult('source_components_schemas_separate.yaml')
        schema_titles = []
        for i in self.source_obj.data_types:
            schema_titles.append(i.title)
        for d_type in self.source_obj.data_types:
            # head
            self.result.append('### ' + d_type.num + ' ###\n')
            self.result.append('    ' + d_type.title + ':\n')
            self.result.append('      description: "' + d_type.description + '"\n')
            self.result.append('      type: object\n')
            mandatory = ""
            for i, _ in enumerate(d_type.att4mand):
                if d_type.att4mand[i] == 'M':
                    mandatory += d_type.att1[i] + ', '
            # required
            if len(mandatory) > 0:
                self.result.append('      required: [' + mandatory[:-2] + ']\n')
            else:
                self.result.append('      #required: []\n')
            self.result.append('      properties:\n')
            for i, _ in enumerate(d_type.att1):
                self.result.append(
                    '        '
                    + d_type.att1[i]
                    + ': # '
                    + d_type.att4mand[i]
                    + ', '
                    + d_type.att5ed[i]
                    + '\n'
                )
                if d_type.att2type[i] in schema_titles:
                    # schema
                    self.result.append(
                        '          $ref: "#/components/schemas/'
                        + d_type.att2type[i]
                        + '" # "'
                        + d_type.att3desc[i]
                        + '"\n'
                    )
                else:
                    # description
                    self.result.append(
                        '          description: "'
                        + d_type.att3desc[i].replace(':', '')
                        + '"\n'
                    )
                    type_ = ""
                    # string
                    if d_type.att2type[i] == 'string':
                        self.result.append('          type: string\n')
                        # version
                        if "('major.minor')" in d_type.att3desc[i]:
                            type_ = "version"
                            self.result.append('          format: version\n')
                            self.result.append('          maxLength: 7\n')
                            self.result.append('          minLength: 3\n')
                            self.result.append(
                                r"          pattern: '^\d{1:3}.\d{1:3}$" + "'\n"
                            )
                        # aid
                        elif ('AID' in d_type.att3desc[i]) or ('aid' == d_type.att1[i]):
                            type_ = "aid"
                            self.result.append('          format: aid\n')
                        # uuid
                        elif d_type.att1[i][-2:].lower() == 'id':
                            type_ = "uuid"
                            self.result.append('          format: uuid\n')
                        # date-time
                        elif "Date" in d_type.att1[i]:
                            type_ = "date-time"
                            self.result.append('          format: date-time\n')
                        # uri
                        elif "URL" in d_type.att3desc[i]:
                            type_ = "uri"
                            self.result.append('          format: uri\n')
                            self.result.append('          maxLength: 500\n')
                        # tlv
                        elif "TLV" in d_type.att3desc[i]:
                            type_ = "tlv"
                            self.result.append('          format: tlv\n')
                            self.result.append('          maxLength: 255\n')
                        # name
                        elif "name" in d_type.att1[i].lower():
                            type_ = "name"
                            self.result.append('          maxLength: 255\n')
                        # name
                        elif "description" in d_type.att1[i].lower():
                            type_ = "description"
                            self.result.append('          maxLength: 255\n')
                        # general
                        else:
                            type_ = "generalString"
                            self.result.append('          maxLength: 255\n')
                    # version
                    elif '<Major>.<Minor>.<Revision>' in d_type.att3desc[i]:
                        type_ = "version"
                        self.result.append('          type: string\n')
                        self.result.append('          format: version\n')
                        self.result.append('          maxLength: 11\n')
                        self.result.append('          minLength: 5\n')
                    # string-vektor
                    elif d_type.att2type[i] == 'string[]':
                        self.result.append('          type: array\n')
                        self.result.append('          items:\n')
                        self.result.append('            type: string\n')
                        if 'Versions' in d_type.att3desc[i]:
                            type_ = "version[]"
                            self.result.append('            format: version\n')
                            self.result.append('            maxLength: 11\n')
                            self.result.append('            minLength: 5\n')
                        elif 'accessAuthorizedDeviceApps' == d_type.att1[i]:
                            type_ = "aid[]"
                            self.result.append('            format: aid\n')
                        elif 'List of IDs' in d_type.att3desc[i]:
                            type_ = "uuid[]"
                            self.result.append('            format: uuid\n')
                        else:
                            self.result.append('            maxLength: 255\n')
                    # general vector
                    elif d_type.att2type[i][-2:] == "[]":
                        self.result.append('          type: array\n')
                        self.result.append('          items:\n')
                        if d_type.att2type[i][:-2] in schema_titles:
                            self.result.append(
                                '            $ref: "#/components/schemas/'
                                + d_type.att2type[i][:-2]
                                + '"\n'
                            )
                        # pair
                        elif 'cspELFsVersions' == d_type.att1[i]:
                            self.result.append('            type: array\n')
                            self.result.append('            minLength: 2\n')
                            self.result.append('            maxLength: 2 # == Pair\n')
                            self.result.append('            items:\n')
                            self.result.append('              type: string\n')
                            self.result.append('              maxLength: 11\n')
                            self.result.append('              minLength: 5\n')
                    # integer
                    elif d_type.att2type[i][:3] == 'int':
                        type_ = "int"
                        self.result.append('          type: integer\n')
                        if 'priority' == d_type.att1[i]:
                            self.result.append('          minimum: 1\n')
                            self.result.append('          maximum: 255\n')
                        if 'errorCategory' == d_type.att1[i]:
                            self.result.append('          minimum: 1000\n')
                            self.result.append('          maximum: 9999\n')
                    # boolean
                    elif d_type.att2type[i] == 'boolean':
                        type_ = "boolean"
                        self.result.append('          type: boolean\n')
                        if d_type.att1[i] == 'published':
                            self.result.append('          default: false\n')
                    # spParameters
                    elif d_type.att2type[i] == 'Map<string, string>':
                        type_ = "spParameters"
                        self.result.append('          type: object\n')
                        self.result.append('          additionalProperties:\n')
                        self.result.append('            type: string\n')
                        self.result.append('            maxLength: 255\n')
                    # _links
                    elif d_type.att2type[i] == 'Map<string, Link>':
                        type_ = "_links"
                        self.result.append('          type: object\n')
                        self.result.append('          additionalProperties:\n')
                        self.result.append('            type: string\n')
                        self.result.append('            format: uri\n')
                        self.result.append('            maxLength: 500\n')
                    if d_type.att5ed[i] == 'No':
                        # readOnly
                        self.result.append('          readOnly: true\n')
                    # example section
                    if type_ == "version":
                        self.result.append(
                            '          example: "'
                            + self.version_examples.pop(0)
                            + '"\n'
                        )
                    elif type_ == "uuid":
                        self.result.append(
                            '          example: "' + self.uuid_examples.pop(0) + '"\n'
                        )
                    elif type_ == "aid":
                        self.result.append(
                            '          example: "' + self.aid_examples.pop(0) + '"\n'
                        )
                    elif type_ == "uri":
                        self.result.append(
                            '          example: "/relative/link/to/resource"\n'
                        )
                    # elif type_ == "tlv":
                    #     self.result.append('          example: "'+self.tlvExamples.pop(0)+'"\n')
                    elif type_ == "name":
                        self.result.append(
                            '          example: "'
                            + makeStringCamelCase(d_type.att3desc[i], ' ').replace(
                                '.', ''
                            )
                            + '"\n'
                        )
                    elif type_ == "description":
                        self.result.append(
                            '          example: "' + d_type.att3desc[i] + '"\n'
                        )
                    elif type_ == "spParameters":
                        examples = ''
                        for i in range(1, 4):
                            examples += (
                                'param'
                                + str(i)
                                + ': "Value'
                                + str(i)
                                + ' which can be evaluated by the App.", '
                            )
                        self.result.append(
                            '          example: { ' + examples[:-2] + ' }\n'
                        )
                    elif type_ == "uuid[]":
                        examples = ''
                        for i in range(1, 4):
                            examples += '"' + self.uuid_examples.pop(0) + '", '
                        self.result.append(
                            '          example: [ ' + examples[:-2] + ' ]\n'
                        )
                    elif type_ == "aid[]":
                        examples = ''
                        for i in range(1, 4):
                            examples += '"' + self.aid_examples.pop(0) + '", '
                        self.result.append(
                            '          example: [ ' + examples[:-2] + ' ]\n'
                        )
            if d_type.att1[i] == "_links":
                if d_type.key1:
                    self.result.append('          example:\n')
                    for i in enumerate(d_type.key1):
                        self.result.append(
                            '            '
                            + d_type.key1[i]
                            + ': '
                            + d_type.key3Desc[i]
                            + '\n'
                        )
                else:
                    self.result.append(
                        '          # KEINE ANGABEN FÜR LINKS, DAHER KEINE BEISPIELE\n'
                    )

            self.result.append('\n')

    def saveResult(self) -> None:
        """write self.result to file for later use
        save to previous specified destination"""
        with open(self.destination_path, 'wt', encoding='utf-8') as file:
            file.writelines(self.result)

    def process(self) -> None:
        """process function"""
        self.addParameters()
        self.addRequestBodies()
        self.addResponses()
        self.addSecuritySchemes()
        self.addSchemas()

    def generate(self) -> None:
        """generate result by combining functions: load AND save"""
        self.process()
        # out
        self.saveResult()

    def generateReturn(self) -> list:
        """generate result by combining functions: load AND return (instead of save)"""
        self.process()
        # out
        return self.result


def fromTo(source: str, destination: str) -> None:
    """
    set locations of source and destination in pdf folder
    use this as handling function from outside:
    import generateComponents
    generateComponents.fromTo('...','...')
    """
    # store cwd for end of function
    temp_cwd = os.getcwd()
    # change cwd to upper folder of sourcefile-folder
    os.chdir(pathSplitL(__file__, 2))
    # define proper components object
    content = Components(source, destination)
    # call generate-method
    content.generate()
    # return to previous cwd
    os.chdir(temp_cwd)


def fromReturn(source: str) -> list:
    """
    set locations of source and destination in pdf folder
    use this as handling function from outside:
    import generateComponents
    generateComponents.fromTo('...','...')
    """
    # store cwd for end of function
    temp_cwd = os.getcwd()
    # change cwd to upper folder of sourcefile-folder
    os.chdir(pathSplitL(__file__, 2))
    # define proper components object
    content = Components(source, '')
    # return to previous cwd
    os.chdir(temp_cwd)
    # call generate-method
    return content.generateReturn()


if __name__ == '__main__':
    fromTo('tr_03165_source.txt', 'source_components.yaml')
