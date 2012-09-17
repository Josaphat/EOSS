package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The main window for the application
 * @author jxv1308
 *
 */
public class EditorWindow extends javax.swing.JFrame {
	java.util.ArrayList<String> constructs;
	
	JMenuBar menubar;
	JTextArea textArea;
	
	public EditorWindow(java.util.ArrayList<String> constructs){
		this.constructs = constructs;
		
		/* CREATE MENU BAR */
		{
			this.menubar = new JMenuBar();
			// FILE MENU
			JMenu menu = new JMenu("File");

			JMenuItem menuItem = new JMenuItem("Open");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Open command");
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Save");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Save command");
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Save As...");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Save As command");
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Exit");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Exit command");
				}
			});
			menu.add(menuItem);

			this.menubar.add(menu);

			// EDIT MENU
			menu = new JMenu("Edit");

			menuItem = new JMenuItem("Cut");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Cut command");
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Copy");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Copy command");
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Paste");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Paste command");
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Word-Wrap");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Word-wrap command");
				}
			});
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Set Tabstop");
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					// TODO Do it better
					System.out.println("Tabstop command");
					int tabsize = textArea.getTabSize();
					try {
						String s = (String)JOptionPane.showInputDialog(
									null,
									"Enter tabstop size:",
									"Setting tabstops",
									JOptionPane.PLAIN_MESSAGE,
									null,
									null,
									null);
						tabsize = Integer.parseInt(s);
					} catch(NumberFormatException ex){
						System.err.println("Number wasn't number");
					}
					textArea.setTabSize(tabsize);
				}
			});
			menu.add(menuItem);

			this.menubar.add(menu);

			// INSERT MENU
			menu = new JMenu("Insert");

			for(String s : this.constructs){
				// We don't know or care what the constructs are
				menuItem = new JMenuItem(s);
				menuItem.setActionCommand(s);
				menuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println(e.getActionCommand() + " command");
					}
				});
				menu.add(menuItem);
			}

			this.menubar.add(menu);
			this.setJMenuBar(this.menubar);
		}

		/* Create center panel */
		{
			this.setLayout(new BorderLayout());
			textArea = new JTextArea();
			JScrollPane areaScrollPane = new JScrollPane(textArea);
			areaScrollPane.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			areaScrollPane.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			areaScrollPane.setPreferredSize(new java.awt.Dimension(250, 250));
			
			this.add(areaScrollPane, BorderLayout.CENTER);
		}
		
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
