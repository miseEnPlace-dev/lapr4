prog: prototype.o
	gcc prototype.o utils/utils.o -o prog -lrt -lpthread

prototype.o: prototype.c
	gcc -Wall -g -c prototype.c -o prototype.o

clean:
	rm -f *.o prog

run: prog
	./prog
