CC = mvn

all:
	$(CC) exec:java -Dexec.mainClass="com.arkavquarium.Arkavquarium"

test:
	$(CC) test