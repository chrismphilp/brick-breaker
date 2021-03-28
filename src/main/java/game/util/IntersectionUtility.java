package game.util;

import game.component.AbstractEntity;
import game.component.Ball;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IntersectionUtility {

    public static boolean hasIntersected(AbstractEntity entity, Ball ball) {
        return IntersectionUtility.hasBallIntersectedVerticallyWithRectangle(entity, ball) &&
                IntersectionUtility.hasBallIntersectedHorizontallyWithRectangle(entity, ball);
    }

    private static boolean hasBallIntersectedVerticallyWithRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getY() + entity.getHeight()) >= (ball.getY() - ball.getHeight()) &&
                (entity.getY() - entity.getHeight()) <= (ball.getY() - ball.getHeight());
    }

    private static boolean hasBallIntersectedHorizontallyWithRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getX() - entity.getWidth()) <= (ball.getX() - ball.getWidth()) &&
                (entity.getX() + entity.getWidth()) >= (ball.getX() - ball.getWidth());
    }
}
