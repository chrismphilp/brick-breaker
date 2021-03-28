package game.component;

import game.util.Direction;

public class Ball extends AbstractEntity {

    private static final float PI = 3.141592f;
    private static final int GRADATION = 8;

    private Direction xDirection = Direction.LEFT;
    private Direction yDirection = Direction.DOWN;

    private static final float X_OFFSET = 0.015f;
    private static final float Y_OFFSET = 0.015f;

    public Ball(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void update() {
        updateDirectionBasedOnWindowIntersections();
        updateOffsets();
        setVertices(calculateCircleVertices());
    }

    public void updateDirectionBasedOnObjectIntersections(boolean hasIntersected) {
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
            case LEFT -> setX(getX() - X_OFFSET);
            case RIGHT -> setX(getX() + X_OFFSET);
        }

        switch (yDirection) {
            case UP -> setY(getY() + Y_OFFSET);
            case DOWN -> setY(getY() - Y_OFFSET);
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
