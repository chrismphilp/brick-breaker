CC=clang
BUILD_DIR=build

CFLAGS = -std=c++20 -O3 -Wall -Wextra -Wpedantic -Wstrict-aliasing -Wno-language-extension-token
CFLAGS += -lmsvcrt -luser32 -lgdi32 -lshell32 -lglfw3
CFLAGS += -Ilib/glad/include -Ilib/glfw/include -Ilib/stb -Ilib/glm/glm
LDFLAGS = lib/glad/src/glad.o

SRC  = $(wildcard src/*.cpp src/*/*.cpp)
OBJ  = $(SRC:.c=.o)

all: dirs libs compile run

dirs: 
	mkdir -p $(BUILD_DIR)

libs:
	cd lib/glad && $(CC) -o src/glad.o -Iinclude -c src/glad.c
	cd lib/glfw && cmake . && make

cr: compile run

compile: $(OBJ) 
	$(CC) -o $(BUILD_DIR)/game.exe $^ $(CFLAGS) $(LDFLAGS)

run: 
	$(BUILD_DIR)/game

clean: 
	rm -rf $(BUILD_DIR)/*