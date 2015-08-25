package me.MrEminent42.cp.listeners;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.GUI.GUIClicks;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class GUIListener extends GUIClicks implements Listener {
	
	

	CloudPacksPlugin plugin;
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase(CloudPacksPlugin.messages.getConfig().getString("gui-key-item"))) {
			onKeyClick();
		}
	}
	
}
