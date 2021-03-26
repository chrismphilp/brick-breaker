package game.component;

public class Brick extends AbstractEntity {

    public Brick(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void updateVertices() {
        setVertices(new float[]{
                // Top half
                getX() + getWidth(), getY() - getHeight(), 0.0f,
                getX() - getWidth(), getY() + getHeight(), 0.0f,
                getX() + getWidth(), getY() + getHeight(), 0.0f,
                // Bottom half
                getX() - getWidth(), getY() + getHeight(), 0.0f,
                getX() - getWidth(), getY() - getHeight(), 0.0f,
                getX() + getWidth(), getY() - getHeight(), 0.0f
        });
    }
}
