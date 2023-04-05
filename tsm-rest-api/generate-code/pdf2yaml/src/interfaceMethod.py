"""
object definition to store methods of the Rest-API
used to create paths-section in OpenApi YAML
"""

import re

from utilityFunctions import returnTextLineFromList


# pylint: disable=too-few-public-methods
class InterfaceMethod:
    """definition of InterfaceMethod Class"""

    # pylint: disable=inconsistent-return-statements
    def __init__(self, interface_method_text: list):
        """initialize object"""

        self.interface_method_text = interface_method_text
        self.tag = ''
        self.operation_id = ''
        # re to detect supkapitel
        if re.search(r'^4.1.[56].[\d]+ [\w /]+\n', self.interface_method_text[0]):
            self.type_ = 'supkapitel'
        # re to detect kapitel
        elif re.search(
            r'^4.1.[56].[\d]+.[\d]+ [\w /]+\n', self.interface_method_text[0]
        ):
            self.type_ = 'kapitel'
        # wrong input ... ERROR
        else:
            self.type_ = 'None'
            return None
        [self.num, self.title] = self.interface_method_text[0].split(r' ', 1)
        self.title = self.title.strip()
        # Handle supkapitel
        if self.type_ == 'supkapitel':
            self.description = []
            for i in range(1, len(self.interface_method_text), 1):
                self.description.append(self.interface_method_text[i].strip())
        # Handle kapitel
        elif self.type_ == 'kapitel':
            if '4.1.6.5.4' in interface_method_text[0]:
                pass
            # get specific lines
            line_rest_url = returnTextLineFromList(
                self.interface_method_text, 'REST-URL\n'
            )
            line_request_method = returnTextLineFromList(
                self.interface_method_text, 'Request Method\n', line_rest_url
            )
            line_request_headers = returnTextLineFromList(
                self.interface_method_text, 'Request Headers\n', line_request_method
            )
            # line_request_filename = 0 # only not-zero if available
            line_request_file = 0  # only not-zero if available
            line_request_body = 0  # only not-zero if available
            if 'Request Body\n' in self.interface_method_text:
                line_request_body = returnTextLineFromList(
                    self.interface_method_text, 'Request Body\n', line_request_headers
                )
                line_request_headers_end = line_request_body
            else:
                if 'Request ELF-File\n' in self.interface_method_text:
                    # line_request_filename = returnTextLineFromList(self.interface_method_text,'Request ELF-Filename\n',line_request_headers)
                    line_request_file = returnTextLineFromList(
                        self.interface_method_text,
                        'Request ELF-File\n',
                        line_request_headers,
                    )
                elif 'Request Script-File\n' in self.interface_method_text:
                    # line_request_filename = returnTextLineFromList(self.interface_method_text,'Request Script-Filename\n',line_request_headers)
                    line_request_file = returnTextLineFromList(
                        self.interface_method_text,
                        'Request Script-File\n',
                        line_request_headers,
                    )
                elif 'Request Cert-File\n' in self.interface_method_text:
                    # line_request_filename = returnTextLineFromList(self.interface_method_text,'Request Cert-Filename\n',line_request_headers)
                    line_request_file = returnTextLineFromList(
                        self.interface_method_text,
                        'Request Cert-File\n',
                        line_request_headers,
                    )
                line_request_headers_end = line_request_file
            line_response_success = returnTextLineFromList(
                self.interface_method_text, 'Response on success:\n', line_request_body
            )
            line_response_headers_succ = returnTextLineFromList(
                self.interface_method_text, 'Response Headers\n', line_response_success
            )
            line_response_body_succ = returnTextLineFromList(
                self.interface_method_text, 'Response Body\n', line_response_success
            )
            line_response_failures = returnTextLineFromList(
                self.interface_method_text,
                'Responses on failures:\n',
                line_response_success,
            )
            line_response_headers_fail = returnTextLineFromList(
                self.interface_method_text, 'Response Headers\n', line_response_failures
            )
            line_response_body_fail = returnTextLineFromList(
                self.interface_method_text, 'Response Body\n', line_response_failures
            )

            # extract information at specific lines

            # rest-url
            rest_url_list = self.interface_method_text[
                line_rest_url + 1 : line_request_method
            ]
            self.rest_url = ''
            for url_line in rest_url_list:
                self.rest_url += (
                    url_line.replace('<<tsmBaseURL>>', '').replace(' ', '').strip()
                )
            if self.rest_url[-1] == '/':
                self.rest_url = self.rest_url[:-1]
            # tag
            self.tag = '/' + self.rest_url.split('/')[1]
            # request method
            self.request_method = (
                self.interface_method_text[line_request_method + 1].lower().strip()
            )
            # description
            self.description = ''
            for i in range(1, line_rest_url):
                self.description += self.interface_method_text[i].strip() + ' '
            self.description = self.description.strip()
            # request headers
            self.request_headers = {}
            for i in range(line_request_headers + 1, line_request_headers_end, 2):
                self.request_headers[
                    self.interface_method_text[i].strip()
                ] = self.interface_method_text[i + 1].strip()
            # request filename
            # if False:#line_request_filename:
            #     self.request_filename = {}
            #     for i in range(line_request_filename+1,line_request_file,2):
            #         self.request_filename[self.interface_method_text[i].strip()] = self.interface_method_text[i+1].strip()
            # else:
            self.request_filename = None

            # request file
            if line_request_file:
                self.request_file = {}
                for i in range(line_request_file + 1, line_response_success, 2):
                    self.request_file[
                        self.interface_method_text[i].strip()
                    ] = self.interface_method_text[i + 1].strip()
            else:
                self.request_file = None
            # request body
            if line_request_body:
                if self.interface_method_text[line_request_body + 1].strip() == '-':
                    self.request_body = None
                else:
                    self.request_body = self.interface_method_text[
                        line_request_body + 1 : line_response_success
                    ]
                    # pull request body type into first element of list
                    # rest of list (elements above 0 if available) are explanation
                    if '<' in self.request_body[0] and '>' in self.request_body[0]:
                        for i, req in enumerate(self.request_body):
                            # # pull type together
                            # if i > 0 and reqTypePart:
                            #     self.request_body[0] = self.request_body[0].strip() + ' ' + req
                            #     self.request_body[i] = ''
                            # if '>' in req:
                            #     reqTypePart = False
                            #     continue
                            # pull single description together
                            if ':' not in req and '<' not in req:
                                self.request_body[i - 1] = (
                                    self.request_body[i - 1].strip() + ' ' + req
                                )
                                self.request_body[i] = ''
                        # pull whole description together
                        if len(self.request_body) > 2:
                            for i in range(2, len(self.request_body)):
                                self.request_body[1] = (
                                    self.request_body[1].strip()
                                    + ', '
                                    + self.request_body[i]
                                )
                                self.request_body[i] = ''
                            while self.request_body[1][:2] == ', ':
                                self.request_body[1] = self.request_body[1][2:]
                            while self.request_body[1][-2:] == ', ':
                                self.request_body[1] = self.request_body[1][:-2]
                            self.request_body[1] += '\n'
                        while '' in self.request_body:
                            self.request_body.remove('')
            else:
                self.request_body = None
            # parameters (if existing)
            re_parameters = re.findall(r'\{\w+\}', self.rest_url)
            self.parameters = []
            for i in re_parameters:
                self.parameters.append(
                    '- $ref: "#/components/parameters/' + i[1:-1] + '"'
                )
            # response headers (success)
            self.response_headers_success = {}
            for i in range(line_response_headers_succ + 1, line_response_body_succ, 2):
                self.response_headers_success[
                    self.interface_method_text[i].strip()
                ] = self.interface_method_text[i + 1].strip()
            # response body (success)
            self.response_body_success = self.interface_method_text[
                line_response_body_succ + 1
            ].strip()
            if 'EntityList<' in self.response_body_success:
                self.response_body_success = self.response_body_success.replace(
                    'EntityList<', ''
                ).replace('>', '[]')
            # response headers (failure)
            self.response_headers_failure = {}
            for i in range(line_response_headers_fail + 1, line_response_body_fail, 2):
                self.response_headers_failure[
                    self.interface_method_text[i].strip()
                ] = self.interface_method_text[i + 1].strip()
            self.response_headers_failure['Status Code'] = [
                x.strip()
                for x in self.response_headers_failure['Status Code'].split(',')
            ]
            # response body (failure) == general error
            self.response_body_failure = {}
            self.response_body_failure['GeneralError'] = []
            temp_list = [
                x.strip()
                for x in self.interface_method_text[line_response_body_fail + 2].split(
                    ','
                )
            ]
            for temp in temp_list:
                if '-' in temp:
                    [lower_s, upper_s] = temp.split('-')
                    for k in range(int(lower_s), int(upper_s) + 1):
                        self.response_body_failure['GeneralError'].append(str(k))
                else:
                    self.response_body_failure['GeneralError'].append(temp.strip())
            self.response_body_failure['GeneralError'] = sorted(
                self.response_body_failure['GeneralError']
            )
            # sort GeneralErrors to corresponding http-StatusCodes
            self.ref400 = '$ref: "#/components/responses/400Error_'
            self.ref401 = '$ref: "#/components/responses/401Error_'
            self.ref500 = '$ref: "#/components/responses/500Error_'
            for error_number in self.response_body_failure['GeneralError']:
                if error_number < '1002':
                    self.ref401 += error_number + '_'
                elif '1001' < error_number < '1020':
                    self.ref400 += error_number + '_'
                elif error_number == '2000':
                    self.ref500 += error_number + '_'
            self.ref400 = self.ref400[:-1] + '"'
            self.ref401 = self.ref401[:-1] + '"'
            self.ref500 = self.ref500[:-1] + '"'

        del self.interface_method_text

    def printContent(self) -> None:
        """
        function to print object content
        """
        if self.type_ == 'supkapitel':
            print(self.num, self.title)
            print('  description:')
            print('    ' + str(self.description))
            print()
        elif self.type_ == 'kapitel':
            print(self.num, self.title)
            print('  description:')
            print('    ' + str(self.description))
            print('  REST-URL:')
            print('    ' + self.rest_url)
            print('  tag:')
            print('    ' + self.tag)
            if self.parameters:
                print('  parameters:')
                for i in self.parameters:
                    print('    ' + i)
            print('  request_method:')
            print('    ' + self.request_method)
            print('  request_headers:')
            for key in list(self.request_headers.keys()):
                print('    ' + key + ': ' + str(self.request_headers[key]))
            if self.request_body:
                print('  request_body:')
                print('    ' + str(self.request_body))
            if self.request_filename:
                print('  request_filename:')
                print('    ' + str(self.request_filename))
            if self.request_file:
                print('  request_file:')
                print('    ' + str(self.request_file))
            print('  response_headers_success:')
            for key in list(self.response_headers_success.keys()):
                print('    ' + key + ': ' + str(self.response_headers_success[key]))
            print('  response_body_success:')
            print('    ' + str(self.response_body_success))
            print('  response_headers_failure:')
            for key in list(self.response_headers_failure.keys()):
                print('    ' + key + ': ' + str(self.response_headers_failure[key]))
            print('  response_body_failure:')
            for key in list(self.response_body_failure.keys()):
                print('    ' + key + ': ' + str(self.response_body_failure[key]))
            print()
        else:
            print('INCORRECT INPUT TO INTERFACEMETHOD, OBJ BUILD FAILED')


###########
# Testing #
###########

if __name__ == '__main__':
    createELF = [
        '4.1.5.4.2 Create ELF and Upload Binary\n',
        'Create a new ExecutableLoadFile and upload corresponding binary data. ELF details and binary must both be provided to create a new ExecutableLoadFile.\n',
        'REST-URL\n',
        '<<tsmBaseURL>>/executable-load-files\n',
        'Request Method\n',
        'POST\n',
        'Request Headers\n',
        'Authorization\n',
        '<<auth-Token>>\n',
        'Content-Type\n',
        'multipart/form-data; boundary=<<boundary>>\n',
        'Content-Length\n',
        'File length in bytes\n',
        'Accept\n',
        'application/json\n',
        'Request ELF-Filename\n',
        'Content-Disposition\n',
        'form-data; name=”elf-filename”\n',
        'Content-Type\n',
        'text/plain\n',
        'Body\n',
        'string\n',
        'Request ELF-File\n',
        'Content-Disposition\n',
        'form-data; name=”elf-file”, filename=”<<fileName>>”\n',
        'Content-Type\n',
        'application/octed-stream\n',
        'Body\n',
        'byte[]\n',
        'Response on success:\n',
        'Response Headers\n',
        'Content-Type\n',
        'application/json\n',
        'Status Code\n',
        '200\n',
        'Response Body\n',
        'ExecutableLoadFile\n',
        'Responses on failures:\n',
        'Response Headers\n',
        'Content-Type\n',
        'application/json\n',
        'Status Code\n',
        '400, 401, 500\n',
        'Response Body\n',
        'GeneralError\n',
        '1000, 1003, 1004, 1007, 1008, 1009, 1010, 1012-1015, 2000\n',
    ]
    getELF = [
        '4.1.5.4.3 Get ELF\n',
        'Get details of a certain ExecutableLoadFile.\n',
        'REST-URL\n',
        '<<tsmBaseURL>>/executable-load-files/{elfId}\n',
        'Request Method\n',
        'GET\n',
        'Request Headers\n',
        'Authorization\n',
        '<<auth-Token>>\n',
        'Accept\n',
        'application/json\n',
        'Request Body\n',
        '-\n',
        'Response on success:\n',
        'Response Headers\n',
        'Content-Type\n',
        'application/json\n',
        'Status Code\n',
        '200\n',
        'Response Body\n',
        'ExecutableLoadFile\n',
        'Responses on failures:\n',
        'Response Headers\n',
        'Content-Type\n',
        'application/json\n',
        'Status Code\n',
        '400, 401, 500\n',
        'Response Body\n',
        'GeneralError\n',
        '1000, 1002, 1010, 2000\n',
    ]

    # wrong input without linebreaks
    # results in obj without content, only source
    getELFwrong = [x.replace('\n', '') for x in getELF]

    iM_createELF = InterfaceMethod(createELF)
    iM_getELF = InterfaceMethod(getELF)
    iM_getELFwrong = InterfaceMethod(getELFwrong)

    iM_createELF.printContent()
    iM_getELF.printContent()
    iM_getELFwrong.printContent()
