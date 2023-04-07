package project3.models;

public class JTypeInstruction implements Instruction{
    private String opcode;
    private int address;

    public JTypeInstruction(String ins){
        opcode = ins.substring(0, 6);
        address = binaryStringToInt(ins.substring(16, 32));
    }

    public int getAddress(){
        return address;
    }
    
    @Override
    public String getOpcode() {
        return opcode;
    }

    @Override
    public int binaryStringToInt(String binaryString){
        return Integer.parseInt(binaryString, 2);
    }
}