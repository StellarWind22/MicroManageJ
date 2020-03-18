package src.me.gannonburks.micromanage.event;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotEvent extends ListenerAdapter {
	
	private boolean isStopped = false;
	private boolean canStop = true;
	
	/**
	 * Constuctor for BotEvent class.
	 * 
	 * @param canStop	If the event can be canceled
	 * 					by another module or not.
	 */
	public BotEvent(boolean canStop)
	{
		this.canStop = canStop;
	}
	
	/**
	 * Method for checking whether or not the
	 * event can be stopped.
	 * 
	 * @return If the event can be stopped.
	 */
	public boolean canStop()
	{
		return this.canStop;
	}
	
	/**
	 * Method for checking whether or not the
	 * event has been stopped.
	 * 
	 * @return If the event is stopped.
	 */
	public boolean isStopped()
	{
		return this.isStopped;
	}
	
	/**
	 * Method for setting an event to stop firing.
	 * 
	 * @param isStoppedIn What to set that status as.
	 */
	public void setStopped(boolean isStoppedIn)
	{
		this.isStopped = isStoppedIn;
	}
}
