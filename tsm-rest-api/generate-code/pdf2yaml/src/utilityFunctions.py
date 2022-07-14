"""
generally useful functions are kept here
to handle the basic level of text processing
"""

import os
import re
#from tabnanny import verbose

###################
# Path Operations #
###################

def pathSplitL(path: str, n_times: int) -> str:
    """
    walks back path n_times
    returns resulting path
    """
    if n_times > 0:
        return pathSplitL(os.path.split(os.path.abspath(path))[0], n_times - 1)
    return path


def pathSplitR(path: str, n_times: int) -> str:
    """
    walks back path n_times
    returns right split of result
    """
    if n_times > 0:
        return pathSplitR(os.path.split(os.path.abspath(path))[0], n_times - 1)
    return os.path.split(path)[1]



######################
# Content Operations #
######################

def shortenCommonTerms(verbose_term: str, delim = ' ') -> str:
    """
    common terms are shortened for better readability of operation_id
    """
    return verbose_term.replace(
        'ApplicationConfig','App'+delim+'Config').replace(
            'PersonalizationScript','Perso'+delim+'Script').replace(
                'Certificate','Cert').replace(
                    'SposConfig','Spos'+delim+'Config').replace(
                        'Retrieve SecureComponentProfiles','SCP').replace(
                            'SecureComponentProfile','Secure'+delim+'Component'+delim+'Profile')


def makeStringCamelCase(string: str, delim: str) -> str:
    """
    convert ordinary string sentences toAppearInCamelcase
    single words are separated by underscore " "
    or
    convert ordinary string filenames toAppearInCamelcase
    single words are separated by underscore "_"
    choose delim accordingly
    """
    string = string.lower().strip()
    for k, char in enumerate(string):
        if char == delim:
            string = string[:k+1] + string[k+1].upper() + string[k+2:]
    return string.replace(delim,"")


def upperFirstChar(string: str) -> str:
    """
    make first char uppercase
    """
    return string[0].upper()+string[1:]


def iFirstUpperChar(string: str) -> int:
    """
    returns position of first upper char
    if there is none, 0 is returned
    """
    result = re.search('[A-Z]',string)
    if result:
        return result.span()[0]
    return 0


def returnTextLineFromList(text: list, line: str, first_index = 0) -> int:
    """
    returns line that contains specific text
    returns 0 if text not found
    """
    for i in range(first_index+1, len(text), 1):
        if text[i] == line:
            return i
    return 0


def getColumnFromTable(table: list, begin: int, end: int, step: int) -> list:
    """
    insert table in list form
    get single column in list form
    """
    result = []
    for i in range(begin,end,step):
        result.append(table[i].strip())
    return result



###############
# OperationID #
###############

def getOperationIdsWithoutDoubles(interface_methods: list) -> list:
    """
    generate operation_ids without double entries
    if an entry is doubled, supkapitel is appended
    len of returned list == len of files list
    """
    #re_list_linked = re.compile(r'}/[\w]+/{')

    # create result lists
    supkapitel = []
    kapitel = []
    for i, interface_method in enumerate(interface_methods):
        # handle latest supkapitel name
        if interface_method.type_ == 'supkapitel':
            last_supkapitel = interface_method.title
            last_supkapitel = shortenCommonTerms(last_supkapitel)
            last_supkapitel = makeStringCamelCase(last_supkapitel.replace('Manage ',''),' ')
            # append kapitel and supkapitel lists
            supkapitel.append(last_supkapitel)
            kapitel.append('_')
        # handle latest kapitel name
        elif interface_method.type_ == 'kapitel':
            last_kapitel = interface_method.title
            # association_keywords = ['Linked','Associated']
            # if any(assoc_key in last_kapitel for assoc_key in association_keywords):
            #     relation = re_list_linked.findall(interface_method.restUrl)
            #     if relation:
            #         relation.reverse()
            #         association = 'To '
            #         for rel in relation:
            #             association += upperFirstChar(rel.replace('}/','').replace('/{','')[:-1]) + ' Of '
            #         last_kapitel += '_' + association[:-4]
            last_kapitel = shortenCommonTerms(last_kapitel)
            last_kapitel = makeStringCamelCase(last_kapitel,' ')
            # append kapitel and supkapitel lists
            supkapitel.append(last_supkapitel)
            kapitel.append(last_kapitel)
    # check for doubles in kapitel list
    doubles = []
    for i, kap_i in enumerate(kapitel):
        for j, kap_j in enumerate(kapitel):
            if (kap_i == kap_j) and (i != j):
                doubles.append(i)
                break
    # construct operation_ids according to doubles
    operation_ids = []
    for i, kap in enumerate(kapitel):
        if i in doubles or 'Related' in kap:
            operation_ids.append(kap+'_'+supkapitel[i])
        else:
            operation_ids.append(kap)
    return operation_ids


def renameOperationIdsWithOneUnderscore(operation_ids: list) -> list:
    """
    combined operation_ids from previous doubles check are renamed
    this is for better readability
    """
    for i, operation_id in enumerate(operation_ids):
        # entries with exactly one underscore
        if ('_' in operation_id) and not '__' in operation_id:
            # general handling
            front = operation_id.split('_',1)[0]
            back = upperFirstChar(operation_id.split('_',1)[1])
            # singular enforced
            if back[-1] == 's':
                back = back[:-1]
            # specific exceptions
            back = back.replace('ElfsAndEm','Elf')
            back = back.replace('ServicesAndFlavor','Service')
            back = back.replace('PersoScript','Script')
            # location of first upper
            first_upper = iFirstUpperChar(front)
            operation_ids[i] = front[:first_upper]+back+front[first_upper:]
    return operation_ids



###################
# return messages #
###################

def msg(text: str, pos: str, len_target = 32) -> str:
    """
    single return message
    """
    # Gesamtlänge - Textlänge - vorhandene Trenner
    len_rest = len_target - len(text) - 7
    if pos == 't':
        start_chr = chr(9484)
        middle_chr_a = chr(9472)
        if len(text) == 0:
            middle_chr_b = chr(9472)
        else:
            middle_chr_b = ' '
        end_chr = chr(9488)
    elif pos == 'm':
        start_chr = chr(9474)
        middle_chr_a = ' '
        middle_chr_b = ' '
        end_chr = chr(9474)
    elif pos == 'b':
        start_chr = chr(9492)
        middle_chr_a = chr(9472)
        if len(text) == 0:
            middle_chr_b = chr(9472)
        else:
            middle_chr_b = ' '
        end_chr = chr(9496)
    return start_chr + middle_chr_a*3 + middle_chr_b + text + middle_chr_b + middle_chr_a*len_rest + end_chr


def msgs(text: list, len_target = 32) -> str:
    """
    return message box with same length
    accounts for enlargement if text-len too big
    """
    result = ''
    for i, val in enumerate(text):
        if len(val) > 76:
            text.insert(i+1,val[76:])
            text[i] = val[:76]
    for i, val in enumerate(text):
        if len_target - len(val) - 7 < 0:
            len_target -= len_target - len(val) - 10
    for i, val in enumerate(text):
        if i == 0:
            result += msg(val,'t',len_target) + '\n'
        elif i == len(text)-1:
            result += msg(val,'b',len_target)
        else:
            result += msg(val,'m',len_target) + '\n'
    return result



###################
# Printing Colors #
###################

#pylint: disable=too-few-public-methods
class BColors:
    """
    source: https://stackoverflow.com/questions/287871/how-to-print-colored-text-to-the-terminal
    usage pre python 3.6: print(BColors.WARNING + "Warning: No active frommets remain. Continue?" + BColors.ENDC)
    usage python 3.6+:    print(f"{BColors.WARNING}Warning: No active frommets remain. Continue?{BColors.ENDC}")
    currently not working
    """
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'



###########
# Testing #
###########

if __name__ == '__main__':
    print('__file__               =',__file__)
    print()
    print('pathSplitL(__file__,3) =',pathSplitL(__file__,3))
    print('pathSplitL(__file__,0) =',pathSplitL(__file__,0))
    print()
    print('pathSplitR(__file__,3) =',pathSplitR(__file__,3))
    print('pathSplitR(__file__,0) =',pathSplitR(__file__,0))
    print()
    print("shortenCommonTerms('ApplicationConfig','')      =",shortenCommonTerms('ApplicationConfig',''))
    print("shortenCommonTerms('PersonalizationScript','_') =",shortenCommonTerms('PersonalizationScript','_'))
    print()
    print("makeStringCamelCase('String_sep_By_UNDERSCOREs','_') =",makeStringCamelCase('String_sep_By_UNDERSCOREs','_'))
    print("makeStringCamelCase('strinG sEp bY SpaceS',' ')      =",makeStringCamelCase('strinG sEp bY SpaceS',' '))
    print()
    print("upperFirstChar('word') =",upperFirstChar('word'))
    print("upperFirstChar('BooK') =",upperFirstChar('BooK'))
    print()
    print("iFirstUpperChar('abCdEf') =",iFirstUpperChar('abCdEf'))
    print("iFirstUpperChar('abcdef') =",iFirstUpperChar('abcdef'))
    print()
    _LISTE_ = ['Haus','Baum','Auto','rot']
    print("returnTextLineFromList(_LISTE_,'Baum') =",returnTextLineFromList(_LISTE_,'Baum'))
    print("returnTextLineFromList(_LISTE_,'blau') =",returnTextLineFromList(_LISTE_,'blau'))
    print("returnTextLineFromList(_LISTE_,'auTo') =",returnTextLineFromList(_LISTE_,'auTo'))
    print()
    _TABELLE_ = [
        's0z0','s1z0','s2z0','s3z0',
        's0z1','s1z1','s2z1','s3z1',
        's0z2','s1z2','s2z2','s3z2',
        's0z3','s1z3','s2z3','s3z3',
    ]
    print('getColumnFromTable(_TABELLE_,1,len(_TABELLE_),4) = ',getColumnFromTable(_TABELLE_,1,len(_TABELLE_),4))
    print()
    b = chr(9472)
    print(chr(9484)+b*3+' function timing (full) '+b*3+chr(9488))
    print(chr(9474)+' '*30+chr(9474))
    print(chr(9492)+b*3+' 0.1699 seconds '+b*11+chr(9496))

    print(msg('function timing (full)','t'))
    print(msg('','m',32))
    print(msg('0.1709 seconds','b',32))

    print(msgs(['function timing (full)','','internal text with larger line than intended','second shorter line','','0.1719 seconds']))

    print(msgs(['','no border text, just internal text, that is split from one line to another, to not exceed the linelength of a common cmd window','']))
