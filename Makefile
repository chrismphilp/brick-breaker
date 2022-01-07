CC=clang
BUILD_DIR=build
LDFLAGS = lib/glad/src/glad.o lib/glfw/src/libglfw3.a -lm
CFLAGS = -std=c11 -O3 -g -Wall -Wextra -Wpedantic -Wstrict-aliasing
CFLAGS += -Ilib/glad/include -Ilib/glfw/include -fbracket-depth=1024

all: dirs libs run

dirs:
	IF not exist $(BUILD_DIR) mkdir $(BUILD_DIR)

libs:
	cd lib/glad && $(CC) -o src/glad.o -Iinclude -c src/glad.c
	cd lib/glfw && cmake . && make

run:
	$(CC) -o $(BIN)/game $^ $(LDFLAGS)