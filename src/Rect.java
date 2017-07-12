public class Rect {
	private int abX1;//绝对位置,左上角
	private int abY1;//绝对位置,左上角
	private int abX2;//绝对位置,右下角
	private int abY2;//绝对位置,右下角
	private int width;//宽
	private int height;//高
	private String name;//名字
	private int x1;//在图片中的位置
	private int y1;
	private int x2;
	private int y2;
	private boolean isSeleted;
	public Rect(int abX1,int abY1,int abX2,int abY2)
	{
		this.abX1=abX1;
		this.abY1=abY1;
		this.abX2=abX2;
		this.abY2=abY2;
		this.width=abX2-abX1;
		this.height=abY2-abY1;
		name=new String();
		isSeleted=false;
	}
	public boolean getSelected()
	{
		return isSeleted;
	}
	public void Select()
	{
		isSeleted=!isSeleted;
	}
	public int getWidth()
	{
		return Math.abs(abX2-abX1);
	}
	public int getHeight()
	{
		return Math.abs(abY2-abY1);
	}
	public int getX1()
	{
		return this.abX1;
	}
	public int getY1()
	{
		return this.abY1;
	}
	public void setX1(int x)
	{
		this.abX1=x;
	}
	public void setY1(int y)
	{
		this.abY1=y;
	}
	public void setX2(int x)
	{
		this.abX2=x;
	}
	public void setY2(int y)
	{
		this.abY2=y;
	}
	public void setName(String name)
	{
		this.name=name;
	}
}
