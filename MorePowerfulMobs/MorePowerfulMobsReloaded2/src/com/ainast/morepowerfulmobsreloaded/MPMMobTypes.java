package com.ainast.morepowerfulmobsreloaded;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.MobType;

public class MPMMobTypes {

	public static void spawnCorruptedResident(Location location){
		LivingEntity entity = (LivingEntity) location.getWorld().spawnCreature(location, EntityType.ZOMBIE);
		
		
	}
	
	public static void spawnCorruptedGuard(Location location){
		
	}
	
	public static ItemStack getCorruptedGuardDrop() {
		ItemStack[] dropTable = { //add items seperated by comma
								new ItemStack(Material.ANVIL),
								TieredItems.getTier1PlateMail()
								};
		int i = MPMTools.generator.nextInt(dropTable.length);
		ItemStack dropItem =  dropTable[i]; 
		return dropItem;
	}
	
	public static void spawnCorruptedDefender(Location location){
		
	}
	
	public static void spawnForestBanditScavenger(Location location){
		
	}
	
	public static void spawnForestBanditArcher(Location location){
		
	}
	
	public static void spawnForestBanditWarrior(Location location){
		
	}
	
}
