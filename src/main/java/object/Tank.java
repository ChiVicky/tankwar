package object;

import javax.swing.*;
import java.awt.*;

public class Tank {
    private int x;              //視窗x軸
    private int y;              //視窗y軸
    private Direction direction;//方向
    private int speed;          //速度
    private boolean[] dirs =new boolean[4];
    private boolean enemy;
//    public static final int UP = 0;
//    public static final int DOWN = 1;
//    public static final int RIGHT = 2;
//    public static final int LEFT = 3;

    public Tank(int x, int y, Direction direction) {
        this(x,y,direction,false);
//        this.x = x;
//        this.y = y;
//        this.direction = direction;
//        speed=5;
    }

    public Tank(int x, int y, Direction direction,boolean enemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed=5;
        this.enemy=enemy;
    }
    public Tank(int speed) {
        this.speed = speed;
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

    public Tank(boolean[] dirs) {
        this.dirs = dirs;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void move(){
        switch(direction){
            case UP:
                y-= speed;
                break;
            case DOWN:
                y+= speed;
                break;
            case LEFT:
                x-= speed;
                break;
            case RIGHT:
                x+= speed;
                break;
            case UP_LEFT:
                x-= speed;
                y-= speed;
                break;
            case UP_RIGHT:
                x+= speed;
                y-= speed;
                break;
            case DOWN_LEFT:
                x-= speed;
                y+= speed;
                break;
            case DOWN_RIGHT:
                x+= speed;
                y+= speed;
                break;
        }
}
public void determineDirection(){
        //0:上,1:下,2:左,3:右
    if (dirs[0] &&  dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
    else if (dirs[0] &&  dirs[3] && !dirs[1] && !dirs[2]) direction = Direction.UP_RIGHT;
    else if (dirs[1] &&  dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
    else if (dirs[1] &&  dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
    else if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) direction = Direction.UP;
    else if (dirs[1] && !dirs[2] && !dirs[3] && !dirs[0]) direction = Direction.DOWN;
    else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LEFT;
    else if (dirs[3] && !dirs[0] && !dirs[1] && !dirs[2]) direction = Direction.RIGHT;
}

    public Image getImage(){
        String name = enemy?"etank":"itank";

        if(direction==Direction.UP)
        return new ImageIcon("assets/images/"+name+"U.png")  .getImage();
        if(direction==Direction.DOWN)
            return new ImageIcon("assets/images/"+name+"D.png")  .getImage();
        if(direction==Direction.LEFT)
            return new ImageIcon("assets/images/"+name+"L.png")  .getImage();
        if(direction==Direction.RIGHT)
            return new ImageIcon("assets/images/"+name+"R.png")  .getImage();
        if(direction==Direction.UP_LEFT)
            return new ImageIcon("assets/images/"+name+"LU.png")  .getImage();
        if(direction==Direction.UP_RIGHT)
            return new ImageIcon("assets/images/"+name+"RU.png")  .getImage();
        if(direction==Direction.DOWN_LEFT)
            return new ImageIcon("assets/images/"+name+"LD.png")  .getImage();
        if(direction==Direction.DOWN_RIGHT)
            return new ImageIcon("assets/images/"+name+"RD.png")  .getImage();

        return null;
    }

    public  boolean isStop(){
        for(int i=0;i< dirs.length;i++){
            if(dirs[i]){
                return false;
            }
        }
        return true;
    }

    public  boolean isMoving(){
        for(int i=0;i< dirs.length;i++){
            if(dirs[i]){
                return true;//偵測到一個按鍵按下時才移動坦克
            }
        }
        return false;
    }
    public void draw(Graphics g){
//        if(!isStop()){
        if(isMoving()){
            determineDirection();
            move();
        }
        g.drawImage(getImage(),x,y,null);
    }
}
