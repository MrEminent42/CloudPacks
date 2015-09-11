package me.MrEminent42.cp;

import java.io.File;
import java.util.Arrays;

import me.MrEminent42.cp.Config.ConfigWrapper;
import me.MrEminent42.cp.listeners.GUIListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudPacksPlugin extends JavaPlugin {
	
	public static ConfigWrapper config;
	public static ConfigWrapper messages;
	
	public void onEnable() {
		
		
		config = new ConfigWrapper(this, getDataFolder() + File.separator, "config.yml");
		messages = new ConfigWrapper(this, getDataFolder() + File.separator, "messages.yml");
		
		config.createFile("Loading config file...", getDescription().getName() + " v" + getDescription().getVersion() + " configuration file");
		messages.createFile("Loading messages file...", "");
		
		config.getConfig().addDefault("key.name", "&6Key to &r%name%");
		config.getConfig().addDefault("key.lore", Arrays.asList("&6Key to &r%name%", "&eCurrent Owner: &r%owner%"));
		config.getConfig().addDefault("auto-givekey", true);
		config.getConfig().addDefault("key-limit", 1);
		
		
		Bukkit.getServer().getPluginManager().registerEvents(new GUIListener(this), this);
		
	}
	
}
