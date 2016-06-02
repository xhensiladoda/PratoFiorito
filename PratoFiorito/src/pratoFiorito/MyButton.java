package pratoFiorito;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;

class MyButton extends JButton implements Serializable
{
	private int bX;
	private int bY;
	public MyButton()
	{
		super();
	}
	
	public void setX(int x)
	{
		bX=x;
	}
	
	
	public void setY(int y)
	{
		bY=y;
	}
	
	public int retY()
	{
		return bY;
	}
	
	public int retX()
	{
		return bX;
	}
}

















