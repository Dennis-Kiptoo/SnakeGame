public class Game {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private Snake snake;
    private Apple apple;
    private boolean inGame = true;
    private int score = 0;

    public Game() {
        snake = new Snake(ALL_DOTS, DOT_SIZE);
        apple = new Apple(RAND_POS, DOT_SIZE);
        initGame();
    }

    private void initGame() {
        snake.initSnake();
        apple.locateApple();
    }

    public void update() {
        if (inGame) {
            checkApple();
            checkCollision();
            snake.move();
        }
    }

    private void checkApple() {
        if (snake.getX(0) == apple.getX() && snake.getY(0) == apple.getY()) {
            snake.grow();
            apple.locateApple();
            score += 10;
        }
    }

    private void checkCollision() {
        if (snake.checkSelfCollision() || snake.checkWallCollision(B_WIDTH, B_HEIGHT)) {
            inGame = false;
        }
    }

    public boolean isInGame() {
        return inGame;
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public int getScore() {
        return score;
    }
}