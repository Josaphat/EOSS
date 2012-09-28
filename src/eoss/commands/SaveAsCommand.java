package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import eoss.gui.TabManager;
import eoss.main.DocumentManager;

public class SaveAsCommand implements ActionListener {

	private DocumentManager documentManager;
	private TabManager tabManager;

	public SaveAsCommand(DocumentManager dm, TabManager tm) {
		this.documentManager = dm;
		this.tabManager = tm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Validate the file
		// if validation fails, ask the user if they would like to save anyway
		// if they would like to save anyway,
		// ask them where to save
		// save
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
			JFileChooser fc = new JFileChooser();

			int returnVal = fc.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					documentManager.save(fc.getSelectedFile(), tabManager
							.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"An error occurred while" + " saving the file.");
				}
			}
		}
	}
}
