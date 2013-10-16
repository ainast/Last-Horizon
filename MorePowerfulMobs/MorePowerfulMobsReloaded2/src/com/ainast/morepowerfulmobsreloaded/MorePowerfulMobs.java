package com.ainast.morepowerfulmobsreloaded;

import java.util.logging.Logger;

import me.egordm.simpleattributes.API.SimpleAttributesAPI;
import me.egordm.simpleattributes.Attributes.AttributeType;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MorePowerfulMobs extends JavaPlugin{
	Logger log;
	boolean eventsRegistered = false;
	//GenericBossMob gbm;
	//MadScientistBossMob msbm;
	BossMob gbm;
	BossMob msbm;
	
	public void onEnable(){
		log = this.getLogger();
		MPMTools m = new MPMTools(this);	
		
		//MUST REGISTER BOSS MOBS BEFORE REGISTERING OTHER EVENTS!!!
		//IF THE MOBS ERROR OUT, MULTIVERSE MUST BE LOADED BEFORE THIS RUNS. 
		//IF THE WORLD WASN'T GENERATED THEN IT CAN RETURN NULL FOR THE WORLD.

		spawnUncleFrank();
		//spawnMadScientistAbel();
				
		getServer().getPluginManager().registerEvents(new MobEvents(), this);
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
	
		getServer().getScheduler().runTaskTimer(this, new PerTickBuffs(), 20, 20);
	}

	private void spawnMadScientistAbel() {
		World r1World = getServer().getWorld("world");
		Location loc = new Location(r1World, 90, 130, -68);
		
		ItemStack[] drops = { TieredItems.getTier1DentedPlateMail() };
		
		ItemStack MSAChestplate =  new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta lim = (LeatherArmorMeta) MSAChestplate.getItemMeta();
		lim.setColor(Color.WHITE);
		MSAChestplate.setItemMeta(lim);
		
		ItemStack MSALeggings =  new ItemStack(Material.LEATHER_LEGGINGS);
		lim = (LeatherArmorMeta) MSALeggings.getItemMeta();
		lim.setColor(Color.WHITE);
		MSALeggings.setItemMeta(lim);
		
		ItemStack MSABoots =  new ItemStack(Material.LEATHER_BOOTS);
		lim = (LeatherArmorMeta) MSABoots.getItemMeta();
		lim.setColor(Color.WHITE);
		MSABoots.setItemMeta(lim);
		
		ItemStack MSAWeapon =  new ItemStack(Material.IRON_HOE);
	
		ItemStack[] equipment = { MSAChestplate, MSALeggings, MSABoots, MSAWeapon };
		//msbm = new MadScientistBossMob("Mad Scientist Abel", loc, EntityType.ZOMBIE, TieredItems.getTier1DentedPlateMail(), equipment, drops, 100, 10000);
		//msbm.setMaxHealth(10000);
		
		msbm = new BossMob();
		msbm.setArmor(equipment);
		msbm.setWeaponInHand(MSAWeapon);
		msbm.setDrops(drops);
		msbm.setExperience(10000);
		msbm.setMaxHealth(100);
		msbm.setLocation("world2", 90, 130, -68);
		msbm.setName("Mad Scientist Abel");
		msbm.setDeathMessage("Abel will see you again");
		msbm.setDeathMessageRadius(30);
		msbm.setType(EntityType.SKELETON);
		msbm.potThrower = true;
		msbm.setLeader(true);
		getServer().getScheduler().runTaskTimer(this, msbm, 300, 100);	
	}

	private void spawnUncleFrank() {
		World r1World = getServer().getWorld("world");
		Location loc = new Location(r1World, 90, 130, -68);
		ItemStack[] drops = { TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves(), TieredItems.getTier1DentedPlateGreaves() };
		//gbm = new GenericBossMob("Evil Frank", loc, EntityType.ZOMBIE, TieredItems.getTier1DentedPlateMail(), null, drops, 100, 10000);
		//gbm.setMaxHealth(10000);
		gbm = new BossMob();
		gbm.setWeaponInHand(new ItemStack(Material.RED_ROSE));
		gbm.setDrops(drops);
		gbm.setExperience(10000);
		gbm.setMaxHealth(100);
		gbm.setLocation("world2", 90, 130, -68);
		gbm.setName("Uncle Frank");
		gbm.setDeathMessage("Frank will see you again");
		gbm.setDeathMessageRadius(30);
		gbm.setType(EntityType.ZOMBIE);
		
		getServer().getScheduler().runTaskTimer(this, gbm, 300, 100);		
	}
}
