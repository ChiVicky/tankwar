import object.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class GameCliect extends JComponent {

    private int ScreenWidth;
    private int ScreenHeight;

    //玩家坦克
    private Tank playerTank;

    GameCliect() {
        this(800, 600);
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
//        playerTank = new Tank(100, 100,Tank.UP);
        playerTank = new Tank(200, 100, Direction.DOWN);
//        playerTank = new Tank(300, 100,Tank.LEFT);
//        playerTank = new Tank(400, 100, Direction.RIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,ScreenWidth,ScreenHeight);
//        g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), null);
    playerTank.draw(g);
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0]=true;
//                playerTank.setDirection(Direction.UP);
//                playerTank.setY(playerTank.getY() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=true;
//                playerTank.setDirection(Direction.DOWN);
//                playerTank.setY(playerTank.getY() + playerTank.getSpeed());
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=true;
//                playerTank.setDirection(Direction.LEFT);
//                playerTank.setX(playerTank.getX() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=true;
//                playerTank.setDirection(Direction.RIGHT);
//                playerTank.setX(playerTank.getX() + playerTank.getSpeed());
                break;
        }
//        repaint();
//        playerTank.move();
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0]=false;
//                playerTank.setDirection(Direction.UP);
//                playerTank.setY(playerTank.getY() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=false;
//                playerTank.setDirection(Direction.DOWN);
//                playerTank.setY(playerTank.getY() + playerTank.getSpeed());
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=false;
//                playerTank.setDirection(Direction.LEFT);
//                playerTank.setX(playerTank.getX() - playerTank.getSpeed());
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=false;
//                playerTank.setDirection(Direction.RIGHT);
//                playerTank.setX(playerTank.getX() + playerTank.getSpeed());
                break;
        }
//        repaint();
//        playerTank.move();
    }
}
