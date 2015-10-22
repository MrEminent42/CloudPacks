package me.MrEminent42.cp;

import java.util.Arrays;

import me.MrEminent42.cp.config.ConfigWrapper;
import me.MrEminent42.cp.config.Localization;
import me.MrEminent42.cp.hooks.AutoSellHook;
import me.MrEminent42.cp.hooks.PrisonUtilsHook;
import me.MrEminent42.cp.hooks.QuickSellHook;
import me.MrEminent42.cp.listeners.GUIListener;
import me.MrEminent42.cp.listeners.KeyListener;
import me.MrEminent42.cp.listeners.LoaderListener;
import me.MrEminent42.cp.listeners.MineListener;
import me.MrEminent42.cp.objects.CloudPack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudPacksPlugin extends JavaPlugin {
	
	public static ConfigWrapper config;
	public static Localization messages;
	
	boolean hookedAS;
	boolean hookedPU;
	boolean hookedQS;
	
	public void onEnable() {
		
		if (!getDescription().getAuthors().get(0).equals("MrEminent42")) {
			getLogger().severe("The author of this plugin is MrEminent42, not someone else!");
			getLogger().severe("Disabling this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		config = new ConfigWrapper(this, null, "config.yml");
		config.createFile("Loading config file...", getDescription().getName() + " configuration file");
		config.getConfig().addDefault("key.name", "&6Key to &r%name%");
		config.getConfig().addDefault("key.lore", Arrays.asList("&6Key to &r%name%", "&eCurrent Owner: &r%owner%"));
		config.getConfig().addDefault("auto-givekey", true);
		config.getConfig().addDefault("key-limit", 1);
		config.saveConfig();

		messages = new Localization();
		messages.createFile("Loading messages file...", "");
		messages.addDefault("general.no-permision", "&cYou don't have permission for this command.");
		messages.addDefault("general.only-players", "&cOnly players can use that command!");
		messages.addDefault("pack.list.title", "&f%player%'s &6Packs");
		messages.addDefault("pack.list.body", "&f%staticid% - &6%name%");
		messages.saveConfig();
		
		// Bukkit.getServer().getPluginManager().registerEvents(new GUIListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LoaderListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new KeyListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MineListener(), this);
		
		if (getServer().getPluginManager().isPluginEnabled("AutoSell")) {
			this.hookedAS = true;
			new AutoSellHook(this);
		}
		
		if (getServer().getPluginManager().isPluginEnabled("PrisonUtils")) {
			this.hookedPU = true;
			new PrisonUtilsHook();
		}
		
		if (getServer().getPluginManager().isPluginEnabled("QuickSell")) {
			hookedQS = true;
			new QuickSellHook();
		}
		
		CloudPack.setupPackFiles();
		
	}
	
	public void onDisable() {
		
	}
	
}
