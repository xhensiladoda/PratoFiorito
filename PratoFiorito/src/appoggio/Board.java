package appoggio;
import bozza.Block;
import bozza.Number;
import bozza.Flower;
import bozza.Matrice;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;



public class Board extends JFrame implements ActionListener
{
	private Matrice board;
	final static int bRows=8;
	final static int bColumns=7;
	private MyButton[][] Mbuttons;
	private int nFlowers=10;
	private LinkedList flowersList;
	private LinkedList buttonsList;
	//private static JFrame frame=new JFrame("Prato Fiorito!");
	private static JPanel panel=new JPanel();
	private MouseListener listener;
	
	public Board()
	{
		setTitle("Prato Fiorito!");
		board=new Matrice(bRows,bColumns);
		panel.setLayout(new GridLayout(bRows,bColumns));
	
		fillButtonBoard();
		fillBoard();
//		ImageIcon icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/1.jpg");
//		Mbuttons[1][1].setIcon(icon);
//		Mbuttons[1][1].setEnabled(false);
		
		add(panel,BorderLayout.CENTER);
		for(int i=0;i<bRows;i++)
			for(int j=0;j<bColumns;j++)
			{
					listener=new MyListener(Mbuttons[i][j],board,Mbuttons,flowersList);
					Mbuttons[i][j].addMouseListener(listener);
					
			}
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		show();
		
		
	}
	
	/*public static void main(String[] args) 
	{
		new Board();
		
	}
	*/
	
	public void fillBoard()
	{	
		System.out.println("Eccomi dentro!");
		for(int i=0; i<bRows; i++)
			for(int j=0; j<bColumns; j++)
			{	
				board.fillMatrice(new Number(), i, j);	
				board.getElement(i, j).setX(i);
				board.getElement(i, j).setY(j);
			}
		
		System.out.println("Eccomi fuori!");
		
		int l;
		int m;
		int count;
		
		board.clearMatrice();
		flowersList=new LinkedList();
		
		for(int i=0;i<nFlowers;i++)
		{
			l=(int)(Math.random()*(bRows));
			m=(int)(Math.random()*(bColumns));
			//control if busy
			if(!(board.getElement(l, m) instanceof Flower))
				{	
					board.fillMatrice(new Flower(), l, m);
					(board.getElement(l, m)).setX(l);
					(board.getElement(l, m)).setY(m);
					
					//((Flower)
					(board.getElement(l, m)).setIcon(-1);
					ImageIcon icon=new ImageIcon();
					icon=((Flower)(board.getElement(l, m))).getIcon();
					System.out.println(((Flower)(board.getElement(l, m))).getString());
					
					flowersList.add(board.getElement(l, m));
					
				}
			else i--;	
		}
		
		
		System.out.println("Numero fiori inseriti: "+flowersList.size());
		
		for(int i=0;i<bRows;i++)
			for(int j=0;j<bColumns;j++)
			{	
				count=0;
				
				if(!(board.getElement(i, j)instanceof Flower))
					
				{	
					
					if((i>0)&&(board.getElement(i-1,j)instanceof Flower))
						count++; 
					if((i>0 && j<(bColumns -1))&&(board.getElement(i-1, j+1)instanceof Flower))
					    count++;
					if((j<(bColumns -1))&&(board.getElement(i, j+1)instanceof Flower))
						count++;
					if(i<(bRows -1)&& j<(bColumns -1)&&(board.getElement(i+1, j+1)instanceof Flower))
						count++;
					if(i<(bRows -1)&&(board.getElement(i+1, j)instanceof Flower))
						count++;
					if(i<(bRows -1)&& j>0 &&(board.getElement(i+1, j-1)instanceof Flower))
						count++;
					if(j>0 && (board.getElement(i, j-1)instanceof Flower))
						count++;
					if(i>0 && j>0 &&(board.getElement(i-1, j-1)instanceof Flower))
						count++;
					
						
					
						

						board.fillMatrice(new Number(), i, j);

						board.getElement(i, j).setValue(count);
					    board.getElement(i, j).setIcon(count);
					    board.getElement(i, j).setX(i);
						board.getElement(i, j).setY(j);
						
					
				}
				
			}
		
		
		System.out.println("Finito inserimento numeri!");
	}
	
	public void controlClick(Block block)
	{
		buttonsList=new LinkedList();
		int x=block.retX();
		int y=block.retY();
		boolean gameOver=false;
		
		switch(block.getValue())
			{
				case -1: controlFlowers();
						 gameOver=true;
						 break;
				case  0: controlEmpty(block);
						 break;
				
				default: controlNumber(block); break;					
				
			}
		
		showButtons(buttonsList);
		if(gameOver==true)
		 gameOver();
	}
	
	public void showButtons(LinkedList list)
	{
		for(int i=0;i<list.size();i++)
		{	
			int x=((Block)list.get(i)).retX();
			int y=((Block)list.get(i)).retY();
			Mbuttons[x][y].setIcon(((Block)list.get(i)).getIcon());
		}
	}
	
	public void gameOver()
	{
//		TODO gameOver constructor!
	}
	
	public void controlNumber(Block block)
	{
		buttonsList.add(block);
	}
	
	public void controlFlowers()
	{	
		buttonsList=(LinkedList)flowersList.clone();
	}
	
	public void controlEmpty(Block block)
	{
		int x=block.retX();
		int y=block.retY();
		
		buttonsList.add(block);
		
		if((x>0)&&(board.getElement(x-1,y).getValue()==0))
			controlEmpty(board.getElement(x-1,y));
		if((x>0 && y<(bColumns -1))&&(board.getElement(x-1, y+1).getValue()==0))
		   controlEmpty(board.getElement(x-1, y+1));
		if((y<(bColumns -1))&&(board.getElement(x, y+1).getValue()==0))
			controlEmpty(board.getElement(x, y+1));
		if(x<(bRows -1)&& y<(bColumns -1)&&(board.getElement(x+1, y+1).getValue()==0))
			controlEmpty(board.getElement(x+1, y+1));
		if(x<(bRows -1)&&(board.getElement(x+1, y).getValue()==0))
			controlEmpty(board.getElement(x+1, y));
		if(x<(bRows -1)&& y>0 &&(board.getElement(x+1, y-1).getValue()==0))
			controlEmpty(board.getElement(x+1, y-1));
		if(y>0 && (board.getElement(x, y-1).getValue()==0))
			controlEmpty(board.getElement(x, y-1));
		if(x>0 && y>0 &&(board.getElement(x-1, y-1).getValue()==0))
			controlEmpty(board.getElement(x-1, y-1));
		
	}
	public void fillButtonBoard()
	{	
		Mbuttons=new MyButton[bRows][bColumns];
		
		
		for(int i=0; i<bRows; i++)
			for(int j=0; j<bColumns; j++)
			{	
				Mbuttons[i][j]=new MyButton();
				Mbuttons[i][j].setX(i);
				Mbuttons[i][j].setY(j);
				Mbuttons[i][j].setPreferredSize(new Dimension(20,20));
				panel.add(Mbuttons[i][j]);
			
			}
		
	
	}
	
	public void setNFlower(int n)
	{
		nFlowers=n;
	}


	public void actionPerformed(ActionEvent event) 
	{
		for(int i=0;i<bRows;i++)
			for(int j=0;j<bColumns;j++)
			{
				if(event.getSource()==Mbuttons[i][j] && (Mbuttons[i][j].isSelected()==true) && (board.getElement(i, j).getValue()==-3))
				{
					Mbuttons[i][j].removeMouseListener(listener);
				}
			}
		
	}
	

}



class MyButton extends JToggleButton
{
	private int bX;
	private int bY;
	private JToggleButton button;
	public MyButton()
	{
		super();
		button=new JToggleButton();
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



class MyListener implements MouseListener
{
	private MyButton button;
	private Matrice board;
	private LinkedList buttonsList;
	private MyButton[][] Mbuttons;
	private LinkedList flowersList;
	private int bRows,bColumns;
	
	
	public MyListener(MyButton button,Matrice board,MyButton[][] Mbuttons,LinkedList flowersList)
	{
		this.button=button;
		this.board=board;
		this.Mbuttons=Mbuttons;
		this.flowersList=flowersList;
		
		bRows=board.getRows();
		bColumns=board.getColumns();
		
		
	}

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	
	public void mouseReleased(MouseEvent event) 
	{
		
		button.setSelected(true);
		ImageIcon icon=new ImageIcon("C:/Users/xhensila/workspace/ProvaPratoFiorito/images/2.jpg");
		button.setIcon(icon);
		int x=button.retX();
		int y=button.retY();
	//	board.getElement(x, y).setIcon(board.getElement(x, y).getValue());
		//ImageIcon icon=board.getElement(x, y).getIcon();
		//button.setIcon(icon);
		System.out.println(icon);
		controlClick(board.getElement(x, y));
		
	}
	
	public void controlClick(Block block)
	{
		System.out.println("Sono su controloClick");
		
		buttonsList=new LinkedList();
		
		int x=block.retX();
		int y=block.retY();
		
		boolean gameOver=false;
		
		switch(board.getElement(x, y).getValue())
			{
				case -1: controlFlowers();
						 gameOver=true;
						 break;
				case  0: controlEmpty(block);
						 break;
				
				default: controlNumber(block); break;					
				
			}
		
		showButtons(buttonsList);
		if(gameOver==true)
		 gameOver();
	}
	
	public void showButtons(LinkedList list)
	{
		ImageIcon icon;
		String filename;
		LinkedList blist=list;
		System.out.println("Sono su showButton");
		for(int i=0;i<blist.size();i++)
		{	
			int x=((Block)blist.get(i)).retX();
			int y=((Block)blist.get(i)).retY();
			icon=new ImageIcon();
			
			if((Block)blist.get(i) instanceof Flower)
			icon=((Flower)blist.get(i)).getIcon();
			
			else if((Block)blist.get(i) instanceof Number)
				icon=((Number)blist.get(i)).getIcon();
			
			Mbuttons[x][y].setIcon(icon);
			board.getElement(x, y).setValue(-3);
			System.out.println(blist.get(i));
		//	System.out.println(icon);
			
		}
	}
	
	public void gameOver()
	{
		System.out.println("Sono su GameOver");
		JOptionPane.showMessageDialog(null,"Game Over!","GameOver!",JOptionPane.WARNING_MESSAGE);
	}
	
	public void controlNumber(Block block)
	{	
		System.out.println("Sono su Number");
		buttonsList.add(block);
	}
	
	public void controlFlowers()
	{	System.out.println("Sono su Flowers");
		buttonsList=(LinkedList)flowersList.clone();
	}
	public void controlEmpty(Block block)
	{
		System.out.println("Sono su Empty");
		
		int x=block.retX();
		int y=block.retY();
		if(!buttonsList.contains(board.getElement(x, y)))
		   buttonsList.add(board.getElement(x, y));
		
		if((x>0)&&(board.getElement(x-1,y).getValue()==0) && !buttonsList.contains(board.getElement(x-1, y)))
			controlEmpty(board.getElement(x-1,y));
		if((x>0 && y<(bColumns -1))&&(board.getElement(x-1, y+1).getValue()==0) &&!buttonsList.contains(board.getElement(x-1, y+1)))
		   controlEmpty(board.getElement(x-1, y+1));
		if((y<(bColumns -1))&&(board.getElement(x, y+1).getValue()==0)&& !buttonsList.contains(board.getElement(x, y+1)))
			controlEmpty(board.getElement(x, y+1));
		if(x<(bRows -1)&& y<(bColumns -1)&&(board.getElement(x+1, y+1).getValue()==0) && !buttonsList.contains(board.getElement(x+1, y+1)))
			controlEmpty(board.getElement(x+1, y+1));
		if(x<(bRows -1)&&(board.getElement(x+1, y).getValue()==0) && !buttonsList.contains(board.getElement(x+1, y)))
			controlEmpty(board.getElement(x+1, y));
		if(x<(bRows -1)&& y>0 &&(board.getElement(x+1, y-1).getValue()==0) && !buttonsList.contains(board.getElement(x+1, y-1)))
			controlEmpty(board.getElement(x+1, y-1));
		if(y>0 && (board.getElement(x, y-1).getValue()==0) && !buttonsList.contains(board.getElement(x, y-1)))
			controlEmpty(board.getElement(x, y-1));
		if(x>0 && y>0 &&(board.getElement(x-1, y-1).getValue()==0)&& !buttonsList.contains(board.getElement(x-1, y-1)))
			controlEmpty(board.getElement(x-1, y-1));
		
	}
	
}











