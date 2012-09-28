package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;

/**
 * This command prompts the user for how many items an HTML Bulleted list should
 * contain and then inserts the HTML for a list of that size.
 * 
 * @author Andrew
 * 
 */
public class InsertBulletedListCommand implements ActionListener {
	private TabManager tabManager;

	/**
	 * Creates a command with a reference to the TabManager that this command
	 * will insert HTML for a bulleted-list into.
	 * 
	 * @param tabManager
	 */
	public InsertBulletedListCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

	/**
	 * Prompts the user for the number of bullets in the list and then inserts
	 * the HTML for a bulleted list into the given TabManager
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = "";

		while ((input != null) && (!input.matches("[0-9]{1,3}"))) {
			input = (String) JOptionPane.showInputDialog(tabManager,
					"Please input the number of list entries:", "Insert List",
					JOptionPane.PLAIN_MESSAGE, null, null, "");
		}

		if (input != null) {
			int numEntries = Integer.parseInt(input);

			String inputText = "<ul>";
			String entryText = "	<li></li>\n";

			for (int i = 0; i < numEntries; i++) {
				inputText += entryText;
			}
			inputText += "</ul>";

			tabManager.InsertText(inputText);
		}
	}
}
