import object.GameObject;

import java.awt.*;

//新增Bullet類別
//繼承Tank類別
public class Bullet extends Tank {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
    }

    //覆載顯示方法(draw)
    @Override
    public void draw(Graphics g) {
        if(!alive){
            return;
        }
        move();     //八方向移動
        collision();//碰撞偵測(collision)
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public boolean isCollisionBound() {
        boolean isCollision = false;

        isCollision= super.isCollisionBound();
        if(isCollision){
            alive =false;

        }
        return isCollision;
    }

    @Override
    public boolean isCollisionObject() {
        boolean isCollision = false;
        for (GameObject gameObject : TankGame.gameCliect.getObjects()) {
            if (gameObject == this) {
                continue;
            }
            if (gameObject instanceof Tank) {
                if (enemy == ((Tank) gameObject).enemy) {
                    continue;
                }
            }
            if (getRectangle().intersects(gameObject.getRectangle())) {

                if(gameObject instanceof Tank){
                        gameObject.setAlive(false);
                }
                isCollision = true;
                alive = false;
                break;
            }
        }
        return isCollision;
    }
}
