"""
contains main function, that should be called by 'python pdf2yaml.py pdf latest'
to read the latest TR pdf-file stored in the pdf directory
"""

import time

import sys
import os
import re

from sourceHandling import Source
from utilityFunctions import pathSplitL

import pdf2txt

def dateKey(file):
    """
    key-function, to only point to dates at the end of a filename
    """
    return file[-12:-4]

def chooseFile():
    """
    source file is chosen depending on input parameter
    this function is robust against not available files or invalid input parameteres
    choose the latest valid file with input parameter 'latest'
    """
    files = []
    for _,_,files in os.walk(os.path.join('.','pdf')):
        pass
    filename = ''

    if len(sys.argv) == 1:
        sys.argv.append('latest')
        sys.argv.append('pdf')
    elif len(sys.argv) == 2:
        sys.argv.append('pdf')

    # source selection
    pdf_file = ''
    txt_file = ''
    # default without specified source is last source used
    if len(sys.argv)==1:
        filename = 'tr_03165_source.txt'
    # if parameter is given, default is txt source
    elif sys.argv[2] == 'txt':
        if sys.argv[1] == 'latest':
            filenames = []
            naming_scheme = re.compile(r'tr_03165_source_\d{8}.txt')
            for file in files:
                if naming_scheme.search(file):
                    if file == naming_scheme.search(file).group():
                        filenames.append(file)
            # sort for safety, choose the last one
            filenames.sort(key=dateKey)
            filename = filenames[-1]
        else:
            filename = 'tr_03165_source_' + sys.argv[1] + '.txt'
    # if explicitly specified, pdf is source
    elif sys.argv[2] == 'pdf':
        filenames = []
        # naming convention: TR-TSMS_V<Major>.<Minor>_<YYYY><MM><DD>.pdf
        naming_scheme = re.compile(r'TR-TSMS_V\d{1,3}\.\d{1,3}_\d{8}.pdf')
        for file in files:
            if naming_scheme.search(file):
                if file == naming_scheme.search(file).group():
                    filenames.append(file)
        # sort for safety, choose the last one
        filenames.sort(key=dateKey)
        if sys.argv[1] == 'latest':
            # read directly from latest pdf
            pdf_file = os.path.join('.','pdf',filenames[-1])
        else:
            # read specific pdf
            for file_i in filenames:
                if sys.argv[1] in file_i:
                    pdf_file = os.path.join('.','pdf',file_i)
                    break
        # pdf found -> parse it
        if pdf_file != '':
            txt_file = pdf_file.replace('.pdf','.txt')
            pdf2txt.parseTR(pdf_file, txt_file)
            filename = os.path.split(txt_file)[-1]
    # backup solution to avoid crash -> last used file
    if (filename not in files) or (filename == ''):
        filename = 'tr_03165_source.txt'

    #print(msgs(['sourcefile chosen:','',os.path.join(os.getcwd(),'pdf',filename),'']))
    print('sourcefile chosen:')
    print(os.path.join(os.getcwd(),'pdf',filename))
    print()
    return filename


def main(yaml_name: str) -> None:
    """
    main routine for the whole program
    """
    # choose root folder of python project as current working directory
    os.chdir(pathSplitL(__file__,2))

    # define file paths
    source_name = chooseFile()
    source_file = os.path.join('.','pdf',source_name)

    # run twice for robustness sake
    # needed if previous result is incomplete or missing
    for _ in range(2):
        # handle source via object construct
        source_obj = Source(source_file, yaml_name)
        source_obj.generate()


if __name__=='__main__':
    pdf_time = time.time()

    YAML_NAME = 'TSM_Backend_API.yaml'

    # main function
    main(YAML_NAME)

    #print(msgs(['pdf2yaml timing (full)','',str(time.time() - pdf_time)[:6]+' seconds',''],32))
    print('pdf2yaml timing (full)')

    print(str(time.time() - pdf_time)[:6]+' seconds')
    print()

    ## dataType Test
    # for dataType in sourceObj.dataTypes:
    #     dataType.printContent()

    ## interfaceMethod Test
    # for interfaceMethod in sourceObj.interfaceMethods:
    #     interfaceMethod.printContent()
