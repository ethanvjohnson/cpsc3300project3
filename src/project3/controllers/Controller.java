package project3.controllers;

import project3.views.*;
import project3.models.*;

/* 
 *                          HIGH LEVEL PSEUDOCODE
 * Initialize all the models
 * 
 * Load the file into the instruction memory 
 * FIXME: project doc said we might not need distinct instruction and data memories?
 * FIXME: Idk, work with what makes sense.
 * 
 * Using the address from the program counter, get the next instruction
 *     Increment the address in PC by 4 (maybe using dedicated ALU?) and
 *     hold onto it for now
 * 
 *     "In hardware" (the controller), split the instruction depending
 *     on the type into it's components (opcode, regs, funct, etc)
 * 
 * Send opcode to the Control
 *     Flips everything approprately
 * 
 * Read the registers using the instruction class methods and get their values
 * OR
 * Perform the hardware shifts necessary to do a jump instruction
 * 
 * Send the ALUOp signal to the ALUControl and feed the ALUInput into the main ALU
 *     Perform necessary hardware shifts for a branch if necessary
 * 
 * Write to data memory
 *     Might require a write to a register or a read of data
 * 
 * Add new computed address to PC
 * Reset Control and ALU control to default values ".reset()"
 * Begin anew
 *   
 */

public class Controller {
    private Scoreboard view;

    public Controller(Scoreboard v) {
        view = v;
    }

    public void runProgram(String filename) throws Exception {
        // Create CPU objects
        ProgramCounter pc = new ProgramCounter(view);
        Control control = new Control();
        Registers registers = new Registers(view);
        ALU alu = new ALU(view, false);
        ALU pcAlu = new ALU(view, true);

        ALUControl aluControl = new ALUControl();
        DataMemory dataMemory = new DataMemory(view);
        InstructionMemory instructions = new InstructionMemory(filename);

        /*
         * Loop while the program counter is not pointing at something out of bounds
         * Does this by comparing the PC address with the possible values in the
         * instruction memory
         */
        while (pc.getAddress() < (instructions.getNumInstructions() * 4)) {
            // Use the pc to get the next instruction
            Instruction currInstruction = instructions.getInstruction(pc.getAddress());

            // Decode instruction opcode and set control values
            control.opcodeDecode(currInstruction.getOpcode());

            // Set up to deal with registers
            int readReg1 = currInstruction.getRs();
            int readReg2 = currInstruction.getRt();

            int writeReg;
            if (control.getRegDst()) {
                writeReg = currInstruction.getRd();
            } else {
                writeReg = readReg2;
            }

            // Setup ALU control
            aluControl.createALUInput(currInstruction.getFunct(), control);

            // Sign extend instruction address
            int address = (int) currInstruction.getAddress();

            // Setup ALU inputs
            int aluInput1 = registers.getRegister(readReg1);
            int aluInput2;

            if (control.getALUSrc()) {
                aluInput2 = address;
            } else {
                aluInput2 = registers.getRegister(readReg2);
            }

            // ALU operation
            int memALUResult = alu.arithmetic(aluInput1, aluInput2, aluControl.getALUInput());

            // Data Memory
            int memAddress = memALUResult;
            int writeData = readReg2;

            int memReadResult = 0;

            if (control.getMemWrite()) {
                dataMemory.addData(memAddress, writeData);
            }
            if (control.getMemRead()) {
                memReadResult = dataMemory.getData(memAddress);
            }

            // Write Back
            int writeBack;

            if (control.getMemtoReg()) {
                writeBack = memReadResult;
            } else {
                writeBack = memALUResult;
            }

            if (control.getRegWrite()) {
                registers.setRegister(writeReg, writeBack, control.getRegWrite());
            }

            // Set the new PC value
            int nextAddress = pcAlu.arithmetic(pc.getAddress(), 4, 0b0010);

            // Calculate jump and branch addresses
            int jumpAddress = currInstruction.getAddress() << 2;
            jumpAddress = (pc.getAddress() & 0xF0000000) + (jumpAddress & 0x0FFFFFFF);

            int branchOffset = address << 2;
            int branchAddress = alu.arithmetic(nextAddress, branchOffset, 0b0010);

            // first mux for PC address calculation
            if (control.getPCSrc() && alu.getZero()) {
                pc.setAddress(branchAddress);
            } else {
                pc.setAddress(nextAddress);
            }

            // second mux for PC address calculation
            if (control.getJump()) {
                pc.setAddress(jumpAddress);
            }

            // Reset control values
            control.reset();
            aluControl.reset();

            // Display the scoreboard
            view.displayScoreboard();
        }
    }

}
