import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//主視窗顯示跟按鍵控制(JFrame)
public class TankGame {

    public static GameCliect gameCliect;
    public static void main(String[] args) {
         gameCliect = new GameCliect();
        JFrame jFrame = new JFrame();                           //宣告JFrame物件(顯示的視窗)
        jFrame.setTitle("坦克大戰遊戲");
        jFrame.add(gameCliect);                                 //將GameClient物件進行裝載(使用JFrame.add方法)
        jFrame.setVisible(true);                                //設定是否顯示視窗框架->顯示視窗
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //完整關閉視窗
        jFrame.pack();                                          //進行pack(),設定根據內容元件決定調整視窗大小

        //接收鍵盤事件的偵聽器介面
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {                //按下某個鍵時呼叫此方法。
                gameCliect.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {               //釋放某個鍵時呼叫此方法。
                gameCliect.keyReleased(e);
            }
        });
    }
    public static GameCliect getGameCliect() {
        return gameCliect;
    }
}
