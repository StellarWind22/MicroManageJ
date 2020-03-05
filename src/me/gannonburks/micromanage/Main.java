package me.gannonburks.micromanage;

import java.util.Random;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;

import me.gannonburks.micromanage.events.Init;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class Main {

	//Global variable
	public static JDA discord;
	public static String msg;
	
	public static Logger log = Logger.getLogger("MicroManage");
	
	//Program Entry Point
	public static void main(String[] args) throws LoginException {
		
		Init.init();
		
		
		if(args.length > 0) {
			
			//Login
			discord = new JDABuilder(AccountType.BOT).setToken(args[0]).build();
			
			discord.getPresence().setActivity(Activity.watching("you"));
			
			
			//Wierd Window Process Thing
			msg = JOptionPane.showInputDialog(null, "Message:", "Send Discord Message?", JOptionPane.YES_NO_OPTION);
			Msg(msg);
			
			while(msg != null) {
				
				msg = JOptionPane.showInputDialog(null, "Message:", "Send Discord Message?", JOptionPane.YES_NO_OPTION);
				Msg(msg);
				
			}
			System.exit(0);
			
		} else {
			
			log.severe("Please enter a token as the first argument!");;
			System.exit(0);
		}
	}
	
	//Send Message
	public static void Msg(String messageIn) {
		
		String message = formatMsg(messageIn);
		
		if(message == null) {
			return;
		}
		
		for(Guild guild : discord.getGuilds())  {
			
			TextChannel channel = guild.getTextChannelsByName("general", true).get(0);
			
			channel.sendMessage(message.replaceAll("\\n", "\n")).queue();;
		}
		
		log.info("Sent message: \"" + message + "\"");
		return;
	}
	
	//Reverse Message
	public static String formatMsg(String msgIn) {
		
		Random rand = new Random();
		
		String[] words = msgIn.split(" ");
		String msgOut = "";
		
		//Loop through and reverse string
		for (int i = 0; i < words.length; i++) {
			
			//Random replace vowels
			if(rand.nextInt(3) == 0) {
				
				words[i] = words[i].replaceAll("[aeiou]", "e");
				words[i] = words[i].replaceAll("[AEIOU]", "E");
			}
			
			//Deal with first word nonsense
            if (i == words.length - 1) {
            	
            	msgOut = words[i] + msgOut;
            	
            } else {
            	
            	//Randomly remove words
            	if(rand.nextInt(3) == 0) {
            		
            		continue;
            		
            	} else {
            		
            		msgOut = " " + words[i] + msgOut; 
            	}
            }
        } 
		return msgOut;
	}
}
