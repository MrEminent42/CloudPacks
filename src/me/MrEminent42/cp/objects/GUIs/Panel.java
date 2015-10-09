package me.MrEminent42.cp.objects.GUIs;

import java.util.HashMap;

import me.MrEminent42.cp.objects.GUIManager.GUI;
import me.MrEminent42.cp.objects.GUIManager.GUIItem;

import org.bukkit.entity.Player;

public class Panel extends GUI{

	public Panel(Player p) {
		super("", 9, new HashMap<Integer, GUIItem>());
	}
	
}
