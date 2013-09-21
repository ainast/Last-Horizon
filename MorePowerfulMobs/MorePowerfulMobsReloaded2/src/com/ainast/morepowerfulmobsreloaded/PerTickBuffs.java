package com.ainast.morepowerfulmobsreloaded;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.egordm.simpleattributes.util.Potions;

import org.bukkit.Bukkit;
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
			MPMTools.calculateAttributes(p);
			HashMap<String, Long> attributes = MPMTools.playerAttributes.get(p);
			Hero h = MPMTools.getHeroes().getCharacterManager().getHero(p);
			h.clearHealthBonuses();
			h.clearMaxMana();
			
			if (attributes.containsKey("Maximum MANA")){
				h.addMaxMana("MPM", attributes.get("Maximum MANA").intValue());
			}
			
			if (attributes.containsKey("Maximum HEALTH")){
				h.addMaxHealth("MPM", attributes.get("Maximum HEALTH").intValue());
			}
			
			if (attributes.containsKey("Reduce cooldown on JUMP")){
				long cooldown = (h.getCooldown("jump") - attributes.get("Reduce cooldown on JUMP")*1000);
				if (cooldown<0) cooldown = 0;
				h.setCooldown("jump", cooldown);
			}
			//
			// add more here.  Same format for reducing cooldowns on other skills.
			//
			
			if (attributes.containsKey("STRENGTH I")){
				PotionEffect temp = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 3);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey("STRENGTH II")){
				PotionEffect temp = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 30);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey("SPEED I")){
				PotionEffect temp = new PotionEffect(PotionEffectType.SPEED, 40, 3);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey("SPEED II")){
				PotionEffect temp = new PotionEffect(PotionEffectType.SPEED, 40, 30);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey("INVISIBILITY")){
				System.out.println("Adding Invisibility");
				PotionEffect temp = new PotionEffect(PotionEffectType.INVISIBILITY, 40, 30);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey("FIRE RESISTANCE I")){
				PotionEffect temp = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 3);
				p.addPotionEffect(temp, true);
			}
			if (attributes.containsKey("FIRE RESISTANCE II")){
				PotionEffect temp = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 30);
				p.addPotionEffect(temp, true);
			}
		}
	}
}
