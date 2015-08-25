	package me.MrEminent42.cp.GUI;

import org.bukkit.event.inventory.InventoryAction;

public abstract class GUIClicks {
	
	public abstract void onKeyClick();
	public abstract void onFriendClick();
	public abstract void onRenameClick();
	public abstract void onGetKeyClick();
	public abstract void onGiveKeyClick();
	public abstract void onCloudPackItemClick(InventoryAction a);
	
}
