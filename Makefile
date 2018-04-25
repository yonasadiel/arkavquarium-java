CC = mvn

all:
	$(CC) compile
	$(CC) exec:java -Dexec.mainClass="com.arkavquarium.Arkavquarium"

test:
	$(CC) test