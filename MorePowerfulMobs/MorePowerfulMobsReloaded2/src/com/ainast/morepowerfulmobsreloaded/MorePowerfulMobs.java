package com.ainast.morepowerfulmobsreloaded;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MorePowerfulMobs extends JavaPlugin{
	Logger log;
	boolean eventsRegistered = false;
	GenericBossMob gbm;
	
	public void onEnable(){
		log = this.getLogger();
		MPMTools m = new MPMTools(this);	
		
		//MUST REGISTER BOSS MOBS BEFORE REGISTERING OTHER EVENTS!!!
		
		World r1World;
		Location loc;
		
		for (World world : getServer().getWorlds()){
			System.out.println("World: " + world.getName());
		}
		
		String world = "world2";
		r1World = getServer().getWorld(world);
		loc = new Location(r1World, 90, 130, -68);
		
		System.out.println("location: " + loc.toString());
		
		ItemStack[] drops = { TieredItems.getTier1DentedPlateMail() };
		gbm = new GenericBossMob("Evil Frank", world, loc, EntityType.ZOMBIE, TieredItems.getTier1DentedPlateMail(), null, drops, 100, 10000);
		//gbm.setMaxHealth(10000);
		getServer().getScheduler().runTaskTimer(this, gbm, 0, 100);		
	
		getServer().getPluginManager().registerEvents(new MobEvents(), this);
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
	
		getServer().getScheduler().runTaskTimer(this, new PerTickBuffs(), 400, 20);

	}
}
