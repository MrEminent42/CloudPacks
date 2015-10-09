package me.MrEminent42.cp.objects.GUIManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUI {
	
	public static HashMap<Player, GUI> open = new HashMap<Player, GUI>();
	
	private String name;
	private int size;
	private HashMap<Integer, GUIItem> items = new HashMap<Integer, GUIItem>();
	private Inventory inv;
	
	public GUI(String name, int size, HashMap<Integer, GUIItem> items) {
		
		this.name = name;
		this.items = items;
		this.size = size;
		this.inv = Bukkit.getServer().createInventory(null, size, name);
		
	}
	
	public void addItem(Integer slot, GUIItem item) { 
		this.items.put(slot, item);
	}
	
	public void open(Player p) {
		for (Integer slot : this.items.keySet()) {
			 this.inv.setItem(slot, this.items.get(slot).getItem());
		}
		
		GUI.open.put(p, this);
		p.openInventory(this.inv);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public HashMap<Integer, GUIItem> getItems() {
		return this.items;
	}
	
	public Inventory getInventory() {
		return this.inv;
	}
}
