# Authors
This project (Project 3 of Clemson's CPSC 3300) was a collaboration between:
* Christian Scott (cfscott@clemson.edu)
* Ethan Johnson (evj@clemson.edu)

# Running the Program
Since we did this project (as per specification recommendation) in Java, we could not easily compile a executable. Thus, this program is best run using the makefile. The command to run it is:
> make run file=<input_binary_file>

# Project Specifications
This project was a simulation of a single cycle CPU implemented in *MIPS*. We used a very limited instruction set of nine instructions: 
* Load word
* Store word
* Branch equal
* Jump
* Add
* Subtract
* AND
* OR
* Set on less than

The program takes in a single binary (.dat) file of an assembly file this limited instruction set and outputs the stats of all the CPU changes per cycle to the terminal/standard out.

# Design Decisions

We followed the MVC paradigm and the observer pattern when designing our program:
- **MODEL**
    * All the discrete logical units were **models** (ALU, ALUControl, Control, Registers, etc.).
    * The models do no passing of information to eachother. They are purely functional; they only take input and produce output. 
    * In this case we followed traditional OOP paradigms and had all data fields private and only accessible via getter/setter functions and the only way objects (**models**) communicated to eachother was through the medium of the **controller**.
    * The instruction memory is only as large as the .dat binary file passed to the program requires it to be. We felt there was no need to have any additional memory as the program is only designed to simulate a CPU for one program, not multiple.
    * The data memory is 4kb large and initialized incrementally in 0-255 blocks.
        - We chose 4kb basically due to what our professor told us.
- **CONTROLLER**
    * Our **controller** is the equivalent of all our hardware circuits and logic implemented in hardware. This includes all the shifts and the sending of any control signals to the appropriate muxes (if statements in our case). 
    * Our **controller** runs througn each stage of the instruction process using the control values to determine what should be used/stored/updated/etc... Because of this, the **view** counts all ALU operations each cycle, including the add operation for branching. 
- **VIEW**
    * Our **view** serves as an observer to many of the logic object classes. The **view** updates it's displayed information upon signal from the changed objects. As the CPU completes each cycle, the **view** is displayed with updated information.
    * We decided to initalize our data memory using a simple incremental to ease testing in the view.
    * We made the decision to *"display... the contents of memory"* as per the project specifications but it seemed very impractical since the memory can be practically infinite and thus unrealistic to need to display.
        - However, we still made a function for it that can be easily commented out.


