package game.component;

import game.util.Direction;

public class Paddle extends AbstractEntity {

    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void update(Direction direction) {
        switch (direction) {
            case LEFT -> setX(getX() - 0.025f);
            case RIGHT -> setX(getX() + 0.025f);
        }
        updateVertices();
    }

    private void updateVertices() {
        setVertices(new float[]{
                // Top half
                getX() + 0.1f, getY() - 0.025f, 0.0f,
                getX() - 0.1f, getY() + 0.025f, 0.0f,
                getX() + 0.1f, getY() + 0.025f, 0.0f,
                // Bottom half
                getX() - 0.1f, getY() + 0.025f, 0.0f,
                getX() - 0.1f, getY() - 0.025f, 0.0f,
                getX() + 0.1f, getY() - 0.025f, 0.0f
        });
    }
}
