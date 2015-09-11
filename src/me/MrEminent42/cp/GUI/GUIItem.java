package me.MrEminent42.cp.GUI;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class GUIItem {
	
	ItemStack item;
	String name;
	List<String> lore;
	int slot;
	GUIClickHandler handler;
	
	
	public GUIItem(ItemStack item, String name, List<String> lore, int slot, GUIClickHandler handler) {
		
		this.item = item;
		this.name = name;
		this.lore = lore;
		this.slot = slot;
		this.handler = handler;
		
		
		
	}
	
}
