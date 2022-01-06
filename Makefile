CC = cl

BUILD_DIR = build

all: dirs libs

dirs:
	IF not exist $(BUILD_DIR) mkdir $(BUILD_DIR)

libs:
	cd lib/glad && $(CC) -o src/glad.o -Iinclude -c src/glad.c
	cd lib/glfw && cmake . && make .