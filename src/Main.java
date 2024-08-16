import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();

        JButton minimizeButton = new JButton("Minimize");
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }
        });

        JButton maximizeButton = new JButton("Maximize");
        maximizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        frame.add(minimizeButton, BorderLayout.NORTH);
        frame.add(maximizeButton, BorderLayout.SOUTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake Game");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}