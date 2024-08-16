import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    private Game game;
    private Timer timer;
    private JButton restartButton;
    private boolean paused = false;

    public GamePanel() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(300, 300));

        game = new Game();
        timer = new Timer(140, this);
        timer.start();

        restartButton = new JButton("Restart");
        restartButton.setBounds(100, 150, 100, 30);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        restartButton.setVisible(false);
        add(restartButton);
        setLayout(null);
    }

    private void restartGame() {
        game = new Game();
        timer.start();
        restartButton.setVisible(false);
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (game.isInGame()) {
            g.setColor(Color.red);
            g.fillRect(game.getApple().getX(), game.getApple().getY(), 10, 10);

            for (int z = 0; z < game.getSnake().getDots(); z++) {
                if (z == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.white);
                }
                g.fillRect(game.getSnake().getX(z), game.getSnake().getY(z), 10, 10);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", Font.BOLD, 14));
            g.drawString("Score: " + game.getScore(), 10, 20);

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (300 - metr.stringWidth(msg)) / 2, 300 / 2);

        restartButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!paused) {
            game.update();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                paused = !paused;
            }

            if ((key == KeyEvent.VK_LEFT) && (!game.getSnake().isRightDirection())) {
                game.getSnake().setDirection(true, false, false, false);
            }

            if ((key == KeyEvent.VK_RIGHT) && (!game.getSnake().isLeftDirection())) {
                game.getSnake().setDirection(false, true, false, false);
            }

            if ((key == KeyEvent.VK_UP) && (!game.getSnake().isDownDirection())) {
                game.getSnake().setDirection(false, false, true, false);
            }

            if ((key == KeyEvent.VK_DOWN) && (!game.getSnake().isUpDirection())) {
                game.getSnake().setDirection(false, false, false, true);
            }
        }
    }
}