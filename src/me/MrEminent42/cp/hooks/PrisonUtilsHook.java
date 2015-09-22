package me.MrEminent42.cp.hooks;

import java.util.List;

import me.MrEminent42.cp.objects.CloudPack;
import me.mrCookieSlime.PrisonUtils.MiningHandler;
import me.mrCookieSlime.PrisonUtils.PrisonUtils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PrisonUtilsHook {
	
	
	public PrisonUtilsHook() { 
		
		PrisonUtils.registerMiningHandler(new MiningHandler(){

			@Override
			public List<ItemStack> onMine(Player p, Block b, List<ItemStack> drops, ItemStack tool) {
				if (drops.isEmpty()) return drops;
				
				for (int i = 0; i < drops.size(); i++) {
					ItemStack item = drops.get(i);
					if (item.getType().equals(Material.AIR)) continue;
					for (CloudPack pk : CloudPack.getLoadedPacks(p.getUniqueId())) {
						if (pk.addItem(item).isEmpty()) drops.remove(item);
					}
				}
				
				return drops;
			}
			
		});
		
	}
}
