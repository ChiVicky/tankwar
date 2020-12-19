import javax.swing.*;

public class TankGame {
    public static void main(String[] args) {
        GameCliect gameCliect =new GameCliect(1024,768);
        JFrame jFrame =new JFrame();
        jFrame.add(gameCliect);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
    }
}
