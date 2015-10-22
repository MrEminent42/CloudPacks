package me.MrEminent42.cp.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.config.CloudPackFile;
import me.MrEminent42.cp.config.ConfigWrapper;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CloudPack {
	
	private static CloudPacksPlugin plugin = CloudPacksPlugin.getPlugin(CloudPacksPlugin.class);
	
	private static ArrayList<CloudPack> packs = new ArrayList<CloudPack>();
	private static ArrayList<CloudPack> open = new ArrayList<CloudPack>();
	private static ArrayList<CloudPackFile> packFiles = new ArrayList<CloudPackFile>();
	
	private String name;
	private UUID id;
	private UUID owner;
	private int rows;
	private int staticID;
	private Inventory inv;
	private ArrayList<ItemStack> contents = new ArrayList<ItemStack>();
	private CloudPackFile packConfig;
	
	public CloudPack(UUID owner, String name, UUID id, Integer staticID, ArrayList<ItemStack> contents, int rows) {
		this.name = name;
		this.owner = owner;
		this.rows = rows;
		this.staticID = staticID;
		this.id = id;
		this.contents = contents;
		this.packConfig = new CloudPackFile(this);
		this.inv = Bukkit.getServer().createInventory(null, rows, name);
		
		packs.add(this);
	}
	
	public CloudPack(CloudPackFile config) {
		config.reloadConfig();
		this.name = config.getName();
		this.id = config.getID();
		this.owner = config.getOwner();
		this.rows = config.getRows();
		this.staticID = config.getStaticID();
		this.contents = config.getContents();
		this.packConfig = config;
		this.inv = Bukkit.getServer().createInventory(null, rows, name);
		
		packs.add(this);
	}
	
	public void showBackpack(Player p) { 
		p.openInventory(inv);
	}
	
	public void giveKey(Player p) {
		CloudPackKey key = new CloudPackKey(this);
		key.give(p);
	}
	
	// Contents \\
	public HashMap<Integer, ItemStack> addItem(ItemStack item) {
		return inv.addItem(item);
	}
	
	public void removeItem(ItemStack item) {
		inv.remove(item);
	}
	
	public void setContents(ArrayList<ItemStack> contents) { 
		this.contents = contents;
	}
	
	public ArrayList<ItemStack> getContents() {
		return this.contents;
	}
	
	// Owner \\
	public UUID getOwner() {
		return this.owner;
	}
	
	// Name \\
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Rows \\
	public int getRows() {
		return this.rows;
	}
	
	// Static ID \\
	public int getStaticID() {
		return this.staticID;
	}
	
	// ID \\
	public UUID getID() {
		return this.id;
	}
	
	// INV \\
	public Inventory getInv() {
		return this.inv;
	}
	
	// Save \\
	public void saveToFile() {
		this.packConfig.getConfig().set("name", this.name);
		this.packConfig.getConfig().set("owner", this.owner);
		this.packConfig.getConfig().set("id", this.id);
		this.packConfig.getConfig().set("rows", this.rows);
		this.packConfig.getConfig().set("static-id", this.staticID);
		this.packConfig.getConfig().set("contents", this.contents);
		this.packConfig.saveConfig();
		this.packConfig.reloadConfig();
	}
	
	// Unload \\
	public void unload() {
		this.saveToFile();
		CloudPack.packs.remove(this);
	}
	
	public static ArrayList<CloudPack> getPlayerPacks(OfflinePlayer p) {
		ArrayList<CloudPack> packs = new ArrayList<CloudPack>();
		for (CloudPack pack : packs) {
			if (pack.getOwner().equals(p.getUniqueId())) packs.add(pack);
		}
		return packs;
	}
	
	public static CloudPack getPack(UUID packID) {
		for (CloudPack pack : packs) {
			if (pack.getID().equals(packID)) return pack;
		}
		return null;
	}
	
	/**
	 * Loads all packs for an OfflinePlayer
	 * @param p - OfflinePlayer to load packs for
	 */
	public static void loadPlayerPacks(OfflinePlayer p) {
		for (CloudPackFile config : packFiles) {
			if (config.getOwner().equals(p.getUniqueId())) {
				new CloudPack(config);
			}
		}
	}
	
	/**
	 * Get all CloudPackConfigs in the storage folder
	 * @return The ArrayList of CloudPackConfigs that were loaded
	 */
	public static ArrayList<CloudPackFile> getAllPackFiles() {
		ArrayList<CloudPackFile> configs = new ArrayList<CloudPackFile>();
		
		File folder = new File("plugins/CloudPacks/storage");
		for (File f : folder.listFiles()) {
			if (!f.isDirectory()) {
				String[] name = f.getName().split(File.separator);
				if (CloudPackFile.isCloudPackConfig(new ConfigWrapper(plugin, "storage", name[name.length - 1]))) {
					configs.add(new CloudPackFile(f));
				}
			}
		}
		
		return configs;
	}
	
	public static void unloadPlayerPacks(OfflinePlayer p) {
		for (CloudPack pack : CloudPack.getPlayerPacks(p)) {
			if (pack.getOwner().equals(p.getUniqueId())) {
				pack.unload();
			}
		}
	}
	
	public static ArrayList<CloudPackFile> setupPackFiles() {
		for (CloudPackFile config : getAllPackFiles()) {
			CloudPack.packFiles.add(config);
		}
		return packFiles;
	}
}
