package me.MrEminent42.cp.objects.GUIs;

import java.util.HashMap;

import me.MrEminent42.cp.objects.GUIManager.ClickHandler;
import me.MrEminent42.cp.objects.GUIManager.GUI;
import me.MrEminent42.cp.objects.GUIManager.GUIItem;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerPanel extends GUI {

	public PlayerPanel(Player p) {
		super(p.getName() + "'s Panel", 9, new HashMap<Integer, GUIItem>());
		
		GUIItem packsItem = new GUIItem(Material.ENDER_CHEST, 1, new ClickHandler(){

			@Override
			public void onClick(Player p, GUIItem clicked) {
				new PackList(p).open(p);
			}
			
		}); 
		
	}
	
}
