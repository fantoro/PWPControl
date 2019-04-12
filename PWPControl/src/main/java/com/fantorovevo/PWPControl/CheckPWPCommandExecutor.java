package com.fantorovevo.PWPControl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;


public final class CheckPWPCommandExecutor implements CommandExecutor {
	//private final PWPControl plugin;
	
	public CheckPWPCommandExecutor(PWPControl plugin) {
		//this.plugin = plugin;
	}
	
	@Override public boolean onCommand(CommandSender cmdsender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("checkpwp")) {
			if(!(cmdsender instanceof Player)) {
				cmdsender.sendMessage("This command can only be ran by a player.");
			}else {
				Player plr = (Player) cmdsender;
				ItemStack selectedItem = plr.getInventory().getItemInMainHand();
				ItemMeta selectedItemMeta = selectedItem.getItemMeta();
				if(selectedItemMeta instanceof Repairable) {
					if(((Repairable) selectedItemMeta).hasRepairCost()){
						plr.sendMessage("The current prior-work penalty value of this item is "+((Repairable) selectedItemMeta).getRepairCost());
					}else {
						plr.sendMessage("This item doesn't appear to have a prior-work penalty value.\nProbably because it wasn't used in an anvil.");
					}
				}
				/*CustomItemTagContainer itemNBT = selectedItemMeta.getCustomTagContainer();
				NamespacedKey repaircost = NamespacedKey.minecraft("repaircost");
				if(itemNBT.hasCustomTag(repaircost, ItemTagType.INTEGER)) {
					plr.sendMessage("The current PWP value of this item is " + itemNBT.getCustomTag(repaircost, ItemTagType.INTEGER));
				}else {
					plr.sendMessage("This item doesn't appear to have a prior-work penalty value.\nProbably because it wasn't used in an anvil."+repaircost.getKey()+repaircost.toString());
				}*/
			}
			return true;
		}
		return false;
	}

}
