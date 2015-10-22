package me.MrEminent42.cp;

import me.MrEminent42.cp.config.Localization;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class CloudPackCommand implements CommandExecutor {
	
	Localization messages = CloudPacksPlugin.getPlugin(CloudPacksPlugin.class).messages;
	 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cloudpacks")) {
			if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
				sendHelp(sender);
				return true;
			}
			
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("list")) {
					if (args.length >= 2) {
						if (sender.hasPermission(new Permission("cloudpacks.list.other", PermissionDefault.OP))) {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							
						}
					}
				} else if (args[0].equalsIgnoreCase("give")) {
					
					
				} else if (args[0].equalsIgnoreCase("givekey")) {
					
				}
			}
			
		}
		return true;
	}
	
	public void sendHelp(CommandSender sender) {
		if (sender instanceof Player) {
			
		}
	}
	
}
