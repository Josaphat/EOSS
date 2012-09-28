package eoss.main;

import java.io.File;
import java.io.IOException;

import eoss.gui.EditorWindow;

/**
 * The Main class of the application. It processes commandline arguments, opens
 * the File for the DocumentManager and creates the GUI for the application.
 */
public class HtmlEditor {
	public static void main(String[] args) {
		File f;

		// If given, attempt to open the file given as a command line parameter
		switch (args.length) {
		case 1:
			f = new File(args[0]);
			if (f.exists() && f.canRead()) {
				break;
			} else {
				System.err.println(args[0] + "does not exist at the specified"
						+ " location.");
			}
		default:
			// Open an "Untitled" file by default;
			// happens if no params are specified or the specified params do
			// not point to files we can open and use.
			f = new File("Untitled.html");
		}

		DocumentManager dm = new DocumentManager();

		try {
			dm.open(f);
			new EditorWindow(dm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}