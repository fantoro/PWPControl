/*
 *  This file is part of PWPControl.
 *
 *  PWPControl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  PWPControl is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with PWPControl.  If not, see <https://www.gnu.org/licenses/>.
 * 
 *  Copyright (C) 2019 fantoro
 * 
 *  fantoro@outlook.com
 * 
*/
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
