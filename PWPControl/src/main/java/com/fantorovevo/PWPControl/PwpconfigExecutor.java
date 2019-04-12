package com.fantorovevo.PWPControl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class PwpconfigExecutor implements CommandExecutor {
	private final PWPControl plugin;
	
	public PwpconfigExecutor(PWPControl plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("pwpconfig")) {
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("enable")) {
					if(sender.hasPermission("pwpcontrol.pwpconfig.enable")) {
						plugin.getConfig().set("pwpenabled", true);
						plugin.saveConfig();
						return true;
					}else {
						sender.sendMessage("You don't have permission to use this command");
						return false;
					}
				}else if(args[0].equalsIgnoreCase("disable")) {
					if(sender.hasPermission("pwpcontrol.pwpconfig.disable")) {
						plugin.getConfig().set("pwpenabled", false);
						plugin.saveConfig();
						return true;
					}else {
						sender.sendMessage("You don't have permission to use this command");
						return false;
					}
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		return false;
	}

}
