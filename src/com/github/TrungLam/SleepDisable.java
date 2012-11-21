package com.github.TrungLam;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SleepDisable extends JavaPlugin implements Listener{
	
	public static SleepDisable plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public static long time;
	public static boolean allowTime;
	
	public void onDisable(){
		logger.info(this.getDescription().getFullName() + " is disabled");
	}
	public void onEnable(){
		logger.info(this.getDescription().getFullName() + " is enabled");
		getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
		allowTime = getConfig().getBoolean("Allow change the time when sleep");
	}
	
	@EventHandler
	public void playerEnter(PlayerBedEnterEvent event){
		Player player = event.getPlayer();
		Player[] players = player.getServer().getOnlinePlayers();
		if (players.length == 1 && allowTime){
			time = player.getWorld().getFullTime();
		}
	}
	
	@EventHandler
	public void playerLeave(PlayerBedLeaveEvent event){
		Player[] players = event.getPlayer().getServer().getOnlinePlayers();
		if (players.length == 1 && allowTime){
			event.getPlayer().getWorld().setTime(time);
		}
	}

}
