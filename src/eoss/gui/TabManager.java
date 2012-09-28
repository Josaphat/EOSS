package eoss.gui;

import java.util.prefs.Preferences;

import javax.swing.JTabbedPane;

/**
 * The TabManager handles communication between entities which would like to
 * modify the contents of the text area currently being edited by a user and the
 * text areas themselves. Clients of the TabManager most likely won't care to
 * have access to particular instances of the areas themselves as long as they
 * can edit what the current user is looking at.
 * 
 * @author
 * 
 */

@SuppressWarnings("serial")
public class TabManager extends JTabbedPane {

	private boolean isWrapped;
	private static Preferences prefs = Preferences.userRoot().node(
			"EOSSHTMLEdit");
	private static String wrappingKey = "isWrapping";

	/**
	 * Construct a TabManager using a previously stored value for whether or not
	 * line wrapping should be enabled. If the wrapping value hasn't been stored
	 * previously, it defaults to being on.
	 */
	public TabManager() {
		this(prefs.getBoolean(wrappingKey, true));
	}

	/**
	 * Constructs a TabManager.
	 * 
	 * @param isWrapped
	 *            value indicating whether wrapping should be turned on or off
	 *            to start the session.
	 */
	public TabManager(boolean isWrapped) {
		super();
		this.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		this.isWrapped = isWrapped;
	}

	/**
	 * Creates a new tab and adds it to the Manager.
	 * 
	 * @param filename
	 *            The name of the file being edited
	 * @param text
	 *            the contents of the file to be displayed.
	 */
	public void AddNewFileTab(String filename, String text) {
		TextPanel panel = new TextPanel(text, this.isWrapped, this);
		this.addTab(filename, panel);
		this.setSelectedIndex(this.getTabCount() - 1);
	}

	/**
	 * Toggles LineWrapping for the documents being managed by this TabManager.
	 * This method stores the new state of line-wrapping on the system so that
	 * it persists across sessions of the application.
	 */
	public void ToggleLineWrapping() {
		this.isWrapped = !this.isWrapped;

		for (int i = 0; i < this.getTabCount(); i++) {
			TextPanel text = (TextPanel) this.getComponentAt(i);
			text.SetLineWrapping(this.isWrapped);
		}

		prefs.putBoolean(wrappingKey, isWrapped);
	}

	/**
	 * Inserts the given text into a document, replacing any highlighted text.
	 * 
	 * @param text
	 *            the text to insert
	 */
	public void InsertText(String text) {
		TextPanel t = ((TextPanel) this.getSelectedComponent());
		t.getTextArea().replaceRange(text, t.getTextArea().getSelectionStart(),
				t.getTextArea().getSelectionEnd());
	}

	/**
	 * Completely replaces the entire contents of the current document with the
	 * contents specified by <i>text</i>.
	 * 
	 * @param text
	 *            The text to replace the current contents with.
	 */
	public void ReplaceAllText(String text) {
		TextPanel t = (TextPanel) this.getSelectedComponent();
		t.getTextArea().setText(text);
	}

	/**
	 * @return The position of the caret in the current document as an offset
	 *         from the beginning of the document.
	 */
	public int GetCursorPosition() {
		TextPanel t = (TextPanel) this.getSelectedComponent();
		return t.getTextArea().getCaret().getDot();
	}

	/**
	 * Sets the position of the caret as an offset from the beginning of the
	 * document currently being edited.
	 * 
	 * @param position
	 *            the index from the beginning of the document of the desired
	 *            position for the caret.
	 */
	public void SetCursorPosition(int position) {
		TextPanel t = (TextPanel) this.getSelectedComponent();
		t.getTextArea().getCaret().setDot(position);
	}

	/**
	 * @return the currently selected text in the display
	 */
	public String GetSelectedText() {
		TextPanel t = (TextPanel) this.getSelectedComponent();
		return t.getTextArea().getSelectedText();
	}

	/**
	 * Sets the spacing of the tabstops in the the view. The width is determined
	 * by an integer which indicates the number of times the largest character
	 * in the current font can fit into a single tab character.
	 * 
	 * @param width
	 *            the width of the tabstops.
	 */
	public void SetTabWidth(int width) {
		this.isWrapped = !this.isWrapped;

		for (int i = 0; i < this.getTabCount(); i++) {
			TextPanel text = (TextPanel) this.getComponentAt(i);
			text.SetTabWidth(width);
		}
	}

	/**
	 * Retrieves the text of the active editing tab.
	 * 
	 * @return the text of the tab with focus.
	 */
	public String getText() {
		return ((TextPanel) this.getSelectedComponent()).getTextArea()
				.getText();
	}
}
