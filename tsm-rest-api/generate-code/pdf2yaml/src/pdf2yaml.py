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

# import contents2latex


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

    filename = 'latest'
    if len(sys.argv) == 2:
      print("filename argument: " + sys.argv[1])
      filename = sys.argv[1]

    # find latest TR file
    if filename.lower() == 'latest':
      files = []
      filenames = []
      for _, _, files in os.walk(os.path.join('.', 'pdf')):
        pass
      for file in files:
        if "tr" in file.lower():
            filenames.append(file)
      filenames.sort(key=dateKey)
      filename = os.path.join(os.getcwd(), 'pdf', filenames[-1])
    else:
      filename = os.path.join(os.getcwd(), 'pdf', filename)
    
    print('TR TSMS sourcefile chosen:')
    print(filename)
    
    ## convert to TXT
    if filename.lower().endswith("pdf"):
      filename_pdf = filename;
      filename = os.path.join(os.getcwd(), 'pdf', "tr_03165_source.txt")
      pdf2txt.parseTR(filename_pdf, filename)

      print('PDF converted to text file:')
      print(filename)

    return filename


def main(yaml_name: str) -> None:
    """
    main routine for the whole program
    """
    # choose root folder of python project as current working directory
    os.chdir(pathSplitL(__file__, 2))

    # define file paths
    source_name = chooseFile()
    source_file = os.path.join('.', 'pdf', source_name)

    # run twice for robustness sake
    # needed if previous result is incomplete or missing
    for _ in range(2):
        # handle source via object construct
        source_obj = Source(source_file, yaml_name)
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
