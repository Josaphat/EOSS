package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eoss.gui.TabManager;

/**
 * This command asks a user what dimensions of table html construct they would
 * like and then inserts it into the TabManager given during construction.
 * 
 * @author Andrew
 */
public class InsertTableCommand implements ActionListener {
	private TabManager tabManager;

	/**
	 * Creates a command to insert a table into the given TabManager
	 * 
	 * @param tabManager
	 *            - for getting the current text area
	 */
	public InsertTableCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField rowField = new JTextField("", 5);
		JTextField colField = new JTextField("", 5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Rows:"));
		myPanel.add(rowField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Columns:"));
		myPanel.add(colField);

		int result = JOptionPane.OK_OPTION;
		String rowInput = rowField.getText();
		String colInput = colField.getText();

		while ((result == JOptionPane.OK_OPTION)
				&& (!rowInput.matches("[0-9]{1,3}") || (!colInput
						.matches("[0-9]{1,2}")))) {
			result = JOptionPane.showConfirmDialog(tabManager, myPanel,
					"Please Enter the Number of Rows and Columns",
					JOptionPane.OK_CANCEL_OPTION);
			rowInput = rowField.getText();
			colInput = colField.getText();
		}

		if (result == JOptionPane.OK_OPTION) {
			int numRows = Integer.parseInt(rowInput);
			int numCols = Integer.parseInt(colInput);

			String inputText = "<table>\n";
			for (int row = 0; row < numRows; row++) {
				inputText += "	<tr>\n";

				for (int col = 0; col < numCols; col++) {
					inputText += "		<td></td>\n";
				}
				inputText += "	</tr>\n";
			}
			inputText += "</table>";

			tabManager.InsertText(inputText);
		}
	}
}
