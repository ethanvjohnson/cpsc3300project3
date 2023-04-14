package project3;

import java.io.*;
import project3.views.*;
import project3.controllers.*;;

public class CPUApp {
    public static void main(String[] args) throws Exception {
        // Setup controllers and views
        Scoreboard view = new Scoreboard();
        Controller controller = new Controller(view);
        view.setController(controller);

        // TODO: run program in using the controller and then print out the view
        // controller.runProgram(args[0]);
        controller.runProgram("output.dat");

        // TODO: run

    }
}

/*
 * 1. PC
 * 2. Instruction Memory X
 * 3. Registers
 * 4. ALU
 * 5. ALU Control
 * 6. Data Memory
 * 7. Control X
 * 
 * SHIFTS GO IN HARDWARE, THUS THEY ARE IN THE CONTROLLER
 * 
 * We need to store the binary somewhere then parse it into discrete 32 bit
 * parts
 * That goes in the instruction memory
 * Controller fetches the next instruction
 * 
 */