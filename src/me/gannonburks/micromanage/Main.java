package me.gannonburks.micromanage;

import java.util.logging.Logger;

import me.gannonburks.micromanage.util.Init;
import net.dv8tion.jda.api.JDA;

public class Main {

	//Instantiate stuff
	public static JDA bot;
	public static final Logger LOG = Logger.getLogger("MicroManage");
	
	//Global Stuff
	public static final String prefix = "-";
	
	//Program Entry Point
	public static void main(String[] args) {
		
		//Register Events
		Init.regEventListeners();
		
		//Login to Discord
		Init.login(args);
	}
}
