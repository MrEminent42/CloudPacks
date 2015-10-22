package me.MrEminent42.cp.listeners;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.GUIManager.ClickHandler;
import me.MrEminent42.cp.objects.GUIManager.GUI;
import me.MrEminent42.cp.objects.GUIManager.GUIItem;
import net.minecraft.server.v1_8_R3.Material;

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
		if (p == null || e.getCurrentItem() == null || e.getCurrentItem().equals(Material.AIR)) return;
		if (GUI.open.containsKey(p) && GUI.open.get(p).getTitle().equals(e.getInventory().getTitle())) {
			ClickHandler handler = GUI.open.get(p.getUniqueId()).getItems().get(e.getSlot()).getHandler();
			if (handler != null) handler.onClick(p, new GUIItem(e.getCurrentItem()));
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (!(e.getPlayer() instanceof Player)) {
			return;
		}
		if (GUI.open.containsKey(e.getPlayer().getUniqueId()) && GUI.open.get(e.getPlayer().getUniqueId()).getTitle().equals(e.getInventory().getTitle())) {
			GUI.open.remove(e.getPlayer().getUniqueId());
		}
	}
}