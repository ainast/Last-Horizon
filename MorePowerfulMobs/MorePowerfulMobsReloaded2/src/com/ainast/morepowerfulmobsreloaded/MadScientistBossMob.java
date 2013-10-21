package com.ainast.morepowerfulmobsreloaded;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MadScientistBossMob implements Runnable, Listener{
	
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
	String bossName = "Shouldn't be null, I change it!";
	String deathMessage;
	double maxHealth;
	int task;
	
	Random generator = new Random(System.currentTimeMillis());
	
	public MadScientistBossMob(String bossName, Location bossLocation, EntityType entityType, ItemStack weaponInHand, ItemStack[] equipment, ItemStack[] drops, int dropChance, int experience){
		this.name = bossName;
		this.location = bossLocation;
		this.entityType = entityType;
		this.weaponInHand = weaponInHand;
		this.equipment = equipment;
		this.drops = drops;
		this.dropChance = dropChance;
		this.experience = experience;
		this.bossName = bossName;
		MPMTools.plugin.getServer().getPluginManager().registerEvents(this,  MPMTools.plugin);
		task = MPMTools.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(MPMTools.plugin, new AbelPotThrower(boss), 40, 10);
	}

	@Override
	public void run(){
		if (!isAlive()){
			spawnNewBoss();
		}
		
		if (boss!=null){	
			if (boss.isDead()){
				MPMTools.plugin.getServer().getScheduler().cancelTask(task);
				setAlive(false);
				return;
			}
		}
	}
	
	private void spawnNewBoss() {
		try {
			//System.out.println(location.toString());
			boss = location.getWorld().spawnCreature(this.location, this.entityType);	
			equipBoss();
			this.boss.setCustomName(this.bossName);
			this.boss.setCustomNameVisible(true);
			//if (this.maxHealth!=0) boss.setMaxHealth(this.maxHealth);
			setAlive(true);
		} catch (Exception e) {
			//System.out.println("[WARNING] boss unable to spawn - " + this.bossName);
			setAlive(false);
		}
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
		this.maxHealth = maxHealth;
	}
	
	public double getMaxHealth(){
		return boss.getMaxHealth();
	}
}

class AbelPotThrower implements Runnable{
	LivingEntity boss;
	public AbelPotThrower(Entity entity){
		boss = (LivingEntity) entity;
	}
	
	@Override
	public void run(){
		if (this.boss!=null){
			//System.out.println("Throw Potion");
			int chance = MPMTools.generator.nextInt(100)+1;
			if (chance<101){
				List<Entity> entityList = boss.getNearbyEntities(30, 30, 30);
				
				for (Entity entity : entityList){
					if (!(entity instanceof Player)) return;
					chance = MPMTools.generator.nextInt(100)+1;
					
					if (chance<101){
						
						ThrownPotion potion = boss.launchProjectile(ThrownPotion.class);
						potion.setShooter(boss);
						potion.getEffects().add(new PotionEffect(PotionEffectType.WITHER, 50, 2));
						potion.setVelocity(boss.getLocation().getDirection().multiply(4));
						
					}
				}	
			}
		}else{

		}
	}
}
