public class Snake {
    private int[] x;
    private int[] y;
    private int dots;
    private int dotSize;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    public Snake(int allDots, int dotSize) {
        x = new int[allDots];
        y = new int[allDots];
        this.dotSize = dotSize;
    }

    public void initSnake() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    }

    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= dotSize;
        }

        if (rightDirection) {
            x[0] += dotSize;
        }

        if (upDirection) {
            y[0] -= dotSize;
        }

        if (downDirection) {
            y[0] += dotSize;
        }
    }

    public void grow() {
        dots++;
    }

    public boolean checkSelfCollision() {
        for (int z = dots; z > 0; z--) {
            if (z > 4 && x[0] == x[z] && y[0] == y[z]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWallCollision(int width, int height) {
        return x[0] >= width || x[0] < 0 || y[0] >= height || y[0] < 0;
    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public void setDirection(boolean left, boolean right, boolean up, boolean down) {
        leftDirection = left;
        rightDirection = right;
        upDirection = up;
        downDirection = down;
    }

    public boolean isRightDirection() {
        return rightDirection;
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public boolean isDownDirection() {
        return downDirection;
    }

    public boolean isUpDirection() {
        return upDirection;
    }

    // Before
// No getDots method

    // After
    public int getDots() {
        return dots;
    }
}