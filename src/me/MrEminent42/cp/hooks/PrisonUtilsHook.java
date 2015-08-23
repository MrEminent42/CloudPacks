package me.MrEminent42.cp.hooks;

import java.util.List;

import me.mrCookieSlime.PrisonUtils.MiningHandler;
import me.mrCookieSlime.PrisonUtils.PrisonUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PrisonUtilsHook {
	
	
	public PrisonUtilsHook() { 
		
		PrisonUtils.registerMiningHandler(new MiningHandler(){

			@Override
			public List<ItemStack> onMine(Player p, Block b, List<ItemStack> drops, ItemStack tool) {
				
				if (tool.getItemMeta().getDisplayName().contains("Announcer!")) {
					Bukkit.getServer().broadcastMessage(ChatColor.GOLD + p.getName() + " broke a block!");
				}
				return drops;
			}
			
		});
		
	}
}
