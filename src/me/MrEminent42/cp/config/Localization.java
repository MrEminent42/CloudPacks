package me.MrEminent42.cp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.MrEminent42.cp.CloudPacksPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Localization extends ConfigWrapper {
	
	static CloudPacksPlugin plugin = CloudPacksPlugin.getPlugin(CloudPacksPlugin.class);

    public Localization() {
    	super(plugin, null, "messages.yml");
    }
    
    public void addDefault(String path, String message) {
    	getConfig().addDefault(path, message);
    }
    
    public ArrayList<String> getMessage(String path) {
    	return new ArrayList<String>(Arrays.asList((String[]) getConfig().getString(path).split("||")));
    }
    
    public void sendMessage(CommandSender sender, String path, Integer customLength) {
    	int go = (customLength == null) ? getMessage(path).size() : customLength;
    	
    	for (int i = 0; i < go; i++) {
    		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getMessage(path).get(i)));
    	}
    }

}