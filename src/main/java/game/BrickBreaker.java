package game;

import engine.IGameLogic;
import engine.Window;
import game.component.Ball;
import game.component.Brick;
import game.component.Paddle;
import game.util.BrickUtility;
import game.util.Direction;
import game.util.IntersectionUtility;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

public class BrickBreaker implements IGameLogic {

    private final Renderer renderer;
    // Paddle
    private final Paddle paddle;
    private static final float PADDLE_WIDTH = 0.1f;
    private static final float PADDLE_HEIGHT = 0.015f;
    // Ball
    private final Ball ball;
    private static final float RADIUS = 0.025f;
    // Bricks
    private final int brickRows = 8;
    private final int brickColumns = 5;
    private final Brick[][] brickArray;
    private static final float BRICK_HEIGHT = 0.035f;
    private static final float BRICK_WIDTH = 0.1f;

    public BrickBreaker() {
        renderer = new Renderer();
        this.paddle = new Paddle(0, -0.75f, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.ball = new Ball(0, 0, RADIUS, RADIUS);
        this.brickArray = BrickUtility.generateBricks(brickRows, brickColumns, BRICK_WIDTH, BRICK_HEIGHT);
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
        paddle.setDirection(direction);
    }

    @Override
    public void update(float interval) {
        paddle.update();
        ball.updateDirectionBasedOnObjectIntersections(IntersectionUtility.hasIntersected(paddle, ball));
        outerLoop:
        for (int x = 0; x < brickRows; x++) {
            for (int y = 0; y < brickColumns; y++) {
                if (!brickArray[x][y].isHit()) {
                    boolean hasIntersected = IntersectionUtility.hasIntersected(brickArray[x][y], ball);
                    ball.updateDirectionBasedOnObjectIntersections(hasIntersected);
                    if (hasIntersected) {
                        brickArray[x][y].setHit(true);
                        break outerLoop;
                    }
                }
            }
        }
        ball.update();
    }

    @Override
    public void render(Window window) {
        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        renderer.clear();
        renderer.render(window, paddle.getVertices(), 3);
        renderer.render(window, ball.getVertices(), 3);

        for (int x = 0; x < brickRows; x++) {
            for (int y = 0; y < brickColumns; y++) {
                if (!brickArray[x][y].isHit()) {
                    renderer.render(window, brickArray[x][y].getVertices(), 3);
                }
            }
        }
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
    }
}
