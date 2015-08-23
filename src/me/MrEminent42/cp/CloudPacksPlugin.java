package me.MrEminent42.cp;

import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Localization;

import org.bukkit.plugin.java.JavaPlugin;

import CSCoreLibSetup.CSCoreLibLoader;

public class CloudPacksPlugin extends JavaPlugin {
	
	public static Config config;
	public static Localization messages;
	
	public void onEnable() {
		CSCoreLibLoader loader = new CSCoreLibLoader(this);
		
		if (loader.load()) {
			
			PluginUtils utils = new PluginUtils(this);
			utils.setupConfig();
			config = utils.getConfig();
			utils.setupLocalization();
			messages = utils.getLocalization();
			utils.setupMetrics();
			
			messages.setDefault("", "&cYou cannot perform %cmd%!");
			messages.save();

		}
	}
	
}
