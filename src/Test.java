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
		//p2为模式选择
		
		
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
		//p3为展示图片的
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置界面关闭事件
				
		frame.setVisible(true);
	}
	public static void main(String args[])
	{
		Test t=new Test();
	}
	
}
