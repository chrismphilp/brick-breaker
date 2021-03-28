package game.component;

import game.util.Direction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ball extends AbstractEntity {

    private static final float PI = 3.141592f;
    private static final int GRADATION = 8;

    private Direction xDirection = Direction.LEFT;
    private Direction yDirection = Direction.DOWN;

    private float xOffset;
    private float yOffset;

    public Ball(float x, float y, float xOffset, float yOffset, float width, float height) {
        super(x, y, width, height);
        setXOffset(xOffset);
        setYOffset(yOffset);
    }

    public void update() {
        updateDirectionBasedOnWindowIntersections();
        updateOffsets();
        setVertices(calculateCircleVertices());
    }

    public void updateDirectionBasedOnPaddleIntersections(boolean hasIntersected, float paddleCentre, float paddleWidth) {
        if (hasIntersected) {
            setXOffset(0.015f * (1 / (paddleWidth / (Math.abs(getX() - paddleCentre)))));
            if (xOffset == Float.POSITIVE_INFINITY || xOffset == Float.NEGATIVE_INFINITY) setXOffset(0);
            System.out.format("x: %s, xOffset: %s, paddleCentre: %s, paddleWidth: %s. \n", getX(), xOffset, paddleCentre, paddleWidth);
            yDirection = yDirection.equals(Direction.UP) ? Direction.DOWN : Direction.UP;
        }
    }

    public void updateDirectionBasedOnBrickIntersections(boolean hasIntersected) {
        if (hasIntersected) {
            yDirection = yDirection.equals(Direction.UP) ? Direction.DOWN : Direction.UP;
        }
    }

    private void updateDirectionBasedOnWindowIntersections() {
        if (getX() >= 1) xDirection = Direction.LEFT;
        else if (getX() <= -1) xDirection = Direction.RIGHT;

        if (getY() >= 1) yDirection = Direction.DOWN;
        else if (getY() <= -1) yDirection = Direction.UP;
    }

    private void updateOffsets() {
        switch (xDirection) {
            case LEFT -> setX(getX() - getXOffset());
            case RIGHT -> setX(getX() + getXOffset());
        }

        switch (yDirection) {
            case UP -> setY(getY() + getYOffset());
            case DOWN -> setY(getY() - getYOffset());
        }
    }

    private float[] calculateCircleVertices() {
        float[] storage = new float[GRADATION * 2 * 9];
        int location = 0;
        float jumps = PI / GRADATION;

        for (int i = 0; i < GRADATION * 2; i++) {
            calculateLocation(storage, location, i * jumps);
            location += 3;

            calculateLocation(storage, location, (i + 1) * jumps);
            location += 3;

            // Centred at origin
            storage[location] = getX();
            storage[location + 1] = getY();
            storage[location + 2] = 0;
            location += 3;
        }
        return storage;
    }

    private void calculateLocation(float[] storage, int location, float div) {
        storage[location] = (float) (getHeight() * Math.cos(div)) + getX();
        storage[location + 1] = (float) (getWidth() * Math.sin(div)) + getY();
        storage[location + 2] = 0;
    }
}
