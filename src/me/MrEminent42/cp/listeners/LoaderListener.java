package me.MrEminent42.cp.listeners;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.CloudPack;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LoaderListener implements Listener {
	
	CloudPacksPlugin plugin;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		CloudPack.loadPlayerPacks(p);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		CloudPack.unloadPlayerPacks(p);
	}
}
