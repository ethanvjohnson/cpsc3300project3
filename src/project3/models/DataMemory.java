package project3.models;

import java.util.HashMap; // import the HashMap class
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

public class DataMemory {
    private HashMap<Integer, Integer> memory;

    // constructor
    public DataMemory() {
        Random rand = new Random();
        memory = new HashMap<Integer, Integer>();

        // populate 4kb of memory with random values
        for (int i = 0; i < 4096; i++) {
            memory.put(i, rand.nextInt(255));
        }
    }

    // other functions
    public void addData(int address, int data) {
        memory.put(address, data);
    }

    public int getData(int address) {
        return memory.get(address);
    }

    public void resetData() {
        memory.clear();
    }
}
