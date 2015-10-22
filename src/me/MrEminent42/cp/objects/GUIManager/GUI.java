package me.MrEminent42.cp.objects.GUIManager;

import java.util.HashMap;
import java.util.UUID;

import me.MrEminent42.cp.CloudPacksPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUI {
	
	public static HashMap<UUID, GUI> open = new HashMap<UUID, GUI>();
	
	protected String title;
	protected int size;
	protected Inventory inv;
	protected HashMap<Integer, GUIItem> items;
	
	public GUI(String title, int size, HashMap<Integer, GUIItem> items) {
		this.title = title;
		this.items = items;
		this.size = size;
	}
	
	public GUI addItem(Integer slot, GUIItem item) { 
		this.items.put(slot, item);
		return this;
	}
	
	public void open(Player p) {
		for (Integer slot : this.items.keySet()) {
			 this.inv.setItem(slot, this.items.get(slot));
		}
		
		GUI.open.put(p.getUniqueId(), this);
		p.openInventory(this.inv);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public HashMap<Integer, GUIItem> getItems() {
		return this.items;
	}
	
//	public Inventory getInventory() {
//		return this.inv;
//	}
}
