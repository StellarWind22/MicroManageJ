package src.me.gannonburks.micromanage.util;

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
import src.me.gannonburks.micromanage.command.Command;
import src.me.gannonburks.micromanage.module.ModuleRegistry;
import src.me.gannonburks.micromanage.server.Server;

public final class SettingsReader {
	
	private final static String SETTINGS_FOLDER = "/settings/";
	
	//get join date
	public static Timestamp getJoinDate(Server server)
	{
		return Timestamp.valueOf(safeParseSettingsFile(server).get("joined").toString());
	}
	
	//get prefix for server
	public static String getPrefix(Server server)
	{
		return safeParseSettingsFile(server).get("prefix").toString();
	}
	
	//return if command is disabled
	public static boolean isDisabedIn(Server server, Command command)
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
	
	//return timestamp of last change to command
	public static Timestamp getLastChanged(Server server, Command command)
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
	
	//get the user that changed that command
	public static User getLastChangedBy(Server server, Command command)
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
	
	//set values
	@SuppressWarnings("unchecked")
	public static boolean setPrefix(Server server, String newPrefix)
	{
		JSONObject settingsFile = (JSONObject)safeParseSettingsFile(server);
		
		settingsFile.replace("prefix", newPrefix);
		
		return overwriteSettingsFile(settingsFile, server);
	}
	
	@SuppressWarnings("unchecked")
	public static boolean setDisabledIn(Server server, Command command, User sender, boolean isDisabled)
	{
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
	
	//ensureFile
	public static File ensureFile(Server server, Path path)
	{
		if(Files.notExists(path))
		{
			generateSettings(server, path);
		}
		return path.toFile();
	}
	
	
	//generate
	@SuppressWarnings("unchecked")
	public static void generateSettings(Server server, Path path)
	{
		ArrayList<Command> commands = ModuleRegistry.getAllGuildCommands();
		ArrayList<JSONObject> commandsJson = new ArrayList<JSONObject>();
		
		//Create main file object
		JSONObject settingsFile = new JSONObject();
		
		//Set basic server info
		settingsFile.put("prefix", Main.DEFAULT_PREFIX);
		settingsFile.put("joined", new Timestamp(System.currentTimeMillis()));
		
		//Generate all the command json objects
		for(Command command : commands)
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
	
	//Parse file into JSONObject
	public static JSONObject parseSettingsFile(Server server) throws ParseException
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
	
	//Parse file into JSONObject with extra parseerror handling stuff
	public static JSONObject safeParseSettingsFile(Server server)
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
	
	public static boolean overwriteSettingsFile(JSONObject parsedFile, Server server)
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
