package project3.models;
import java.io.*;

/** 
 * Takes the input file as input and outputs the instruction in Instruction class
 */
public class InstructionMemory {
    private String data;
    
    public InstructionMemory(String fileName) throws Exception {
        FileReader inputFile = new FileReader(fileName);

        // Read the binary file data 
        BufferedReader buffer = new BufferedReader(inputFile);
        data = buffer.readLine();
        buffer.close();
    }

    // takes the address given by the PC and converts it into one of our instruction classes
    public Instruction getInstruction(int address) {
        Instruction ins;
        // address * 8 because bytes to bits conversion
        String instruction = data.substring((address*8), (address*8)+32);

        String opcode = instruction.substring(0, 6);
        switch(opcode) {
            // R-type
            case "000000":
                ins = new RTypeInstruction(instruction);
                break;
            // I-type
            case "000100":
            case "100010":
            case "101011":
                ins = new ITypeInstruction(instruction);
                break;
            // J-type
            case "000010":
                ins = new JTypeInstruction(instruction);
                break;
            // code should never reach here
            default:
                ins = new RTypeInstruction(instruction);
                System.err.println("Error: opcode not recognized\n");
                break;
            
        }
        return ins;
    }
}

