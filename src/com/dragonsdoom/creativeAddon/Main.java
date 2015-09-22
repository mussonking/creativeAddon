package com.dragonsdoom.creativeAddon;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {
	public static Permission perms = null;
	public static Main plugin;
	
	
		public void onEnable(){
			setupVaultPermissions();
			plugin = this;
			Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
			
			//executors
			getCommand("rankup").setExecutor(new RankUp(this));
			getCommand("mode").setExecutor(new GMCommand(this));
			getCommand("we").setExecutor(new WETimer(this));

			
			
			//transfer JavaPlugin
			new RankUp(this);
			new GMCommand(this);
			new WETimer(this);
			
			
		   loadConfiguration();
		   getLogger().info("creativeAddon Plugin enabled!");
		  
		}
		
		private void loadConfiguration() {
			//RankUp() config part
			String firstrank = "rank.order.firstrank";
			getConfig().addDefault(firstrank, "Noob");
			String secondrank = "rank.order.secondrank";
			getConfig().addDefault(secondrank, "Member");
			String thirdrank = "rank.order.thirdrank";
			getConfig().addDefault(thirdrank, "Builder");
			String fourthrank = "rank.order.fourtthrank";
			getConfig().addDefault(fourthrank, "Advanced-Builder");
			String fifthrank = "rank.order.fifthrank";
			getConfig().addDefault(fifthrank, "Supreme-Builder");
			String sixthrank = "rank.order.sixthrank";
			getConfig().addDefault(sixthrank, "Master-Builder");
			String modperm = "rank.perm.modperm";
			getConfig().addDefault(modperm, "rankup.staff");
			
			//WETimer() config part
			
			String worldeditNC = "WorldEdit.timer.donator.noCooldown.perm";
			getConfig().addDefault(worldeditNC, "donator.we.nolimit");
			
			String worldeditDT1 = "WorldEdit.timer.donator.lvl1.timer";
			getConfig().addDefault(worldeditDT1, "30000");
			String worldeditDP1 = "WorldEdit.timer.donator.lvl1.perm";
			getConfig().addDefault(worldeditDP1, "donator.we.1");
			
			String worldeditDT2 = "WorldEdit.timer.donator.lvl2.timer";
			getConfig().addDefault(worldeditDT2, "15000");		
			String worldeditDP2 = "WorldEdit.timer.donator.lvl2.perm";
			getConfig().addDefault(worldeditDP2, "donator.we.2");
			
			String worldeditNDT1 = "WorldEdit.timer.non-donator.lvl1.timer";
			getConfig().addDefault(worldeditNDT1, "210000");
			String worldeditNDP1 = "WorldEdit.timer.non-donator.lvl1.perm";
			getConfig().addDefault(worldeditNDP1, "nondonator.we.1");
			
			String worldeditNDT2 = "WorldEdit.timer.non-donator.lvl2.timer";
			getConfig().addDefault(worldeditNDT2, "180000");
			String worldeditNDP2 = "WorldEdit.timer.non-donator.lvl2.perm";
			getConfig().addDefault(worldeditNDP2, "nondonator.we.2");
			
			String worldeditNDT3 = "WorldEdit.timer.non-donator.lvl3.timer";
			getConfig().addDefault(worldeditNDT3, "120000");
			String worldeditNDP3 = "WorldEdit.timer.non-donator.lvl3.perm";
			getConfig().addDefault(worldeditNDP3, "nondonator.we.3");
			
			String[] worldeditDC = {"replace", "repl"};
			getConfig().addDefault("WorldEdit.denied-Commands", Arrays.asList(worldeditDC));
			
			getConfig().options().copyDefaults(true);
			saveConfig();
		}

		public boolean setupVaultPermissions(){
	        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
	        perms = rsp.getProvider();
	        return perms != null;
	    }
				

}
