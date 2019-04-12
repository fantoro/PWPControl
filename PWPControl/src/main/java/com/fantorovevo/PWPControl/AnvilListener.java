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

import java.util.ListIterator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

public final class AnvilListener implements Listener {
	private final PWPControl plugin;
	
	public AnvilListener(PWPControl plugin) {
		this.plugin = plugin;
		
		this.plugin.getServer().getPluginManager().registerEvents(this,plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onAnvilClick(PrepareAnvilEvent event){
		if(!(plugin.getConfig().getBoolean("pwpenabled"))){
			ListIterator<ItemStack> invIterator = event.getInventory().iterator();
			while(invIterator.hasNext()) {
				ItemStack nextItem = invIterator.next();
				if(nextItem!=null) {
					ItemMeta nextItemMeta = nextItem.getItemMeta();
					if(nextItemMeta instanceof Repairable) {
							if(((Repairable)nextItemMeta).hasRepairCost()) {
							((Repairable)nextItemMeta).setRepairCost(0);
							nextItem.setItemMeta(nextItemMeta);
						}
					}
				}
			}
		}
	}
}
