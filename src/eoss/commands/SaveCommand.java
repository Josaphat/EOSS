package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;
import eoss.main.DocumentManager;

public class SaveCommand implements ActionListener {
	private DocumentManager documentManager;
	private TabManager tabManager;

	public SaveCommand(DocumentManager dm, TabManager tm) {
		this.documentManager = dm;
		this.tabManager = tm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO:
		// We first need to update the text in the DocumentManager
		// and THEN call save on it.

		boolean save = false;

		if (documentManager.validate(tabManager.getText())) {
			save = true;
		} else {
			int choice = JOptionPane
					.showConfirmDialog(
							null,
							"The file you are trying to save"
									+ " is not well-formed HTML. Would you like to save anyway?",
							"Well-formed check failed",
							JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				save = true;
			} else {
				save = false;
			}
		}

		if (save) {
			try {
				documentManager.save(tabManager.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occurred while"
						+ " saving the file.");
			}
		}
	}
}
