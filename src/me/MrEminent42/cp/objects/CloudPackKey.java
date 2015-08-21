package me.MrEminent42.cp.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.MrEminent42.cp.CloudPacksPlugin;

public class CloudPackKey {
	
	CloudPacksPlugin plugin;
	CloudPack p;
	String name;
	List<String> lore;
	
	public CloudPackKey(CloudPack p) {
		this.p = p;
		this.name = CloudPacksPlugin.config.getString("key.name");
		this.lore = CloudPacksPlugin.config.getStringList("key.lore");
	}
	
	public void give(Player p) {
		ItemStack item = new ItemStack(Material.matchMaterial(CloudPacksPlugin.config.getString("key.item")));
		List<String> lore = new ArrayList<String>();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(this.name.replaceAll("%name%", this.p.getName()).replaceAll("%owner%", Bukkit.getPlayer(this.p.getOwner()).getName()));
		for (String s : this.lore) lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("%name%", this.p.getName()).replaceAll("%owner%", Bukkit.getPlayer(this.p.getOwner()).getName())));
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7ID: " + this.p.getID()));
		meta.setLore(lore);
		
		p.getInventory().addItem(item);
		p.updateInventory();
	}
	
	public boolean opens(CloudPack p) {
		for (String s : this.lore) {
			if (s.contains("ID")) {
				UUID id = UUID.fromString(s.split(" ")[1]);
				if (id.equals(p.getID())) return true;
			} 
		}
		
		return false;
	}
	
}
