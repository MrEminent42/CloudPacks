package me.MrEminent42.cp.listeners;

import java.util.logging.Level;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.CloudPack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	CloudPacksPlugin plugin;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		Bukkit.getLogger().log(Level.INFO, "Loading all CloudPacks for " + p.getName() + "...");
		
		CloudPack.loadPlayerPacks(p);
	}
}
