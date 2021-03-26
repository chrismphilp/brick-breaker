package game.component;

public interface Entity {
    void setLocation(float x, float y);

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getHeight();

    void setHeight(float height);

    float getWidth();

    void setWidth(float width);
    
    float[] getVertices();

    boolean intersects(Entity other);
}
