package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import src.me.gannonburks.micromanage.event.BotEvent;
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
		ServerRegistry.register(new Server(event.getGuild()));
	}
	
	@Override
	public void onGuildLeave(GuildLeaveEvent event)
	{
		ServerRegistry.register(new Server(event.getGuild()));
	}
}
