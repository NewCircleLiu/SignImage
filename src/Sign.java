import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Sign {
	JFrame frame=new JFrame("Sign");
//	JPanel mainPanel=new JPanel();
	JPanel p1=new JPanel(); //功能按钮
	JPanel p2=new JPanel(); //两种模式
	JPanel p3=new JPanel(); //展示图片的位置
	JButton jb1=new JButton("打开");
	JButton jb2=new JButton("复制"); //同等于ctrl+c
	JButton jb3=new JButton("粘贴"); //同等于ctrl+v
	JButton jb4=new JButton("输出"); //输出到某文件
	JButton jb5=new JButton("改名");
	JRadioButton radiobutton1=new JRadioButton("标注模式",true); //初始为标注模式
	JRadioButton radiobutton2=new JRadioButton("编辑模式",false);
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
		con.gridx=0; //第0列
		con.gridy=1; //第1行
		con.gridheight=1; //跨越了1行
		con.gridwidth=1; //一列
		frame.getContentPane().add(p2,con);
		con.gridx=0; //第0列
		con.gridy=2; //第1行
		con.gridheight=6; //跨越了1行
		con.gridwidth=1; //一列
		frame.getContentPane().add(p3,con);
		//p3为展示图片的
		
		p1.add(jb1);p1.add(jb2);p1.add(jb3);p1.add(jb4);p1.add(jb5);
		jb1.setSize(200, 200);
		jb2.setSize(200, 200);
		jb3.setSize(200, 100);
		jb4.setSize(100, 100);
		jb5.setSize(100, 100);
		//p1为功能版
		
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
