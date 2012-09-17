package commands;

public class OpenCommand implements Command {
	
	main.DocumentManager docMan;
	
	public OpenCommand(main.DocumentManager dm){
		docMan = dm;
	}

	@Override
	public void execute() {
		// TODO
		System.out.println("Open!");
		docMan.open();
	}

}
