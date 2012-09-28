package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;
import eoss.main.BufferNotOpenException;
import eoss.main.DocumentManager;
import eoss.main.FileBuffer;

/**
 * Prompts for save on close. Can be added as an ActionListener OR a
 * WindowListener Will be called on actionPerformed or WindowClosing Prompts the
 * user for saving documents. Note that the window *may not close* as a result
 * of this Command, as the user may cancel it.
 * 
 * @author julian boilen
 * 
 */
public class ExitCommand extends WindowAdapter implements ActionListener {

	private DocumentManager dm;
	private TabManager tm;

	public ExitCommand(DocumentManager dm, TabManager tm) {
		this.dm = dm;
		this.tm = tm;
	}

	public void actionPerformed(ActionEvent e) {
		exit();
	}

	public void windowClosing(WindowEvent e) {
		exit();
	}

	public void exit() {
		// For each buffer, the command
		for (FileBuffer fb : dm.getOpenBuffers()) {
			// checks to see if it's saved
			if (fb.isSaved()) {
				// if it is, it closes the buffer
				try {
					dm.close(fb);
				} catch (BufferNotOpenException e1) {
					e1.printStackTrace();
				}
			}
			// if it is not, it asks the user to save
			else {
				int result = JOptionPane.showConfirmDialog(tm,
						"Do you want to save the changes you made to the "
								+ "document \"" + fb.getName() + "\"?");
				// if they don't want to save, close the buffer
				if (result == JOptionPane.NO_OPTION) {
					dm.close();
				} else if (result == JOptionPane.YES_OPTION) {
					// otherwise save the buffer
					try {
						dm.save(tm.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					dm.close();
				} else {
					return;
				}
			}

			// And finally terminate the application
			System.exit(0);
		}
	}
}
