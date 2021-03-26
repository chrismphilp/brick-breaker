package game.component;

public class Brick extends AbstractEntity {

    public Brick(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public float[] getVertices() {
        return new float[]{
                // Top half
                0.1f, 0.1f, 0.0f,
                0.1f, -0.1f, 0.0f,
                0.1f, 0.1f, 0.0f,
                // Bottom half
                0.1f, 0.1f, 0.0f,
                0.1f, -0.1f, 0.0f,
                0.1f, -0.1f, 0.0f
        };
    }
}
