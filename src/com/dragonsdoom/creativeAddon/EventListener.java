package com.dragonsdoom.creativeAddon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class EventListener implements Listener
	{
		Main plugin;

		public EventListener(Main ins) {
			this.plugin = ins;
		}


		@EventHandler(priority=EventPriority.HIGHEST)
		public void onPreprocess(PlayerCommandPreprocessEvent event)
		{
			Player player = event.getPlayer();
			String command = event.getMessage();
			if (player.isOp()) {
				return;
			}
		//command = command.substring(1).split("   ")[0];
			//if (command.equalsIgnoreCase("//copy")) {
			if(command.contains("//") || command.contains("/tool")) {
				event.setCancelled(true);
				player.sendMessage("Please use /we <command name> instead of //command."); 
				player.sendMessage("Exemple: //copy   --->   /we copy");
					return;
			}
		}
	}