package me.MrEminent42.cp.listeners;

import java.io.File;
import java.util.UUID;
import java.util.logging.Level;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.Config.CloudPackConfig;
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
		
		for (File file : new File("plugins/CloudPack/storage").listFiles()) {
			if (file.isDirectory() || !(file.getName().endsWith(".pack"))) continue;
			CloudPackConfig cfg = new CloudPackConfig(file);
			cfg.createFile("", "");
			
			if (cfg.getConfig().getString("owner").equalsIgnoreCase(p.getName())) CloudPack.loadPack(UUID.fromString(cfg.getConfig().getString("id")));
			
		}
	}
}
