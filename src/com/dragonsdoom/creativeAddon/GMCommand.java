package com.dragonsdoom.creativeAddon;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand implements CommandExecutor{
	
	public GMCommand(Main main) {}

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){	
		if(sender instanceof Player){
	           Player player = (Player) sender;	 	
		       if(cmd.getName().equalsIgnoreCase("mode")){
		    	   if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
		    		   player.setGameMode(GameMode.CREATIVE);
		    		   player.sendMessage("Your gamemode is now set to Creative Mode");
		    	       Main.plugin.getLogger().info(player.getName() + " has changed their gamemode to Creative");
		    	   }
		    	   if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
		    		   player.setGameMode(GameMode.SURVIVAL);
		    		   player.sendMessage("Your gamemode is now set to Survival Mode");
		    	       Main.plugin.getLogger().info(player.getName() + " has changed their gamemode to Survival");
		    	   }
		    	   
		    	   return false;
		       }
		       return false;
		}
		return false;
	}

	
}