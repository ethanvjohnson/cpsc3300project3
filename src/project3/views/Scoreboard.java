package project3.views;

import project3.controllers.*;

public class Scoreboard {
    Controller controller;
    int pc;
    int cycles;
    // ORDER: and, or, add, sub, slt
    int[] aluOps;
    int[] regs;
    int[] memory;
    int memReads;
    int memWrites;
    int instructionNum;

    public Scoreboard() {
        pc = 0;
        aluOps = new int[5];
        regs = new int[32];
        regs[0] = 0;
        memory = new int[4096];
    }

    public void setController(Controller c) {
        controller = c;
    }

    public void displayScoreboard() {
        System.out.println("===============  SCOREBOARD  ===============");
        System.out.println("Current Instruction = " + instructionNum);

        System.out.println("Cycles = " + cycles);
        System.out.println("PC = " + String.format("0x%08X", pc));

        // print out all the ALU Operations
        System.out.println();
        System.out.println("NUMBER OF TOTAL ALU OPERATIONS");
        System.out.printf("AND = %d, OR = %d, Add = %d, sub = %d, slt = %d%n",
                aluOps[0], aluOps[1], aluOps[2], aluOps[3], aluOps[4]);

        // print out all the memory operations
        System.out.println();
        System.out.println("NUMBER OF TOTAL MEMORY OPERATIONS");
        System.out.printf("Memory Reads = %d%n", memReads);
        System.out.printf("Memory Writes = %d%n", memWrites);

        // print out contents of all the registers
        System.out.println();
        System.out.println("CONTENT OF REGISTERS");
        for (int i = 0; i < 32; i++) {
            System.out.printf("Reg%02d = %03d, ", i, regs[i]);
            if ((i + 1) % 8 == 0)
                System.out.println();
        }

        // print out memory of all the memory
        System.out.println();
        System.out.println("CONTENT OF MEMORY");
        for (int i = 0; i < 4096; i++) {
            if ((i % 32) == 0) {
                if (i != 0) {
                    System.out.println();
                }
                System.out.printf("%08X: ", i);
            }
            System.out.printf("%02X ", memory[i]);
        }
        System.out.println();
        System.out.println("============================================");
    }

    public void updatePC(int address) {
        pc = address;
    }

    public void updateCycles() {
        cycles += 1;
    }

    public void updateAluOps(int type) {
        aluOps[type] += 1;
    }

    public void updateRegs(int regNum, int regData) {
        regs[regNum] = regData;
    }

    public void updateMemory(int address, int memData) {
        memory[address] = memData;
    }

    public void updateMemReads() {
        memReads += 1;
    }

    public void updateMemWrites() {
        memWrites += 1;
    }

    public void updateInstructionNum(int insNum) {
        instructionNum = insNum;
    }
}

/*
 * # Cycles:
 * 
 * ALU Operations:
 * # Add:
 * # Sub:
 * # AND:
 * # OR:
 * # slt:
 * 
 * Memory:
 * # Memory Reads:
 * # Memory Writes:
 * 
 */

/*
 * View" - at least one "text" view that will display a "scoreboard" that shows
 * the
 * contents of PC, registers, and memory; also of logic block statistics - they
 * should use the
 * observer pattern to implement this. Note: The view is expected to update
 * every cycle.
 * For the text view, that means it should print the scoreboard on every cycle.
 * 5. You should track # cycles for a given program. Also, you should track ALU
 * arithmetic
 * operations (how many add, sub, etc ops). Note: Some instructions besides
 * "add" use
 * add. example is beq; this counts as an ALU arithmetic op, incrementing the PC
 * does not.
 * 6. You should track # of memory reads/writes too. Note that for simplicity,
 * you don't need
 * to implement separate instruction and data memories illustrated in textbook
 * 4.4. You just need one memory that contains instructions and data together.
 * 7. The control should track the # of each individual instructio
 */