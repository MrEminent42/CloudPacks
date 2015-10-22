package me.MrEminent42.cp.objects.GUIManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIItem extends ItemStack {
	
	private ClickHandler handler;
	public ItemMeta meta;
	
	public GUIItem(Material mat, Integer amount, ClickHandler handler) {
		super(mat, amount);
		this.handler = handler;
		this.meta = getItemMeta();
	}
	
	public GUIItem(ItemStack item) {
		super(item.getType(), item.getAmount());
	}

	public ClickHandler getHandler() {
		return handler;
	}
	
}
