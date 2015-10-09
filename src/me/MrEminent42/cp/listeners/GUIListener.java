package me.MrEminent42.cp.listeners;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.GUIManager.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {
	
	CloudPacksPlugin plugin = CloudPacksPlugin.getPlugin(CloudPacksPlugin.class);
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		if (GUI.open.containsKey(p)) {
			if (GUI.open.get(p).getInventory().equals(e.getInventory())) {
				GUI.open.get(p).getItems().get(e.getSlot()).getHandler().onClick(p);
			}
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (GUI.open.containsKey(e.getPlayer())) {
			GUI.open.remove(e.getPlayer());
		}
	}
}
