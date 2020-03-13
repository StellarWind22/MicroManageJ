package src.me.gannonburks.micromanage.command;

public class Command implements ICommand {

	//Meta Stuff
	private String label;
	private String description;
	
	//Server Stuff
	private boolean canFireInGuild = true;
	private boolean canFireInPrivate = true;
	private boolean canFireInConsole = true;
	
	//Disable Stuff
	private boolean canDisable = true;
	
	public Command(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{	
		this.label = label.toLowerCase();
		this.description = description;
		
		this.canFireInGuild = canFireInGuild;
		this.canFireInPrivate = canFireInPrivate;
		this.canFireInConsole = canFireInConsole;
		
		this.canDisable = canDisable;
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

	/*
	 * Command Firing Rules
	 */
	public boolean canFireInGuild()
	{
		return this.canFireInGuild;
	}
	
	public boolean canFireInPrivate()
	{
		return this.canFireInPrivate;
	}
	
	public boolean canFireInConsole()
	{
		return this.canFireInConsole;
	}
	
	//canDisable
	public boolean canDisable()
	{
		return this.canDisable;
	}
}
