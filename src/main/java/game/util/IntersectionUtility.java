package game.util;

import game.component.AbstractEntity;
import game.component.Ball;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IntersectionUtility {

    public static boolean hasIntersected(AbstractEntity entity, Ball ball) {
        return hasBallIntersectedWithTopOfRectangle(entity, ball) &&
                hasBallIntersectedWithBottomOfRectangle(entity, ball) &&
                hasBallIntersectedWithLeftOfRectangle(entity, ball) &&
                hasBallIntersectedWithRightOfRectangle(entity, ball);
    }

    private static boolean hasBallIntersectedWithTopOfRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getY() + entity.getHeight()) >= (ball.getY() - ball.getHeight());
    }

    private static boolean hasBallIntersectedWithBottomOfRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getY() - entity.getHeight()) <= (ball.getY() + ball.getHeight());
    }

    private static boolean hasBallIntersectedWithLeftOfRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getX() - entity.getWidth()) <= (ball.getX() + ball.getWidth());
    }

    private static boolean hasBallIntersectedWithRightOfRectangle(AbstractEntity entity, Ball ball) {
        return (entity.getX() + entity.getWidth()) >= (ball.getX() - ball.getWidth());
    }
}
