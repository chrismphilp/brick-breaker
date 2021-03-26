package game.component;

import lombok.Data;

@Data
public abstract class AbstractEntity implements Entity {

    private float x, y, height, width;
    private float[] vertices = new float[3];

    public AbstractEntity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean intersects(Entity other) {
        return true;
    }
}
