import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Test {
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
	public Test()
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
		
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints s= new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH; 
		s.gridx=0;
		s.gridy=0;
		s.gridheight=1;
		frame.getContentPane().add(p1,s);
		s.gridx=0;
		s.gridy=1;
		s.gridheight=1;
		frame.getContentPane().add(p2,s);
		s.gridx=0;
		s.gridy=2;
		s.gridheight=8;
		frame.getContentPane().add(p3,s);
		//p3ΪչʾͼƬ��
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ý���ر��¼�
				
		frame.setVisible(true);
	}
	public static void main(String args[])
	{
		Test t=new Test();
	}
	
}
