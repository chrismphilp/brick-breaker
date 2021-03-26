package game.util;

import game.component.Brick;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class BrickUtility {

    public static Brick[][] generateBricks(int rows, int columns, float width, float height) {
        Brick[][] bricks = new Brick[rows][columns];

        float rowIncrement = 1.5f / (rows - 1);
        float columnIncrement = 1f / columns;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                bricks[x][y] = new Brick(-0.7f + (x * rowIncrement), y * columnIncrement, width, height);
                bricks[x][y].updateVertices();
            }
        }
        return bricks;
    }
}
