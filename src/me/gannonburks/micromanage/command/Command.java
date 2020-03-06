package me.gannonburks.micromanage.command;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class Command implements ICommand {

	private static String label;
	
	public Command(String labelIn) {
		
		label = labelIn.toLowerCase();
	}
	
	//Command Fire Event
	public void fireInGuild(String[] args, User sender, TextChannel channel) {
		return;
	}
	
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel) {
		return;
	}
	
	//Label Getter
	public String getLabel() {
		return label;
	}
	
	//Label Setter
	public void setLabel(String labelIn) {
		label = labelIn;
	}
}