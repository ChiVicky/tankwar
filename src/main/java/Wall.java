import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject {//繼承至GameObject
    //    private int x;
    //    private int y;
    //    private Image image;
    //     移除x,y,image等屬性
    private boolean horizontal;
    private int bricks;

    public Wall(int x, int y, boolean horizontal, int bricks, Image[] image) {
//        this.x = x;
//        this.y = y;
//        image = new ImageIcon("assets/images/brick.png").getImage();
//     移除x,y,image等屬性
        super(x, y, image);//需呼叫父類別的建構方法
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    //覆寫getRectangle方法
    //取得物件的寬度
    @Override
    public Rectangle getRectangle() {
        //判斷是物件是水平還是垂直，取得不同的物件寬度
        return horizontal ? new Rectangle(x,y,bricks* width,height): new Rectangle(x,y,width,bricks*height);
    }

    //需要實作draw方法
    public void draw(Graphics g) {
        if (horizontal) {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x + i * width, y, null);//修改取寬
            }
        } else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x, y + i * height, null);//修改取長
            }
        }
    }


}
