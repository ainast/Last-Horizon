package com.ainast.morepowerfulmobsreloaded;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionThrower extends BossMob implements Runnable {
	int task;
	
	public PotionThrower(){
		super();
		task = MPMTools.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(MPMTools.plugin, new PotThrower(boss), 10, 10);
		System.out.println("Scheduled Task: " + task);
	}
	
	@Override
	public void run(){
		if (isDead()){
			MPMTools.plugin.getServer().getScheduler().cancelTask(task);
		}
	}
}

