package me.MrEminent42.cp.listeners;

import java.util.List;

import me.MrEminent42.cp.objects.CloudPack;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class MineListener implements Listener {
	
	public static List<ItemStack> handleMining(Player p, List<ItemStack> list) {
		if (list.isEmpty()) return list;
		
		for (int i = 0; i < list.size(); i++) {
			ItemStack item = list.get(i);
			if (item.getType() == Material.AIR) continue;
			for (CloudPack pk : CloudPack.getPlayerPacks(p)) {
				if (pk.addItem(item).isEmpty()) list.remove(item);
			}
		}
		
		if (list.isEmpty()) return list;
		for (int i = 0; i < list.size(); i++) {
			p.getInventory().addItem(list.get(i));
			list.remove(i);
		}
		

		p.updateInventory();
		return list;
	}
	
}
