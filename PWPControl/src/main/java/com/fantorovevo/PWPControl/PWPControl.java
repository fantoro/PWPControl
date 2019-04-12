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

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.inventory.PrepareAnvilEvent;


public final class PWPControl extends JavaPlugin {
	private Logger log = getLogger();
	private AnvilListener anvListener;
	
	@Override public void onEnable() {	
		/*
		 * print out copyright notice
		 */
		log.info("PWPControl Copyright (C) 2019 fantoro\nThis program comes with ABSOLUTELY NO WARRANTY;\nThis is free software, and you are welcome to redistribute it under certain conditions;");
		
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
		/*
		 * unregister events
		 */
		log.info("Unregistering the anvil event.");
		PrepareAnvilEvent.getHandlerList().unregister(anvListener);
	}
}
