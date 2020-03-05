package me.gannonburks.micromanage;

import javax.security.auth.login.LoginException;

import me.gannonburks.micromanage.util.Log;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

	//Global variable
	public static JDA discord;
	
	//Program Entry Point
	public static void main(String[] args) throws LoginException {
		
		if(args.length > 0) {
			
			//Login
			discord = new JDABuilder(AccountType.BOT).setToken(args[0]).build();
			
		} else {
			
			Log.Severe("Please enter a token as the first argument!");;
			System.exit(0);
		}
	}
}
