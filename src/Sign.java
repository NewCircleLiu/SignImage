import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Sign implements MouseListener{
	JFrame frame=new JFrame("Sign");
	JPanel p1=new JPanel(); //功能按钮
	JPanel p2=new JPanel(); //两种模式
	PicPanel p3=new PicPanel(); //展示图片的位置
	//
	JButton jb1=new JButton("打开");
	JButton jb2=new JButton("复制"); //同等于ctrl+c
	JButton jb3=new JButton("粘贴"); //同等于ctrl+v
	JButton jb4=new JButton("输出"); //输出到某文件
	JButton jb5=new JButton("改名");
	JButton jb6=new JButton("删除");

	JRadioButton radiobutton1=new JRadioButton("标注模式",true); //初始为标注模式
	JRadioButton radiobutton2=new JRadioButton("编辑模式",false);
	ButtonGroup bgroup=new ButtonGroup();
	
	//
	JFileChooser chooser = new JFileChooser();
	//JLabel pic=new JLabel();
	JLabel tool = new JLabel("工具栏");
	JLabel mode = new JLabel("模式选择");
	
	boolean isSign=true;
	public Sign()
	{
		frame.setSize(1000, 800);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		p1.add(tool);
		p1.add(jb1);p1.add(jb2);p1.add(jb3);p1.add(jb4);p1.add(jb5);p1.add(jb6);
		
		p2.add(mode);
		p2.add(radiobutton1);p2.add(radiobutton2);
		bgroup.add(radiobutton1);
		bgroup.add(radiobutton2);
		//p2为模式选择
		
		
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.getContentPane().add(p1);
		p1.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.05)));
		frame.getContentPane().add(p2);
		p2.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.05)));
		frame.getContentPane().add(p3);
		p3.setPreferredSize(new Dimension((int)(frame.getWidth()*0.9),(int)(frame.getHeight()*0.8)));
		//p3为展示图片的
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置界面关闭事件
				
		frame.setVisible(true);
		

	}
	public void start()
	{
		
		
		jb1.addMouseListener(this);
		//打开
		jb2.addMouseListener(this);
		//复制
		jb3.addMouseListener(this);
		//粘贴
		jb4.addMouseListener(this);
		//输出
		jb5.addMouseListener(this);
		//改名
		jb6.addMouseListener(this);
		//删除
		//frame.setFocusable(true);

		radiobutton1.addMouseListener(this);
		radiobutton2.addMouseListener(this);
		jb1.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e){
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_C)
				{
					p3.Copy();
					//System.out.println("sss");
				}
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_V)
				{
					p3.Paste();
				}
			}
		}
		);
		radiobutton1.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e){
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_C)
				{
					p3.Copy();
					//System.out.println("sss");
				}
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_V)
				{
					p3.Paste();
				}
			}
		}
		);
		radiobutton2.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e){
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_C)
				{
					p3.Copy();
					//System.out.println("sss");
				}
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_V)
				{
					p3.Paste();
				}
			}
		}
		);
		
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getComponent()==jb1) //打开
		{
			int result=chooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				String fileName = chooser.getSelectedFile().getPath();
				//ImageIcon image=new ImageIcon(fileName);
				try {
					p3.changePic(fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//image.setImage(image.getImage().getScaledInstance((int)(frame.getWidth()*0.75), (int)(frame.getHeight()*0.75),Image.SCALE_DEFAULT));
				//pic.setIcon(image);
			}
		}
		if(e.getComponent()==jb2) //复制
		{
			p3.Copy();
		}
		if(e.getComponent()==jb3) //粘贴
		{
			p3.Paste();
		}
		if(e.getComponent()==jb4) //输出
		{
			try {
				p3.Output();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getComponent()==jb5)
		{
			p3.Rename(); //改名儿
		}
		if(e.getComponent()==jb6)
		{
			p3.Remove();
		}
		if(e.getComponent()==radiobutton1 || e.getComponent()==radiobutton2)
		{
			if(radiobutton1.isSelected()){ //标注mode
				p3.setMode(true);
			}
			else{
				p3.setMode(false);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	public static void main(String[] args)
	{
		Sign sign=new Sign();
		sign.start();
	}
}
