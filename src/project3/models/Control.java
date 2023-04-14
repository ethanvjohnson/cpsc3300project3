package project3.models;

public class Control {
    // Control Signals
    private boolean regDst;
    private boolean regWrite;
    private boolean ALUSrc;
    private boolean PCSrc;
    private boolean memRead;
    private boolean MemWrite;
    private boolean memtoReg;
    private boolean branch;
    // ALUOp can be considered a 2 bit control signal 01 = (p1=0, p0=01)
    private boolean ALUOp1;
    private boolean ALUOp0;
    private boolean jump;

    public Control(){
        regDst = false;
        regWrite = false;
        ALUSrc = false;
        PCSrc = false;
        memRead = false;
        MemWrite = false;
        memtoReg = false;
        branch = false;
        ALUOp1 = false;
        ALUOp0 = false;
        jump = false;
    }


    public void opcodeDecode(String opcodeString){
        switch (opcodeString) {
            // arithmetic/R-format
            case "000000":
                regDst = true;
                regWrite = true;
                ALUOp1 = true;
                break;
            // j
            case "000010": 
                jump = true; 
                break;
            // beq
            case "000100": 
                branch = true;
                ALUOp0 = true;
                break;
            // lw
            case "100010": 
                ALUSrc = true;
                memtoReg = true;
                regWrite = true;
                memRead = true;
                break;
            // sw
            case "101011": 
                ALUSrc = true;
                MemWrite = true;
                break;                                
        }
    }

    public void reset(){
        regDst = false;
        regWrite = false;
        ALUSrc = false;
        PCSrc = false;
        memRead = false;
        MemWrite = false;
        memtoReg = false;
        branch = false;
        ALUOp1 = false;
        ALUOp0 = false;
        jump = false;
    }

    // getter functions
    public boolean getRegDst() { return this.regDst; }
    public boolean getRegWrite() { return this.regWrite; }
    public boolean getALUSrc() { return this.ALUSrc; }
    public boolean getPCSrc() { return this.PCSrc; }
    public boolean getMemRead() { return this.memRead; }
    public boolean getMemWrite() { return this.MemWrite; }
    public boolean getMemtoReg() { return this.memtoReg; }
    public boolean getBranch() { return this.branch; }
    public boolean getALUOp1() { return this.ALUOp1; }  
    public boolean getALUOp0() { return this.ALUOp0; }
    public boolean getJump() { return this.jump; }
}
