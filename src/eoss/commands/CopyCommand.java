package eoss.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eoss.gui.TabManager;

/**
 * The CopyCommand executes a copy-to-clipboard operation on the TabManager.
 * 
 * @author
 */
public class CopyCommand implements ActionListener {
	private TabManager tabManager;

	/**
	 * Creates the CopyCommand object with a reference to the TabManager the
	 * command is going to retrieve the copied text from.
	 * 
	 * @param tabManager manager which contains the textArea being copied from
	 */
	public CopyCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

	/**
	 * This method executes the actual copy operation.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = tabManager.GetSelectedText();
		StringSelection ss = new StringSelection(text);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}

}
