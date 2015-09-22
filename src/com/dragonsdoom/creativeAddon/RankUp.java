package com.dragonsdoom.creativeAddon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.permission.Permission;


public class RankUp implements CommandExecutor{
	
	public RankUp(Main main) {}
	public static Permission perms = null;
	
	public boolean setupVaultPermissions(){
        RegisteredServiceProvider<Permission> rsp = Main.plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
        
    }
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;	 
	       if(cmd.getName().equalsIgnoreCase("rankup")){
	    	   String permworld = "world"; 
	    	   if(player.hasPermission(Main.plugin.getConfig().getString("rank.perm.modperm"))) {
	    		   if(args.length == 0) {
	    			   sender.sendMessage("You need to specify a Username");
	    			   	return false;
	    		   }
	    		   
	    			   if(!player.isOnline()){
	    				   sender.sendMessage("Make sure the user is Online and in the Default world.");
 			   			return false;
	    			   }
	    		   		String userGroup = perms.getPrimaryGroup(permworld, args[0]);
	    		   		if(userGroup == Main.plugin.getConfig().getString("rank.order.firstrank")) {
	    		   			sender.sendMessage("User need member and up to use rankup command");
	    		   			return true;
	    		   		}
	    		   		if(userGroup.equals(Main.plugin.getConfig().getString("rank.order.secondrank"))) {
	    		   			sender.sendMessage("User need member and up to use rankup command");
	    		   			Main.plugin.getServer().dispatchCommand(player, "manuadd " + args[0] + " " + Main.plugin.getConfig().getString("rank.order.thirdrank"));
	    		   			return true;
	    		   		}
	    		   		if(userGroup.equals(Main.plugin.getConfig().getString("rank.order.thirddrank"))) {
	    		   			sender.sendMessage("User need member and up to use rankup command");
	    		   			Main.plugin.getServer().dispatchCommand(player, "manuadd " + args[0] + " " + Main.plugin.getConfig().getString("rank.order.fourthrank"));
	    		   			return true;
	    		   		}
	    		   		if(userGroup.equals(Main.plugin.getConfig().getString("rank.order.fourthrank"))) {
	    		   			sender.sendMessage("User need member and up to use rankup command");
	    		   			Main.plugin.getServer().dispatchCommand(player, "manuadd " + args[0] + " " + Main.plugin.getConfig().getString("rank.order.fifthrank"));
	    		   			return true;
	    		   		}
	    		   		if(userGroup.equals(Main.plugin.getConfig().getString("rank.order.fifthrank"))) {
	    		   			sender.sendMessage("User need member and up to use rankup command");
	    		   			Main.plugin.getServer().dispatchCommand(player, "manuadd " + args[0] + " " + Main.plugin.getConfig().getString("rank.order.sixthrank"));
	    		   			return true;
	    		   		}
	    		   		
	    		   		else { sender.sendMessage("Make sure the user is not staff or donator."); }
	    		}  
	    	   	else { sender.sendMessage("You do not have access to this command."); }
	    	}	    	   				
	   	 	
	 	return false;
}

}
