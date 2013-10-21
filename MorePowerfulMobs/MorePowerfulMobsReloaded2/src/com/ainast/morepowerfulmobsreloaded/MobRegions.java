package com.ainast.morepowerfulmobsreloaded;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class MobRegions {
	
	//this is your region, then call it from in MobEvents
	public static void region1(LivingEntity entity){
		//System.out.println("region 1 detected");
		//this listens for spawns of type zombie.
		if (entity.getType().equals(EntityType.ZOMBIE)){
			//System.out.println("Zombie spawn detected");
			MPMMobTypes.spawnCorruptedResident(entity);
		}
	}
}

