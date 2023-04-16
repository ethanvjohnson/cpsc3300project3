package project3.models;

/* 
 * Kind of a weird unit to have as we have to emulate memory but we do not
 * actually have data in this memory beforehand.
 * 
 * 
 * The dictonary class is obsolete in Java and HashMap is now used. They
 * are both dictonary / map / associative array data structure and the naming is
 * purely semantic in our case.
 */

import project3.views.Scoreboard;

public class DataMemory {
    private int[] memory;
    // size of the memory block, in this case 4kb
    private static int DATASIZE = 4096;
    Scoreboard view;

    // constructor
    public DataMemory(Scoreboard view) {
        this.view = view;

        memory = new int[DATASIZE];

        // populate 4kb of memory with random values
        for (int i = 0; i < DATASIZE; i++) {
            memory[i] = (i % 256);
            view.updateMemory(i, (i % 256));
        }
    }

    // other functions
    public void addData(int address, int data) {
        memory[address] = data;
        view.updateMemory(address, data);
        view.updateMemWrites();

    }

    public int getData(int address) {
        view.updateMemReads();
        return memory[address];
    }
}
