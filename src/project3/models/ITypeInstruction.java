package project3.models;

public class ITypeInstruction implements Instruction{
    private String opcode;
    private int rs;
    private int rt;
    private int imm;

    public ITypeInstruction(String ins){
        opcode = ins.substring(0, 6);
        rs = binaryStringToInt(ins.substring(6, 11));
        rt = binaryStringToInt(ins.substring(11, 16));
        imm = binaryStringToInt(ins.substring(16, 32));
    }

    public int getRs(){
        return rs;
    }
    public int getRt(){
        return rt;
    }
    public int getImm(){
        return imm;
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