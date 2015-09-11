package me.MrEminent42.cp.GUI;

import java.util.HashMap;

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
	
}
