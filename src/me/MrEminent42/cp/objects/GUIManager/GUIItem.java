package me.MrEminent42.cp.objects.GUIManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class GUIItem {
	
	private ItemStack item;
	private String name;
	private ArrayList<String> lore;
	private ClickHandler handler;
	
	
	public GUIItem(ItemStack item, String name, ArrayList<String> lore, ClickHandler handler) {
		this.item = item;
		this.name = name;
		this.lore = lore;
		this.handler = handler;
	}


	public ClickHandler getHandler() {
		return handler;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getLore() {
		return lore;
	}
	
}
