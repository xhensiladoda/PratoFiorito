package pratoFiorito;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Flower extends Block implements Serializable
{
//	private int value;
	
	public Flower()
	{	
		super();
		setValue(-1);
	}
	
	@Override
	public void updateExposedIcon() {
		setIcon(new ImageIcon("images/flower.jpg"));
	}
	
//	public void setIcon(int n)
//	{
//		if(n==-1)
//		icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/lily.jpg");
//	}
	
	public String getString()
	{
		return getIcon().toString();
	}
}

