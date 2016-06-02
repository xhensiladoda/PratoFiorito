package bozza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class MenuBar 
{	
	JMenu fileMenu;
	JMenuBar menuBar;
		
	public MenuBar()
		{

		fileMenu=new JMenu("File");
		JMenuItem newItem=fileMenu.add(new TestAction("New"));
		
		fileMenu.add(new AbstractAction("Exit")
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
	
		JMenu optionMenu=new JMenu("Options");
		ButtonGroup group=new ButtonGroup();
		JRadioButtonMenuItem easyItem=new JRadioButtonMenuItem("Easy: 10 Flowers  9x9");
		easyItem.setSelected(true);
		easyItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
			}
			
		});
		
		JRadioButtonMenuItem mediumItem=new JRadioButtonMenuItem("Medium: 40 Flowers  16x16");
		JRadioButtonMenuItem hardItem=new JRadioButtonMenuItem("Hard: 99 Flowers  16x30");
		
		group.add(easyItem);
		group.add(mediumItem);
		group.add(hardItem);
		
		optionMenu.add(easyItem);
		optionMenu.addSeparator();
		optionMenu.add(mediumItem);
		optionMenu.addSeparator();
		optionMenu.add(hardItem);
		optionMenu.addSeparator();
		
		JMenu helpMenu=new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		Action aboutAction=new TestAction("About");
		aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		helpMenu.add(aboutAction);
		menuBar=new JMenuBar();
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		menuBar.add(optionMenu);
		}
		
		public JMenuBar returnJBar()
		{
			return menuBar;
		}
}


