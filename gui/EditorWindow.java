package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The main window for the application
 * @author Josaphat Valdivia
 *
 */
public class EditorWindow extends javax.swing.JFrame {
	java.util.ArrayList<String> constructs;
	
	JMenuBar menubar;
	
	public EditorWindow(java.util.ArrayList<String> constructs){
		this.constructs = constructs;
		
		// Create the menu
		this.menubar = new JMenuBar();
		
		// FILE MENU
		JMenu menu = new JMenu("File");
		
		JMenuItem menuItem = new JMenuItem("Open");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save As...");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Exit");
		menu.add(menuItem);
		
		this.menubar.add(menu);
		
		// EDIT MENU
		menu = new JMenu("Edit");
		
		menuItem = new JMenuItem("Cut");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Copy");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Paste");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Word-Wrap");
		menu.add(menuItem);
		
		this.menubar.add(menu);
		
		// INSERT MENU
		menu = new JMenu("Insert");
		
		for(String s : this.constructs){
			// We don't know or care what the constructs are
			menuItem = new JMenuItem(s);
			menu.add(menuItem);
		}
		
		this.menubar.add(menu);
		
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
