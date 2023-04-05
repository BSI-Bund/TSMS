"""
file description
"""


def dataTypeConversion(data_type_obj_list) -> None:
    """
    conversion of dataType-objects to LaTeX-format
    """
    with open('dataTypeTable.txt', 'w', encoding='utf-8') as file:
        output_text = ''
        for data_type_obj in data_type_obj_list:
            output_text += (
                r'\subsubsection{'
                + data_type_obj.title
                + r'}{sssec:dt_'
                + data_type_obj.title
                + r'}%'
                + data_type_obj.num
                + '\n\n'
            )
            output_text += data_type_obj.description + '\n\n'
            output_text += (
                r'\begin{tabularx}{\linewidth}{|L{\tabAttA}|L{\tabAttB}|L{\tabAttC}|X|X|}'
                + '\n'
            )
            output_text += (
                '\t'
                + r'\tabHeader{Attribute}&\tabHeader{Type}&\tabHeader{Description}&\tabHeader{Mand.}&\tabHeader{Ed.}\\\hline'
                + '\n'
            )
            for i, att in enumerate(data_type_obj.att1):
                output_text += (
                    '\t'
                    + att
                    + '&'
                    + data_type_obj.att2type[i]
                    + '&'
                    + data_type_obj.att3desc[i]
                    + '&'
                    + data_type_obj.att4mand[i]
                    + '&'
                    + data_type_obj.att5ed[i]
                    + r'\\\hline'
                    + '\n'
                )
            output_text += r'\end{tabularx}'
            output_text += 5 * '\n'
            # if
        file.write(output_text)


def interfaceMethodConversion(interface_method_obj_list) -> None:
    """
    conversion of interfaceMethod-objects to LaTeX-format
    """
    with open('interfaceMethodTable.txt', 'w', encoding='utf-8') as file:
        output_text = ''
        for interface_method_obj in interface_method_obj_list:
            if interface_method_obj.type_ == 'supkapitel':
                output_text += (
                    r'\subsubsection{'
                    + interface_method_obj.title
                    + r'}\label{sssec:im_'
                    + interface_method_obj.title.replace(' ', '_')
                    + r'}%'
                    + interface_method_obj.num
                    + '\n\n'
                )
                if len(interface_method_obj.description) > 0:
                    if isinstance(interface_method_obj.description, str):
                        output_text += interface_method_obj.description + '\n\n'
                    elif isinstance(interface_method_obj.description, list):
                        for desc in interface_method_obj.description:
                            output_text += desc + '\n\n'
            else:
                output_text += (
                    r'\paragraph{'
                    + interface_method_obj.title
                    + r'}\label{par:im_'
                    + interface_method_obj.title.replace(' ', '_')
                    + r'}%'
                    + interface_method_obj.num
                    + '\n\n'
                )
                if len(interface_method_obj.description) > 0:
                    if isinstance(interface_method_obj.description, str):
                        output_text += interface_method_obj.description + '\n\n'
                    elif isinstance(interface_method_obj.description, list):
                        for desc in interface_method_obj.description:
                            output_text += desc + '\n\n'
                output_text += (
                    r'\begin{tabularx}{\linewidth}{|L{\tabAttA}|L{\tabAttB}|X|}' + '\n'
                )
                output_text += '\t' + r'\hline' + '\n'
                output_text += (
                    '\t'
                    + r'REST-URL&\multicol{2}{l|}{'
                    + interface_method_obj.rest_url
                    + r'}\\'
                    + '\n'
                )
                output_text += '\t' + r'\hline' + '\n'
                output_text += (
                    '\t'
                    + r'Request Method&\multicol{2}{l|}{'
                    + interface_method_obj.request_method
                    + r'}\\'
                    + '\n'
                )
                output_text += '\t' + r'\hline' + '\n'
                output_text += (
                    '\t'
                    + r'\multirow[t]{'
                    + str(len(interface_method_obj.request_headers))
                    + r'}{*}{Request Headers}'
                )
                for i, request_header in enumerate(
                    interface_method_obj.request_headers.items()
                ):
                    if i > 0:
                        output_text += '\t'
                    output_text += (
                        r'&'
                        + request_header[0]
                        + r'&'
                        + request_header[1]
                        + r'\\'
                        + '\n'
                    )
                    if i + 1 < len(interface_method_obj.request_headers):
                        output_text += '\t' + r'\cline{2-3}' + '\n'
                    else:
                        output_text += '\t' + r'\hline' + '\n'
                output_text += '\t' + r'\hline' + '\n'
                output_text += '\t' + r'Request Body&'
                if isinstance(interface_method_obj.request_body, list):
                    if len(interface_method_obj.request_body) > 1:
                        output_text += (
                            interface_method_obj.request_body[0]
                            + r'&'
                            + interface_method_obj.request_body[1]
                            + r'\\'
                            + '\n'
                        )
                    else:
                        output_text += (
                            r'\multicol{2}{l|}{'
                            + interface_method_obj.request_body[0]
                            + r'}\\'
                            + '\n'
                        )
                else:
                    output_text += r'\multicol{2}{l|}{'
                    if interface_method_obj.request_body is None:
                        output_text += '-'
                    else:
                        output_text += interface_method_obj.request_body
                    output_text += r'}\\' + '\n'
                output_text += '\t' + r'\hline' + '\n'
                output_text += r'\end{tabularx}' + '\n'
            output_text += '\n\n'
        file.write(output_text)
