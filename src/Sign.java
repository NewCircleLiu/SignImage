import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Sign {
	JFrame frame=new JFrame("Sign");
//	JPanel mainPanel=new JPanel();
	JPanel p1=new JPanel(); //���ܰ�ť
	JPanel p2=new JPanel(); //����ģʽ
	JPanel p3=new JPanel(); //չʾͼƬ��λ��
	JButton jb1=new JButton("��");
	JButton jb2=new JButton("����"); //ͬ����ctrl+c
	JButton jb3=new JButton("ճ��"); //ͬ����ctrl+v
	JButton jb4=new JButton("���"); //�����ĳ�ļ�
	JButton jb5=new JButton("����");
	JRadioButton radiobutton1=new JRadioButton("��עģʽ",true); //��ʼΪ��עģʽ
	JRadioButton radiobutton2=new JRadioButton("�༭ģʽ",false);
	ButtonGroup bgroup=new ButtonGroup();
	boolean isSign=true;
	public Sign()
	{
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		GridBagConstraints con=new GridBagConstraints();
		frame.setLayout(new GridLayout(8, 1));
		con.gridx=0;
		con.gridy=0;
		con.gridheight=1;
		con.gridwidth=1;
		frame.getContentPane().add(p1,con);
		con.gridx=0; //��0��
		con.gridy=1; //��1��
		con.gridheight=1; //��Խ��1��
		con.gridwidth=1; //һ��
		frame.getContentPane().add(p2,con);
		con.gridx=0; //��0��
		con.gridy=2; //��1��
		con.gridheight=6; //��Խ��1��
		con.gridwidth=1; //һ��
		frame.getContentPane().add(p3,con);
		//p3ΪչʾͼƬ��
		
		p1.add(jb1);p1.add(jb2);p1.add(jb3);p1.add(jb4);p1.add(jb5);
		jb1.setSize(200, 200);
		jb2.setSize(200, 200);
		jb3.setSize(200, 100);
		jb4.setSize(100, 100);
		jb5.setSize(100, 100);
		//p1Ϊ���ܰ�
		
		p2.add(radiobutton1);p2.add(radiobutton2);
		bgroup.add(radiobutton1);
		bgroup.add(radiobutton2);
		//
		frame.setVisible(true);
		frame.setSize(1000, 800);
	}
	public static void main(String[] args)
	{
		Sign sign=new Sign();
	}
}
