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
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

public final class ItempwpExecutor implements CommandExecutor {
	
	private final PWPControl plugin;
	
	public ItempwpExecutor(PWPControl plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("itempwp")||command.getName().equalsIgnoreCase("ipwp")) {
			if(sender instanceof Player) {
				Player plr = (Player) sender;
				if(!(args.length < 1)) {
					if(args[0].equalsIgnoreCase("get")) {
						if(plr.hasPermission("pwpcontrol.itempwp.get")) {
							ItemMeta plrIMeta = plr.getInventory().getItemInMainHand().getItemMeta();
							if(plrIMeta instanceof Repairable) {
								if(((Repairable)plrIMeta).hasRepairCost()) {
									sender.sendMessage("The prior-work penalty value of this item is " + ((Repairable)plrIMeta).getRepairCost());
								} else {
									sender.sendMessage("This item doesn't appear to have a prior-work penalty value");
								}
							}
							return true;
						}else {
							sender.sendMessage("You don't have permission to use this command");
							return false;
						}
					}else if(args[0].equalsIgnoreCase("set")) {
						int targetRepairCost;
						if(args.length<2) return false;
						try {
							targetRepairCost = Integer.parseInt(args[1]);
						} catch(NumberFormatException e) {
							sender.sendMessage("Invalid [value] argument");
							return false;
						}
						if(plr.hasPermission("pwpcontrol.itempwp.set")) {
							ItemMeta plrIMeta = plr.getInventory().getItemInMainHand().getItemMeta();
							if(plrIMeta instanceof Repairable) {
								((Repairable)plrIMeta).setRepairCost(targetRepairCost);
								plr.getInventory().getItemInMainHand().setItemMeta(plrIMeta);
							}else {
								sender.sendMessage("This item isn't repairable because of that, the prior-work penalty cannot be set");
							}
							return true;
						}else {
							sender.sendMessage("You don't have permission to use this command");
							return false;
						}
					}
					else {
						return false;
					}
				} else {
					return false;
				}
			}else {
				sender.sendMessage("This command can be run only by the player");
				return false;
			}
		}
		return false;
	}

}
