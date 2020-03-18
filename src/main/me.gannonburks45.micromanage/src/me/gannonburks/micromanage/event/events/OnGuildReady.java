package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import src.me.gannonburks.micromanage.event.BotEvent;
import src.me.gannonburks.micromanage.server.DiscordServer;
import src.me.gannonburks.micromanage.server.ServerRegistry;

public class OnGuildReady extends BotEvent {

	public OnGuildReady(boolean canStopIn)
	{
		super(canStopIn);
	}

	@Override
	public void onGuildReady(GuildReadyEvent event)
	{
		ServerRegistry.register(new DiscordServer(event.getGuild()));
	}
}
