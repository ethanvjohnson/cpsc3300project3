package project3.models;

public class Instruction {
    private String opcode;
    private int rs;
    private int rt;
    private int rd;
    private int imm;
    private short address;
    private String shamt;
    private String funct;

    public Instruction() {

    }

    public Instruction(String ins) {
        opcode = ins.substring(0, 6);
        rs = binaryStringToInt(ins.substring(6, 11));
        rt = binaryStringToInt(ins.substring(11, 16));
        rd = binaryStringToInt(ins.substring(16, 21));
        imm = binaryStringToInt(ins.substring(16, 32));
        address = binaryStringToShort(ins.substring(16, 32));
        shamt = ins.substring(21, 26);
        funct = ins.substring(26, 32);
    }

    public int getRs() {
        return rs;
    }

    public int getRt() {
        return rt;
    }

    public int getRd() {
        return rd;
    }

    public int getImm() {
        return imm;
    }

    public int getAddress() {
        return address;
    }

    public String getShamt() {
        return shamt;
    }

    public String getFunct() {
        return funct;
    }

    public String getOpcode() {
        return opcode;
    }

    public int binaryStringToInt(String binaryString) {
        return Integer.parseInt(binaryString, 2);
    }

    public short binaryStringToShort(String binaryString) {
        return Short.parseShort(binaryString, 2);
    }
}