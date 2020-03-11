package src.me.gannonburks.micromanage.event.events;

import net.dv8tion.jda.api.events.ReadyEvent;
import src.me.gannonburks.micromanage.event.BotEvent;

public class OnBotReady extends BotEvent {

	public OnBotReady(boolean canStopIn) {
		super(canStopIn);
	}

	@Override
	public void onReady(ReadyEvent event)
	{
		
	}
}
