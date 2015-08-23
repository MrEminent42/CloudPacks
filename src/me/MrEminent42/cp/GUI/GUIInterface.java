package me.MrEminent42.cp.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.MrEminent42.cp.objects.CloudPack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIInterface implements Listener {
	
	ChestMenu inv;
	UUID id;
	List<Waiting> waiting = new ArrayList<Waiting>();
	HashMap<Player, String> input = new HashMap<Player, String>();
	
	public GUIInterface(Player p, GUIType type, CloudPack pack) {
		this.id = id;
		switch (type) {
		default:
			break;
		case PACKLIST:
			this.inv = new ChestMenu(ChatColor.translateAlternateColorCodes('&', "&1Your CloudPacks"));
			List<CloudPack> packs = new ArrayList<CloudPack>();
			List<ItemStack> items = new ArrayList<ItemStack>();
			
			for (int i = 0; i < CloudPack.getPacks(p.getUniqueId()).size(); i++) {
				ItemStack item = new ItemStack(Material.ENDER_CHEST);
				ItemMeta im = item.getItemMeta();
				String owner = p.getName();
				final UUID id = CloudPack.getPacks(p.getUniqueId()).get(i).getID();
				
				if (Bukkit.getPlayer(CloudPack.getPacks(p.getUniqueId()).get(i).getOwner()).getName().equalsIgnoreCase(p.getName())) owner = "You";
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', CloudPack.getPacks(p.getUniqueId()).get(i).getName()));
				im.setLore(Arrays.asList(
						ChatColor.translateAlternateColorCodes('&', "&6Size: " + CloudPack.getPacks(p.getUniqueId()).get(i).getRows()), 
						ChatColor.translateAlternateColorCodes('&', "&6Owner: " + owner), 
						ChatColor.translateAlternateColorCodes('&', ""), 
						ChatColor.translateAlternateColorCodes('&', "&6Left click to open"), 
						ChatColor.translateAlternateColorCodes('&', "&6Right click to view options"), 
						ChatColor.translateAlternateColorCodes('&', ""), 
						ChatColor.translateAlternateColorCodes('&', "&7ID: " + CloudPack.getPacks(p.getUniqueId()).get(i).getID())
				));
				item.setItemMeta(im);
				items.add(item);
				inv.addItem(i, items.get(i));
				inv.addMenuClickHandler(i, new MenuClickHandler(){
					
					@Override
					public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
						new GUIInterface(p, GUIType.PACKOPTIONS, CloudPack.getPack(id));
						return false;
					}
					
				});
			}
			
			inv.build().open(p);
		case PACKOPTIONS:
			this.inv = new ChestMenu(ChatColor.translateAlternateColorCodes('&', pack.getName() + "&1 Options"));
			
			// Key Item \\
			
			
		case GIVEPACK:
			this.inv = new ChestMenu(ChatColor.translateAlternateColorCodes('&', "&1Give a CloudPack"));
		case ADMINPANEL:
			this.inv = new ChestMenu(ChatColor.translateAlternateColorCodes('&', "&1CloudPack Admin Panel"));
		
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (waiting.contains(new Waiting(e.getPlayer(), InputType.GIVEKEY_PLAYER))) {
			if (Bukkit.getPlayer(e.getMessage().split(" ")[0]) == null) {
				// TODO send message saying no
				return;
			}
			
			if (e.getMessage().split(" ")[0].equalsIgnoreCase("all")) {
				for (Player p : Bukkit.getOnlinePlayers()) CloudPack.getPack(this.id).giveKey(p);
			}
			
			CloudPack.getPack(this.id).giveKey(Bukkit.getPlayer(e.getMessage().split(" ")[0]));
		} else if (waiting.contains(new Waiting(e.getPlayer(), InputType.PACK_RENAME))) {
			CloudPack.getPack(this.id);
		}
	}
	
}
