package pratoFiorito;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class MyListener implements MouseListener,Serializable
{
	private Board board;
	private MyTimer timer;
	
	public MyListener(Board board,MyTimer timer)
	{
		this.board = board;
		this.timer=timer;
	}
	
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	
	public void mouseReleased(MouseEvent event) 
	{	
		System.out.println(board.getBoardClicked());
		
		Block button = (Block)event.getSource();
		
		//left
		if(((event.getModifiers()&InputEvent.BUTTON1_MASK)== InputEvent.BUTTON1_MASK))
		{	
			if(board.getBoardClicked()==false)
				timer.StartMyTimer();
			
			if(button.getStatus()!=BlockStatus.flag)
			{
				board.blochettinoClicked(button);
			}
				
		}
		//right
		if(((event.getModifiers()&InputEvent.BUTTON3_MASK)== InputEvent.BUTTON3_MASK))
		{	
			if(board.getBoardClicked()==false)
			timer.StartMyTimer();
			
			button.changeHideStatus();
			button.repaint();
			if(button.getStatus()==BlockStatus.flag)
				{
					board.decCount();
					timer.updateFLabel(board.getCount());
				}
			if(button.getStatus()==BlockStatus.question)
				{
					board.incCount();
					timer.updateFLabel(board.getCount());
				}
		}
		
		board.setBoardClicked();
		board.setfirstClick();
		
	}
}