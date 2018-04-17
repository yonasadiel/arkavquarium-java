OBJS = models/*.java controllers/*.java views/*.java Arkavquarium.java

CC = javac
COMPILER_FLAGS = 
LINKER_FLAGS = 
OBJ_FOLDER = bin/

all : $(OBJS)
	$(CC) $(OBJS) $(COMPILER_FLAGS) $(LINKER_FLAGS) -d $(OBJ_FOLDER)