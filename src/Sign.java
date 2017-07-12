import java.awt.*;
import java.awt.event.*;
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
	//
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
		p1.add(jb1);p1.add(jb2);p1.add(jb3);p1.add(jb4);p1.add(jb5);
		
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
		//pic.addMouseListener(this);//ͼƬ����~
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getComponent()==jb1)
		{
			int result=chooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				String fileName = chooser.getSelectedFile().getPath();
				//ImageIcon image=new ImageIcon(fileName);
				p3.changePic(fileName);
				//image.setImage(image.getImage().getScaledInstance((int)(frame.getWidth()*0.75), (int)(frame.getHeight()*0.75),Image.SCALE_DEFAULT));
				//pic.setIcon(image);
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
