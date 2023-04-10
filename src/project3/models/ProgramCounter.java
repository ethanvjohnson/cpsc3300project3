package project3.models;

/* Pretty minimal class. Only really needs to store the address of the next 
 * instruction. 
 * 
 * We could add some of the math/logic for calculating the PC value here,
 * but that is usually done in hardware + ALU which can be considered the
 * controller in this project. 
 */
public class ProgramCounter {
    private int address;

    public ProgramCounter(){
        address = 0x0;
    }

    public int getAddress() {
        return address;
    }
    public void setAddress(int address) {
        this.address = address;
    }
}
