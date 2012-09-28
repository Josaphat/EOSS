package eoss.gui;

import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ButtonPanel contains various buttons which are useful for an application
 * which supports
 * <ul>
 * <li>Saving a file</li>
 * <li>Opening a file</li>
 * <li>Cut and Paste</li>
 * <li>content validation</li>
 * <li>tables and lists</li>
 * 
 * This functionality lends itself particularly well to HTML with it's tables
 * and lists, but could be used in other contexts.
 * 
 * @author Andrew Mueller
 */

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

	/* Named constants for the path to the images directory and the images */
	private static final String IMAGES_DIRECTORY = "Images/";
	private static final String SAVE_IMAGE = IMAGES_DIRECTORY + "save.png";
	private static final String OPEN_IMAGE = IMAGES_DIRECTORY + "open.png";
	private static final String CUT_IMAGE = IMAGES_DIRECTORY + "cut.png";
	private static final String PASTE_IMAGE = IMAGES_DIRECTORY + "paste.png";
	private static final String VALIDATE_IMAGE = IMAGES_DIRECTORY
			+ "validate.png";
	private static final String BOLD_IMAGE = IMAGES_DIRECTORY + "bold.png";
	private static final String ITALICS_IMAGE = IMAGES_DIRECTORY
			+ "italics.png";
	private static final String TABLE_IMAGE = IMAGES_DIRECTORY + "table.png";
	private static final String BULLIST_IMAGE = IMAGES_DIRECTORY
			+ "bulletedList.png";
	private static final String NUMLIST_IMAGE = IMAGES_DIRECTORY
			+ "numberedList.png";
	private static final String DICLIST_IMAGE = IMAGES_DIRECTORY
			+ "dictionaryList.png";

	/* Buttons in the panel */
	private JButton save;
	private JButton open;
	private JButton cut;
	private JButton paste;
	private JButton validate;
	private JButton bold;
	private JButton italics;
	private JButton table;
	private JButton numberedList;
	private JButton bulletedList;
	private JButton dictionaryList;

	/**
	 * Constructs a button panel.
	 * 
	 * Creates a button panel for an application which supports saving, opening,
	 * cutting, pasting, validation, bold, italics, tables, and various types of
	 * lists.
	 * 
	 * @param docMan
	 *            The documentManager which will manage saving and opening files
	 * @param tabManager
	 *            The tab manager which will provide and be provided the text to
	 *            display and save.
	 */
	public ButtonPanel(eoss.main.DocumentManager docMan, TabManager tabManager) {
		super(new FlowLayout(FlowLayout.LEFT));
		save = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(SAVE_IMAGE)));
		save.setToolTipText("Save");
		save.setMargin(new Insets(0, 0, 0, 0));
		save
				.addActionListener(new eoss.commands.SaveCommand(docMan,
						tabManager));

		open = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(OPEN_IMAGE)));
		open.setToolTipText("Open");
		open.setMargin(new Insets(0, 0, 0, 0));
		open
				.addActionListener(new eoss.commands.OpenCommand(docMan,
						tabManager));

		cut = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(CUT_IMAGE)));
		cut.setToolTipText("Cut");
		cut.setMargin(new Insets(0, 0, 0, 0));
		cut.addActionListener(new eoss.commands.CutCommand(tabManager));

		paste = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(PASTE_IMAGE)));
		paste.setToolTipText("Paste");
		paste.setMargin(new Insets(0, 0, 0, 0));
		paste.addActionListener(new eoss.commands.PasteCommand(tabManager));

		validate = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(VALIDATE_IMAGE)));
		validate.setToolTipText("Validate");
		validate.setMargin(new Insets(0, 0, 0, 0));
		validate.addActionListener(new eoss.commands.ValidateCommand(docMan,
				tabManager));

		bold = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(BOLD_IMAGE)));
		bold.setToolTipText("Bold");
		bold.setMargin(new Insets(0, 0, 0, 0));
		bold.addActionListener(new eoss.commands.InsertCommand(tabManager,
				"<b></b>"));

		italics = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(ITALICS_IMAGE)));
		italics.setToolTipText("Italics");
		italics.setMargin(new Insets(0, 0, 0, 0));
		italics.addActionListener(new eoss.commands.InsertCommand(tabManager,
				"<i></i>"));

		table = new JButton(new ImageIcon(ClassLoader.getSystemClassLoader()
				.getResource(TABLE_IMAGE)));
		table.setToolTipText("Table");
		table.setMargin(new Insets(0, 0, 0, 0));
		table
				.addActionListener(new eoss.commands.InsertTableCommand(
						tabManager));

		numberedList = new JButton(new ImageIcon(ClassLoader
				.getSystemClassLoader().getResource(NUMLIST_IMAGE)));
		numberedList.setToolTipText("Numbered List");
		numberedList.setMargin(new Insets(0, 0, 0, 0));
		numberedList
				.addActionListener(new eoss.commands.InsertNumberedListCommand(
						tabManager));

		bulletedList = new JButton(new ImageIcon(ClassLoader
				.getSystemClassLoader().getResource(BULLIST_IMAGE)));
		bulletedList.setToolTipText("Bulleted List");
		bulletedList.setMargin(new Insets(0, 0, 0, 0));
		bulletedList
				.addActionListener(new eoss.commands.InsertBulletedListCommand(
						tabManager));

		dictionaryList = new JButton(new ImageIcon(ClassLoader
				.getSystemClassLoader().getResource(DICLIST_IMAGE)));
		dictionaryList.setToolTipText("Dictionary List");
		dictionaryList.setMargin(new Insets(0, 0, 0, 0));
		dictionaryList
				.addActionListener(new eoss.commands.InsertDictionaryListCommand(
						tabManager));

		add(save);
		add(open);
		add(cut);
		add(paste);
		add(new JLabel("  "));
		add(validate);
		add(new JLabel("  "));
		add(bold);
		add(italics);
		add(new JLabel(" "));
		add(table);
		add(new JLabel(" "));
		add(numberedList);
		add(bulletedList);
		add(dictionaryList);
	}
}
