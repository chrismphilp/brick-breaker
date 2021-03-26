package game.util;

import game.component.AbstractEntity;
import game.component.Ball;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IntersectionUtility {

    public static boolean hasBallIntersectedVerticallyWithRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getY() + 0.015f) >= (ball.getY() - 0.025f) &&
                (entity.getY() - 0.015f) <= (ball.getY() - 0.025f);
    }

    public static boolean hasBallIntersectedHorizontallyWithRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getX() - 0.1f) <= (ball.getX() - 0.025f) &&
                (entity.getX() + 0.1f) >= (ball.getX() - 0.025f);
    }
}
