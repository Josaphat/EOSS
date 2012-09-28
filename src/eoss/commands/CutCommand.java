package eoss.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eoss.gui.TabManager;

/**
 * The CutCommand executes a copy-to-clipboard operation followed immediately by
 * a delete operation to obtain the effect of a cut-to-clipboard operation.
 * 
 * @author
 * 
 */
public class CutCommand implements ActionListener {

	private TabManager tabManager;

	/**
	 * Creates a CutCommand with the given TabManager from which it will obtain
	 * and remove the selected text.
	 * 
	 * @param tabManager	the TabManager from which text is being cut
	 */
	public CutCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

	/**
	 * Copies the selected text into the clipboard and then deletes it from the
	 * text area
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = tabManager.GetSelectedText();
		StringSelection ss = new StringSelection(text);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		tabManager.InsertText("");
	}

}
