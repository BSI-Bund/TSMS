"""
object definition to store data types of the Rest-API
used to create schemas-section in OpenApi YAML
"""

from utilityFunctions import returnTextLineFromList, getColumnFromTable


class DataType:
    """
    definition of DataType Class
    """

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
