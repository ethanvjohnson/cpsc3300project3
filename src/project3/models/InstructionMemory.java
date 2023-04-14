package project3.models;

import java.io.*;
import java.lang.StringBuilder;

/**
 * Takes the input file as input and outputs the instruction in Instruction
 * class
 */
public class InstructionMemory {
    private String data;
    private int numInstructions;

    public InstructionMemory(String fileName) throws Exception {
        InputStream inputStream = new FileInputStream(fileName);
        long fileSize = new File(fileName).length();

        // Read binary file data
        byte[] fileData = new byte[(int) fileSize];

        inputStream.read(fileData);

        // Convert binary data into "binary" string
        // (print statements used for testing only)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (int) fileSize; i++) {
            sb.append(binToString(fileData[i]));
        }

        // Store the converted data
        data = sb.toString();
        numInstructions = data.length();
        System.out.println(numInstructions);
        inputStream.close();
    }

    // takes the address given by the PC and converts it into one of our instruction
    // classes
    public Instruction getInstruction(int address) {
        Instruction ins;
        // address * 8 because bytes to bits conversion
        String instruction = data.substring((address * 8), (address * 8) + 32);

        String opcode = instruction.substring(0, 6);
        switch (opcode) {
            // Valid Instruction
            case "000000":
            case "000100":
            case "100010":
            case "101011":
            case "000010":
                ins = new Instruction(instruction);
                break;
            // Invalid Instruction (code should never reach here)
            default:
                ins = new Instruction(instruction);
                System.err.println("Error: opcode not recognized\n");
                break;

        }
        return ins;
    }

    // Convert from binary byte to string
    private String binToString(byte bin) {
        int len = 8;
        char[] str = { '0', '0', '0', '0', '0', '0', '0', '0' };
        String result;

        if (bin == 0) {
            result = new String(str);
            return result;
        } else if (bin > 0) {
            // ordinary binary conversion
            for (int i = 0; i < len; i++) {
                if (bin % 2 == 0) {
                    str[len - i - 1] = '0';
                } else {
                    str[len - i - 1] = '1';
                }
                bin /= 2;
            }
        } else {
            // 2s complement negative conversion
            bin *= -1;
            Boolean flip = false;

            for (int i = 0; i < len; i++) {
                if (flip) {
                    if (bin % 2 == 0) {
                        str[len - i - 1] = '1';
                    } else {
                        str[len - i - 1] = '0';
                    }
                } else {
                    if (bin % 2 == 0) {
                        str[len - i - 1] = '0';
                    } else {
                        str[len - i - 1] = '1';
                    }
                }

                if (bin % 2 == 1) {
                    flip = true;
                }

                bin /= 2;
            }
        }

        result = new String(str);
        return result;
    }
}
