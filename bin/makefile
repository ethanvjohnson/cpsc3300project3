JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	project3/CPUApp.java \
	project3/models/ALU.java \
	project3/models/ALUControl.java \
	project3/models/Control.java \
	project3/models/DataMemory.java \
	project3/models/Instruction.java \
	project3/models/InstructionMemory.java \
	project3/models/ProgramCounter.java \
	project3/models/Registers.java \
	project3/views/Scoreboard.java \
	project3/controllers/Controller.java

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	java project3.CPUApp $(file)

clean:
	$(RM) *.class