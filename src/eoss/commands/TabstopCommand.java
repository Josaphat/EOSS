package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eoss.gui.TabManager;

public class TabstopCommand implements ActionListener {
	TabManager tabManager;

	public TabstopCommand(TabManager tabs) {
		this.tabManager = tabs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = "";
		while (!(input == null) && (!input.matches("[0-9]+"))) {
			input = (String) JOptionPane.showInputDialog(null,
					"Please input the tab width:", "Set Tab Width",
					JOptionPane.PLAIN_MESSAGE, null, null, "");
		}

		if (input != null) {
			int width = Integer.parseInt(input);

			this.tabManager.SetTabWidth(width);
		}
	}

}
