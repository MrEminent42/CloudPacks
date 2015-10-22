package me.MrEminent42.cp.objects.GUIs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import me.MrEminent42.cp.objects.CloudPack;
import me.MrEminent42.cp.objects.GUIManager.ClickHandler;
import me.MrEminent42.cp.objects.GUIManager.GUI;
import me.MrEminent42.cp.objects.GUIManager.GUIItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class PackList extends GUI {

	public PackList(Player p, Integer page) {
		super(p.getName() + "'s Packs", 45, new HashMap<Integer, GUIItem>());
		
		final ArrayList<CloudPack> packs = new ArrayList<CloudPack>(CloudPack.getPlayerPacks(p));
		for (int i = 0; i < packs.size(); i++) {
			GUIItem item = new GUIItem(Material.ENDER_CHEST, i, new ClickHandler(){

				@Override
				public void onClick(Player p, GUIItem clicked) {
					for (CloudPack pack : CloudPack.getPlayerPacks(p)) {
						if (pack.getName().equalsIgnoreCase(pack.getName()) && UUID.fromString(clicked.getItemMeta().getLore().get(0).split(" ")[1]).equals(pack.getID())) {
							new PackOptions(pack).open(p);
						}
					}
					
				}
				
			});
			
			ItemMeta meta = item.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.GRAY + "ID: " + packs.get(i).getID()));
			item.setItemMeta(meta);
			
			addItem(i, item);
		}
	}

	
	
}
