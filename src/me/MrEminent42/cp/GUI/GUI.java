package me.MrEminent42.cp.GUI;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GUI {
	
	private String name;
	private int size;
	private HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
	
	public GUI(String name, int size, HashMap<Integer, ItemStack> items) {
		
		this.name = name;
		this.items = items;
		this.size = size;
		
	}
	
	public void addItem(ItemStack item, int slot) { 
		this.items.put(slot, item);
	}
	
	public void open(Player p) {
		// TODO Finish This.
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public HashMap<Integer, ItemStack> getItems() {
		return this.items;
	}
}
