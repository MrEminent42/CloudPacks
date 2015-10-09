package me.MrEminent42.cp.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.CloudPack;

import org.bukkit.inventory.ItemStack;

public class CloudPackConfig extends ConfigWrapper {
	
	static CloudPacksPlugin plugin = CloudPacksPlugin.getPlugin(CloudPacksPlugin.class);

	String name;
	int rows;
	UUID owner;
	UUID id;
	ArrayList<ItemStack> contents;
	
	/**
	 * Create a config from a filled file. Does not add values. Used to see values of packs.
	 * @param file
	 */
	public CloudPackConfig(File file) {
		super(plugin, "storage", file.getName());
		reloadConfig();
		
		this.name = getConfig().getString("name");
		this.owner = UUID.fromString(getConfig().getString("owner"));
		this.rows = getConfig().getInt("rows");
		this.id = UUID.fromString("id");
		for (String s : getConfig().getConfigurationSection("contents").getKeys(false)) {
			this.contents.add(getConfig().getItemStack("contents." + s));
		}
	}
	
	/**
	 * Creating a config from a pack. Adds all values according to pack.
	 * @param pack
	 */
    public CloudPackConfig(CloudPack pack) {
    	super(plugin, "storage", pack.getID().toString() + ".pack");
    	
    	createFile(null, ""
    			+ "\nCloudPack Name: " + name
    			+ "\nCloudPack ID: " + id.toString()
    			+ "\n\nWARNING: DO NOT EDIT THIS FILE WHEN THE SERVER IS RUNNING."
    			+ "\n\nALL changes will be overriden when shutting down the server or reloading."
    			+ "\nIF you need to make a change to this file, shut down your server first!");
    	
    	getConfig().set("name", this.name = pack.getName());
    	getConfig().set("owner", this.owner = pack.getOwner());
    	getConfig().set("rows", this.rows = pack.getRows());
    	getConfig().set("id", this.id = pack.getID());
    	getConfig().set("contents", this.contents = pack.getContents());
    	saveConfig();
    }
    
    public int getRows() {
    	return rows;
    }
    
    public String getName() {
    	return name;
    }
    
    public UUID getID() {
    	return id;
    }
    
    public UUID getOwner() {
    	return owner;
    }
	
    public ArrayList<ItemStack> getContents() {
    	return contents;
    }
    
    public static boolean isCloudPackConfig(ConfigWrapper config) {
    	if (config.getConfig().contains("name") && config.getConfig().contains("owner") && config.getConfig().contains("contents") && config.getConfig().contains("id") && config.getConfig().contains("owner") && config.getConfig().getName().endsWith(".pack")) {
    		return true;
    	}
    	return false;
    }
}