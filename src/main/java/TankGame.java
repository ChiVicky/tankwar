import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {
    public static void main(String[] args) {
        GameCliect gameCliect =new GameCliect();
        JFrame jFrame =new JFrame();
        jFrame.setTitle("坦克大戰遊戲");
        jFrame.add(gameCliect);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();

        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameCliect.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    }
}
