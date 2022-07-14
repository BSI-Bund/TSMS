"""
automatic PDF extraction is done here
text manipulation is kept to a minimum to ensure a correct result with concurrent performance
done in around 0.3s, versus 4 min in manual copy-paste-extraction
"""

# used:
## PyMuPDF (aka 'fitz'): Python bindings for MuPDF, which is a lightweight PDF and XPS viewer. The library can access files in PDF, XPS, OpenXPS, epub, comic and fiction book formats, and it is known for its top performance and high rendering quality.
### structure is generally the same as with copying from adobe reader manually, small changes necessary

# feasyble:
## PDFMiner: Is written entirely in Python, and works well for Python 2.4. For Python 3, use the cloned package PDFMiner.six. Both packages allow you to parse, analyze, and convert PDF documents. This includes the support for PDF 1.7 as well as
## CJK languages (Chinese, Japanese, and Korean), and various font types (Type1, TrueType, Type3, and CID).
### different configuration in output file, would have to rewrite lot of functions

# not feasible:
## PyPDF2: A Python library to extract document information and content, split documents page-by-page, merge documents, crop pages, and add watermarks. PyPDF2 supports both unencrypted and encrypted documents.
### wrongfully put linebreaks '\n'
## PDFQuery: It describes itself as 'a fast and friendly PDF scraping library' which is implemented as a wrapper around PDFMiner, lxml, and pyquery. Its design aim is 'to reliably extract data from sets of PDFs with as little code as possible.'
### how to use?
## tabula-py: It is a simple Python wrapper of tabula-java, which can read tables from PDFs and convert them into Pandas DataFrames. It also enables you to convert a PDF file into a CSV/TSV/JSON file.
### only tables, no context

# not tested:
## pdflib for Python: An extension of the Poppler Library that offers Python bindings for it. It allows you to parse, analyze, and convert PDF documents. Not to be confused with its commercial pendant that has the same name.
## PyFPDF: A library for PDF document generation under Python. Ported from the FPDF PHP library, a well-known PDFlib-extension replacement with many examples, scripts, and derivatives.
## PDFTables: A commercial service that offers extraction from tables that comes as a PDF document. Offers an API so that PDFTables can be used as SAAS.
## PyX - the Python graphics package: PyX is a Python package for the creation of PostScript, PDF, and SVG files. It combines an abstraction of the PostScript drawing model with a TeX/LaTeX interface. Complex tasks like creating 2D and 3D plots in
## publication-ready quality are built out of these primitives.
## ReportLab: An ambitious, industrial-strength library largely focused on precise creation of PDF documents. Available freely as an Open Source version as well as a commercial, enhanced version named ReportLab PLUS.
## pdfrw: A pure Python-based PDF parser to read and write PDF. It faithfully reproduces vector formats without rasterization. In conjunction with ReportLab, it helps to re-use portions of existing PDFs in new PDFs created with ReportLab.

import os
import re
import time

import fitz

from utilityFunctions import pathSplitL #, msgs

def pyMuPdfParser(source_file: str, dest_file: str) -> None:
    """
    function to parse pdf with pyMuPdf
    """
    doc = fitz.open(source_file)
    with open(dest_file, 'wb') as fout:
        for page in doc:
            fout.write(page.get_text().encode('utf-8') + bytes((12,)))

def parseTR(pdf_file: str, txt_file: str) -> None:
    """
    function to translate a tr-pdf to a tr-txt
    """
    txt_time = time.time()

    pyMuPdfParser(pdf_file, txt_file)

    #re_soleChapNum = re.compile(r'[\d\.]+\n')

    with open(txt_file, 'rt', encoding='utf-8') as file:
        text = file.readlines()

    # delete last line, just '\f' there
    del text[-1]

    # make header and footer compatible to adobe readers version
    for i, line in enumerate(text):
        if '\f' in line:
            text[i] = line.strip() + '\n'
            text[i+1] = text[i+1].strip() + ' '

    # pull title and num together
    re_num = re.compile(r'^\d\.[\d\.]+$')
    for i, line in enumerate(text):
        if re_num.search(line.strip()):
            text[i] = line.strip() + ' '
    re_num_short = re.compile(r'^[\d]{1}$')
    for i, line in enumerate(text):
        if re_num_short.search(line.strip()):
            text[i] = line.strip() + ' '

    with open(txt_file, 'wt', encoding='utf-8') as file:
        file.writelines(text)

    with open(txt_file, 'rt', encoding='utf-8') as file:
        text = file.readlines()

    # crop source to relevant chapters
    re_chap_a = re.compile(r'^4.1.[34] Data Types$')
    re_chap_b = re.compile(r'^4.1.[45] Common Definitions$')
    re_chap_c = re.compile(r'^4.1.[56] Interface Methods$')
    re_chap_d = re.compile(r'^4.2 TSM-API$')

    for i, line in enumerate(text):
        if re_chap_a.search(line.strip()):
            chap_a = i
        if re_chap_d.search(line.strip()):
            chap_d = i
    #text = text[chap_a:chap_d+1]

    # no line shall end by semicolon, minus (connecting words) or comma (after error number)
    re_semicolon_end = re.compile(r'\;$')
    re_minus_end = re.compile(r'[^\-]\-$')
    re_comma_end = re.compile(r'[ \,][\d]{4}\,$')
    re_slash_end = re.compile(r'Id\}\/$')
    for i, line in enumerate(text):
        if re_semicolon_end.search(line.strip()) or re_comma_end.search(line.strip()) or re_slash_end.search(line.strip()):
            text[i] = line.strip() + ' '
        elif re_minus_end.search(line.strip()):
            text[i] = line.strip()

    # no line shall start by bracket
    re_bracket_start = re.compile(r'^\(')
    for i, line in enumerate(text):
        if re_bracket_start.search(line.strip()):
            text[i-1] = text[i-1].strip() + ' '
        if '  ' in line:
            text[i] = line.replace('  ',' ')

    with open(txt_file, 'wt', encoding='utf-8') as file:
        file.writelines(text)

    with open(txt_file, 'rt', encoding='utf-8') as file:
        text = file.readlines()

    # brackets have to be closed within a line
    for i, line in enumerate(text):
        if '(' in line and ')' not in line:
            text[i] = line.strip() + ' '

    with open(txt_file, 'wt', encoding='utf-8') as file:
        file.writelines(text)

    with open(txt_file, 'rt', encoding='utf-8') as file:
        text = file.readlines()

    for i, line in enumerate(text):
        if re_chap_a.search(line.strip()):
            chap_a = i
        if re_chap_b.search(line.strip()):
            chap_b = i
        if re_chap_c.search(line.strip()):
            chap_c = i
        if re_chap_d.search(line.strip()):
            chap_d = i

    # clear empty lines and spaces at line end
    for i, line in enumerate(text):
        if len(line.strip()) != 0:
            text[i] = line.strip().replace('> ','>\n') + '\n'
        else:
            text[i] = line.strip()

    # section 4.1.4
    replace_a = [
        ## Content
        ('tiatio\n','tiatio'),
        ('nstant\n','nstant'),
        (' string\n','\nstring\n'),
        ('<string,\n','<string, '),
        ('Mand. Ed.\n','Mand.\nEd.\n'),
        (' string[]\n','\nstring[]\n'),
        ('Config Config','Config\nConfig'),
        ('allowedDeploy\n','allowedDeploy'),
        ('Instantiation\n','Instantiation'),
        ('Config Persona','Config\nPersona'),
        ('SecureComponent\n','SecureComponent'),
        ('AuthorizedDevice\n','AuthorizedDevice'),
        ('DataBlock boolean','DataBlock\nboolean'),
        ('Configs EntityList','Configs\nEntityList'),
        ('ApplicationInstanti\n','ApplicationInstanti'),
        ('boolean Flag, whether ','boolean\nFlag, whether '),
        ('Requirements Technical ','Requirements\nTechnical '),
        ('entProfiles EntityList<','entProfiles\nEntityList<'),
        ('contextSpecificAttribut\n','contextSpecificAttribut'),
        ('accessAuthorizedDeviceA\n','accessAuthorizedDeviceA'),
        ('contextSpecificAttribute\n','contextSpecificAttribute'),

        ## Key-Description
        (' is\n',' is '),
        (' the\n',' the '),
        (' this\n',' this '),
        (' this ELF\n',' this ELF '),
        (' the ApplicationConfig\n',' the ApplicationConfig '),
        (' the PersonalizationConfig\n',' the PersonalizationConfig '),

        ## Att-Description [MANDATORY]
        (' provide\n',' provide '),

        ## Att-Description, normal Description [OPTIONAL, time consuming, no benefit ...]
        # (' a\n',' a '),
        # (' A\n',' A '),
        # (' as\n',' as '),
        # (' an\n',' an '),
        # (' if\n',' if '),
        # (' it\n',' it '),
        # (' in\n',' in '),
        # (' In\n',' In '),
        # (' be\n',' be '),
        # (' of\n',' of '),
        # (' or\n',' or '),
        # (' to\n',' to '),
        # (' yet\n',' yet '),
        # (' can\n',' can '),
        # (' are\n',' are '),
        # (' its\n',' its '),
        # (' not\n',' not '),
        # (' via\n',' via '),
        # (' and\n',' and '),
        # (' for\n',' for '),
        # (' data\n',' data '),
        # (' Data\n',' Data '),
        # (' must\n',' must '),
        # ('. The\n','. The '),
        # (' from\n',' from '),
        # (' used\n',' used '),
        # (' only\n',' only '),
        # (' when\n',' when '),
        # (' that\n',' that '),
        # (' ELFs.\n',' ELFs. '),
        # (' state\n',' state '),
        # (' valid\n',' valid '),
        # (' value\n',' value '),
        # (' which\n',' which '),
        # (' model\n',' model '),
        # (' first.\n',' first. '),
        # (' longer\n',' longer '),
        # (' a CAP.\n',' a CAP. '),
        # (' update\n',' update '),
        # (' deploy\n',' deploy '),
        # (' related\n',' related '),
        # (' example\n',' example '),
        # (' certain\n',' certain '),
        # (' backend\n',' backend '),
        # (' extends\n',' extends '),
        # (' follows\n',' follows '),
        # (' hidden.\n',' hidden. '),
        # (' instance\n',' instance '),
        # (' JavaCard\n',' JavaCard '),
        # (' only CAP\n',' only CAP '),
        # (' existing\n',' existing '),
        # (' specific\n',' specific '),
        # (' omitted,\n',' omitted, '),
        # (' Subset of ',' Subset of\n'),
        # (' technical\n',' technical '),
        # (' following\n',' following '),
        # (' interface\n',' interface '),
        # (' lifecycle\n',' lifecycle '),
        # (' different\n',' different '),
        # (' AID format\n',' AID format '),
        # (' a Service,\n',' a Service, '),
        # (' considered\n',' considered '),
        # (' identifies\n',' identifies '),
        # (' parameters\n',' parameters '),
        # (' referenced\n',' referenced '),
        # (' the Flavor\n',' the Flavor '),
        # (' the Flavor.\n',' the Flavor. '),
        # (' attestation\n',' attestation '),
        # (' application\n',' application '),
        # (' TSM-support\n',' TSM-support '),
        # (' provisioning\n',' provisioning '),
        # (' communication\n',' communication '),
        # (' modifications\n',' modifications '),
        # (' Automatically\n',' Automatically '),
        # (' during Version\n',' during Version '),
        # (' [GPC_SPE_034].\n',' [GPC_SPE_034]. '),
        # (' AttestationToken\n',' AttestationToken '),
        # ('InstantiationConfigs,\n','InstantiationConfigs, '),
        # (' applicationInstantation\n',' applicationInstantation '),
        # (' identification of this ELF.\n',' identification of this ELF. '),
        # ('iple ApplicationInstantiationConfigs\n','iple ApplicationInstantiationConfigs '),
        # (' identification of this Certificate.\n',' identification of this Certificate. '),
    ]

    # section 4.1.6
    replace_b = [
        ## Content
        (' the\n',' the '),
        ('to be\n','to be '),
        ('-file”,\n','-file”, '),
        ('<string,\n','<string, '),

        ## Description [OPTIONAL, time consuming, no benefit ...]
        # (' in\n',' in '),
        # (' an\n',' an '),
        # (' or\n',' or '),
        # (' of\n',' of '),
        # (' use\n',' use '),
        # (' and\n',' and '),
        # (' both\n',' both '),
        # ('. The\n','. The '),
        # (' method\n',' method '),
        # (' create\n',' create '),
        # (' to this\n',' to this '),
        # (' are not\n',' are not '),
        # (' to link\n',' to link '),
        # ('. Linking\n','. Linking '),
        # (' a binary\n',' a binary '),
        # (' and their\n',' and their '),
        # (' with a SP\n',' with a SP '),
        # (' attributes\n',' attributes '),
        # (' associated\n',' associated '),
        # (' to provide\n',' to provide '),
        # (' of specific\n',' of specific '),
        # (' via methods\n',' via methods '),
        # (' this Flavor,\n',' this Flavor, '),
        # (' this Version,\n',' this Version, '),
        # (' or SposConfig\n',' or SposConfig '),
        # (' personalization\n',' personalization '),
        # ('figs, is deleted.\n','figs, is deleted. '),
        # ('sions and Flavors\n','sions and Flavors '),
        # (' 4.1.5.4. Managing\n',' 4.1.5.4. Managing '),
        # ('. An ApplicationConfig\n','. An ApplicationConfig '),
        # (' and PersonalizationScripts\n',' and PersonalizationScripts '),
    ]

    counter_a = [0] * len(replace_a)
    counter_b = [0] * len(replace_b)

    # combine long lines
    for i in range(chap_a,chap_b):
        for j, repl_a in enumerate(replace_a):
            if repl_a[0] in text[i]:
                text[i] = text[i].replace(repl_a[0],repl_a[1])
                counter_a[j] += 1
    for i in range(chap_c,chap_d):
        for j, repl_b in enumerate(replace_b):
            if repl_b[0] in text[i]:
                text[i] = text[i].replace(repl_b[0],repl_b[1])
                counter_b[j] += 1

    with open(txt_file, 'wt', encoding='utf-8') as file:
        file.writelines(text)

    #print(msgs(['pdf2txt timing (part)','',str(time.time() - txt_time)[:6]+' seconds','']))
    print('pdf2txt timing (part)')
    print(str(time.time() - txt_time)[:6]+' seconds')
    print()



###########
# Testing #
###########

if __name__ == '__main__':
    os.chdir(pathSplitL(__file__,2))

    FILENAME_PDF = os.path.join('.','pdf','TR-TSMS_V1.0_20220603.pdf')
    FILENAME_TXT = FILENAME_PDF.replace('.pdf','')+'.txt'

    parseTR(FILENAME_PDF, FILENAME_TXT)
