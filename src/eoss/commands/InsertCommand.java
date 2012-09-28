package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eoss.gui.TabManager;

/**
 * The Insert command inserts texts into a given TabManager. The Text is given
 * at the time of instantiation of the command. It supports the insertion of
 * text over a selection.
 * 
 * @author Andrew
 * 
 */
public class InsertCommand implements ActionListener {

	private String text;
	private TabManager tabManager;

	/**
	 * Creates an insert command object to insert the given string
	 * 
	 * @param tabManager
	 *            the TabManager where text is to be inserted
	 * @param text
	 *            the string to insert
	 */
	public InsertCommand(TabManager tabManager, String text) {
		this.text = text;
		this.tabManager = tabManager;
	}

	/**
	 * Inserts text into the TabManager by overwriting a selection. This is
	 * intuitive behavior for a text-editor.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		tabManager.InsertText(text);
	}

}
