import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class PicPanel extends JPanel implements MouseListener,MouseMotionListener{
	private Image pic;
	//ArrayList<Rect> rectList=new ArrayList<Rect>();
	int num_pic;
	Rect [] rectList=new Rect[1024];
	boolean isSign;//是否是标注模式
	int x1,y1,x2,y2;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private String fileName; //标注的图片的信息
	
	public PicPanel()
	{
		this.num_pic=0;
		this.isSign=true;
		this.x1=0;
		this.y1=0;
		this.x2=0;
		this.y2=0;
		fileName=new String();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	private Rect checkRect(int x,int y) //查看当前鼠标的位置有无标注框，若有，在标注框的什么位置
	{
		return null;
	}
	public int getNum()
	{
		return num_pic;
	}
	public void changeMode(boolean flag)
	{
		isSign=flag;
	}
	public void Output()
	{
		
	}
	public void paint(Graphics g)
	{
		if(pic!=null)
		{
			super.paintComponent(g);
			g.drawImage(pic, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setColor(Color.BLACK);
			for (int i=0;i<num_pic;i++) 
			{
				g.drawRect(rectList[i].getX1(), rectList[i].getY1(), rectList[i].getWidth(), rectList[i].getHeight());
			}
		}
	}
	public void changePic(String fileName)
	{
		num_pic=0;
		pic=new ImageIcon(fileName).getImage();
		this.fileName=fileName;
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null)
		{
			if(isSign)
			{
				x1=e.getX();
				y1=e.getY();
				rectList[num_pic++]=new Rect(x1,y1,0,0);
			}
			else
			{
				
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		String name=(String)JOptionPane.showInputDialog(this,"Name:","请输入标注框的名字");
		rectList[num_pic-1].setName(name);
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
        x2 = e.getX();
        y2 = e.getY();
        int X=x1<x2?x1:x2;
        int Y=y1<y2?y1:y2;
        rectList[num_pic-1].setX1(X);
        rectList[num_pic-1].setY1(Y);
        rectList[num_pic-1].setX2(X==x2?x1:x2);
        rectList[num_pic-1].setY2(Y==y2?y1:y2);
        repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null)
		{
			x1=e.getX();
			y1=e.getY();
			if(this.isSign)
			{
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
			else
			{
				
			}
		}
	}
/*	public static void main(String args[])
	{
		JFrame frame=new JFrame("test");
		PicPanel p=new PicPanel();
		frame.getContentPane().add(p);
		frame.setVisible(true);
		frame.setSize(800, 800);
		p.setSize(800, 800);
	}
*/
}
