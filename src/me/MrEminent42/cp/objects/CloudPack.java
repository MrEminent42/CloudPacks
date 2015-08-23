package me.MrEminent42.cp.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CloudPack {
	
	private static ArrayList<CloudPack> active = new ArrayList<CloudPack>();
	
	private String name;
	private UUID id;
	private UUID owner;
	private int rows;
	private ChestMenu inv;
	private List<ItemStack> contents = new ArrayList<ItemStack>();
	
	private Config bc;
	
	public CloudPack(UUID owner, String name, UUID id, List<ItemStack> contents, int rows, boolean giveKey) {
		this.name = name;
		this.owner = owner;
		this.rows = rows;
		this.id = id;
		this.bc = new Config("plugins/CloudPack/storage/" + id + ".yml");
		this.bc.setValue("name", name);
		this.bc.setValue("contents", contents);
		this.bc.setValue("rows", rows);
		
		if (giveKey) {
			giveKey(Bukkit.getPlayer(name));
		}
		
		active.add(this);
	}
	
	public void showBackpack(Player p) { 
		inv.open(p);
	}
	
	public void giveKey(Player p) {
		CloudPackKey key = new CloudPackKey(this);
		key.give(p);
	}
	
	public boolean isActive(CloudPack p) {
		if (active.contains(p)) return true;
		return false;
	}
	
	// Pack Management \\
	public static ArrayList<CloudPack> getPacks(UUID owner) {
		ArrayList<CloudPack> packs = new ArrayList<CloudPack>();
		
		File folder = new File("plugins/CloudPacks/storage");
		for (File file : folder.listFiles()) {
			Config cfg = new Config(file);
			if (file.isDirectory()) continue;
			List<ItemStack> items = new ArrayList<ItemStack>();
			int rows = cfg.getKeys("contents").size();
			for (int i = 0; i < rows; i++) items.add(cfg.getItem("contents" + i));
			packs.add(new CloudPack(UUID.fromString(file.getName()), cfg.getString("name"), 
					UUID.fromString(file.getName()), items, rows, false));
		}
		return packs;
	}
	
	public static CloudPack getPack(UUID id) {
		File file = new File("plugins/CloudPacks/storage" + id + ".yml");
		Config cfg = new Config(file);
		List<ItemStack> items = new ArrayList<ItemStack>();
		for (int i = 0; i < cfg.getInt("rows"); i++) items.add(cfg.getItem("contents" + i));
		
		return new CloudPack(UUID.fromString(file.getName()), cfg.getString("name"), 
				UUID.fromString(file.getName()), items, cfg.getInt("rows"), false);
	}
	
	// Contents \\
	public boolean addItem(ItemStack item) {
		if (InvUtils.fits(inv.toInventory(), item)) {
			inv.toInventory().addItem(item);
			return true;
		}
		return false;
	}
	
	public void removeItem(ItemStack item) {
		inv.toInventory().remove(item);
	}
	
	public void setContents(List<ItemStack> contents) { 
		this.contents = contents;
	}
	
	public List<ItemStack> getContents() {
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
	
	// ID \\
	public UUID getID() {
		return this.id;
	}
	
}
