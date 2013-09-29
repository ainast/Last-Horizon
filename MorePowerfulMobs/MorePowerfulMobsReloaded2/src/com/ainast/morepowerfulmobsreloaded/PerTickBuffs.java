package com.ainast.morepowerfulmobsreloaded;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.egordm.simpleattributes.util.Potions;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.effects.EffectType;

public class PerTickBuffs implements Runnable {

	@Override
	public void run(){
		if (Bukkit.getOnlinePlayers()==null) return;
		Player[] playersOnline =  Bukkit.getOnlinePlayers();
		for (Player p : playersOnline){
			Hero h = MPMTools.getHeroes().getCharacterManager().getHero(p);
			h.resetMaxHP();
			h.clearHealthBonuses();
			h.clearMaxMana();
			if (p.isDead()) continue;
			
			MPMTools.calculateAttributes(p);
			HashMap<String, Long> attributes = MPMTools.playerAttributes.get(p);

			

			
			if (attributes.containsKey(MPMAttributeType.MAXIMUM_MANA)){
				h.addMaxMana("MPM", attributes.get(MPMAttributeType.MAXIMUM_MANA).intValue());
			}
			
			if (attributes.containsKey(MPMAttributeType.MAXIMUM_HEALTH)){
				
				p.setMaxHealth(attributes.get(MPMAttributeType.MAXIMUM_HEALTH).doubleValue());
				if (p.getHealth()>attributes.get(MPMAttributeType.MAXIMUM_HEALTH).doubleValue()){
					p.setHealth(attributes.get(MPMAttributeType.MAXIMUM_HEALTH));
				}
			}
			
			if (attributes.containsKey(MPMAttributeType.REDUCE_COOLDOWN_ON_JUMP)){
				long cooldown = (h.getCooldown("jump") - attributes.get(MPMAttributeType.REDUCE_COOLDOWN_ON_JUMP)*1000);
				if (cooldown<0) cooldown = 0;
				h.setCooldown("jump", cooldown);
			}
			
			if (attributes.containsKey(MPMAttributeType.REDUCE_COOLDOWN_ON_FIREBALL)){
				long cooldown = (h.getCooldown("fireball") - attributes.get(MPMAttributeType.REDUCE_COOLDOWN_ON_FIREBALL)*1000);
				if (cooldown<0) cooldown = 0;
				h.setCooldown("fireball", cooldown);
			}
			
			//
			// add more here.  Same format for reducing cooldowns on other skills.
			//
			
			if (attributes.containsKey(MPMAttributeType.STRENGTH_1)){
				PotionEffect temp = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 2);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.STRENGTH_2)){
				PotionEffect temp = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 4);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.SPEED_1)){
				PotionEffect temp = new PotionEffect(PotionEffectType.SPEED, 40, 2);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.SPEED_2)){
				PotionEffect temp = new PotionEffect(PotionEffectType.SPEED, 40, 4);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.SLOW_1)){
				PotionEffect temp = new PotionEffect(PotionEffectType.SLOW, 40, 2);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.SLOW_2)){
				PotionEffect temp = new PotionEffect(PotionEffectType.SLOW, 40, 4);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.INVISIBILITY)){
				PotionEffect temp = new PotionEffect(PotionEffectType.INVISIBILITY, 40, 2);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.FIRE_RESISTANCE_1)){
				PotionEffect temp = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 2);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.FIRE_RESISTANCE_2)){
				PotionEffect temp = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 4);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey(MPMAttributeType.CAT_PURR)){
				int chance = MPMTools.generator.nextInt(100)+1;
				if (chance<25){
					p.getWorld().playSound(p.getLocation(), Sound.CAT_PURR, 1, 5);
				}
			}
		}
	}
}
