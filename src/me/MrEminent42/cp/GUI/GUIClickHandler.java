package me.MrEminent42.cp.GUI;

import org.bukkit.entity.Player;

public abstract class GUIClickHandler {
	
	public abstract void onKeyClick(Player p);
	public abstract void onFriendClick(Player p);
	public abstract void onRenameClick(Player p);
	public abstract void onGetKeyClick(Player p);
	public abstract void onGiveKeyClick(Player p);
	public abstract void onCloudPackItemClick(Player p);
	
}
