package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandActionListener implements ActionListener {
	commands.Command command;
	
	/**
	 * TODO: Docs
	 * @param c
	 */
	public CommandActionListener(commands.Command c){
		this.command = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command.execute();
	}

}
