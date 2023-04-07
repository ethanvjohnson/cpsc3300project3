#!/usr/bin/env python3

import sys
import re

def read_reg(str):
    num_str = re.findall(r'-?\d+', str)
    # converts strings into ints
    num_int = [int(num) for num in num_str]
    # returns a list for I-type instructions, req. list syntax in a few weird places
    return num_int

def twos_complement (value, bitLength):
    return bin(value & (2**bitLength - 1))[2:]

def bin_format(str):
    b_string = bin(int(str, 2))[2:]
    b_string = b_string.zfill(32)
    return b_string

def bitstring_to_bytes(s):
    return int(s, 2).to_bytes((len(s) + 7) // 8, byteorder='big')



# LEGAL_INSTRUCTIONS but L_I for brevity
L_I = ['lw', 'sw', 'add', 'sub', 'and', 'or', 'slt', 'beq', 'j']
OPCODES = [34, 43, 0, 0, 0, 0, 0, 4, 2]
FUNCT = [0, 0, 32, 34, 36, 37, 42]

# open file and read into string to perform string manip
assert(len(sys.argv) == 3)
input_file = sys.argv[1]
output_file = sys.argv[2]

ifp = open(input_file, "r")
ofp = open(output_file, "wb")

contents = ifp.read()

# perform string manip
# split into list of strings; each line a string
instructions = contents.splitlines()

line = 0
for instruction in instructions:
    line += 1
    b_string = ''
    # split into individual words/args
    ilist = instruction.split()
    # check to make sure instruction is correctly formatted
 
    if ilist[0] in L_I:
        # instruction "type" according to my lists
        itype = L_I.index(ilist[0])

        # if R-type
        if(itype in [2, 3, 4, 5, 6]):
            opcode = OPCODES[itype]
            b_string += format(opcode, '06b')

            # read registers 1-3, check for errors, append to b_string
            error_flag = 0
            reg_list = []
            for i in range(1, 4):
                reg_num = read_reg(ilist[i])
                reg_list.append(reg_num[0])

                if(reg_num[0] > 32 or reg_num[0] < 0):
                    print("Error found in line " + str(line) + 
                          ". Register is out of range\n")
                    error_flag = 1


            # due to the disparity of reg order bwtn human-readable and binary
            b_string += format(reg_list[1], '05b')
            b_string += format(reg_list[2], '05b')
            b_string += format(reg_list[0], '05b')

            if error_flag == 1:
                continue
            
            # shamt
            b_string += '00000'

            funct = FUNCT[itype]
            b_string += format(funct, '06b')

            
            # print(bin_format(b_string))
            ofp.write(bitstring_to_bytes(bin_format(b_string)))

        # if I-type 
        elif(itype in [0, 1, 7]):
            opcode = OPCODES[itype]
            b_string += format(opcode, '06b')

            # lw, sw
            if(itype != 7):
                reg_rt = read_reg(ilist[1])

                temp = read_reg(ilist[2])
                immediate = temp[0]
                reg_rs = temp[1]

                b_string += format(reg_rs, '05b')
                b_string += format(reg_rt[0], '05b')
                b_string += format(immediate, '016b')

            # beq
            else:
                # read registers 1 and 2, check for errors, append to b_string
                reg_rs = read_reg(ilist[1])
                reg_rt = read_reg(ilist[2])
                immediate = read_reg(ilist[3])
                immediate = twos_complement(immediate[0], 16)

                # register error checking
                if((reg_rs[0] > 32 or reg_rs[0] < 0) or
                   (reg_rt[0] > 32 or reg_rt[0] < 0)):
                    print("Error found in line " + str(line) + 
                            ". Register is out of range\n")
                    continue

                b_string += format(reg_rs[0], '05b')
                b_string += format(reg_rt[0], '05b')
                # b_string += format(immediate, '016b')
                b_string += immediate



            # print(bin_format(b_string))
            ofp.write(bitstring_to_bytes(bin_format(b_string)))

        # if J-type
        elif(itype == 8):
            opcode = OPCODES[itype]
            b_string += format(opcode, '06b')

            # get the address
            address = read_reg(ilist[1])
            address = twos_complement(address[0], 26)
            b_string += address

            # print(bin_format(b_string))
            ofp.write(bitstring_to_bytes(bin_format(b_string)))
        
    else:
        print("Error found in line " + str(line) + ". Unsupported instruction\n")


ifp.close()
ofp.close()
