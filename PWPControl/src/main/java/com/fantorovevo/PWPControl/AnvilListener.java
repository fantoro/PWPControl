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
