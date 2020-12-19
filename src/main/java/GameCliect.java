import javax.swing.*;
import java.awt.*;

public class GameCliect extends JComponent {

    private int ScreenWidth;
    private int ScreenHeight;
    GameCliect() {
        this.setPreferredSize(new Dimension(800,600));
    }

    public GameCliect(int screenWidth, int screenHeight) {
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("assets/images/itankD.png").getImage()
        ,400,50,null);
    }
}
