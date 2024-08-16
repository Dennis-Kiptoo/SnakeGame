import java.util.Random;

public class Apple {

    private int x;
    private int y;
    private int randPos;
    private int dotSize;
    private Random random;

    public Apple(int randPos, int dotSize) {
        this.randPos = randPos;
        this.dotSize = dotSize;
        random = new Random();
    }

    public void locateApple() {
        x = random.nextInt(randPos) * dotSize;
        y = random.nextInt(randPos) * dotSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
