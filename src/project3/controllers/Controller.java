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


    public Controller(Scoreboard v){
        view = v;
    }
}
