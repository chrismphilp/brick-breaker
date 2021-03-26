package game.component;

import game.util.Direction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paddle extends AbstractEntity {

    private Direction direction = Direction.STATIONARY;

    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void update() {
        switch (direction) {
            case LEFT -> setX(getX() - 0.025f);
            case RIGHT -> setX(getX() + 0.025f);
        }
        updateVertices();
    }

    private void updateVertices() {
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
