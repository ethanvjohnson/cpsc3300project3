package project3;
import java.io.*;
import project3.views.*;
import project3.controllers.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Setup to read from the input file
        System.out.println(args[0]);
        // TODO: construct instruction memory with file

        // System.out.println(line);
        
        // Setup controllers and views
        Scoreboard view = new Scoreboard();
        Controller controller = new Controller(view);
        view.setController(controller);
        
        // TODO: send the binary string to the controller and print out the view
        

    }
}

/*
1. PC 
2. Instruction Memory X
3. Registers 
4. ALU
5. ALU Control
6. Data Memory
7. Control X

SHIFTS GO IN HARDWARE, THUS THEY ARE IN THE CONTROLLER

We need to store the binary somewhere then parse it into discrete 32 bit parts
    That goes in the instruction memory
Controller fetches the next instruction

*/ 