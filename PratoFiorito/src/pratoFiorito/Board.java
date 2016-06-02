package pratoFiorito;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements Serializable {
	private Matrice board;
	private int bRows;
	private int bColumns;
	private int nFlowers;
	private LinkedList<Block> flowersList;
	private JPanel panel;
	private MyListener listener;
	private MyTimer statusPanel;
	private int count;
	private int scoperte;
	private boolean fioriScoperti;
	private boolean boardClicked;
	private boolean firstClick;


	public Board(int n, int m, int l) {

		bRows = n;
		bColumns = m;
		nFlowers = l;
		panel = new JPanel();
		statusPanel = new MyTimer();
		createBoard();
	}

	public void clearBoard() {
		board.clearMatrice();
	}

	public void clearButtonBoard() {
		panel.removeAll();
	}
	
	public void createBoard()
	{	
		System.out.println("Sono su createBoard");
		count = nFlowers;
		scoperte = 0;
		fioriScoperti = false;
		boardClicked = false;
		firstClick=false;
		setLayout(new BorderLayout());
		board=new Matrice(bRows, bColumns);
		
		panel.setLayout(new GridLayout(bRows, bColumns));
		
		statusPanel.updateFLabel(nFlowers);

		fillBoard();
		fillButtonBoard();
		addMListener();
		add(statusPanel, BorderLayout.SOUTH);
		add(panel, BorderLayout.NORTH);
		
	}

	public void fillBoard() {
		System.out.println("Eccomi dentro!");
		int l;
		int m;
		int count;

		flowersList = new LinkedList<Block>();

		for (int i = 0; i < nFlowers; i++) {
			l = (int) (Math.random() * (bRows));
			m = (int) (Math.random() * (bColumns));
			// control if busy
			if (board.getElement(l, m) != null) {
				i--;
			} else // libero
			{
				Flower f = new Flower();
				f.setX(l);
				f.setY(m);
				board.fillMatrice(f, l, m);
				flowersList.add(f);
			}

		}

		System.out.println("Numero fiori inseriti: " + flowersList.size());

		for (int i = 0; i < bRows; i++)
			for (int j = 0; j < bColumns; j++) {
				count = 0;

				if (!(board.getElement(i, j) instanceof Flower))

				{

					if ((i > 0)
							&& (board.getElement(i - 1, j) instanceof Flower))
						count++;
					if ((i > 0 && j < (bColumns - 1))
							&& (board.getElement(i - 1, j + 1) instanceof Flower))
						count++;
					if ((j < (bColumns - 1))
							&& (board.getElement(i, j + 1) instanceof Flower))
						count++;
					if (i < (bRows - 1)
							&& j < (bColumns - 1)
							&& (board.getElement(i + 1, j + 1) instanceof Flower))
						count++;
					if (i < (bRows - 1)
							&& (board.getElement(i + 1, j) instanceof Flower))
						count++;
					if (i < (bRows - 1)
							&& j > 0
							&& (board.getElement(i + 1, j - 1) instanceof Flower))
						count++;
					if (j > 0 && (board.getElement(i, j - 1) instanceof Flower))
						count++;
					if (i > 0
							&& j > 0
							&& (board.getElement(i - 1, j - 1) instanceof Flower))
						count++;

					Number n = new Number(count);
					board.fillMatrice(n, i, j);

					n.setX(i);
					n.setY(j);

				}

			}

		System.out.println("Finito inserimento numeri!");
	}

	public void fillButtonBoard() {
		
		panel.removeAll();
		System.out.println("inserisco i buttons " +bRows +","+bColumns);
 		for (int i = 0; i < bRows; i++) {
			for (int j = 0; j < bColumns; j++) {
				MyButton b = board.getElement(i, j);
				b.setPreferredSize(new Dimension(20, 20));

				panel.add(b);
			}
		}
//		this.repaint();
	}

	public void setNFlower(int n) {
		nFlowers = n;
	}

	public int getNFlower() {
		return nFlowers;
	}

	public int getCount() {
		return count;
	}

	public void decCount() {
		count--;
	}

	public void incCount() {
		count++;
	}

	public void setRows(int rows) {
		bRows = rows;
	}

	public void setColumns(int columns) {
		bColumns = columns;
	}

	public int getScoperte() {
		return scoperte;
	}

	public boolean getFioriScoperti() {
		return fioriScoperti;
	}

	public void setBoardClicked() {
		boardClicked = true;
	}

	public boolean getBoardClicked() {
		return boardClicked;
	}
	
	public void setfirstClick() {
		firstClick = true;
	}

	public boolean getfirstClick() {
		return firstClick;
	}
	

	public void addMListener() {
		
		for (int i = 0; i < bRows; i++)
			for (int j = 0; j < bColumns; j++) {

				listener = new MyListener(this, statusPanel);
				board.getElement(i, j).addMouseListener(listener);
			}

	}

	public void blochettinoClicked(Block block) {
		
		System.out.println(" sono su blochettino clicked " + firstClick);
		
		System.out.println(block.getMouseListeners()[0] + "//////");
		if (block instanceof Flower) {
			
			if(firstClick==false) // primo click ed è fiore!
			{	
				System.out.println("Fiore beccato! nuovo mondo");
				createBoard();
				System.out.println(firstClick + "***");
				int x=block.retX();
				int y=block.retY();
				board.getElement(x,y).getMouseListeners()[0].mouseReleased(new MouseEvent(board.getElement(x, y), 0, 0, InputEvent.BUTTON1_MASK, 0, 0, 0, 0, 0, false, 0));
				System.out.println("Generato mouseEvent");
				
			}
			else
			{
				fioriScoperti = true;
				System.out.println(flowersList.size());
				for (Block b : flowersList) {
					b.setStatusExposed();
					b.repaint();
				}
				Block bl;
				for (int i = 0; i < bRows; i++)
					for (int j = 0; j < bColumns; j++) {
						bl = board.getElement(i, j);
						if (bl.getStatus() == BlockStatus.flag) {
							bl.changeToError();
							bl.updateIcon();
							bl.repaint();
						}
					}
				setGameOver();
			}
				
			
		} else if (block instanceof Number) {

			List<Number> list = this.getNearBlockToExpose((Number) block);

			for (Block b : list) {
				if (b.getStatus() != BlockStatus.hide)
					System.out.println("BLAAAAAAAAAAA");
			}

			scoperte = scoperte + list.size();
			for (Block b : list) {
				b.setStatusExposed();
				b.repaint();
				if (b.getMouseListeners().length != 0)
					b.removeMouseListener(b.getMouseListeners()[0]);
			}

		}

		System.out.println(scoperte);
		System.out.println((bColumns * bRows) - nFlowers);
		if (scoperte == ((bColumns * bRows) - nFlowers))
			winGame();
	}

	public List<Number> getNearBlockToExpose(Number arg) {

		List<Number> list = new LinkedList<Number>();
		List<Number> searchList = new LinkedList<Number>();

		if (arg.getValue() == 0) {
			searchList.add(arg);// solo blocchi vuoti
		} else {
			list.add(arg);
		}

		while (searchList.size() != 0) {
			for (int i = 0; i < searchList.size(); i++) {
				Number block = searchList.get(i);

				list.add(block);
				searchList.remove(block);

				int x = block.retX();
				int y = block.retY();

				Number b;
				b = (Number) board.getElement(x + 1, y - 1); // destra su
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x + 1, y); // destra
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x + 1, y + 1); // destra giu
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x, y - 1); // su
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x, y + 1); // giu
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x - 1, y - 1); // sinistra su
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x - 1, y); // sinistra
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}
				b = (Number) board.getElement(x - 1, y + 1); // sinistra giu
				if (b != null && b.getStatus() != BlockStatus.exposed
						&& b.getStatus() != BlockStatus.flag
						&& !list.contains(b) && !searchList.contains(b)) {
					if (b.getValue() == 0)
						searchList.add(b);
					else
						list.add(b);
				}

			}
		}

		return list;

	}

	public void setGameOver() {
		statusPanel.StopMyTimer();

		for (int i = 0; i < bRows; i++) {
			for (int j = 0; j < bColumns; j++) {
				Block b = board.getElement(i, j);
				if (b.getMouseListeners().length != 0) {
					b.removeMouseListener(b.getMouseListeners()[0]);
				}
			}
		}

		System.out.println("Sono su GameOver");
		JOptionPane.showMessageDialog(null, "Game Over!", "GameOver!",
				JOptionPane.WARNING_MESSAGE);
	}

	public void winGame() {
		statusPanel.StopMyTimer();
		for (int i = 0; i < bRows; i++) {
			for (int j = 0; j < bColumns; j++) {
				Block b = board.getElement(i, j);
				if (b.getMouseListeners().length != 0) {
					b.removeMouseListener(b.getMouseListeners()[0]);
				}
			}
		}
		JOptionPane.showMessageDialog(null,
				"Congratulations!\n" + "You win!\n", null,
				JOptionPane.PLAIN_MESSAGE);
	}

	public Block getBlock(int x, int y) {
		return board.getElement(x, y);
	}
	
	
	private void readObject(ObjectInputStream in) throws IOException,
	ClassNotFoundException {
		
		in.defaultReadObject();
		boardClicked=false;
		this.setVisible(true);
	}

}
