"""
contains main function, that should be called by 'python pdf2yaml.py pdf latest'
to read the latest TR pdf-file stored in the pdf directory
"""

import time
import sys
from pathlib import Path

from sourceHandling import Source

import pdf2txt

# import contents2latex


def dateKey(file):
    """
    key-function, to only point to dates at the end of a filename
    """
    return str(file)[-12:-4]

def versionKey(file):
    """
    key-function, to only point to sanitized version numbers in the filename
    expecting format:
        TR-TSMS_V1.0.5.pdf
    or
        TR-TSMS_V1.1.pdf
    or
        TR-TSMS_V1.0.5_pre.pdf
    """
    file = str(file).replace('.pdf','')
    while len(str(file)) < 14:
        file += '.0'
    return file

def chooseFile():
    """
    source file is chosen depending on input parameter
    this function is robust against not available files or invalid input parameteres
    choose the latest valid file with input parameter 'latest'
    """

    arg = 'latest'
    if len(sys.argv) == 2:
        print('filename argument: ' + sys.argv[1] + '\n')
        arg = sys.argv[1]

    root_dir = Path(__file__).parents[1]
    # find latest TR file
    if arg.lower() == 'latest':
        files = []
        for i in root_dir.glob('pdf/*.pdf'):
            files.append(i.name)
        files.sort(key=versionKey) # key=dateKey
        filename = root_dir / 'pdf' / files[-1]
    elif arg == 'txt':
        filename = root_dir / 'pdf' / 'tr_03165_source.txt'
    else:
        filename = root_dir / 'pdf' / arg

    print('TR TSMS sourcefile chosen:')
    print(filename)

    ## convert to TXT
    if filename.suffix == '.pdf':
        filename_pdf = filename
        filename = root_dir / 'pdf' / 'tr_03165_source.txt'
        pdf2txt.parseTR(filename_pdf, filename)

        print('PDF converted to text file:')
        print(filename)

    # extract version
    version = '1.0.5'
    with open(filename, 'rt', encoding='utf-8') as fobj:
        for line in fobj:
            if 'Version' in line:
                version = line.split(' ',1)[1].strip()
                break

    return filename, version


def main(yaml_name: str) -> None:
    """
    main routine for the whole program
    """
    # choose root folder of python project as current working directory
    cwd = Path(__file__).parents[1]

    # define file paths
    source_name, source_version = chooseFile()
    source_file = Path(cwd, 'pdf', source_name.name)

    # run twice for robustness sake
    # needed if previous result is incomplete or missing
    for _ in range(2):
        # handle source via object construct
        source_obj = Source(source_file, yaml_name)
        source_obj.set_version(source_version)
        source_obj.generate()

    # Convert Table-Content to LaTeX-format
    # contents2latex.dataTypeConversion(source_obj.data_types)
    # contents2latex.interfaceMethodConversion(source_obj.interface_methods)


if __name__ == '__main__':
    pdf_time = time.time()

    print()
    print('################')
    print('### PDF2YAML ###')
    print('################')
    print()

    YAML_NAME = 'TSM_Backend_API.yaml'

    # main function
    main(YAML_NAME)

    # print(msgs(['pdf2yaml timing (full)','',str(time.time() - pdf_time)[:6]+' seconds',''],32))
    print('pdf2yaml timing (full)')

    print(str(time.time() - pdf_time)[:6] + ' seconds')
    print()

    ## dataType Test
    # for dataType in sourceObj.dataTypes:
    #     dataType.printContent()

    ## interfaceMethod Test
    # for interfaceMethod in sourceObj.interfaceMethods:
    #     interfaceMethod.printContent()
