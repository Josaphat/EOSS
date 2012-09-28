package eoss.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import eoss.gui.TabManager;

public class PasteCommand implements ActionListener {
	TabManager tabManager;

	/**
	 * Copies the selected text into the local clipboard on execution
	 * 
	 * @param dm
	 *            The document manager
	 * @param textView
	 *            The text component with the selected text
	 */
	public PasteCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
				.getContents(null);
		String text = new String();
		if ((t != null) && (t.isDataFlavorSupported(DataFlavor.stringFlavor))) {
			try {
				text = (String) t.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		tabManager.InsertText(text);
	}
}
