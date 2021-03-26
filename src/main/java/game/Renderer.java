package game;

import engine.ShaderProgram;
import engine.Utils;
import engine.Window;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private int vboId;
    private int vaoId;
    private ShaderProgram shaderProgram;

    public Renderer() {
    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/fragment.fs"));
        shaderProgram.link();
    }

    public void render(Window window, float[] vertices, int size) {
        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        createVertexBuffer(vertices, size);

        shaderProgram.bind();

        // Bind to the VAO
        glBindVertexArray(vaoId);

        // Draw the vertices
        glDrawArrays(GL_TRIANGLES, 0, vertices.length / size);

        // Restore state
        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    private void createVertexBuffer(float[] vertices, int vertexSize) {
        FloatBuffer verticesBuffer = null;
        try {
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            // Create the VAO and bind to it
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Create the VBO and bint to it
            vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            // Enable location 0
            glEnableVertexAttribArray(0);
            // Define structure of the data
            glVertexAttribPointer(0, vertexSize, GL_FLOAT, false, 0, 0);

            // Unbind the VBO
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            // Unbind the VAO
            glBindVertexArray(0);
        } finally {
            if (verticesBuffer != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
        }
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }

        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}