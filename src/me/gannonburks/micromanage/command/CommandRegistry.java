package me.gannonburks.micromanage.command;

import java.util.ArrayList;

public class CommandRegistry {

	private static ArrayList<Command> cmds = new ArrayList<Command>();
	
	//Register
	public static void register(Command cmdIn) {
		
		cmds.add(cmdIn);
	}
	
	//RegisterAll
	public static void registerAll(Command[] cmdsIn) {
		
		for(Command cmdLItem : cmdsIn) {
			
			register(cmdLItem);
		}
	}
	
	//Deregister
	public static void deRegister(Command cmdIn) {
		
		for(Command cmd : cmds) {
			
			if(cmd.equals(cmdIn)) {
				
				cmds.remove(cmdIn);
			}
		}
	}
	
	//contains
	public static boolean contains(Command cmdIn) {
		
		for(Command cmd : cmds) {
			
			if(cmd.equals(cmdIn)) {
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(String labelIn) {
		
		for(Command cmd: cmds) {
			
			if(cmd.getLabel().equalsIgnoreCase(labelIn)) {
				return true;
			}
		}
		return false;
	}
	
	//getCommand
	public static Command getFromLabel(String labelIn) {
		
		for(Command cmd : cmds) {
			
			if(cmd.getLabel().equalsIgnoreCase(labelIn)) {
				
				return cmd;
			}
		}
		return null;
	}
}
