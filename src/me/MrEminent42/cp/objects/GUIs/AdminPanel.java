package me.MrEminent42.cp.objects.GUIs;

import java.util.HashMap;

import me.MrEminent42.cp.CloudPacksPlugin;
import me.MrEminent42.cp.objects.GUIManager.GUI;
import me.MrEminent42.cp.objects.GUIManager.GUIItem;

public class AdminPanel extends GUI {

	public AdminPanel() {
		super(CloudPacksPlugin.messages.getConfig().getString("GUI.adminpanel.title"), 9, new HashMap<Integer, GUIItem>());
		
	}
	
}
