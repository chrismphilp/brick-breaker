package game.component;

import lombok.Data;

@Data
public abstract class AbstractEntity implements Entity {

    private float x, y, height, width;
    private float[] vertices = new float[3];

    public AbstractEntity(float x, float y, float width, float height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }
}
