package com.ainast.morepowerfulmobsreloaded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import net.minecraft.server.v1_6_R2.ItemTool;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GenericBossMob implements Runnable, Listener{
	
	LivingEntity boss;
	String name;
	Location location;
	boolean alive = false;
	EntityType entityType;
	ItemStack weaponInHand;
	ItemStack[] equipment;
	ItemStack[] drops;
	int dropChance;
	int experience;
	
	String deathMessage;
	
	Random generator = new Random(System.currentTimeMillis());
	
	public GenericBossMob(String bossName, Location bossLocation, EntityType entityType, ItemStack weaponInHand, ItemStack[] equipment, ItemStack[] drops, int dropChance, int experience){
		this.name = bossName;
		this.location = bossLocation;
		this.entityType = entityType;
		this.weaponInHand = weaponInHand;
		this.equipment = equipment;
		this.drops = drops;
		this.dropChance = dropChance;
		this.experience = experience;
		
		MPMTools.plugin.getServer().getPluginManager().registerEvents(this,  MPMTools.plugin);
	}

	@Override
	public void run(){
		if (!isAlive()){
			spawnNewBoss();
		}
	}
	
	private void spawnNewBoss() {
		System.out.println("spaw New Boss?");
		boss = location.getWorld().spawnCreature(location, entityType);	
		setAlive(true);
		equipBoss();
	}
	
	private void equipBoss() {
		boss.getEquipment().setArmorContents(equipment);
	}

	public void dropBossItems(Location location){
		int randomNumber = generator.nextInt(100)+1;
		if (randomNumber<=dropChance){
			for (ItemStack item : drops){
				Bukkit.getWorld(location.getWorld().getName()).dropItem(location, TieredItems.getTier1DentedPlateMail());
			}
		}
	}

	public boolean isAlive(){
		return alive;	
	}

	public void setAlive(boolean alive){
		this.alive =  alive;
	}
	
	@EventHandler
	public void onBossDeathEvent(EntityDeathEvent event){
		LivingEntity entity = event.getEntity();
		if (entity.equals(boss)){
			setAlive(false);
			
			event.setDroppedExp(experience);
			event.getDrops().clear();
			
			dropBossItems(entity.getLocation());
			sendMassMessage(deathMessage, entity);
			}	
		}

	private void sendMassMessage(String message, Entity entity) {
		if (message!=null){
			List<Entity> entities = entity.getNearbyEntities(30,30,30);
			for (Entity e : entities){
				if (e instanceof Player){
					((Player) e).getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('$', deathMessage));
				}
			}
		}
	}
	
	public void setMaxHealth(double maxHealth){
		boss.setMaxHealth(maxHealth);
	}
	
	public double getMaxHealth(){
		return boss.getMaxHealth();
	}
}
