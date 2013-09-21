package com.ainast.morepowerfulmobsreloaded;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class MorePowerfulMobs extends JavaPlugin{
	Logger log;
	boolean eventsRegistered = false;
	GenericBossMob gbm;
	
	public void onEnable(){
		log = this.getLogger();
		MPMTools m = new MPMTools(this);	
		
		//MUST REGISTER BOSS MOBS BEFORE REGISTERING OTHER EVENTS!!!
		//World r1World = getServer().getWorld("World");
		//Location loc = new Location(r1World, -126, 99, 461);
		//gbm = new GenericBossMob("Evil Frank", loc, EntityType.ZOMBIE, new ItemStack(Material.DIAMOND_SWORD), null, null, 0, 0);
		//gbm.setMaxHealth(10000);
		//getServer().getScheduler().runTaskTimer(this, gbm, 0, 400);		
	
		getServer().getPluginManager().registerEvents(new MobEvents(), this);
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
	
		getServer().getScheduler().runTaskTimer(this, new PerTickBuffs(), 0, 20);

	}
}
