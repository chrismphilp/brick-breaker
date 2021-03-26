package game;

import engine.IGameLogic;
import engine.Window;
import game.component.Ball;
import game.component.Brick;
import game.component.Paddle;
import game.util.Direction;
import game.util.IntersectionUtility;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

public class BrickBreaker implements IGameLogic {

    private final Paddle paddle;
    private final Ball ball;
    private final Brick brick;
    private final Renderer renderer;

    public BrickBreaker() {
        this.paddle = new Paddle(0, -0.75f, 5, 5);
        this.ball = new Ball(0, 0, 5, 5);
        this.brick = new Brick(0, 1, 5, 5);
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
    }

    @Override
    public void input(Window window) {
        Direction direction = Direction.STATIONARY;
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            direction = Direction.LEFT;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            direction = Direction.RIGHT;
        }
        paddle.update(direction);
        ball.updateVertices(
                IntersectionUtility.hasBallIntersectedVerticallyWithPaddle(paddle, ball),
                IntersectionUtility.hasBallIntersectedHorizontallyWithPaddle(paddle, ball)
        );
    }

    @Override
    public void update(float interval) {
        System.out.format("\n Ball: x: %s, y: %s, Paddle: x: %s, y: %s", ball.getX(), ball.getY(), paddle.getX(), paddle.getY());
    }

    @Override
    public void render(Window window) {
        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        renderer.clear();
        renderer.render(window, paddle.getVertices(), 3);
        renderer.render(window, ball.getVertices(), 3);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
    }
}
