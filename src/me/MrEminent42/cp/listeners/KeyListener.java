package me.MrEminent42.cp.listeners;

import me.MrEminent42.cp.objects.CloudPackKey;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class KeyListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (CloudPackKey.isCloudPackKey(e.getItem())) {
		}
	}
	
}
