package eoss.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A GUI container that holds and manages a text area for editing HTML. Though
 * the text area handles most of the functions for editing text, this class
 * contains several convenience methods for line wrapping and setting tab width.
 * The main point of this class is to set up the text part of the GUI so that it
 * looks good and has the ability to scroll.
 * 
 * @author Josaphat Valdivia
 * 
 */
@SuppressWarnings("serial")
public class TextPanel extends JPanel {

	private JTextArea textArea;

	/**
	 * This constructor creates the look and feel of each text panel
	 * 
	 * @param text
	 *            - starting text area text
	 * @param isWrapped
	 *            - true if line wrapping is on
	 * @param tabMan
	 *            - attached for auto indenting
	 */
	public TextPanel(String text, boolean isWrapped, TabManager tabMan) {
		super(new BorderLayout());
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText(text);
		textArea.setLineWrap(isWrapped);
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		textArea.addKeyListener(new HtmlKeyListener(tabMan));

		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setPreferredSize(new java.awt.Dimension(250, 250));

		this.add(areaScrollPane);
	}

	/**
	 * @return a reference to the JTextArea contained in this panel.
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * Sets whether line-wrapping is enabled for the JTextArea contained in this
	 * panel.
	 * 
	 * @param isWrapped
	 *            true if line-wrapping is turned on, false otherwise.
	 */
	public void SetLineWrapping(boolean isWrapped) {
		this.textArea.setLineWrap(isWrapped);
	}

	/**
	 * Sets the displayed width of the tabs in the JTextArea contained in this
	 * panel.
	 * 
	 * @param width
	 *            the width of the tabs.
	 * @see JTextArea#setTabSize()
	 */
	public void SetTabWidth(int width) {
		this.textArea.setTabSize(width);
	}
}
