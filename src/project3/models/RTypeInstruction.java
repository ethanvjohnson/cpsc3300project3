package project3.models;

public class RTypeInstruction implements Instruction{
    private String opcode;
    private int rs;
    private int rt;
    private int rd;
    private String shamt;
    private String funct;

    public RTypeInstruction(String ins){
        opcode = ins.substring(0, 6);
        rs = binaryStringToInt(ins.substring(6, 11));
        rt = binaryStringToInt(ins.substring(11, 16));
        rd = binaryStringToInt(ins.substring(16, 21));
        shamt = ins.substring(21, 26);
        funct = ins.substring(26, 32);
    }

    public int getRs(){
        return rs;
    }
    public int getRt(){
        return rt;
    }
    public int getRd(){
        return rd;
    }
    public String getShamt() {
        return shamt;
    }
    public String getFunct(){
        return funct;
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