package src.me.gannonburks.micromanage.event;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotEvent extends ListenerAdapter implements IBotEvent {
	
	private boolean isStopped = false;
	private boolean canStop = true;
	
	public BotEvent(boolean canStopIn)
	{
		this.canStop = canStopIn;
	}
	
	//getter canStop
	public boolean canStop()
	{
		return this.canStop;
	}
	
	//getter isStopped
	public boolean isStopped()
	{
		return this.isStopped;
	}
	
	//setter isStopped
	public void setStopped(boolean isStoppedIn)
	{
		this.isStopped = isStoppedIn;
	}
}
