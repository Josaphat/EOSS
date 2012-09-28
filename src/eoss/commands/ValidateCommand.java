package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;
import eoss.main.DocumentManager;

public class ValidateCommand implements ActionListener {
	private DocumentManager documentManager;
	private TabManager tabManager;

	public ValidateCommand(DocumentManager documentManager, TabManager tm) {
		this.documentManager = documentManager;
		this.tabManager = tm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.documentManager.validate(tabManager.getText())) {
			JOptionPane.showConfirmDialog(null, "Validation succeeded!",
					"Validation Results", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showConfirmDialog(null, "Validation failed!",
					"Validation Results", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
