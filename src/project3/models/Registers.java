package project3.models;

/* 
 * MIPS has 32 registers, each one 32 bits. Decided on using an array
 * as that is a very simple implementation and can be accessed via index
 * i.e. register 3 is just array[3]
 */
public class Registers {
    private int[] registers;

    // constructor
    public Registers(){
        registers = new int[32];
        registers[0] = 0;
    }

    public void setRegister(int regNum, int value, boolean regWrite) {
        if(regWrite) registers[regNum] = value;
        // else, do nothing
    }

    public int getRegister(int regNum) {
        return registers[regNum];
    }
}
