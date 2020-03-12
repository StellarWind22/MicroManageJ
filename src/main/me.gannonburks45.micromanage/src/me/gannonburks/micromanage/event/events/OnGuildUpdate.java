package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.CommandRegistry;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.Server;
import src.me.gannonburks.micromanage.server.ServerRegistry;

public class OnGuildUpdate extends BotEvent {

	public OnGuildUpdate(boolean canStopIn)
	{
		super(canStopIn);
	}

	@Override
	public void onGuildJoin(GuildJoinEvent event)
	{
		CommandRegistry reg = new CommandRegistry();
		
		reg.registerAll(ModuleRegistry.getAllCommands());
		
		ServerRegistry.register(new Server(event.getGuild(), Main.DEFAULT_PREFIX, reg));
	}
	
	@Override
	public void onGuildLeave(GuildLeaveEvent event)
	{
		CommandRegistry reg = new CommandRegistry();
		
		reg.registerAll(ModuleRegistry.getAllCommands());
		
		ServerRegistry.register(new Server(event.getGuild(), Main.DEFAULT_PREFIX, reg));
	}
}
