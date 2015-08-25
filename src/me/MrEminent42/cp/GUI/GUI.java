package me.MrEminent42.cp.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.CloudPack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GUI {
	
	CloudPacksPlugin plugin;
	Inventory inv;
	UUID id;
	
	ConversationFactory factory = new ConversationFactory(new CloudPacksPlugin());
	
	public GUI(Player p, GUIType type, CloudPack pack) {
		this.id = pack.getID();
		switch (type) {
		default:
			break;
		case PACKLIST:
			this.inv = Bukkit.getServer().createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', plugin.messages.getConfig().getString("gui.your-backpacks")));
			List<CloudPack> packs = CloudPack.getLoadedPacks(p.getUniqueId());
			List<ItemStack> items = new ArrayList<ItemStack>();
			
			for (int i = 0; i < packs.size(); i++) {
				ItemStack item = new ItemStack(Material.ENDER_CHEST);
				ItemMeta im = item.getItemMeta();
				String owner = p.getName();
				final UUID id = CloudPack.getLoadedPacks(p.getUniqueId()).get(i).getID();
				
				if (Bukkit.getPlayer(CloudPack.getLoadedPacks(p.getUniqueId()).get(i).getOwner()).getName().equalsIgnoreCase(p.getName())) owner = "You";
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', CloudPack.getLoadedPacks(p.getUniqueId()).get(i).getName()));
				im.setLore(Arrays.asList(
						ChatColor.translateAlternateColorCodes('&', "&6Size: " + CloudPack.getLoadedPacks(p.getUniqueId()).get(i).getRows()), 
						ChatColor.translateAlternateColorCodes('&', "&6Owner: " + owner), 
						ChatColor.translateAlternateColorCodes('&', ""), 
						ChatColor.translateAlternateColorCodes('&', "&6Left click to open"), 
						ChatColor.translateAlternateColorCodes('&', "&6Right click to view options"), 
						ChatColor.translateAlternateColorCodes('&', ""), 
						ChatColor.translateAlternateColorCodes('&', "&7ID: " + CloudPack.getLoadedPacks(p.getUniqueId()).get(i).getID())
				));
				item.setItemMeta(im);
				items.add(item);
				inv.addItem(items.get(i));
//				inv.addMenuClickHandler(i, new MenuClickHandler(){
//					
//					@Override
//					public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
//						new GUIInterface(p, GUIType.PACKOPTIONS, CloudPack.getPack(id));
//						return false;
//					}
//					
//				});
			}
			
			p.openInventory(inv);
		case PACKOPTIONS:
			this.inv = Bukkit.getServer().createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', plugin.messages.getConfig().getString("gui.options").replace("%name%", pack.getName())));
			
			// Key Item \\
			
			// Rename Item \\
			inv.addItem(new ItemStack(Material.BLAZE_ROD));

//				@Override
//				public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
//					factory.withFirstPrompt(new PackRenamePrompt());
//					return false;
//				}
			
			// Friends Item \\
			
		case GIVEPACK:
			this.inv = Bukkit.getServer().createInventory(null, 0, ChatColor.translateAlternateColorCodes('&', "&1Give a CloudPack"));
		case ADMINPANEL:
			this.inv = Bukkit.getServer().createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&1CloudPack Admin Panel"));
		
		}
	}
	
//	@EventHandler
//	public void onPlayerChat(AsyncPlayerChatEvent e) {
//		if (waiting.contains(new Waiting(e.getPlayer(), InputType.GIVEKEY_PLAYER))) {
//			if (Bukkit.getPlayer(e.getMessage().split(" ")[0]) == null) {
//				// TODO send message saying no
//				return;
//			}
//			
//			if (e.getMessage().split(" ")[0].equalsIgnoreCase("all")) {
//				for (Player p : Bukkit.getOnlinePlayers()) CloudPack.getPack(this.id).giveKey(p);
//			}
//			
//			CloudPack.getPack(this.id).giveKey(Bukkit.getPlayer(e.getMessage().split(" ")[0]));
//		} else if (waiting.contains(new Waiting(e.getPlayer(), InputType.PACK_RENAME))) {
//			CloudPack.getPack(this.id);
//		}
//	}
	
}
