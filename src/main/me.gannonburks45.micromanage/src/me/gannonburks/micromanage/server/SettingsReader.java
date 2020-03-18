package src.me.gannonburks.micromanage.server;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.dv8tion.jda.api.entities.User;
import src.me.gannonburks.micromanage.Main;
import src.me.gannonburks.micromanage.command.BotCommand;
import src.me.gannonburks.micromanage.module.ModuleRegistry;

public final class SettingsReader {
	
	private final static String SETTINGS_FOLDER = "../settings/";
	
	/**
	 * Method for getting timestamp of the day
	 * the bot joined the server.
	 * 
	 * @param server Server to get join date for.
	 * 
	 * @return Timestamp of join date.
	 */
	protected static Timestamp getJoinDate(DiscordServer server)
	{
		return Timestamp.valueOf(safeParseSettingsFile(server).get("joined").toString());
	}
	
	/**
	 * Method for getting the prefix from the
	 * server settings .json file.
	 * 
	 * @param server Server to get prefix from.
	 * 
	 * @return Prefix of the server.
	 */
	protected static String getPrefix(DiscordServer server)
	{
		return safeParseSettingsFile(server).get("prefix").toString();
	}
	
	/**
	 * Method for checking if a command is disabled
	 * in the server.
	 * 
	 * @param server	Server to check in.
	 * @param command	Command to check for.
	 * 
	 * @return If command is disabled
	 */
	protected static boolean isDisabedIn(DiscordServer server, BotCommand command)
	{
		JSONObject[] commands = (JSONObject[]) ((JSONArray)safeParseSettingsFile(server).get("commands")).toArray();
		String label = command.getLabel();
		
		for(JSONObject commandContainer : commands)
		{
			JSONObject commandJson = (JSONObject) commandContainer.get("command");
			
			if(commandJson.get("label").toString() == label)
			{
				return Boolean.parseBoolean(commandJson.get("disabled").toString());
			}
		}
		return false;
	}
	
	/**
	 * Method for getting the timestamp of the date
	 * that the command was last changed.
	 * 
	 * @param server	Server to check in.
	 * @param command	Command to check for.
	 * 
	 * @return Last changed timestamp.
	 */
	protected static Timestamp getLastChanged(DiscordServer server, BotCommand command)
	{
		JSONObject[] commands = (JSONObject[]) ((JSONArray)safeParseSettingsFile(server).get("commands")).toArray();
		String label = command.getLabel();
		
		for(JSONObject commandContainer : commands)
		{
			JSONObject commandJson = (JSONObject) commandContainer.get("command");
			
			if(commandJson.get("label").toString() == label)
			{
				return Timestamp.valueOf(commandJson.get("last_changed").toString());
			}
		}
		return null;
	}
	
	/**
	 * Method for getting the JDA User object
	 * of the user that last changed it.
	 * 
	 * @param server	Server to look in.
	 * @param command	Command to get from.
	 * 
	 * @return JDA User object if last edit was a user.
	 */
	protected static User getLastChangedBy(DiscordServer server, BotCommand command)
	{
		JSONObject[] commands = (JSONObject[]) ((JSONArray)safeParseSettingsFile(server).get("commands")).toArray();
		String label = command.getLabel();
		
		for(JSONObject commandContainer : commands)
		{
			JSONObject commandJson = (JSONObject) commandContainer.get("command");
			
			if(commandJson.get("label").toString() == label)
			{
				return Main.bot.getUserById(commandJson.get("changed_by").toString());
			}
		}
		return null;
	}
	
	/**
	 * Method for changing the prefix setting
	 * of a server
	 * 
	 * @param server	Server to change setting in.
	 * @param newPrefix	Value to set prefix to.
	 * 
	 * @return If operation was successful.
	 */
	@SuppressWarnings("unchecked")
	protected static boolean setPrefix(DiscordServer server, String newPrefix)
	{
		JSONObject settingsFile = (JSONObject)safeParseSettingsFile(server);
		
		settingsFile.replace("prefix", newPrefix);
		
		return overwriteSettingsFile(settingsFile, server);
	}
	
	/**
	 * Method for changing the disabled status
	 * of a command in a server.
	 * 
	 * @param server		Server to change setting in.
	 * @param command		Command to change status of.
	 * @param sender		User who requested this change.
	 * @param isDisabled	Value to change it to.
	 * 
	 * @return If operation was successful.
	 */
	@SuppressWarnings("unchecked")
	protected static boolean setDisabledIn(DiscordServer server, BotCommand command, User sender, boolean isDisabled)
	{
		if(!(command.canDisable()))
		{
			return false;
		}
		
		JSONObject settingsFile = (JSONObject)safeParseSettingsFile(server);
		JSONObject[] commands = (JSONObject[]) ((JSONArray)settingsFile.get("commands")).toArray();
		String label = command.getLabel();
		
		for(JSONObject commandContainer : commands)
		{
			JSONObject commandJson = (JSONObject) commandContainer.get("command");
			
			if(commandJson.get("label").toString() == label && commandJson.get("disabled").toString() != null)
			{
				commandJson.replace("disabled", isDisabled);
				commandJson.replace("last_changed", new Timestamp(System.currentTimeMillis()));
				commandJson.replace("changed_by", sender.getId());
				
				return overwriteSettingsFile(settingsFile, server);
			}
		}
		return false;
	}
	
	/**
	 * Method for ensuring that a settings file exists
	 * before reading from it.
	 * 
	 * @param server	Server to ensure settings file for.
	 * @param path		Path of file.
	 * 
	 * @return File object for the file.
	 */
	private static File ensureFile(DiscordServer server, Path path)
	{
		if(Files.notExists(path))
		{
			generateSettings(server, path);
		}
		return path.toFile();
	}
	
	
	/**
	 * Method for generating settings file for a server.
	 * 
	 * @param server	Server to generate file for.
	 * @param path		Path of file.
	 */
	@SuppressWarnings("unchecked")
	private static void generateSettings(DiscordServer server, Path path)
	{
		ArrayList<BotCommand> commands = ModuleRegistry.getAllGuildCommands();
		ArrayList<JSONObject> commandsJson = new ArrayList<JSONObject>();
		
		//Create main file object
		JSONObject settingsFile = new JSONObject();
		
		//Set basic server info
		settingsFile.put("prefix", Main.DEFAULT_PREFIX);
		settingsFile.put("joined", new Timestamp(System.currentTimeMillis()));
		
		//Generate all the command json objects
		for(BotCommand command : commands)
		{
			//Create Objects
			JSONObject commandJsonDetails = new JSONObject();
			JSONObject commandJsonObject = new JSONObject();
			
			//Label
			commandJsonDetails.put("label", command.getLabel());
			
			//Disabled
			if(command.canDisable())
			{
				commandJsonDetails.put("disabled", false);
			}
			else
			{
				commandJsonDetails.put("disabled", null);
			}
			
			//Last changed
			commandJsonDetails.put("last_changed", new Timestamp(System.currentTimeMillis()));
			
			//Changed by
			commandJsonDetails.put("changed_by", null);
			
			//Shove into command object
			commandJsonObject.put("command", commandJsonDetails);
			
			//add to list of command objects
			commandsJson.add(commandJsonObject);
		}
		
		//shove them into commands json array
		JSONArray commandList = new JSONArray();
		commandList.addAll(commandsJson);
		
		//Stick that into file object
		settingsFile.put("commands", commandList);
		
		//Write actual file
		try (FileWriter file = new FileWriter(path.toString()))
		{
			file.write(settingsFile.toJSONString());
			file.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for parsing the settings file
	 * into a JSONObject.
	 * 
	 * @param server Server to parse file for.
	 * 
	 * @return JSONObject of the file.
	 * 
	 * @throws ParseException If json file is invalid.
	 */
	private static JSONObject parseSettingsFile(DiscordServer server) throws ParseException
	{
		try {
			//Read In File
			Object settingsFile = new JSONParser().parse(new FileReader(ensureFile(server, Paths.get(SETTINGS_FOLDER + server.getName() + ".json"))));
			
			return (JSONObject) settingsFile;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method for parsing the settings file
	 * into a JSONObject with some extra error
	 * handling stuff
	 * 
	 * @param server Server to parse file for.
	 * 
	 * @return JSONObject if it worked.
	 */
	private static JSONObject safeParseSettingsFile(DiscordServer server)
	{
		try
		{
			return parseSettingsFile(server);
		}
		catch (ParseException e)
		{
			generateSettings(server, Paths.get(SETTINGS_FOLDER + server.getName() + ".json"));
			
			try
			{
				return parseSettingsFile(server);
			}
			catch
			(ParseException e1)
			{
				e1.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * Method for overwriting the settings
	 * file with a new JSONObject.
	 * 
	 * @param parsedFile	JSONObject to overwrite file with.
	 * @param server		Server to overwrite the file of.
	 * 
	 * @return If operation was successful.
	 */
	private static boolean overwriteSettingsFile(JSONObject parsedFile, DiscordServer server)
	{
		Path path = Paths.get(SETTINGS_FOLDER + server.getName() + ".json");
		
		try (FileWriter file = new FileWriter(path.toString()))
		{
			file.write(parsedFile.toJSONString());
			file.flush();
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
