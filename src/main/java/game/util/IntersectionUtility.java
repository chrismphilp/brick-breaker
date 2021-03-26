package game.util;

import game.component.Ball;
import game.component.Paddle;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IntersectionUtility {

    public boolean hasBallIntersectedVerticallyWithPaddle(Paddle paddle, Ball ball) {
        return paddle.getY() == ball.getY();
    }

    public boolean hasBallIntersectedHorizontallyWithPaddle(Paddle paddle, Ball ball) {
        return paddle.getX() == ball.getX();
    }
}
