package me.MrEminent42.cp.hooks;

import java.util.List;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.CloudPack;
import me.clip.autosell.events.DropsToInventoryEvent;
import me.clip.autosell.events.SellAllEvent;
import me.clip.autosell.events.SignSellEvent;
import me.clip.autosell.objects.Shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AutoSellHook implements Listener {
	
	CloudPacksPlugin plugin;
	
	public AutoSellHook(CloudPacksPlugin pl) {
		this.plugin = pl;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSellAll(SellAllEvent e) {
		Shop shop = e.getShop();
		
		int addItems = 0;
		double addMoney = 0;
		
		for (CloudPack pk : CloudPack.getActivatedPacks(e.getPlayer().getUniqueId())) {
			List<ItemStack> backpackItems = pk.getContents();
			
			for (ItemStack item : backpackItems) {
				if (item == null || item.getType() == Material.AIR) continue;
				
				for (ItemStack shopItem : shop.getPrices().keySet()) {
					if (shopItem.getType() == item.getType() && shopItem.getDurability() == item.getDurability()) {
						pk.removeItem(item);
						
						addItems = addItems + item.getAmount();
						double temp = item.getAmount() * shop.getPrices().get(shopItem);
						addMoney = addMoney + temp;
					}
				}
			}
			
			
		}
		
		if (addMoney > 0 && addItems > 0) {
			if (e.hasMultiplier()) addMoney = addMoney* e.getMultiplier();
			e.setTotalCost(e.getTotalCost() + addMoney);
			e.setTotalItems(e.getTotalItems() + addItems);
		}
	}
	
	@EventHandler
	public void onSignSell(SignSellEvent e) {
		Shop shop = e.getShop();
		
		int addItems = 0;
		double addMoney = 0;
		
		for (CloudPack pk : CloudPack.getActivatedPacks(e.getPlayer().getUniqueId())) {
			List<ItemStack> backpackItems = pk.getContents();
			
			for (ItemStack item : backpackItems) {
				if (item == null || item.getType() == Material.AIR) continue;
				
				for (ItemStack shopItem : shop.getPrices().keySet()) {
					if (shopItem.getType() == item.getType() && shopItem.getDurability() == item.getDurability()) {
						pk.removeItem(item);
						
						addItems = addItems + item.getAmount();
						double temp = item.getAmount() * shop.getPrices().get(shopItem);
						addMoney = addMoney + temp;
					}
				}
			}
			
			
		}
		
		if (addMoney > 0 && addItems > 0) {
			if (e.hasMultiplier()) addMoney = addMoney* e.getMultiplier();
			e.setTotalCost(e.getTotalCost() + addMoney);
			e.setTotalItems(e.getTotalItems() + addItems);
		}
	}
	
	@EventHandler
	public void dropsToInventory(DropsToInventoryEvent e) {
		Player p = e.getPlayer();
		Inventory pInv = p.getInventory();
		if (e.getDrops().isEmpty()) return;
		
		for (int i = 0; i < e.getDrops().size(); i++) {
			ItemStack item = e.getDrops().get(i);
			if (item.getType() == Material.AIR) continue;
			for (CloudPack pk : CloudPack.getActivatedPacks(e.getPlayer().getUniqueId())) {
				if (pk.addItem(item).isEmpty()) e.getDrops().remove(item);
			}
		}
	}

}
