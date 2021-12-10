package Task16;

import javax.swing.*;
import java.awt.*;

public class PointsMovingDemo extends JFrame {
    JPanel panel = new JPanel();
    Graphics g;
    JButton btn = new JButton("Place point");
    int i;

    public PointsMovingDemo() {
        setBounds(100, 200, 600, 600);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        btn.setBounds(200, 10, 160, 20);
        contentPane.add(btn);
        panel.setBounds(30, 40, 520, 500);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        btn.addActionListener(e -> {
            new PointThread(panel).start();
            i++;
            repaint();
        });
    }

    public static void main(String[] args) {
        PointsMovingDemo frame = new PointsMovingDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


