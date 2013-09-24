package com.ainast.morepowerfulmobsreloaded;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.herocraftonline.heroes.api.events.ExperienceChangeEvent;
import com.herocraftonline.heroes.api.events.HeroEnterCombatEvent;
import com.herocraftonline.heroes.api.events.HeroKillCharacterEvent;
import com.herocraftonline.heroes.api.events.HeroRegainHealthEvent;
import com.herocraftonline.heroes.api.events.HeroRegainManaEvent;
import com.herocraftonline.heroes.api.events.SkillDamageEvent;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.skill.SkillType;

public class PlayerEvents implements Listener{
	HashMap<Player, List<ItemStack>> itemsToReadd = new HashMap<Player, List<ItemStack>>();
	
	@EventHandler
	public void onCharacterDamageEvent(SkillDamageEvent event){
		
		if (!(event.getEntity() instanceof Player)) return;
		
		Set<SkillType> st = event.getSkill().getTypes();
		
		System.out.println(st.toString());
		
		Player player = (Player) event.getEntity();
		
		HashMap<String, Long> attributes = MPMTools.playerAttributes.get(player);
	
		double oldDamage = event.getDamage();
		double modifiedDamage = 0;
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_DARK)){
			if (st.contains(SkillType.DARK)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_DARK);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_EARTH)){
			if (st.contains(SkillType.EARTH)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_EARTH);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_FORCE)){
			if (st.contains(SkillType.FORCE)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_FORCE);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_ICE)){
			if (st.contains(SkillType.ICE)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_ICE);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_LIGHT)){
			if (st.contains(SkillType.LIGHT)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_LIGHT);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_LIGHTNING)){
			if (st.contains(SkillType.LIGHTNING)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_LIGHTNING);
				modifiedDamage += oldDamage * (value/100);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_PHYSICAL)){
			if (st.contains(SkillType.PHYSICAL)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_PHYSICAL);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey(MPMAttributeType.RESISTANCE_TO_MAGIC)){
			if (st.contains(SkillType.MANA)){
				long value = attributes.get(MPMAttributeType.RESISTANCE_TO_MAGIC);
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		System.out.println("Damage: " + oldDamage);
		System.out.println("modifiedDamage: " + modifiedDamage);
		System.out.println("Total: " + (oldDamage-modifiedDamage));
		event.setDamage(oldDamage - modifiedDamage);
	}
	
	@EventHandler
	public void onHeroRegainManaEvent(HeroRegainManaEvent event){
		Hero hero = event.getHero();
		HashMap<String, Long> attributes = MPMTools.playerAttributes.get(hero.getPlayer());
		if (attributes.containsKey(MPMAttributeType.MANA_REGENERATION)){
			long extraRegen = attributes.get(MPMAttributeType.MANA_REGENERATION);
			event.setAmount((int) (event.getAmount() + extraRegen));
		}	
	}
	
	@EventHandler
	public void onHeroRegainHealthEvent(HeroRegainHealthEvent event){
		Hero hero = event.getHero();
		HashMap<String, Long> attributes = MPMTools.playerAttributes.get(hero.getPlayer());
		if (attributes.containsKey(MPMAttributeType.HEAL_BONUS)){
			long extraHealth = attributes.get(MPMAttributeType.HEAL_BONUS);
			event.setAmount((double) (event.getAmount() + extraHealth));
		}	
	}
	
	@EventHandler
	public void onPlayerRegainHealthEvent(EntityRegainHealthEvent event){
		if (!(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		
		if (MPMTools.playerAttributes.containsKey(player)){
			if (MPMTools.playerAttributes.get(player).containsKey(MPMAttributeType.HEALTH_REGENERATION)){
				double oldAmount = event.getAmount();
				double modifier = MPMTools.playerAttributes.get(player).get(MPMAttributeType.HEALTH_REGENERATION);
				event.setAmount(oldAmount + modifier);
			}
		}
	}
	
	@EventHandler
	public void onExperienceChangeEvent(ExperienceChangeEvent event){
		double oldExperience = event.getExpChange();
		double experience = oldExperience;
		Player player = event.getHero().getPlayer();
		if (MPMTools.playerAttributes.containsKey(player)){
			HashMap<String, Long> attributes = MPMTools.playerAttributes.get(player);
			if (attributes.containsKey(MPMAttributeType.NEGATE_EXPERIENCE)){
				event.setCancelled(true);
				return;
			}
			
			if (attributes.containsKey(MPMAttributeType.INCREASE_EXPERIENCE)){
				experience = (oldExperience + (oldExperience * attributes.get(MPMAttributeType.INCREASE_EXPERIENCE)/100.0));
			}
			
			if (attributes.containsKey(MPMAttributeType.DECREASE_EXPERIENCE)){
				experience =  (oldExperience - (oldExperience * attributes.get(MPMAttributeType.INCREASE_EXPERIENCE)/100.0));
			}		
		}
		event.setExpGain(experience);
	}
	
	
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event){
		Player player = event.getEntity();
		if (MPMTools.playerAttributes.containsKey(player)){
			List<ItemStack> drops = new ArrayList<ItemStack>(event.getDrops());
			List<ItemStack> dropsToRemove =  new ArrayList<ItemStack>();
			List<ItemStack> dropsToAdd = new ArrayList<ItemStack>();
			
			for (ItemStack item : drops){
				if (item.hasItemMeta()){
					if (item.getItemMeta().hasLore()){
						if (item.getItemMeta().getLore().contains(MPMAttributeType.DEATH_DEFYING)){
							System.out.println(item.getItemMeta().getDisplayName());
							System.out.println(item.getItemMeta().getLore().toString());
							dropsToRemove.add(item);
							item.setDurability((short) (item.getDurability() + ItemTools.durabilityModifier(item.getType())));
							
							dropsToAdd.add(item);
							
							
						}else if(item.getItemMeta().getLore().contains(MPMAttributeType.DEVILS_TAKE)){
							dropsToRemove.add(item);
						}
					}
				}
			}
		
			this.itemsToReadd.put(player, dropsToAdd);			
			for (ItemStack item : dropsToRemove){
				event.getDrops().remove(item);
			}
		}
	}
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event){
		if (itemsToReadd.containsKey(event.getPlayer())){
			List<ItemStack> readd = itemsToReadd.get(event.getPlayer());
			for (ItemStack item : readd){
				event.getPlayer().getInventory().addItem(item);
			}
			
			itemsToReadd.remove(event.getPlayer());
		}	
	}
	
	@EventHandler
	public void onHeroEnterCombatEvent(HeroEnterCombatEvent event){
		Hero hero = event.getHero();
		Player player = hero.getPlayer();
		if (MPMTools.playerAttributes.containsKey(player)){
			if (MPMTools.playerAttributes.get(player).containsKey(MPMAttributeType.DRAGON_GROWL)){
				player.getWorld().playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);	
			}
		
		}
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if (player.getItemInHand()==null) return;
		
		if (event.getAction()==Action.RIGHT_CLICK_AIR && MPMTools.playerAttributes.containsKey(player)){
			if (MPMTools.playerAttributes.get(player).containsKey(MPMAttributeType.MITCHIRINEKO_MARCH)){
				InputStream is = MPMTools.plugin.getResource("mo.mid");
				
				Set<Player> playerList = new HashSet<Player>();
				for (Entity e : player.getNearbyEntities(35, 35, 35)){
					if (e instanceof Player) playerList.add((Player) e);
				}
				playerList.add(player);
				try {
					MidiUtil.playMidi(is, (float) 1, playerList);
				} catch (InvalidMidiDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MidiUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
