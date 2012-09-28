package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import eoss.gui.TabManager;
import eoss.main.DocumentManager;

/**
 * This command opens a JFileChooser dialog and executes the calls to load the
 * file.
 * 
 * @author Josaphat Valdivia
 * 
 */
public class OpenCommand implements ActionListener {

	private DocumentManager documentManager;
	private TabManager tabManager;

	public OpenCommand(DocumentManager documentManager, TabManager tabManager) {
		this.documentManager = documentManager;
		this.tabManager = tabManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			// Open the file
			try {
				String text = documentManager.open(file);
				tabManager.AddNewFileTab(file.getName(), text);
				if (!documentManager.validate(text)) {
					JOptionPane.showConfirmDialog(null, "Validation failed!",
							"Validation Results", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
