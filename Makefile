CC=clang
BUILD_DIR=build

CFLAGS = -Wextra -Wpedantic -Wstrict-aliasing
CFLAGS += -lmsvcrt -luser32 -lgdi32 -lshell32 -lglfw3
CFLAGS += -Ilib/glad/include -Ilib/glfw/include
LDFLAGS = lib/glad/src/glad.o

all: dirs libs run

dirs:
	mkdir -p $(BUILD_DIR)

libs:
	cd lib/glad && $(CC) -o src/glad.o -Iinclude -c src/glad.c
	cd lib/glfw && cmake . && make

run:
	$(CC) main.cpp -o $(BUILD_DIR)/game.exe $(CFLAGS) $(LDFLAGS)