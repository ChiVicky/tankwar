import javax.swing.*;
import java.awt.*;

public class Tool {//宣告Tools類別->工具類別
    //靜態讀取圖形方法
    public static Image getImage(String fileName){
        return new ImageIcon("assets/images/"+fileName).getImage();
    }
}
