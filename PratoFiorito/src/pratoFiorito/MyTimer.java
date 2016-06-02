package pratoFiorito;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyTimer extends JPanel implements ActionListener,Serializable
{	
	Timer timer;
	long startTime;
	String string;
	private JLabel timeLabel;
	private JLabel flowersLabel;
	long passedTime;
	long difftime;

	
	public MyTimer()
	{	
		setLayout(new BorderLayout());
		timer=new Timer(0,this);
		timeLabel=new JLabel();
		flowersLabel=new JLabel();
		ImageIcon ticon=new ImageIcon("images/clock.jpg");
		ImageIcon ficon=new ImageIcon("images/flowerblack.jpg");
		passedTime=0;
		difftime=0;
		timeLabel.setIcon(ticon);
		flowersLabel.setIcon(ficon);
		
		add(timeLabel,BorderLayout.WEST);
		add(flowersLabel,BorderLayout.EAST);

	}
	
	public void actionPerformed(ActionEvent event) 
	{
		
					
	                difftime = passedTime + System.currentTimeMillis () - startTime;
	               		
	                int seconds = (int) (difftime / 1000 % 60);
	                int minutes = (int) (difftime / 60000 % 60);
	                int hours = (int) (difftime / 3600000);

	                string = String.format ("%d:%02d:%02d", hours, minutes,
	                                          seconds);     
	                timeLabel.setText (string);
	}
	
	public void StartMyTimer()
	{
		 startTime = System.currentTimeMillis ();	
		 timer.start ();
	}
	public void StopMyTimer()
	{
		timer.stop();
	}
	
	public String updateString()
	{
		return string;
	}
	
	public void updateFLabel(int n)
	{	
		String s=String.format("%d", n);
		flowersLabel.setText(s);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,
	ClassNotFoundException {

		in.defaultReadObject();
		timer=new Timer(0,this);
		passedTime=difftime;
	}
}

