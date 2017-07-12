import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class Test {
     public static void main(String[] args) {
         // 全屏运行
         GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                 .getDefaultScreenDevice();
         gd.setFullScreenWindow(new RectDraw());
     }
}

class RectDraw extends JFrame {
     private static final long serialVersionUID = 1L;
     int orgx, orgy, endx, endy;
     Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

     public RectDraw() {
         setVisible(true);
         this.addMouseListener(new MouseAdapter() {
             public void mousePressed(MouseEvent e) {
                 orgx = e.getX();
                 orgy = e.getY();
             }
         });
         this.addMouseMotionListener(new MouseMotionAdapter() {
             public void mouseDragged(MouseEvent e) {
                 endx = e.getX();
                 endy = e.getY();
                 Graphics g = getGraphics();
                 g.clearRect(0, 0, d.width, d.height);
                 g.setColor(Color.BLUE);
                 g.drawRect(orgx, orgy, endx - orgx, endy - orgy);
             }
         });
         this.addKeyListener(new KeyAdapter() {

             @Override
             public void keyReleased(KeyEvent e) {
                 // 按Esc键退出
                 if (e.getKeyCode() == 27) {
                     System.exit(0);
                 }
             }
         });
     }
}