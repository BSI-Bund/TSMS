"""
provides general example values for different formats (UUID, AID, TLV, names, boolean, int, ...)
contains random and fixed example generating process with same interface
"""

import secrets
import random
import time
import re

### random value functions ###


class RandomValueExamples:
    """
    definition of RandomValueExamples Class
    """

    @classmethod
    def generateUuid(cls) -> str:
        """generate random UUID value"""
        return (
            secrets.token_hex(4)
            + '-'
            + secrets.token_hex(2)
            + '-'
            + secrets.token_hex(2)
            + '-'
            + secrets.token_hex(2)
            + '-'
            + secrets.token_hex(6)
        )

    @classmethod
    def generateAid(cls) -> str:
        """generate random AID value"""
        return (
            'A00'
            + '0000'
            + str(secrets.randbelow(10000)).zfill(4)
            + str(secrets.randbelow(10000)).zfill(4)
            + 'C'
            + f'{secrets.randbits(4):>04b}'
        )

    @classmethod
    def generateServiceName(cls) -> str:
        """generate random service name value"""
        return (
            random.choice(['pay', 'book', 'auth', 'open'])
            + random.choice(['Hotel', 'Rental', 'Home', 'App', 'Device', 'Token'])
            + 'By'
            + random.choice(['JCN', 'Jtol', 'BNE', 'SecNat'])
        )

    @classmethod
    def generateFlavorName(cls) -> str:
        """generate random flavor name value"""
        return (
            random.choice(['XX-', 'XY-', 'YX-', 'YY-'])
            + str(secrets.randbelow(1000)).zfill(3)
            + random.choice(['A-', 'B-', 'C-', 'D-'])
            + str(secrets.randbelow(100)).zfill(2)
        )

    @classmethod
    def generateVersion(cls) -> str:
        """generate random Version (eg. 1.2.3) value"""
        return (
            str(secrets.randbelow(10)).zfill(1)
            + '.'
            + str(secrets.randbelow(55)).zfill(2)
            + '.'
            + str(secrets.randbelow(130)).zfill(3)
        )

    @classmethod
    def generateVersionMM(cls) -> str:
        """generate random Version (eg. 1.2) value"""
        return (
            str(secrets.randbelow(10)).zfill(1)
            + '.'
            + str(secrets.randbelow(55)).zfill(2)
        )

    @classmethod
    def generateTlv(cls) -> str:
        """generate random TLV value"""
        num = secrets.choice(range(7, 10))
        tlv = f'{secrets.randbits(8):02x} {num:02x}'
        for _ in range(num):
            tlv += f' {secrets.randbits(8):02x}'
        return tlv

    @classmethod
    def generateUri(cls) -> str:
        """generate random URI value"""
        return '/relative/uri/to/resource'
        # alt: '/relative/link/to/resource'

    @classmethod
    def generateInt(cls, lower, upper) -> str:
        """generate random int value"""
        return str(secrets.choice(range(lower, upper)))

    @classmethod
    def generateBoolean(cls) -> str:
        """generate random boolean value"""
        return str(bool(secrets.randbits(1))).lower()

    @classmethod
    def reset(cls) -> None:
        """empty method to have same interface as the other class"""
        pass  # pylint: disable=unnecessary-pass

    @classmethod
    def get(cls, example_type: str, lower=0, upper=256) -> str:
        """central interface function for random values"""
        if example_type == 'uuid':
            return cls.generateUuid()
        if example_type == 'aid':
            return cls.generateAid()
        if example_type == 'service_name':
            return cls.generateServiceName()
        if example_type == 'flavor_name':
            return cls.generateFlavorName()
        if example_type == 'version':
            return cls.generateVersion()
        if example_type == 'versionMM':
            return cls.generateVersionMM()
        if example_type == 'tlv':
            return cls.generateTlv()
        if example_type == 'uri':
            return cls.generateUri()
        if example_type == 'int':
            return cls.generateInt(lower, upper)
        if example_type == 'bool':
            return cls.generateBoolean()
        if example_type == 'date-time':
            now = time.localtime(time.time())
            return f'{now.tm_year:04d}-{now.tm_mon:02d}-{now.tm_mday:02d}T{now.tm_hour:02d}:{now.tm_min:02d}:{now.tm_sec:02d}Z'
        return 'INVALID INPUT [function: randomValueExamples.get(<string>)]'


def randomizeComponentsExamples(components: list) -> list:
    """randomize uuid and aid values for components examples"""
    re_uuid = re.compile('[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}')
    re_aid = re.compile('A[0-9]{14}C[01]{4}')
    for num, component in enumerate(components):
        results = re_uuid.findall(component)
        for j in results:
            components[num] = component.replace(j, RandomValueExamples.get('uuid'))
        results = re_aid.findall(component)
        for j in results:
            components[num] = component.replace(j, RandomValueExamples.get('aid'))
    return components


def binaryFromString(val: str) -> str:
    """binary representation of string"""
    return_value = ''
    for num in val:
        return_value += f'{ord(num):08b}'
    return return_value


### fixed value functions ###


class FixedValueExamples:
    """
    definition of FixedValueExamples Class
    """

    exampleListUUID = [
        # paths
        '62d2a5d8-f591-f9ec-32b3-558047c576a7',  # example, service-sposConfigId
        'c207b03f-75c5-c5ff-2460-fe6cec8ab803',  # example, version-allowList [0]
        'ca85a700-9b54-e89a-8b3f-60296b75c26f',  # example, version-allowList [1]
        '553e809b-5610-53f0-ed91-5a12fa0e65ac',  # example, version-allowList [2]
        '54050ff0-7d9e-ef28-b120-3c3e81ae77af',  # example, flavor-elfID [0]
        '7860e9a9-70b3-43b1-b2d7-fc6199c92cb4',  # example, flavor-elfID [1]
        '2b3447ca-eecc-eba9-3f48-2f61ae43c742',  # example, flavor-elfID [2]
        '40b455b5-1e45-fd2f-319b-83794b2a8d82',  # example, flavor-appInstaConfig [0]
        '1e555fae-c0bb-9915-b086-b7f77a52ca69',  # example, flavor-appInstaConfig [1]
        'efe37b02-819a-9dc1-f656-b00b80072581',  # example, flavor-appInstaConfig [2]
        '9b2990c9-59b0-a979-d4b2-11ff5fe00cb2',  # example, flavor-appInstaConfig [3]
        '105030db-8fd7-095f-953e-a4143a3576c3',  # example, flavor-appInstaConfig [4]
        'b3f08e86-ebed-1a72-984d-8030cbe89b79',  # example, flavor-appInstaConfig [5]
        'b9ee54ed-b5dc-61c5-7e9f-01beea073f92',  # example, emID
        '5d9ecaf4-e832-4c96-833b-054546a56c21',  # example, appConfigID
        '7a06adf4-87c3-bdda-047d-bb727813329c',  # example, personalizationConfig-certID
        'a8369519-ed68-0181-e7e6-13471beaa418',  # example, personalizationConfig-persoScriptID
        '652f7175-d33a-0616-cf61-0451f2587361',  # example, sposConfig-certID
        # parameters
        'bc675568-34ac-40b0-abc0-03929b2d5ccd',
        'e59d3d32-6756-76ac-ffff-feeba10c081a',
        'ff686d37-4674-1456-26fa-c9ed658bb41c',
        'c73e8ddc-0318-7940-dc7d-ae5bab5ce549',
        '4079f402-4e86-b4b0-0c4f-a21681ece9c8',
        '615c1678-895b-ff21-7c6c-bcd9fed11b84',
        #'a0f59df9-7ee2-161f-9325-46e1ecc0ecae',
        '5bd3cb36-3688-e41b-45b4-24abb956e2c5',
        'ffe6404d-0589-e66c-f837-3e89cfe3d19a',
        'f071b74c-ea66-b415-9a66-4ba06c637d13',
        '6c4a3aa8-5240-ad98-4673-a820be2df3b0',
        # schemas
        'fde02274-914f-b360-c679-74970a9d6cef',
        'de10f784-d526-618f-443f-2a98cb2c9893',
        '167f43e0-2ada-d0d0-ba18-b23fd6accd18',
        'bb5c8384-f856-ca00-2b52-bcf9f45e1f39',
        '39fb1716-4a34-87ab-18be-8ee81e7f2d67',
        'bb451440-fdc1-1983-43f2-df17104a4715',
        'fd4aad1f-50f8-1534-aeab-652bafa65604',
        '96f3baa5-d50c-84e4-b285-07b679aa6953',
        '69c1eede-6767-ddbc-6c84-3903126e51b4',
        'a38d4f8a-a4d5-d5c9-b134-0657c74a9853',
        '26dffd3f-59fd-80b3-bc31-29dca2795831',
        '47db1f00-d894-af9a-a27b-1303d61fa3f4',
        '8fa10eff-19a2-7c4b-1102-2e8b867d3fda',
        '8339dcd0-b9e1-6b73-b5f0-33793d4e2307',
        '060dc3ac-71dd-3bae-e00a-a8fe85ceb022',
        '2355d041-c1c6-4ab1-e0d2-a15a41e85351',
        'f36d5f2e-0950-6e80-6367-fe7e6250a713',
        '6cda80a1-efb3-acf3-8b18-14a10ff2ad24',
        # extension
        '06475326-cda5-a009-d342-c01dbff19904',
        '6b95cefe-7ccc-d1e1-b267-9d12481a6f8b',
        '31f648fc-8b6c-3767-7907-6f9464e77461',
        'abbacd23-a58f-d34d-8c55-729ff40dee26',
        'aef323a1-4665-4679-60e3-a555141dd321',
        'bd9a86f0-16ee-8a46-2524-121035f7e006',
        '74aeba88-3554-586a-7545-d7956e9b10cf',
        '05a7b798-00f7-11a4-1cad-0b6cbf17e82e',
        '3302d2b0-e4d4-cb2f-af41-7f8003aee48e',
        '105030db-8fd7-095f-953e-a4143a3576c3',
        'b3f08e86-ebed-1a72-984d-8030cbe89b79',
        'e5f8f333-3e95-83e7-b5d7-766428757560',
        '5dc9a676-847d-2606-bc15-fc8eea036be0',
        '00f5cc8a-f5c7-3929-682f-8eef5972f55a',
    ]

    exampleListAID = [
        # paths
        'A00000021784013C0110',  # example, service-accessAuthor [0]
        'A00000007220548C0001',  # example, service-accessAuthor [1]
        'A00000033467106C1111',  # example, service-accessAuthor [2]
        'A00000089316631C0000',  # example, appConfig-instanceAid
        # 'A00000095083184C0011',
        # 'A00000067684050C0010',
        # 'A00000035863789C0110',
        # 'A00000018627173C0001',
        # parameters
        # schemas
        'A00000025128620C1110',
        'A00000039998960C1111',
        'A00000086243583C0111',
        'A00000052220986C0110',
        'A00000030027697C0110',
        'A00000065553856C1001',
        'A00000037797423C1100',
        # extension
        'A00000033751152C1111',
        'A00000092116968C1001',
        'A00000094437147C0010',
        'A00000057969655C1011',
        'A00000055584583C0000',
        'A00000069074348C0111',
        'A00000099469262C1010',
        'A00000058380060C1000',
        'A00000070392748C0100',
        'A00000093355473C1001',
        'A00000058460556C0001',
        'A00000088804730C1000',
        'A00000059455419C0010',
        'A00000090977727C1100',
        'A00000049270775C1011',
        'A00000067470160C0110',
        'A00000047052323C1111',
        'A00000063699648C1011',
        'A00000062627072C1001',
        'A00000030923268C1000',
        'A00000023417222C1011',
        'A00000058998654C0100',
    ]

    exampleListServiceName = [
        'authDeviceByBNE',
        'openDeviceBySecNat',
        'payTokenByJtol',
        'bookHotelByJtol',
        'openTokenByJCN',
        'authHotelBySecNat',
        'openTokenByBNE',
        'openTokenByBNE',
        'authAppBySecNat',
        'payRentalByBNE',
        'payTokenByJCN',
        'authTokenByJCN',
        'openRentalBySecNat',
        'openDeviceByJtol',
        'authHomeByBNE',
        'authDeviceByJCN',
        'openHomeBySecNat',
        'payRentalBySecNat',
        'authDeviceByBNE',
        'authRentalByBNE',
        'authRentalBySecNat',
        'bookHomeByJCN',
        'payHomeByJCN',
        'openHotelByBNE',
        'bookRentalByJCN',
        'openRentalBySecNat',
        'authTokenByJCN',
        'openHotelByBNE',
        'authHotelByJtol',
        'openHotelByJtol',
    ]

    exampleListFlavorName = [
        'YY-624B-11',  # example, flavor-name
        'XX-414D-31',  # example, elf-name
        'XX-413D-58',  # example, appConfig-name
        'YX-471D-01',  # example, persoScript-name
        'XX-159D-47',  # example, cert-name
        'YY-688D-47',
        'XX-364B-04',
        'XX-015D-02',
        'YX-392A-37',
        'YX-516B-26',
        'XY-104A-65',
        'YY-082C-27',
        'XX-329B-27',
        'YY-979A-92',
        'XY-743B-96',
        'YY-673C-24',
        'XX-579D-90',
        'YY-762C-79',
        'XY-829A-89',
        'XY-483B-41',
        'YY-448D-60',
        'XX-635D-53',
        'XX-489A-84',
        'XX-728B-71',
        'YY-488B-43',
        'YY-891C-56',
        'YY-023B-46',
        'YY-950D-10',
        'XX-416C-86',
        'XX-980A-56',
    ]

    exampleListVersion = [
        # paths
        #'5.23.124', # example, version-tag
        # parameters
        '4.31.005',
        # schemas
        '3.1.4',
        '3.0.5',
        '2.3.1',
        '8.00.008',
        '7.11.116',
        '1.13.008',
        '5.41.028',
        '5.25.102',
        '9.52.110',
        '3.08.129',
        '9.20.070',
        '7.18.018',
        '6.33.040',
        '1.42.095',
        '7.33.050',
        '9.24.040',
        '5.14.065',
        '2.02.021',
        '1.37.028',
        # extension
        '7.03.021',
        '9.06.087',
        '3.04.010',
        '6.42.071',
        '8.45.085',
        '0.00.087',
        '4.50.013',
        '1.26.082',
        '4.53.104',
        '2.05.046',
        '6.13.048',
        '1.15.042',
        '3.12.087',
    ]

    exampleListVersionMM = [
        # paths
        # parameters
        # schemas
        '1.7',
        '5.12',
        '2.2',
        '1.2',
        '3.1',
        # extension
        '7.54',
        '6.23',
        '0.48',
        '4.21',
        '6.15',
        '8.20',
        '3.22',
        '2.08',
        '9.44',
        '2.16',
        '6.23',
        '6.27',
    ]

    exampleListTLV = [
        'f5 09 5e 0f d2 68 12 d4 7f f3 d5',  # example, installConfig-appSpecificationInstallParam
        '0f 09 e6 ea e9 06 8d 89 19 a4 8a',
        '2c 08 d7 af 24 8c 75 50 3c 2d',
        'aa 08 1e 0e f8 25 69 3a 03 83',
        '55 08 f2 59 73 b4 90 e8 b5 60',
        'ab 09 f2 56 cd eb 84 05 22 36 5d',
        '25 08 e3 9a 82 ec e0 5a 5c eb',
        '33 07 ee f1 23 a4 de bb f4',
        '3a 08 ba 8c 58 4e 38 98 74 05',
        'ad 07 9f 60 89 fe 44 a1 1a',
        'dc 08 15 3c 5e 9c 7c 44 49 35',
        '65 09 f8 dc 63 b4 9c 57 b1 b5 e9',
        '06 09 17 d5 d3 64 cf 56 1c da af',
        '70 08 2f 6d ea 5f c6 aa 79 c1',
        '83 08 b8 2c 6d 16 39 b0 c7 9e',
        'b7 09 db ab bc 68 d1 db e2 fd 45',
        '4a 07 0a 33 f9 c5 d0 21 2a',
        '9a 09 36 7c cf bf 63 3b f2 ef a7',
        'ef 07 01 be 06 a0 2d 65 ef',
        '94 07 5b 32 58 f3 f3 f2 35',
        'd1 08 cf 12 c2 61 5d b7 8a a3',
        'c5 08 15 f4 e8 a7 34 51 ce 8f',
        'e5 09 b4 b6 e7 97 b7 13 d3 ed 30',
        '7a 07 0a 3a cb 24 86 98 b3',
        '03 09 eb 02 af fb 67 44 7d 6a d0',
        '14 08 2f c5 58 af 48 b2 2d 32',
        'c2 08 7d 96 56 01 6d 5b a6 12',
        'ff 08 eb bd a0 de 7b 7c 00 63',
        'b7 09 af 49 67 d5 ac 4b ed 93 bc',
        '35 09 c9 b3 1c fd 9f 7f 42 4d 3e',
    ]

    exampleListINT = [
        '137',  # example, appInstaConfig [0]
        '81',  # example, appInstaConfig [1]
        '132',  # example, appInstaConfig [2]
        '131',
        '184',
        '245',
        '227',
        '172',
        '58',
        '12',
        '78',
        '32',
        '208',
        '251',
        '41',
        '102',
        '76',
        '127',
        '114',
        '149',
        '14',
        '64',
        '32',
        '57',
        '217',
        '78',
        '193',
        '119',
        '121',
        '189',
    ]

    boolean = True

    # counter for all elements
    counter = [0] * 10

    @classmethod
    def reset(cls) -> None:
        """interface function to reset internal counter and be able to reproduce original behaviour"""
        cls.counter = [0] * 10
        cls.boolean = True

    @classmethod
    def inc(cls, num: int) -> int:
        """internal function to increase counter"""
        cls.counter[num] += 1
        return cls.counter[num] - 1

    @classmethod
    def get(
        cls, example_type: str, lower=0, upper=256
    ) -> str:  # pylint: disable=unused-argument
        """interface function to receive fixed example values"""
        if example_type == 'uuid':
            return cls.exampleListUUID[cls.inc(0)]
        if example_type == 'aid':
            return cls.exampleListAID[cls.inc(1)]
        if example_type == 'service_name':
            return cls.exampleListServiceName[cls.inc(2)]
        if example_type == 'flavor_name':
            return cls.exampleListFlavorName[cls.inc(3)]
        if example_type == 'version':
            return cls.exampleListVersion[cls.inc(4)]
        if example_type == 'versionMM':
            return cls.exampleListVersionMM[cls.inc(5)]
        if example_type == 'tlv':
            return cls.exampleListTLV[cls.inc(6)]
        if example_type == 'uri':
            return RandomValueExamples.get('uri')
        if example_type == 'int':
            value = cls.exampleListINT[cls.inc(8)]
            while not lower <= int(value) <= upper:
                value = cls.exampleListINT[cls.inc(8)]
            return value
        if example_type == 'bool':
            cls.boolean = not cls.boolean
            return str(cls.boolean).lower()
        if example_type == 'date-time':
            now = time.localtime(time.time())
            return f'{now.tm_year:04d}-{now.tm_mon:02d}-{now.tm_mday:02d}T{now.tm_hour:02d}:{now.tm_min:02d}:{now.tm_sec:02d}Z'
        return 'INVALID INPUT [function: fixedValueExamples.get(<string>)]'


# test routine for development purposes
if __name__ == '__main__':
    example_types = [
        'uuid',
        'aid',
        'service_name',
        'flavor_name',
        'version',
        'versionMM',
        'tlv',
        'uri',
        'int',
        'bool',
        'date-time',
        'new_type',
    ]
    for t in example_types:
        for i in range(10):
            print(RandomValueExamples.get(t))
        print(' ')
    for t in example_types:
        for i in range(5):
            print(FixedValueExamples.get(t))
        print(' ')

    print(binaryFromString('ELF-File Content'))
    print(binaryFromString('Script-File Content'))
    print(binaryFromString('Cert-File Content'))
