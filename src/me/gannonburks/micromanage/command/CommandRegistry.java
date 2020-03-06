package me.gannonburks.micromanage.command;

import java.util.ArrayList;

public class CommandRegistry {

	private static ArrayList<Command> registry = new ArrayList<Command>();
	
	//Register
	public static void register(Command cmdIn) {
		
		registry.add(cmdIn);
	}
	
	//RegisterAll
	public static void registerAll(Command[] cmdsIn) {
		
		for(Command cmdLItem : cmdsIn) {
			
			register(cmdLItem);
		}
	}
	
	//Deregister
	public static void deRegister(Command cmdIn) {
		
		for(Command cmd : registry) {
			
			if(cmd.equals(cmdIn)) {
				
				registry.remove(cmdIn);
			}
		}
	}
	
	//contains
	public static boolean contains(Command command) {
		
		return registry.contains(command);
	}
	
	public static boolean contains(String labelIn) {
		
		return registry.contains(get(labelIn));
	}
	
	//getCommand
	public static Command get(String labelIn) {
		
		for(Command cmd : registry) {
			
			if(cmd.getLabel().equalsIgnoreCase(labelIn)) {
				
				return cmd.getCommand();
			}
		}
		return null;
	}
	
	//getAll
	public static Command[] getAll() {
		
		ArrayList<Command> cmdsOut = new ArrayList<Command>();
		
		for(Command cmd : registry) {
			
			cmdsOut.add(cmd);
		}
		
		return (Command[]) cmdsOut.toArray();
	}
}
