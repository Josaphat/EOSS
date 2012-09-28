package eoss.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import eoss.commands.ExitCommand;
import eoss.main.DocumentManager;

/**
 * The main window for the application. Creates all of the swing components
 * needed for an appropriate "text-editing" environment.
 * 
 * @author Josaphat Valdivia
 */
@SuppressWarnings("serial")
public class EditorWindow extends JFrame {	
	private HTMLMenuBar menubar;
	private ButtonPanel buttonPanel;
	private final TabManager tabManager;
	
	/**
	 * Puts together all of the parts of the application that need to talk to
	 * each other to allow the user to edit their HTML files.
	 * @param documentManager The DocumentManager which is going to keep track
	 * of the File data and handle opening and saving files which the GUI will
	 * then display.
	 */
	public EditorWindow(DocumentManager documentManager) {
		super("HTML Editor");
		
		this.tabManager = new TabManager();
		this.tabManager.AddNewFileTab(documentManager.getName(),
				                      documentManager.getText());
		this.tabManager.addChangeListener(
				new TabChangeListener(documentManager));
		
		this.add(tabManager, BorderLayout.CENTER);
		
		buttonPanel = new ButtonPanel(documentManager, tabManager);
		this.add(buttonPanel, BorderLayout.NORTH);
		
		this.menubar = new HTMLMenuBar(documentManager, tabManager);
		this.setJMenuBar(this.menubar);
		
		this.pack();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ExitCommand(documentManager, tabManager));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);

	}
}
