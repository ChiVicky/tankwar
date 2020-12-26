import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BaseMultiResolutionImage;
import java.util.ArrayList;
import java.util.List;

//主遊戲邏輯跟圖形顯示(JComponent)
public class GameCliect extends JComponent { //GameClient繼承JComponent類別(擁有繪製功能)

    private int ScreenWidth; //視窗寬度
    private int ScreenHeight;//視窗長度
    private Tank playerTank;//玩家坦克
    private List<Tank> enemyTanks = new ArrayList<>();//敵方坦克
    private List<Wall> walls = new ArrayList<>();//牆
    private Image BackGroundImg;

    GameCliect() {
        this(800, 600);//設定畫面大小
    }

    public GameCliect(int screenWidth, int screenHeight) {
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public void init() {
//        BackGroundImg = new ImageIcon("assets/images/sand.jpg").getImage();
        BackGroundImg = Tool.getImage("sand.jpg");
        Image[] brickImage = {Tool.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];
        String[] sub = {"U", "D", "L", "R", "LU", "RU", "LD", "RD"};
        //節省每次讀取圖形的時間，記憶體也只會有一份圖形
        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tool.getImage("itank" + sub[i] + ".png");
            eTankImage[i] = Tool.getImage("etank" + sub[i] + ".png");
        }

        playerTank = new Tank(400, 50, Direction.DOWN,iTankImage);

        for (int i = 0; i < 3; i++) {//3列
            for (int j = 0; j < 4; j++) {//4行
                enemyTanks.add(new Tank(250 + j * 90, 300 + i * 90, Direction.UP, true,eTankImage));
            }
        }
        Image[] images = {
                new ImageIcon("assets/images/brick.png").getImage()
        };
        walls.add(new Wall(200, 150, true, 15, images));
        walls.add(new Wall(100, 150, false, 13, images));
        walls.add(new Wall(700, 150, false, 13, images));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ScreenWidth, ScreenHeight);
        g.drawImage(BackGroundImg, 0, 0, null);
        playerTank.draw(g);
        for (Tank tank : enemyTanks) {
            tank.draw(g);
        }
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }
    }
}
