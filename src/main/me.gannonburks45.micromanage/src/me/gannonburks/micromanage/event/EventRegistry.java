package src.me.gannonburks.micromanage.event;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import net.dv8tion.jda.internal.utils.Checks;

public class EventRegistry {

	private ArrayList<BotEvent> registry = new ArrayList<BotEvent>();
	
	//register
	public void register(BotEvent event)
	{
		this.registry.add(event);
	}
	
	//registerAll
	public void registerAll(@Nonnull BotEvent... events)
	{
		Checks.noneNull(events, "events");
		
		Collections.addAll(this.registry, events);
	}
	
	//deRegister
	public void deRegister(BotEvent event)
	{
		for(BotEvent regEvent : this.registry)
		{
			if(regEvent.equals(event))
			{
				this.registry.remove(event);
			}
		}
	}
	
	//Contains
	
	
	//Get
	public BotEvent get(String nameIn)
	{
		for(BotEvent event : this.registry)
		{
			if(event.getClass().getName().equalsIgnoreCase(nameIn))
			{
				return event;
			}
		}
		return null;
	}
	
	//GetAll
	public ArrayList<BotEvent> getAll()
	{
		return this.registry;
	}
}
