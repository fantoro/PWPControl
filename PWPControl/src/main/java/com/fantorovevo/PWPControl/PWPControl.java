package com.fantorovevo.PWPControl;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.inventory.PrepareAnvilEvent;


public final class PWPControl extends JavaPlugin {
	private Logger log = getLogger();
	private AnvilListener anvListener;
	
	@Override public void onEnable() {
		/*log.info("Registering /checkpwp's command executor.");
		CheckPWPCommandExecutor executor = new CheckPWPCommandExecutor(this);
		this.getCommand("checkpwp").setExecutor(executor);*/
		
		/*
		 * Check if a config exists, make a new one based on the default config if it doesn't exist
		 */
		
		
		/*
		 * register commands
		 */
		log.info("Registering PWPControl's commands.");
		this.getCommand("itempwp").setExecutor(new ItempwpExecutor(this));
		this.getCommand("pwpconfig").setExecutor(new PwpconfigExecutor(this));
		
		/*
		 * register events
		 */
		log.info("Registering the anvil event.");
		anvListener = new AnvilListener(this);
	}
	@Override public void onDisable() {
		//log.info("PWPControl has been disabled");
		
		/*
		 * unregister events
		 */
		log.info("Unregistering the anvil event.");
		PrepareAnvilEvent.getHandlerList().unregister(anvListener);
	}
}
