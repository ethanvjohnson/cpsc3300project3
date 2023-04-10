package project3.models;

/* 
 * Needs to generate a ALU control signal based on the inputs from the
 * control and the funct fields of the instruction
 * 
 * Outputs a 4 bit control signal that indicates to the ALU which arithmetic
 * operation to perform
 */
public class ALUControl {
    // can be a binary literal i.e. 0b0010 = "add"
    // Then have an enum or switch in the ALU to translate
    // int/binary to instruction
    private int ALUInput;

    public ALUControl() {
        ALUInput = 0;
    }

    // creates the ALU control input but does not return it
    public void createALUInput(String funct, Control control) {
        // transform control input into int for easier consumption in switch
        int ALUOp;
        if(control.getALUOp1() == true && control.getALUOp0() == true) {
            ALUOp = 0b11;
        } else if(control.getALUOp1() == true) {
            ALUOp = 0b10;
        } else if(control.getALUOp0() == true) {
            ALUOp = 0b01;
        } else ALUOp = 0b00;

        // use switch to hardcode ALUInput
        switch(ALUOp) {
            case 0b00:
                ALUInput = 0b0010; // add
                break;
            case 0b01:
                ALUInput = 0b0110; // sub
                break;
            case 0b11:
                // another switch statement to differentiate R-type funct
                switch(funct) {
                    case "100000":
                        ALUInput = 0b0010; // add
                        break;
                    case "100010":
                        ALUInput = 0b0110; // sub
                        break;
                    case "100100":
                        ALUInput = 0b0000; // and
                        break;
                    case "100101":
                        ALUInput = 0b0001; // or
                        break;
                    case "101010":
                        ALUInput = 0b0111; // set on less than
                        break;
                }
                break;
            default:
                System.err.println("ERROR. NOT VALID ALUOp");
        }
    }

    public int getALUInput() {
        return ALUInput;
    }

    public void reset() {
        ALUInput = 0;
    }
}
