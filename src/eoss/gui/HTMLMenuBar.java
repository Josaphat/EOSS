package eoss.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import eoss.commands.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Creates a menu bar for an application which supports inserting HTML
 * constructs, saving, opening, validating, word wrapping, and automatic
 * indentation.
 * 
 * @author
 */
@SuppressWarnings("serial")
public class HTMLMenuBar extends JMenuBar {
	public HTMLMenuBar(eoss.main.DocumentManager docsManager,
			TabManager tabManager) {
		// FILE MENU
		JMenu menu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new eoss.commands.OpenCommand(docsManager,
				tabManager));
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new eoss.commands.SaveCommand(docsManager,
				tabManager));
		menu.add(menuItem);

		menuItem = new JMenuItem("Save As...");
		menuItem.addActionListener(new SaveAsCommand(docsManager, tabManager));
		menu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ExitCommand(docsManager, tabManager));
		menu.add(menuItem);

		this.add(menu);

		// EDIT MENU
		menu = new JMenu("Edit");

		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(new CutCommand(tabManager));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(new CopyCommand(tabManager));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(new PasteCommand(tabManager));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Word-Wrap");
		menuItem.addActionListener(new WrapCommand(tabManager));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK));

		menu.add(menuItem);

		menuItem = new JMenuItem("Set Tabstop");
		menuItem.addActionListener(new TabstopCommand(tabManager));

		menu.add(menuItem);

		menuItem = new JMenuItem("Format Selection");
		menuItem.addActionListener(new FormatSelectionCommand(tabManager));

		menu.add(menuItem);

		menuItem = new JMenuItem("Indent to match");
		menuItem.addActionListener(new IndentToMatchCommand(tabManager));

		menu.add(menuItem);

		menuItem = new JMenuItem("Validate HTML");
		menuItem
				.addActionListener(new ValidateCommand(docsManager, tabManager));

		menu.add(menuItem);
		this.add(menu);

		// INSERT MENU
		menu = new JMenu("Insert");

		menuItem = new JMenuItem("Bold Tag");
		menuItem.addActionListener(new InsertCommand(tabManager, "<b></b>"));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menuItem = new JMenuItem("Italics Tag");
		menuItem.addActionListener(new InsertCommand(tabManager, "<i></i>"));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menuItem = new JMenuItem("Table Tags");
		menuItem.addActionListener(new InsertTableCommand(tabManager));
		menu.add(menuItem);

		menuItem = new JMenuItem("Numbered List Tags");
		menuItem.addActionListener(new InsertNumberedListCommand(tabManager));
		menu.add(menuItem);

		menuItem = new JMenuItem("Bulleted List Tags");
		menuItem.addActionListener(new InsertBulletedListCommand(tabManager));
		menu.add(menuItem);

		menuItem = new JMenuItem("Dictionary List Tags");
		menuItem.addActionListener(new InsertDictionaryListCommand(tabManager));
		menu.add(menuItem);

		this.add(menu);
	}
}
