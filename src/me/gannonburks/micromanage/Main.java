package me.gannonburks.micromanage;

import java.util.Scanner;
import java.util.logging.Logger;

import me.gannonburks.micromanage.util.Init;
import me.gannonburks.micromanage.util.MsgHandler;
import net.dv8tion.jda.api.JDA;

public class Main {

	//Instantiate stuff
	public static JDA bot;
	public static final Logger LOG = Logger.getLogger("MicroManage");
	
	//Global Stuff
	public static final String prefix = "-";
	
	//Program Entry Point
	public static void main(String[] args) {
		
		//Register Commands
		Init.regCommands();
		
		//Login to Discord
		Init.login(args);
		
		//"Console"
		Scanner keyboard = new Scanner(System.in);
		
		String inp = "";
		
		while(!(inp.startsWith(prefix + "shutdown"))) {
		
			inp = keyboard.nextLine();
			
			if(inp.startsWith(prefix + "say")) {
				
				MsgHandler.sendMsgBroadcast("general", inp.replaceFirst(prefix + "say", "").trim(), 1);
				continue;
			}
		}
		
		//Exit Program
		keyboard.close();
		System.exit(0);
	}
}
