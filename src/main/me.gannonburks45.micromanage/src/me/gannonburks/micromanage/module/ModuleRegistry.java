package src.me.gannonburks.micromanage.module;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

import src.me.gannonburks.micromanage.util.Logger;

public class ModuleRegistry {

	private static ArrayList<Module> registry = new ArrayList<Module>();
	
	//Register
	public static void register(Module moduleIn)
	{
		registry.add(moduleIn);
	}
	
	//RegisterAll
	public void registerAll(@Nonnull Module... modulesIn)
	{
		
		for(Module module : modulesIn)
		{
			if(contains(module.getName()))
			{
				Logger.error("module registry already contains a module with the name \"" + module.getName() + "\".");
				return;
			}
		}
		
		Collections.addAll(registry, modulesIn);
	}
	
	
	//Deregister
	
	
	//Contains
	public boolean contains(Module moduleIn)
	{
		return registry.contains(moduleIn);
	}
	
	public boolean contains(String moduleIn)
	{
		return registry.contains(get(moduleIn));
	}
	
	//Get
	public Module get(String moduleIn)
	{
		for(Module module : registry)
		{
			if(module.getName().equalsIgnoreCase(moduleIn))
			{
				return module;
			}
		}
		return null;
	}
	
	//GetAll
	public ArrayList<Module> getAll()
	{
		return registry;
	}
}
