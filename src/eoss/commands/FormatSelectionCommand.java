package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eoss.gui.TabManager;

public class FormatSelectionCommand implements ActionListener {
	private TabManager tabManager;

	public FormatSelectionCommand(TabManager tabManager) {
		super();
		this.tabManager = tabManager;
	}

	public void actionPerformed(ActionEvent e) {
		String text = tabManager.GetSelectedText();
		text = this.autoIndent(text);
		tabManager.InsertText(text);
	}

	/**
	 * Tabs and newlines the given XML structure. All newlines and tabs are
	 * removed before formatting. They will begin at level 0;
	 * 
	 * @param str
	 *            text to format.
	 * @return The formatted text.
	 */
	public String autoIndent(String str) {
		if ((str != null) && (str != "")) {

			str = str.replace("\t", "");
			str = str.replace("\n", "");

			int level = 0;
			StringBuilder out = new StringBuilder();
			String remainder = str.toString();
			boolean done = false;

			while ((!done) && (remainder.length() > 0)) {
				if (level < 0) {
					level = 0;
				}

				System.out.println("REMAINDER: " + remainder);

				int lt = remainder.indexOf('<');// next tag

				if (lt == -1) {// the are no more tags
					out.append(remainder);
					done = true;
				} else {
					tabAndAppend(out, level, remainder.substring(0, lt));// add
																			// contents
																			// of
																			// tag
					remainder = remainder.substring(lt);// remove stuff from
														// remainder so it
														// starts at next tag

					boolean closing = false;
					{// Determine tag type while accounting for whitespace <
						// /tag>
						boolean stop = false;
						for (int i = 1; (!stop) && (i < remainder.length()); i++) {
							switch (remainder.charAt(i)) {
							case '/': // closing tag!
								closing = true;
								stop = true;
								break;
							case ' ': // keep moving along
								stop = false;
								break;
							default: // found something else, not closing tag
								stop = true;
								closing = false;
								break;
							}

						}
					}// end determine tag type

					int gt = remainder.indexOf('>');
					int nextlt = remainder.substring(1).indexOf('<');

					if (gt == -1) {
						tabAndAppend(out, level, remainder);
						remainder = "";
					} else if ((nextlt != -1) && (gt > nextlt)) {
						// encountered partial tag <sdasds<asd>
						String partial = remainder.substring(0, nextlt + 1);
						remainder = remainder.substring(nextlt + 1);
						tabAndAppend(out, level, partial + "\n");
					} else {
						// accounts for finding the end of a partial tag
						if (closing) {
							level--;
						}
						out.append('\n');

						tabAndAppend(out, level, remainder.substring(0, gt + 1));
						out.append('\n');

						if (!closing) {
							level++;
							// out.append('\n');
						}
						remainder = remainder.substring(gt + 1);
					}

				}
			}
			// System.out.println(out);
			return out.toString();
		}
		return "";
	}

	private StringBuilder tabAndAppend(StringBuilder str, int level,
			String contents) {
		// System.out.println("indenting "+contents+" to "+level);
		for (int l = 0; l < level; l++) {
			str.append("\t");
		}
		str.append(contents);
		return str;
	}

}
