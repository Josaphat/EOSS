package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eoss.gui.TabManager;

public class WrapCommand implements ActionListener {
	private TabManager tabs;

	public WrapCommand(TabManager tab) {
		this.tabs = tab;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tabs.ToggleLineWrapping();
	}
}
