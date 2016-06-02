package pratoFiorito;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;

enum BlockStatus
{
	hide,
	flag,
	question,
	exposed,
	error
}

public abstract class Block extends MyButton implements Serializable
{
	private int x;
	private int y;
	private int value;
	private ImageIcon icon;
	private BlockStatus status;
	private MyListener listener;
	
	public BlockStatus getStatus()
	{
		return this.status;
	}
	
	public void setStatusExposed()
	{
		this.status = BlockStatus.exposed;
		this.updateIcon();
	}
	
	public void changeHideStatus()
	{
		if(status == BlockStatus.exposed)
		{
			System.out.println("ERRORE: impossibile usare changeHideStatus su un blocco esposto.");
			System.exit(1);
		}
		if(status == BlockStatus.hide)
			{
				status = BlockStatus.flag;
				
			}
		else if(status == BlockStatus.flag)
			{
				status = BlockStatus.question;	
				
			}
		else if(status == BlockStatus.question)
				status = BlockStatus.hide;
		this.updateIcon();
		
	}
	
	public void changeToError()
	{
		status=BlockStatus.error;
	}
	
	public ImageIcon getIcon() 
	{
		return icon;
	}
	
	public void updateIcon()
	{
		if(status == BlockStatus.hide)
			this.icon =new ImageIcon("images/hide.jpg");
		else if(status == BlockStatus.flag)
			setIcon(new ImageIcon("images/flag.jpg"));
		else if(status == BlockStatus.question)
			setIcon(new ImageIcon("images/question.jpg"));
		else if(status == BlockStatus.exposed)
			this.updateExposedIcon();
		else if(status==BlockStatus.error)
			setIcon(new ImageIcon("images/cross.jpg"));
	}
	
	public abstract void updateExposedIcon();
	
	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
	}
	

	public Block()
	{
		this.removeMouseListener(this.getMouseListeners()[0]);
		x=0;
		y=0;
		status = BlockStatus.hide;
		updateIcon();
	}
	
	public int retX()
	    {
	    	return x;
	    }
	public int retY()
	    {
	    	return y;
	    }

	public void setX(int x) 
	{
		this.x = x;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value=value;
	}

}
