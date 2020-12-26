import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {//繼承至GameObject
    //    private int x;                    //視窗x軸
    //    private int y;                    //視窗y軸

    private Direction direction;            //方向
    private int speed;                      //速度
    private boolean[] dirs = new boolean[4];//新增一個boolean變數->用來使用複合按鍵
    private boolean enemy;                  //新增一個boolean變數->用來辨識敵方坦克


    //傳入圖形Image[] image(坦克有多種圖形)
    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    //傳入圖形Image[] image(坦克有多種圖形)
    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);//需呼叫父類別的建構方法
        this.direction = direction;
        speed = 5;
        this.enemy = enemy;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                x -= speed;
                y -= speed;
                break;
            case UP_RIGHT:
                x += speed;
                y -= speed;
                break;
            case DOWN_LEFT:
                x -= speed;
                y += speed;
                break;
            case DOWN_RIGHT:
                x += speed;
                y += speed;
                break;
        }
        if(x<0){
            x=0;
        }else if(x>TankGame.getGameCliect().getScreenWidth()-width){
            x=TankGame.getGameCliect().getScreenWidth()-width;
        }
        if(y<0){
            y=0;
        }else if(y>TankGame.getGameCliect().getScreenHeight()-height){
            y=TankGame.getGameCliect().getScreenHeight()-height;
        }
    }

    public void determineDirection() {
        //0:上,1:下,2:左,3:右
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[1] && !dirs[2]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) direction = Direction.UP;
        else if (dirs[1] && !dirs[2] && !dirs[3] && !dirs[0]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[0] && !dirs[1] && !dirs[2]) direction = Direction.RIGHT;
    }

//優化讀取圖形方法->移除getImage方法
//    public Image getImage() {
//        String name = enemy ? "etank" : "itank";
//
//        if (direction == Direction.UP)
//            return new ImageIcon("assets/images/" + name + "U.png").getImage();//內部每次讀取圖檔耗時跟效能，改成統一由外部讀取後，傳入物件內進行繪製
//        if (direction == Direction.DOWN)
//            return new ImageIcon("assets/images/" + name + "D.png").getImage();
//        if (direction == Direction.LEFT)
//            return new ImageIcon("assets/images/" + name + "L.png").getImage();
//        if (direction == Direction.RIGHT)
//            return new ImageIcon("assets/images/" + name + "R.png").getImage();
//        if (direction == Direction.UP_LEFT)
//            return new ImageIcon("assets/images/" + name + "LU.png").getImage();
//        if (direction == Direction.UP_RIGHT)
//            return new ImageIcon("assets/images/" + name + "RU.png").getImage();
//        if (direction == Direction.DOWN_LEFT)
//            return new ImageIcon("assets/images/" + name + "LD.png").getImage();
//        if (direction == Direction.DOWN_RIGHT)
//            return new ImageIcon("assets/images/" + name + "RD.png").getImage();
//
//        return null;
//    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isMoving() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return true;//偵測到一個按鍵按下時才移動坦克
            }
        }
        return false;
    }

    public void draw(Graphics g) {
//        if(!isStop()){
        if (isMoving()) {
            determineDirection();
            move();
        }
        //改寫坦克繪製方法(不每次重複讀取遊戲圖片)
        g.drawImage(image[direction.ordinal()], x, y, null);//按照方向(direction.ordinal())位置取圖形
    }
}
