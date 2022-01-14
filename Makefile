CC=clang
BUILD_DIR=build

CFLAGS = -std=c++20 -O3 -Wall -Wextra -Wpedantic -Wstrict-aliasing
CFLAGS += -lmsvcrt -luser32 -lgdi32 -lshell32 -lglfw3
CFLAGS += -Ilib/glad/include -Ilib/glfw/include
LDFLAGS = lib/glad/src/glad.o

all: dirs libs compile run

dirs: 
	mkdir -p $(BUILD_DIR)

libs:
	cd lib/glad && $(CC) -o src/glad.o -Iinclude -c src/glad.c
	cd lib/glfw && cmake . && make

cr: compile run

compile: 
	$(CC) main.cpp -o $(BUILD_DIR)/game.exe $(CFLAGS) $(LDFLAGS)

run: 
	./build/game

clean: 
	rm -rf $(BUILD_DIR)/*