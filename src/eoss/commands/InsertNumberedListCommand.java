package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;

/**
 * This command asks how many entries long the list should be and then inserts
 * the html for a NumberedList of that many items into the TabManager given at
 * construction time.
 * 
 * @author Andrew
 * 
 */
public class InsertNumberedListCommand implements ActionListener {
	private TabManager tabManager;

	/**
	 * Creates a command to insert a numbered list into the given TabManager
	 * 
	 * @param tabManager
	 *            the TabManager to insert the Html for a numbered list into
	 */
	public InsertNumberedListCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

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

			String inputText = "<ol>";
			String entryText = "	<li></li>\n";

			for (int i = 0; i < numEntries; i++) {
				inputText += entryText;
			}
			inputText += "</ol>";

			tabManager.InsertText(inputText);
		}
	}

}
