package src.me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

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
	
	/*
	 * COMMAND FIRE EVENTS
	 */
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		return;
	}
	
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		return;
	}
	
	public static void fireInConsole(String args)
	{
		return;
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
