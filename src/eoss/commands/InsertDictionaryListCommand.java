package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;

/**
 * Command to insert DictionaryList html code. This command asks for a number of
 * entries in the list and inserts the code for them into a TabManager given at
 * construction. The &lt;dt&gt; and &lt;dd&gt; tags that comprises an entry are
 * always inserted in pairs.
 * 
 * @author Andrew
 */
public class InsertDictionaryListCommand implements ActionListener {
	private TabManager tabManager;

	/**
	 * Constructs a command with a TabManager into which the Dictionary List
	 * html is going to be inserted.
	 * 
	 * @param tabManager
	 */
	public InsertDictionaryListCommand(TabManager tabManager) {
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

			String inputText = "<dl>";
			String entryText = "	<dt></dt>\n" + "	<dd></dd>\n";

			for (int i = 0; i < numEntries; i++) {
				inputText += entryText;
			}
			inputText += "</dl>";

			tabManager.InsertText(inputText);
		}
	}
}
