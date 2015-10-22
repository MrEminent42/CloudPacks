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
	
	static CloudPacksPlugin plugin = CloudPacksPlugin.getPlugin(CloudPacksPlugin.class);
	
	CloudPack pack;
	String name;
	List<String> lore;
	
	public CloudPackKey(CloudPack pack) {
		this.pack = pack;
		this.name = CloudPacksPlugin.messages.getMessage("key.name").get(0);
		this.lore = CloudPacksPlugin.messages.getMessage("key.lore");
	}
	
	public CloudPackKey(UUID packID) {
		new CloudPackKey(CloudPack.getPack(packID));
	}
	
	public void give(Player p) {
		ItemStack item = new ItemStack(Material.matchMaterial(CloudPacksPlugin.config.getConfig().getString("key.item")));
		List<String> lore = new ArrayList<String>();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(this.name.replaceAll("%name%", this.pack.getName()).replaceAll("%owner%", Bukkit.getPlayer(this.pack.getOwner()).getName()));
		for (String s : this.lore) lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("%name%", this.pack.getName()).replaceAll("%owner%", Bukkit.getPlayer(this.pack.getOwner()).getName())));
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7ID: " + this.pack.getID()));
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
	
	public static CloudPack opens(CloudPackKey key) {
		return key.pack;
	}
	
	public static boolean isCloudPackKey(ItemStack item) {
		if (item.getItemMeta().getLore().get(item.getItemMeta().getLore().size() - 1).contains("ID: ")) return true;
		return false;
	}
	
}
