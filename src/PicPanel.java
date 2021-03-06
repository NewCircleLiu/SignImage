import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import jxl.*;
import jxl.write.*;
import jxl.write.Label;
import jxl.write.biff.RowsExceededException;

public class PicPanel extends JPanel implements MouseListener,MouseMotionListener{
	private Image pic;
	//ArrayList<Rect> rectList=new ArrayList<Rect>();
	int num_pic;
	Rect [] rectList=new Rect[1024];
	boolean isSign;//是否是标注模式
	int x1,y1,x2,y2;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private String fileName; //标注的图片的信息
	String status;
	int now;
	int copy_index; //当前要复制粘贴的rect的标号
	double p1;
	double p2;
	int [][] bmp_data;
	boolean isBmp;
	double scale=1.0;
	int pic_width;
	int pic_height;
	
	public PicPanel()
	{
		this.num_pic=0;
		this.isSign=true;
		status=null;
		isBmp=false;
		
		
		now=-1; //没选中任何标注框
		this.x1=0;
		this.y1=0;
		this.x2=0;
		this.y2=0;
		fileName=new String();
		addMouseListener(this);
		addMouseMotionListener(this);
		copy_index=-1;

	}
	private void checkRect(int x,int y) //查看当前鼠标的位置有无标注框，若有，在标注框的什么位置
	{
		int i;
		for(i=0;i<num_pic;i++)
		{
			if(rectList[i].getX1()-2<x && x<rectList[i].getX1()+2 && y<rectList[i].getY2() && y>rectList[i].getY1())
			{
				now=i;
				status="LEFT";
				break;
			}
			if(rectList[i].getX2()-2<x && x<rectList[i].getX2()+2 && y<rectList[i].getY2() && y>rectList[i].getY1())
			{
				now=i;
				status="RIGHT";
				break;
			}
			if(rectList[i].getY2()-2<y && y<rectList[i].getY2()+2 && x<rectList[i].getX2() && x>rectList[i].getX1())
			{
				now=i;
				status="DOWN";
				break;
			}
			if(rectList[i].getY1()-2<y && y<rectList[i].getY1()+2 && x<rectList[i].getX2() && x>rectList[i].getX1())
			{
				now=i;
				status="UP";
				break;
			}
			if(rectList[i].getX1()+2<x && x<rectList[i].getX2()-2 && y<rectList[i].getY2()-2 && y>rectList[i].getY1()+2)
			{
				now=i;
				status="INNER";
				break;
			}
			if(x==rectList[i].getX1() && y==rectList[i].getY1())
			{
				now=i;
				status="LEFTUP";
				break;
			}
			if(x==rectList[i].getX1() && y==rectList[i].getY2())
			{
				now=i;
				status="LEFTDOWN";
				break;
			}
			if(x==rectList[i].getX2() && y==rectList[i].getY1())
			{
				now=i;
				status="RIGHTUP";
				break;
			}
			if(x==rectList[i].getX2() && y==rectList[i].getY2())
			{
				now=i;
				status="RIGHTDOWN";
				break;
			}
		}
		if(i==num_pic)
		{
			now=-1;
			status=null;
		}
	}
	private void unSelect()
	{
		for(int i=0;i<num_pic;i++)
		{
			rectList[i].Select(false);
		}
	}

	public int getNum()
	{
		return num_pic;
	}
	public void setMode(boolean flag)
	{
		isSign=flag;
	}
	public void Output() throws IOException
	{
		if(pic!=null || isBmp)
		{
			Object [] options={"txt","xls","xml"};
			int index=fileName.lastIndexOf("\\");
			String folder=fileName.substring(0,index);
			String name=fileName.substring(index+1,fileName.length());
			BufferedImage bufferedImage = ImageIO.read(new File(fileName));   
			int width = bufferedImage.getWidth();   
			int height = bufferedImage.getHeight();  
			String option=(String)JOptionPane.showInputDialog(null,"请选择要输出的文件类型","输出",JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
			if(option.equalsIgnoreCase("txt"))
			{
				String path=fileName.split("\\.")[0]+".txt";
				BufferedWriter bw = new BufferedWriter(new FileWriter(path));
				bw.append("Folder:"+folder+"\r\n");
				bw.append("Filename:"+name+"\r\n");
				bw.append("Image:"+width+"\tx\t"+height+"\r\n");
				bw.append("Name\t"+"xmin\t"+"ymin\t"+"xmax\t"+"ymax"+"\r\n");
				for(int i=0;i<num_pic;i++)
				{
					bw.append(rectList[i].getName()+"\t"+(int)(rectList[i].getX1()*p1)+"\t"+(int)(rectList[i].getY1()*p2)+"\t"+(int)(rectList[i].getX2()*p1)+"\t"+(int)(rectList[i].getY2()*p2)+"\r\n");
					//bw.append(rectList[i].getName()+"\t"+rectList[i].getX1()+"\t"+rectList[i].getY1()+"\t"+rectList[i].getX2()+"\t"+rectList[i].getY2()+"\r\n");
				}
				bw.close();
			}
			if(option.equalsIgnoreCase("xml"))
			{
				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder;
					builder = factory.newDocumentBuilder();
		            Document document = builder.newDocument();
		            document.setXmlVersion("1.0");
		            document.setXmlStandalone(true);
		            Element root = document.createElement("annotation");//root
		            document.appendChild(root);  //添加根节点
		            Element fold = document.createElement("folder"); 
		            fold.appendChild(document.createTextNode(fileName.substring(0,index))); 
		            root.appendChild(fold);
		            Element NAME=document.createElement("filename"); 
		            NAME.appendChild(document.createTextNode(name));
		            root.appendChild(NAME);
		            Element size=document.createElement("size");
		            root.appendChild(size);
		            Element WIDTH=document.createElement("width");
		            WIDTH.appendChild(document.createTextNode(Integer.toString(width)));
		            Element HEIGHT=document.createElement("height");
		            HEIGHT.appendChild(document.createTextNode(Integer.toString(height)));
		            Element DEPTH=document.createElement("depth");
		            DEPTH.appendChild(document.createTextNode(Integer.toString(3)));
		            size.appendChild(WIDTH);size.appendChild(HEIGHT);size.appendChild(DEPTH);
		            for(int i=0;i<num_pic;i++)
		            {
		            	Element obj=document.createElement("object");
		            	Element obj_name=document.createElement("name");
		            	obj_name.appendChild(document.createTextNode(rectList[i].getName()));
		            	obj.appendChild(obj_name);
		            	Element bndbox=document.createElement("bndbox");
		            	Element xmin=document.createElement("xmin");
		            	xmin.appendChild(document.createTextNode(Integer.toString((int)(rectList[i].getX1()*p1))));
		            	bndbox.appendChild(xmin);
		            	Element ymin=document.createElement("ymin");
		            	ymin.appendChild(document.createTextNode(Integer.toString((int)(rectList[i].getY1()*p2))));
		            	bndbox.appendChild(ymin);
		            	Element xmax=document.createElement("xmax");
		            	xmax.appendChild(document.createTextNode(Integer.toString((int)(rectList[i].getX2()*p1))));
		            	bndbox.appendChild(xmax);
		            	Element ymax=document.createElement("ymax");
		            	ymax.appendChild(document.createTextNode(Integer.toString((int)(rectList[i].getY2()*p2))));
		            	bndbox.appendChild(ymax);
		            	obj.appendChild(bndbox);
		            	root.appendChild(obj);
		            }
		            TransformerFactory tf = TransformerFactory.newInstance();
		            Transformer transformer = tf.newTransformer();
		            DOMSource source = new DOMSource(document);
		            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName.split("\\.")[0]+".xml"));
		            StreamResult result = new StreamResult(pw);
		            transformer.transform(source, result);
		            
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(option.equalsIgnoreCase("xls"))
			{

		        try {
					WritableWorkbook book=Workbook.createWorkbook(new FileOutputStream(fileName.split("\\.")[0]+".xls"));
					WritableSheet sheet=book.createSheet("sheet",0);
			        Label FOLDER = new Label(0,0,"Folder:"+folder);
					sheet.addCell(FOLDER);
			        Label FILE = new Label(0,1,"File:"+name);
			        sheet.addCell(FILE);
			        Label SIZE = new Label(0,2,"size:"+width+"  x  "+height);
			        sheet.addCell(SIZE);
			        Label t1 = new Label(0,3,"name");
			        Label t2 = new Label(1,3,"xmin");
			        Label t3 = new Label(2,3,"ymin");
			        Label t4 = new Label(3,3,"xmax");
			        Label t5 = new Label(4,3,"ymax");
			        sheet.addCell(t1);sheet.addCell(t2);sheet.addCell(t3);
			        sheet.addCell(t4);sheet.addCell(t5);
			        for(int i=0;i<num_pic;i++)
			        {
			        	sheet.addCell(new Label(0,4+i,rectList[i].getName()));
			        	sheet.addCell(new Label(1,4+i,Integer.toString((int)(rectList[i].getX1()*p1))));
			        	sheet.addCell(new Label(2,4+i,Integer.toString((int)(rectList[i].getY1()*p2))));
			        	sheet.addCell(new Label(3,4+i,Integer.toString((int)(rectList[i].getX2()*p1))));
			        	sheet.addCell(new Label(4,4+i,Integer.toString((int)(rectList[i].getY2()*p2))));
			        }
		            book.write();
		            book.close();
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "请先在打开要标注的图片", "提示", JOptionPane.ERROR_MESSAGE);
		}

	}
	private int getSelected()//返回他的index
	{
		int i;
		boolean flag=false;
		for(i=0;i<num_pic;i++)
		{
			if(rectList[i].getSelected())
			{
				flag=true;
				break;
			}
		}
		if(!flag)
		{
			return -1;
		}
		else
		{
			return i;
		}
	}
	public void Rename() //改名字
	{
		if(pic!=null || isBmp)
		{
			if(!isSign)//编辑模式下
			{
				int index=getSelected();
				if(index!=-1) //当前有标注框被选中
				{
					String new_name=(String)JOptionPane.showInputDialog(this,"Name:","请输入标注框的新名字");
					rectList[index].setName(new_name);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "请先选中一个标注框", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请先在编辑模式下更改标注框名字", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "请先打开一张图片", "提示", JOptionPane.ERROR_MESSAGE);
		}

	}
	public void Remove() //删除选中的框框
	{
		if(pic!=null ||isBmp)
		{
			if(!isSign)
			{
				int index=getSelected();
				if(index!=-1)
				{
					int i;
					for(i=index;i<num_pic-1;i++)
					{
						rectList[i]=rectList[i+1];
					}
					num_pic--;
					repaint();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "请选中要删除的图片", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请在编辑模式下操作", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "请先打开一张图片", "提示", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void Copy()//按下复制这个按钮/ctrl+c的时候
	{
		if(pic!=null || isBmp)
		{
			if(!isSign)
			{
				int index=getSelected();
				if(index!=-1)
				{
					copy_index=index;
				}
				else
				{
					JOptionPane.showMessageDialog(this, "请先选中一个标注框", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请先在编辑模式下复制标注框", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "请先打开一张图片", "提示", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void Paste()//按下粘贴这个按钮/ctrl+v的时候
	{
		if(pic!=null || isBmp)
		{
			if(!isSign)
			{
				if(copy_index!=-1)
				{
					rectList[num_pic++]=new Rect(rectList[copy_index].getX1()-10, rectList[copy_index].getY1()-10, rectList[copy_index].getX2()-10, rectList[copy_index].getY2()-10);
					rectList[num_pic-1].setName(rectList[copy_index].getName()+"(1)");
					unSelect();
					rectList[num_pic-1].Select(true);
					copy_index=-1;
					repaint();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "请先复制一个标注框", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请先在编辑模式下复制标注框", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "请先打开一张图片", "提示", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void paint(Graphics g)
	{
		if(pic!=null || isBmp)
		{
			super.paintComponent(g);
			g.drawImage(pic, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setColor(Color.BLACK);
			for (int i=0;i<num_pic;i++) 
			{
				if(rectList[i].getSelected()) //如果被选中了
				{
					g.setColor(Color.RED);
				}
				else
				{
					g.setColor(Color.BLACK);
				}
				g.drawRect(rectList[i].getX1(), rectList[i].getY1(), rectList[i].getWidth(), rectList[i].getHeight());
			}
		}
	}
	public void changePic(String fileName) throws IOException
	{
		num_pic=0; //重新标注
		BufferedImage bufferedImage = ImageIO.read(new File(fileName));   
		pic_width = bufferedImage.getWidth();   
		pic_height = bufferedImage.getHeight();
		if(fileName.split("\\.")[1].equalsIgnoreCase("bmp"))
		{
			
//			bmp_data=new int[pic_height][pic_width];
//			bmp_data=BmpReader.ReadBMPPic(fileName);
			pic=new ImageIcon(Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource())).getImage();
			isBmp=true;
		}
		else
		{
			isBmp=false;
			//pic=ImageIO.read(getRegisteredKeyStrokes())
			pic=new ImageIcon(fileName).getImage(); 
			
		}
		p1=(double)pic_width/(double)this.getWidth();
		p2=(double)pic_height/(double)this.getWidth();
		this.fileName=fileName;
		//System.out.println("p1:"+p1+"p2"+p2);
		//System.out.println(fileName.substring(fileName.lastIndexOf("\\"),fileName.length()-1));
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null || isBmp)
		{
			if(!isSign) //如果是编辑模式的话
			{
				x1=e.getX();
				y1=e.getY();
				if(now==-1)//它点选的附近没有
				{
					unSelect();
				}
				else
				{
					unSelect();
					rectList[now].Select(true);
				}
				repaint();
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null || isBmp)
		{
			if(isSign) //标注模式
			{
				x1=e.getX();
				y1=e.getY();
				rectList[num_pic++]=new Rect(x1,y1,0,0);
			}
			else //编辑模式下
			{
				x1=e.getX();
				y1=e.getY();
				if(now!=-1)
				{
					rectList[now].Select(true);
				}
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null || isBmp)
		{
			if(isSign)
			{
				String name=(String)JOptionPane.showInputDialog(this,"Name:","请输入标注框的名字");
				rectList[num_pic-1].setName(name);
				unSelect();
				rectList[num_pic-1].Select(true);
				repaint();
			}
			else
			{
				repaint();
			}
		}
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
		if(pic!=null || isBmp)
		{
			if(isSign)
			{
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
			else
			{
				if(now!=-1)
				{
			        x2 = e.getX();
			        y2 = e.getY();
			        unSelect();
			        rectList[now].Select(true);
					if(status.equalsIgnoreCase("INNER"))
					{
				        int l_w=x2-(rectList[now].getX2()+rectList[now].getX1())/2; //在x轴上移动了多少
				        int l_h=y2-(rectList[now].getY2()+rectList[now].getY1())/2; //在y轴上移动了多少
				        rectList[now].Move(l_w, l_h);
				       // System.out.println("x1:"+rectList[now].getX1());
					}
					if(status.equalsIgnoreCase("UP"))
					{
				        int scale=y2-rectList[now].getY1();
				        rectList[now].resize(scale, "UP");
					}
					if(status.equalsIgnoreCase("DOWN"))
					{
				        int scale=y2-rectList[now].getY2();
				        rectList[now].resize(scale, "DOWN");
					}
					if(status.equalsIgnoreCase("LEFT"))
					{
						int scale=x2-rectList[now].getX1();
						rectList[now].resize(scale, "LEFT");
					}
					if(status.equalsIgnoreCase("RIGHT"))
					{
						int scale=x2-rectList[now].getX2();
						rectList[now].resize(scale, "RIGHT");
					}
					repaint();
				}
			}
		}

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null || isBmp)
		{
			x1=e.getX();
			y1=e.getY();
			if(this.isSign) //标注模式
			{
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
			else //编辑模式
			{
				checkRect(x1, y1);
				if(now==-1)//附近没有标注框
				{
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				else //返回了某个标注框
				{
					if(status.equalsIgnoreCase("UP") || status.equalsIgnoreCase("DOWN"))
					{
						setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
					}
					if(status.equalsIgnoreCase("LEFT") || status.equalsIgnoreCase("RIGHT"))
					{
						setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					}
					if(status.equalsIgnoreCase("LEFTUP") || status.equalsIgnoreCase("RIGHTDOWN"))
					{
						setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
					}
					if(status.equalsIgnoreCase("RIGHTUP") || status.equalsIgnoreCase("LEFTDOWN"))
					{
						setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
					}
					if(status.equalsIgnoreCase("INNER"))
					{
						setCursor(new Cursor(Cursor.MOVE_CURSOR));
					}
				}
			}
		}
	}
//	@Override
/*	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if(pic!=null)
		{
			int num = e.getWheelRotation();
			scale=2;
			BufferedImage image=null;
			BufferedImage newImage=null;
			try {
				image = ImageIO.read(new File(this.fileName));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pic=ImageResize.zoomOutImage(image, new Integer(2));
			pic_width=(int)(pic_width*scale);
			pic_height=(int)(pic_height*scale);
			//pic=pic.getScaledInstance((int)(pic_width*scale), (int)(pic_height*scale), Image.SCALE_REPLICATE);
			repaint();
		}
	}
*/	
	

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
