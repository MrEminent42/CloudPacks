package me.MrEminent42.cp.GUI;

import org.bukkit.entity.Player;

public class Waiting {
	
	Player p;
	InputType type;
	
	public Waiting(Player p, InputType type) {
		this.p = p;
		this.type = type;
	}
	
	public InputType getType() {
		return type;
	}
	
	public Player getPlayer() {
		return p;
	}
	
}
