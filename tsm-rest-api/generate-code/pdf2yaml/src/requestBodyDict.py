"""
examples for request bodies are generated for usage in the yaml
mode of random or fixed examples can be chosen
"""

import re
import os

# choose 'fixedValueExamples' OR 'randomValueExamples' here
from exampleValues import FixedValueExamples as ExampleValues
from utilityFunctions import makeStringCamelCase, pathSplitL


def deleteEmptyLines(text: list) -> list:
    """
    empty lines or lines beginnig with comments are ignored
    """
    for i, line in enumerate(text):
        if len(line.lstrip()) > 0:
            if line.lstrip()[0] == '#':
                text[i] = ''
        else:
            text[i] = line.lstrip()
    return text


def removeLinksTillEnd(text: list) -> list:
    """
    line that starts with '_links' and following lines are ignored
    """
    for i, line in enumerate(text):
        if len(line) > 14:
            #print(text[i][8:14] + " == '_links' :: " + str(text[i][8:14] == '_links'))
            if line[8:14] == '_links':
                text = text[:i]
                break
    return text


def dictOfProperties(text: list) -> dict:
    """
    generates a dict of property related items
    key: property
    value: list of related items
    """
    return_value = {}
    last_key = ''
    for _, line in enumerate(text):
        if len(line) > 10:
            if (line[:8] == '        ') and (line[8] != ' '):
                return_value[line.strip()] = []
                last_key = line.strip()
            elif line[:10] == '          ':
                return_value[last_key].append(line[10:].rstrip())
    return return_value


def dictOfPropertiesEditable(full_dict: dict) -> dict:
    """
    not-editable properties are ignored
    """
    reduced_dict = {}
    for i in full_dict:
        if i[-2:] != 'No':
            reduced_dict[i.split(':',1)[0]] = full_dict[i]
    return reduced_dict


def listOfPropertyEntries(title: str, reduced_dict: dict) -> list:
    """
    translate properties with respective entries to a valid 'property: example' list
    """
    property_entries = []
    for i in reduced_dict:
        example_value = ''
        no_break = True
        ## PRIO 0: format for random example
        for j, line_j in enumerate(reduced_dict[i]):
            if (line_j=='type: string') and no_break:
                for _, line_k in enumerate(reduced_dict[i]):
                    if line_k[:8] == 'format: ':
                        property_entries.append(i+': "'+ExampleValues.get(line_k[8:])+'"')
                        no_break = False
                        break
            if (line_j=='type: array') and no_break:
                for _, line_k in enumerate(reduced_dict[i]):
                    if (line_k[:10] == '  format: ') and no_break:
                        for _ in range(3): # range(secrets.choice(range(3,6))):
                            example_value += '"' + ExampleValues.get(line_k[10:]) + '", '
                        property_entries.append(i+': [ '+example_value[:-2]+' ]')
                        no_break = False
                        break
            if (reduced_dict[i][j]=='type: object') and no_break:
                for _, line_k in enumerate(reduced_dict[i]):
                    if (line_k == '  type: string') and no_break:
                        for num in range(3): # range(secrets.choice(range(3,6))):
                            example_value += 'param' + str(num) + ': "Value' + str(num) + ' which can be evaluated by the App.", '
                        property_entries.append(i+': { '+example_value[:-2]+' }')
                        no_break = False
                        break
        ## PRIO 1: special cases
        if (i == 'cspELFsVersions') and no_break: ## list of pairs (aid-string, version-string)
            for j in range(4): # range(secrets.choice(range(3,7))):
                example_value += '[ "'+ExampleValues.get('aid')+'", "' + ExampleValues.get('version')+'" ], '
            property_entries.append(i+': [ '+example_value[:-2]+' ]')
            continue
        if (i == 'cspVendor') and no_break:
            csp_vendor_list = ['"none"','"thales"','"nxp"']
            for j in csp_vendor_list: # random.sample(csp_vendor_list,secrets.choice(range(1,3))):
                example_value += j + ', '
            property_entries.append(i+': [ '+example_value[:-2]+' ]')
            continue
        if (i == 'name') and no_break:
            if title == 'Service':
                property_entries.append(i+': "'+ExampleValues.get('service_name')+'"')
            elif title in ('Flavor', 'ApplicationConfig'):
                property_entries.append(i+': "'+ExampleValues.get('flavor_name')+'"')
            else:
                for j in reduced_dict[i]:
                    if j[:13] == 'description: ':
                        property_entries.append(i+': "'+makeStringCamelCase(j[13:].replace('"','').replace('.',''),' ')+'"')
                        no_break = False
                        break
            continue
        if (i == 'fileName') and no_break:
            example_value = ExampleValues.get('flavor_name')
            property_entries.append(i+': "'+example_value+'"')
            continue
        if (i == 'accessAuthorizedDeviceApps') and no_break:
            for j in range(3): # range(secrets.choice(range(3,6))):
                example_value += '"'+ExampleValues.get('aid')+'", '
            property_entries.append(i+': [ '+example_value[:-2]+' ]')
            continue
        if (i == 'applicationInstantiationConfigs') and no_break:
            for j in range(3): # range(secrets.choice(range(3,6))):
                example_value += '{ priority: '+ ExampleValues.get('int', 0, 256) + ', executableModuleId: "' + ExampleValues.get('uuid')+'", applicationConfigId: "'+ExampleValues.get('uuid')+'" }, '
            property_entries.append(i+': [ '+example_value[:-2]+' ]')
            continue
        if (i in ('allowList', 'allowedDeployments')) and no_break:
            for j in range(3): # range(secrets.choice(range(3,6))):
                example_value += '"'+ExampleValues.get('uuid')+'", '
            property_entries.append(i+': [ '+example_value[:-2]+' ]')
            continue
        ## PRIO 2: random boolean example value
        for j, line_j in enumerate(reduced_dict[i]):
            if (line_j == 'type: boolean') and no_break:
                #lastBoolean = bool(secrets.randbits(1))
                property_entries.append(i+': '+ExampleValues.get('bool'))
                #lastBoolean = not(lastBoolean)
                no_break = False
                break
        ## PRIO 3: random integer example value
        for j, line_j in enumerate(reduced_dict[i]):
            if (line_j == 'type: integer') and no_break:
                property_entries.append(i+': '+ExampleValues.get('int', 1, 256))
                no_break = False
                break
        ## PRIO 4: general array example
        for j, line_j in enumerate(reduced_dict[i]):
            if (line_j == 'type: array') and no_break:
                for _, line_k in enumerate(reduced_dict[i]):
                    if line_k == '  type: string':
                        property_entries.append(i+': [ "string1", "string2", "string3" ]')
                        no_break = False
                        break
        ## PRIO 5: existing real example (fix value) -- only for manually written description
        for j, line_j in enumerate(reduced_dict[i]):
            if (reduced_dict[i][j][:9] == 'example: ') and no_break:
                property_entries.append(i+': '+reduced_dict[i][j][9:])
                no_break = False
                break
        ## PRIO 6: general type as example -- deprecated
        for j, line_j in enumerate(reduced_dict[i]):
            if (reduced_dict[i][j][:6] == 'type: ') and no_break:
                property_entries.append(i+': DEPRECATED # "'+reduced_dict[i][j]+'"')
                no_break = False
                break
    return property_entries


def generateRequestBodyExamples(source_path: str) -> dict:
    """
    function to generate examples of request bodies as dict
    """
    # define commonly used regular expressions
    re_schema_title = re.compile(r'\s{4}[\w-]+:')

    # define source
    #source_path = os.path.join('.','TSM_Backend_API.yaml')
    # check for source existance
    if not os.path.isfile(source_path):
        return {}

    # get editable-state from compiled schema-source
    with open(source_path,'rt',encoding='utf-8') as source_file:
        components_text = source_file.readlines()

    # search for schemas-iterator, delete lines from components text before that
    for i, line in enumerate(components_text):
        if line.rstrip() == '  schemas:':
            components_text = components_text[i:]
            break

    # search for title-lines
    title_lines = []
    for i, line in enumerate(components_text):
        title_result = re_schema_title.search(line)
        if title_result is not None:
            if line[:-1] == title_result.group():
                title_lines.append(i)
    title_lines.append(len(components_text))

    # define return value dictionary
    dictionary = {}

    # here is where the magic happens
    for i in range(len(title_lines)-1):
        component_text_single = components_text[title_lines[i]:title_lines[i+1]]
        key = component_text_single[0].strip()[:-1]
        component_text_single = deleteEmptyLines(component_text_single)
        component_text_single = list(filter(None, component_text_single))
        component_text_single = removeLinksTillEnd(component_text_single)
        property_dict = dictOfProperties(component_text_single)
        property_dict_editable = dictOfPropertiesEditable(property_dict)
        dictionary[key] = listOfPropertyEntries(key, property_dict_editable)
    return dictionary



###########
# Testing #
###########

if __name__ == '__main__':
    yaml_dir = os.path.join(os.getcwd().split('tsm-rest-api')[0],'tsm-rest-api','tsm-rest-api.yaml')

    print(yaml_dir)

    # choose root folder of python project as current working directory
    os.chdir(pathSplitL(__file__,2))

    test_dictionary = generateRequestBodyExamples(yaml_dir)

    for keyword in test_dictionary.items():
        print(keyword[0])
        for value in keyword[1]:
            print('   ', value)
