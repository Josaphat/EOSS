package eoss.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eoss.commands.IndentToMatchCommand;

/**
 * This listener responds to the ENTER key being typed by a user and triggers
 * automatic indentation.
 * 
 * @author
 */
public class HtmlKeyListener implements KeyListener {

	private IndentToMatchCommand indentCommand;

	/**
	 * Constructs the listener with the given TabManager.
	 * 
	 * @param tabMan
	 *            the TabManager object on which the automatic indentation will
	 *            occur
	 */
	public HtmlKeyListener(TabManager tabMan) {
		super();
		this.indentCommand = new IndentToMatchCommand(tabMan);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	/**
	 * When a keyEvent is received, this method checks whether the event was
	 * triggered by the ENTER key being pressed. If it was, it executes an
	 * IndentToMatchCommand.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.indentCommand.actionPerformed(null);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
