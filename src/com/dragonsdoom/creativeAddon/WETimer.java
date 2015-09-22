package com.dragonsdoom.creativeAddon;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.milkbowl.vault.permission.Permission;

public class WETimer implements CommandExecutor{
	
	public WETimer(Main main) {}
	
	private static Table<String, String, Long> cooldowns = HashBasedTable.create();
	
	public static long getCooldown(Player player, String key) {
        return calculateRemainder(cooldowns.get(player.getName(), key));
    }
	
	
	 public static boolean tryCooldown(Player player, String key, long delay) {
	        if (getCooldown(player, key) <= 0) {
	            setCooldown(player, key, delay);
	            return true;
	        }
	        return false;
	    }
	 
	 
	 public static long setCooldown(Player player, String key, long delay) {
	        return calculateRemainder(
	                cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));
	    }
	 
	 
	 private static long calculateRemainder(Long expireTime) {
	        return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
	    }
	
		
	 
	 public static Permission perms = null;
	 public boolean setupVaultPermissions(){
	        RegisteredServiceProvider<Permission> rsp = Main.plugin.getServer().getServicesManager().getRegistration(Permission.class);
	        perms = rsp.getProvider();
	        return perms != null;
	  }
	 

	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){	
		Player player = (Player) sender;
		if(sender instanceof Player){   
			if(cmd.getName().equalsIgnoreCase("we")){
		    	String myString = "";
			    for(int i = 0; i < args.length; i++){
			    	String arg = args[i] + " ";
			        myString = myString + arg;
			    }
			if(player.hasPermission("we.permission")) {    
				List<String> dcstring = Main.plugin.getConfig().getStringList("WorldEdit.denied-Commands");
				if(!dcstring.contains(args[0])) {
					if(tryCooldown(player, "we", 0)) {
		//run the command		
						if(args[0].equalsIgnoreCase("tool")) {
							Main.plugin.getServer().dispatchCommand(player, myString);
						}
						if(!args[0].equalsIgnoreCase("tool")) {
							Main.plugin.getServer().dispatchCommand(player, "/" + myString);
						}
						
						if(player.hasPermission(Main.plugin.getConfig().getString("WorldEdit.timer.donator.noCooldown.perm"))) {
								return true;
						}
		    	   //start the timer depending on permission
						if(player.hasPermission(Main.plugin.getConfig().getString("WorldEdit.timer.donator.lvl1.perm"))) {
							long cooldownTime = Long.valueOf(Main.plugin.getConfig().getString("WorldEdit.timer.donator.lvl1.timer"));
							setCooldown(player, "we", cooldownTime );
							return true;
						}
						if(player.hasPermission(Main.plugin.getConfig().getString("WorldEdit.timer.donator.lvl2.perm"))) {
							long cooldownTime = Long.valueOf(Main.plugin.getConfig().getString("WorldEdit.timer.donator.lvl2.timer"));
							setCooldown(player, "we", cooldownTime );
							return true;
						}
						if(player.hasPermission(Main.plugin.getConfig().getString("WorldEdit.timer.non-donator.lvl1.perm"))) {
							long cooldownTime = Long.valueOf(Main.plugin.getConfig().getString("WorldEdit.timer.non-donator.lvl1.timer"));
							setCooldown(player, "we", cooldownTime );
							return true;
						}
						if(player.hasPermission(Main.plugin.getConfig().getString("WorldEdit.timer.non-donator.lvl2.perm"))) {
							long cooldownTime = Long.valueOf(Main.plugin.getConfig().getString("WorldEdit.timer.non-donator.lvl2.timer"));
							setCooldown(player, "we", cooldownTime );
							return true;
						}
						if(player.hasPermission(Main.plugin.getConfig().getString("WorldEdit.timer.non-donator.lvl3.perm"))) {
							long cooldownTime = Long.valueOf(Main.plugin.getConfig().getString("WorldEdit.timer.non-donator.lvl3.timer"));
							setCooldown(player, "we", cooldownTime );
				    		return true;
				    	}
					
						
					} else {
						float minuteleft = Math.round(getCooldown(player, "we") / 1000);
						player.sendMessage("WorldEdit is still in cooldown. Waiting time remaining: " + minuteleft +" seconds" );
						}
		    	   
					} else {
						player.sendMessage("You cant use this WorldEdit command."); 
						}
			
		    } else { player.sendMessage("Yo do not have access to WorldEdit commands."); }
				}
		
		}
		
		return false;
	 }
}