import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Sign implements MouseListener{
	JFrame frame=new JFrame("Sign");
	JPanel p1=new JPanel(); //���ܰ�ť
	JPanel p2=new JPanel(); //����ģʽ
	PicPanel p3=new PicPanel(); //չʾͼƬ��λ��
	//
	JButton jb1=new JButton("��");
	JButton jb2=new JButton("����"); //ͬ����ctrl+c
	JButton jb3=new JButton("ճ��"); //ͬ����ctrl+v
	JButton jb4=new JButton("���"); //�����ĳ�ļ�
	JButton jb5=new JButton("����");
	JButton jb6=new JButton("ɾ��");

	JRadioButton radiobutton1=new JRadioButton("��עģʽ",true); //��ʼΪ��עģʽ
	JRadioButton radiobutton2=new JRadioButton("�༭ģʽ",false);
	ButtonGroup bgroup=new ButtonGroup();
	
	//
	JFileChooser chooser = new JFileChooser();
	//JLabel pic=new JLabel();
	JLabel tool = new JLabel("������");
	JLabel mode = new JLabel("ģʽѡ��");
	
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
		//p2Ϊģʽѡ��
		
		
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.getContentPane().add(p1);
		p1.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.05)));
		frame.getContentPane().add(p2);
		p2.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.05)));
		frame.getContentPane().add(p3);
		p3.setPreferredSize(new Dimension((int)(frame.getWidth()*0.9),(int)(frame.getHeight()*0.8)));
		//p3ΪչʾͼƬ��
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ý���ر��¼�
				
		frame.setVisible(true);
		

	}
	public void start()
	{
		
		
		jb1.addMouseListener(this);
		//��
		jb2.addMouseListener(this);
		//����
		jb3.addMouseListener(this);
		//ճ��
		jb4.addMouseListener(this);
		//���
		jb5.addMouseListener(this);
		//����
		jb6.addMouseListener(this);
		//ɾ��
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
		if(e.getComponent()==jb1) //��
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
		if(e.getComponent()==jb2) //����
		{
			p3.Copy();
		}
		if(e.getComponent()==jb3) //ճ��
		{
			p3.Paste();
		}
		if(e.getComponent()==jb4) //���
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
			p3.Rename(); //������
		}
		if(e.getComponent()==jb6)
		{
			p3.Remove();
		}
		if(e.getComponent()==radiobutton1 || e.getComponent()==radiobutton2)
		{
			if(radiobutton1.isSelected()){ //��עmode
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
