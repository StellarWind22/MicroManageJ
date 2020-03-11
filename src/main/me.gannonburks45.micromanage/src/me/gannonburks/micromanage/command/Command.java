package src.me.gannonburks.micromanage.command;

public class Command implements ICommand {

	//Meta Stuff
	private String label;
	private String description;
	
	//Disable-able stuff
	private boolean disabled = false;
	private boolean canDisable = true;
	
	public Command(String labelIn, boolean canDisableIn, String descriptionIn)
	{	
		this.label = labelIn.toLowerCase();
		this.canDisable = canDisableIn;
		this.description = descriptionIn;
	}
	
	//Label Getter
	public String getLabel()
	{
		return this.label;
	}

	//Description Getter
	public String getDescription()
	{
		return this.description;
	}

	//Disabled getter
	public boolean isDisabled()
	{
		return this.disabled;
	}

	//Disabled setter
	public void setDisabled(boolean disabledIn)
	{
		this.disabled = disabledIn;
	}

	public boolean canDisable() {
		return this.canDisable;
	}
}
