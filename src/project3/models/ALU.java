package project3.models;

/* 
 * Takes 3 inputs:
 *     2 registers and a ALU Control input,
 * 
 * Outputs the result of the arithmetic operation and a zero output
 */
public class ALU {
    private boolean zero;

    public ALU() {
        zero = false;
    }

    public int arithmetic(int reg1, int reg2, int ALUControlInput) {
        int result = 0;
        switch(ALUControlInput) {
            case 0b0000: // AND
                result = reg1 & reg2;
                break;
            case 0b0001: // OR
                result = reg1 | reg2;
                break;
            case 0b0010: // add
                result = reg1 + reg2;
                break;
            case 0b0110: // subtract
                result = reg1 - reg2;
                break;
            case 0b0111: // set on less than
                if (reg1 < reg2) result = 1;
                else result = 0;
                break;
            default:
                System.err.println("ERROR. NOT VALID ALUOp");
        }

        if(result == 0) this.zero = true;
        else this.zero = false;
        return result;
    }

    public boolean getZero() { return this.zero; }
}
