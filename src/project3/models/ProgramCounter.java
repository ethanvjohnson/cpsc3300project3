package project3.models;

import project3.views.Scoreboard;

/* Pretty minimal class. Only really needs to store the address of the next 
 * instruction. 
 * 
 * We could add some of the math/logic for calculating the PC value here,
 * but that is usually done in hardware + ALU which can be considered the
 * controller in this project. 
 */
public class ProgramCounter {
    // address is in bytes
    private int address;
    Scoreboard view;

    public ProgramCounter(Scoreboard view) {
        address = 0x0;
        this.view = view;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
        view.updatePC(address);
    }

}
