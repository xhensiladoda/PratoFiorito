package bozza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusPanel extends JPanel
{	
	private JLabel timeLabel;
	private JLabel flowersLabel;
	private ImageIcon ticon;
	private ImageIcon ficon;
	private static StatusPanel instance;
	Timer timer;
	long startTime;
	String string;
	
	public StatusPanel()
	{	
		timer=new Timer(0,new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				long diffTime = System.currentTimeMillis () - startTime;

			                int decSeconds = (int) (diffTime % 1000 / 100);
			                int seconds = (int) (diffTime / 1000 % 60);
			                int minutes = (int) (diffTime / 60000 % 60);
			                int hours = (int) (diffTime / 3600000);

			                string = String.format ("%d:%02d:%02d", hours, minutes,
			                                          seconds);     
			}
		});
		timeLabel=new JLabel("Time: ");
		flowersLabel=new JLabel("Flowers: ");
		ticon=new ImageIcon("images/clock3.jpg");
		ficon=new ImageIcon("images/flowerblack.jpg");
		
		timeLabel.setIcon(ticon);
		flowersLabel.setIcon(ficon);
		add(timeLabel);
		add(flowersLabel);
		
		
	}
	
	public void setInstance()
	{
		this.instance=this;
	}
	
	public StatusPanel getInstance()
	{
		return instance;
	}
}
