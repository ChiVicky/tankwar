import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BaseMultiResolutionImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//主遊戲邏輯跟圖形顯示(JComponent)
public class GameCliect extends JComponent { //GameClient繼承JComponent類別(擁有繪製功能)

    private int ScreenWidth; //視窗寬度
    private int ScreenHeight;//視窗長度
    private Tank playerTank;//玩家坦克
    private List<Tank> enemyTanks = new ArrayList<>();//敵方坦克
    private List<Wall> walls = new ArrayList<>();//牆
    private Image BackGroundImg;
    public static Image[] bulletImg = new Image[8];//子彈圖形

    private List<GameObject> objects = new ArrayList<>();//使用父類別進行集合類 型宣告


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
            bulletImg[i] = Tool.getImage("missile" + sub[i] + ".png");//增加子彈圖片
        }

        playerTank = new Tank(400, 50, Direction.DOWN, iTankImage);

        for (int i = 0; i < 3; i++) {//3列
            for (int j = 0; j < 4; j++) {//4行
                enemyTanks.add(new Tank(220 + j * 120, 300 + i * 120, Direction.UP, true, eTankImage));
            }
        }
        Image[] images = {
                new ImageIcon("assets/images/brick.png").getImage()
        };
        walls.add(new Wall(200, 150, true, 15, images));
        walls.add(new Wall(100, 150, false, 13, images));
        walls.add(new Wall(750, 150, false, 13, images));
        objects.add(playerTank);
        objects.addAll(walls);
        objects.addAll(enemyTanks);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ScreenWidth, ScreenHeight);
        g.drawImage(BackGroundImg, 0, 0, null);
//        playerTank.draw(g);
//        for (Tank tank : enemyTanks) {
//            tank.draw(g);
//        }
//        for (Wall wall : walls) {
//            wall.draw(g);
//        }
        for (GameObject object : objects) {//優點:統一控管遊戲物件
            object.draw(g);
        }
        System.out.println(objects.size());

        Iterator<GameObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isAlive()) {
                iterator.remove();
            }
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

            case KeyEvent.VK_A://新增按鍵功能->按下A(按鍵發射)
                playerTank.fire();
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

    public int getScreenWidth() {
        return ScreenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    //增加物件方法
    public void addGameObject(GameObject object) {
        getObjects().add(object);
    }
}
