package me.MrEminent42.cp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MrEminent42.cp.objects.CloudPack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import org.bukkit.inventory.ItemStack;

public class CloudPackUtils {
	
	public static ArrayList<CloudPack> getPacks(UUID owner) {
		ArrayList<CloudPack> packs = new ArrayList<CloudPack>();
		
		File folder = new File("plugins/CloudPacks/storage");
		for (File file : folder.listFiles()) {
			Config cfg = new Config(file);
			if (file.isDirectory()) continue;
			List<ItemStack> items = new ArrayList<ItemStack>();
			int rows = cfg.getKeys("contents").size();
			for (int i = 0; i < rows; i++) items.add(cfg.getItem("contents" + i));
			packs.add(new CloudPack(UUID.fromString(file.getName()), cfg.getString("name"), items, rows, false));
		}
		
		return packs;
		
	}
	
	public static void something() {
		
	}
	
}
