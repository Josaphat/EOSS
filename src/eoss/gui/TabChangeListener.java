package eoss.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;

import eoss.main.DocumentManager;

/**
 * The TabChangeListener implements the ChangeListener interface for the purpose
 * of relaying tab-changes to a DocumentManager so that it knows which Document
 * is currently being edited.
 * 
 * @author Josaphat Valdivia
 * 
 */
public class TabChangeListener implements ChangeListener {
	DocumentManager dm;

	/**
	 * Constructs a TabChangeListener with a DocumentManager it is going to keep
	 * abreast of changes.
	 * 
	 * @param dm
	 *            The DocumentManager this listener is notifying
	 */
	public TabChangeListener(DocumentManager dm) {
		this.dm = dm;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		int selectedIndex = ((JTabbedPane) arg0.getSource()).getSelectedIndex();

		if (selectedIndex < 0) {
			// No tabs are open.
		} else {
			dm.setSelectedBuffer(selectedIndex);
		}
	}
}
