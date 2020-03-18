package src.me.gannonburks.micromanage.command;

public class BotCommand implements IBotCommand
{
	private String label;
	private String description;
	
	private boolean canFireInGuild = true;
	private boolean canFireInPrivate = true;
	private boolean canFireInConsole = true;
	
	private boolean canDisable = true;
	
	/**
	 * Constructor for BotCommand class.
	 * 
	 * @param label				Label for the command.
	 * @param description		Description of the command.
	 * 
	 * @param canFireInGuild	If the command can be fired in a guild channel.
	 * @param canFireInPrivate	If the command can be fired in a private channel.
	 * @param canFireInConsole	If the command can be fired in the console.
	 * 
	 * @param can Disable		If the command can be disabled by the "setdisabled" command
	 */
	public BotCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{	
		this.label = label.toLowerCase();
		this.description = description;
		
		this.canFireInGuild = canFireInGuild;
		this.canFireInPrivate = canFireInPrivate;
		this.canFireInConsole = canFireInConsole;
		
		this.canDisable = canDisable;
	}
	
	/**
	 * Getter method for label of the command.
	 * 
	 * @return The label of the command.
	 */
	public String getLabel()
	{
		return this.label;
	}

	/**
	 * Getter method for description of the command.
	 * 
	 * @return The description of the command.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Method for checking whether or not the command
	 * can be run in a guild channel.
	 * 
	 * @return If the command can be fired in a guild.
	 */
	public boolean canFireInGuild()
	{
		return this.canFireInGuild;
	}
	
	/**
	 * Method for checking whether or not the command
	 * can be run in a private channel.
	 * 
	 * @return If the command can be fired in a private channel.
	 */
	public boolean canFireInPrivate()
	{
		return this.canFireInPrivate;
	}
	
	/**
	 * Method for checking whether or not the command
	 * can be run from the console.
	 * 
	 * @return If the command can be fired from the console.
	 */
	public boolean canFireInConsole()
	{
		return this.canFireInConsole;
	}
	
	/**
	 * Method for checking whether or not the command
	 * can be disabled via the "setdisabled" command.
	 * 
	 * @return If the command can be disabled.
	 */
	public boolean canDisable()
	{
		return this.canDisable;
	}
}
