package object;

import java.awt.*;
//所有遊戲物件的父類別->GameObject
public abstract class GameObject { //抽象類別(不能產生實體)
    protected int x;            //物件x軸位置
    protected int y;            //物件y軸位置
    protected int oldX;            //物件上一個動作的x軸位置
    protected int oldY;            //物件上一個動作的y軸位置
    protected Image[] image;    //物件的圖形陣列
    protected int width;        //物件的寬度
    protected int height;       //物件的長度

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        width=image[0].getWidth(null);  //提取寬度
        height=image[0].getHeight(null);//提取長度
    }

    //取得物件的寬度
    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }
    public abstract void draw(Graphics g);//繼承需覆寫draw方法
}
