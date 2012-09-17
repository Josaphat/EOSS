package commands;

public class SaveCommand implements Command {

	main.DocumentManager docMan;
	
	public SaveCommand(main.DocumentManager dm){
		docMan = dm;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Save");
		docMan.save();
	}
}
