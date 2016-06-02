package pratoFiorito;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Game extends JFrame {
	private Board boardPanel;
	private int bRows;
	private int bColumns;
	private int nFlowers;
	private int nrDifficulty;
	transient JMenu fileMenu;
	transient JMenuBar menuBar;
	transient JMenu optionMenu;
	transient JMenu helpMenu;
	transient JMenu difficulty;

	public Game() {
		initLayout();
		StartGame();
	}
	
	private void initLayout(){
		final Game thisGame = this;

		setTitle("Prato Fiorito");

		fileMenu = new JMenu("File");

		JMenuItem newItem = fileMenu.add(new MenuItem("New",
				new MenuItemClickListener() {

					@Override
					public void menuItemClick(ActionEvent e) {
						newGame();
					}
				}));

		JMenuItem saveItem = fileMenu.add(new MenuItem("Save",
				new MenuItemClickListener() {

					@Override
					public void menuItemClick(ActionEvent e) {
						saveGame(thisGame);
					}
				}));

		fileMenu.add(new MenuItem("Exit", new MenuItemClickListener() {

			@Override
			public void menuItemClick(ActionEvent e) {
				onExit();
			}
		}));

		optionMenu = new JMenu("Options");
		difficulty = new JMenu("Difficulty ");
		optionMenu.add(difficulty);
		
		difficulty.add(new MenuItem("Easy: 10 Flowers  9x9",
				new MenuItemClickListener() {

					@Override
					public void menuItemClick(ActionEvent e) {
						setDifficulty(1);
						newGame();
					}
				}));
		difficulty.addSeparator();
		difficulty.add(new MenuItem("Medium: 40 Flowers  16x16",
				new MenuItemClickListener() {

					@Override
					public void menuItemClick(ActionEvent e) {
						setDifficulty(2);
						newGame();
					}
				}));
		difficulty.addSeparator();
		difficulty.add(new MenuItem("Hard: 99 Flowers  16x30",
				new MenuItemClickListener() {

					@Override
					public void menuItemClick(ActionEvent e) {
						setDifficulty(3);
						newGame();
					}
				}));
		difficulty.addSeparator();

		helpMenu = new JMenu("Help");
		helpMenu.add(new MenuItem("About", new MenuItemClickListener() {
			
			@Override
			public void menuItemClick(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Xhensila Doda", null,
						JOptionPane.PLAIN_MESSAGE);
			}
		}));

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		menuBar.add(optionMenu);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				super.windowClosing(e);
				onExit();

			}
		});
	}

	public void onExit() {
		if (boardPanel.getBoardClicked() == true && boardPanel.getFioriScoperti()==false) {
			Object[] options = { "Save", "Don't Save", "Cancel" };

			int n = JOptionPane.showOptionDialog(null,
					"Choose an option for the current game! ", "Exit",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}

			if (n == JOptionPane.YES_OPTION) {
				Game.saveGame(this);
				System.exit(0);
			}

		} else
			System.exit(0);

	}

	public static void saveGame(Game game) {

		JMenuBar b = game.getJMenuBar();
		game.setJMenuBar(null);
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Game.txt"));
			out.writeObject(game);
			out.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			game.setJMenuBar(b);
		}
	}

	public static void loadGame() {

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"Game.txt"));
			Game newFrame = (Game) in.readObject();
			in.close();
		} catch (Exception e) {
			File f = new File("Game.txt");
			f.delete();
			System.err.println("Errore file: eliminato");
			e.printStackTrace();
		}

	}

	public void StartGame() {
		setDifficulty(1);
		drawBoard();

	}

	public void drawBoard() {
		boardPanel = new Board(bRows, bColumns, nFlowers);
		add(boardPanel, BorderLayout.CENTER);

		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocation(300, 100);
		pack();
		setVisible(true);
	}

	public void newGame() {
		if (boardPanel.getFioriScoperti() == true
				|| boardPanel.getfirstClick() == false) {
			remove(boardPanel);
			drawBoard();
		} else if (boardPanel.getfirstClick() == true
				&& boardPanel.getFioriScoperti() == false) {

			Object[] options = { "Yes, please", "No, thanks" };

			int n = JOptionPane.showOptionDialog(null,
					"Would you like to abandon this game "
							+ "and to start a New Game?", "Warning!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == JOptionPane.YES_OPTION) {
				remove(boardPanel);
				drawBoard();
			}
		}

	}

	public void setDifficulty(int n) {
		switch (n) {
		case 1:
			bRows = 9;
			bColumns = 9;
			nFlowers = 10;
			nrDifficulty = 1;
			break;

		case 2:
			bRows = 16;
			bColumns = 16;
			nFlowers = 40;
			nrDifficulty = 2;
			break;
		case 3:
			bRows = 16;
			bColumns = 30;
			nFlowers = 99;
			nrDifficulty = 3;
			break;
		}
	}

	public int getNrDifficulty() {
		return nrDifficulty;
	}

	public static void main(String[] args) {
		File file = new File("Game.txt");
		if (file.exists()) {
			loadGame();
			file.delete();

		} else
			new Game();
	}

	private void writeObject(ObjectOutputStream oos)
    throws IOException {		
		oos.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		// our "pseudo-constructor"
		in.defaultReadObject();
		// now we are a "live" object again, so let's run rebuild and start

		initLayout();
		this.setVisible(true);
	}
}

class MenuItem extends AbstractAction implements Serializable {
	MenuItemClickListener listener;

	public MenuItem(String name) {
		this(name, null);
	}

	public MenuItem(String name, MenuItemClickListener listener) {
		super(name);
		this.listener = listener;
	}

	public void actionPerformed(ActionEvent event) {
		if (listener != null)
			listener.menuItemClick(event);
		
	}

}

abstract class MenuItemClickListener implements Serializable {
	public abstract void menuItemClick(ActionEvent e);
}


