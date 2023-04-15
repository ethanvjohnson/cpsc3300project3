package project3.models;

import java.util.Random;
/* 
 * Kind of a weird unit to have as we have to emulate memory but we do not
 * actually have data in this memory beforehand.
 * 
 * I think a simple solution is a dictonary of key:value pairs
 * where the key is the address and the value is the data
 * being stored. That way we can access values we have inputted and overwrite
 * and such.
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

        Random rand = new Random();
        memory = new int[DATASIZE];

        // populate 4kb of memory with random values
        for (int i = 0; i < DATASIZE; i++) {
            addData(i, rand.nextInt(255));
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
