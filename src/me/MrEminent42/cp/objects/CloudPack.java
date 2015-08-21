package me.MrEminent42.cp.objects;

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
	
	public CloudPack(UUID owner, String name, List<ItemStack> contents, int rows, boolean giveKey) {
		this.name = name;
		this.owner = owner;
		this.rows = rows;
		this.id = UUID.randomUUID();
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
	
	// Contents \\
	public boolean addItem(ItemStack item) {
		if (InvUtils.fits(inv.toInventory(), item)) {
			inv.toInventory().addItem(item);
			return true;
		}
		return false;
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
	
	// Rows \\
	public int getRows() {
		return this.rows;
	}
	
	// ID \\
	public UUID getID() {
		return this.id;
	}
	
}
