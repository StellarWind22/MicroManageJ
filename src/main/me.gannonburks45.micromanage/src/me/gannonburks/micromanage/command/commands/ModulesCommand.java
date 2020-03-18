package src.me.gannonburks.micromanage.command.commands;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.module.Module;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.util.MessageHandler;

public class ModulesCommand extends BotCommand {

	public ModulesCommand(String label, String description, boolean canFireInGuild, boolean canFireInPrivate, boolean canFireInConsole, boolean canDisable)
	{
		super(label, description, canFireInGuild, canFireInPrivate, canFireInConsole, canDisable);
	}

	@Override
	public void fireInGuild(String[] args, User sender, TextChannel channel)
	{
		MessageHandler.sendMsgGuild(channel, "List of Modules:");
		
		for(Module module : ModuleRegistry.getAllModules())
		{
			MessageHandler.sendMsgGuild(channel, "> " + module.getName() + "[" + module.getVersion() + "] By \"" + module.getAuthor() + "\" - " + module.getDescription());
		}
		return;
	}
	
	@Override
	public void fireInPrivate(String[] args, User sender, PrivateChannel channel)
	{
		MessageHandler.sendMsgPrivate(channel, "List of Modules:");
		
		for(Module module : ModuleRegistry.getAllModules())
		{
			MessageHandler.sendMsgPrivate(channel, "> " + module.getName() + "[" + module.getVersion() + "] By \"" + module.getAuthor() + "\" - " + module.getDescription());
		}
		return;
	}
	
	@Override
	public void fireInConsole(String[] args)
	{
		System.out.println("List of Modules:");
		
		for(Module module : ModuleRegistry.getAllModules())
		{
			System.out.println("> " + module.getName() + "[" + module.getVersion() + "] By \"" + module.getAuthor() + "\" - " + module.getDescription());
		}
		return;
	}
}
