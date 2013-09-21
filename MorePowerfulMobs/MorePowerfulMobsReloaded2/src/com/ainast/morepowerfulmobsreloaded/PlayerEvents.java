package com.ainast.morepowerfulmobsreloaded;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

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
import com.herocraftonline.heroes.api.events.HeroKillCharacterEvent;
import com.herocraftonline.heroes.api.events.HeroRegainHealthEvent;
import com.herocraftonline.heroes.api.events.HeroRegainManaEvent;
import com.herocraftonline.heroes.api.events.SkillDamageEvent;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.skill.SkillType;

public class PlayerEvents implements Listener{
	
	HashMap<Player, List<ItemStack>> returnOnDeath = new HashMap<Player, List<ItemStack>>();
	
	
	@EventHandler
	public void onCharacterDamageEvent(SkillDamageEvent event){
		
		if (!(event.getEntity() instanceof Player)) return;
		
		Set<SkillType> st = event.getSkill().getTypes();
		
		System.out.println(st.toString());
		
		Player player = (Player) event.getEntity();
		
		HashMap<String, Long> attributes = MPMTools.playerAttributes.get(player);
	
		double oldDamage = event.getDamage();
		double modifiedDamage = 0;
		if (attributes.containsKey("Resistance to DARK")){
			if (st.contains(SkillType.DARK)){
				long value = attributes.get("Resistance to DARK");
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey("Resistance to EARTH")){
			if (st.contains(SkillType.EARTH)){
				long value = attributes.get("Resistance to EARTH");
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey("Resistance to FORCE")){
			if (st.contains(SkillType.FORCE)){
				long value = attributes.get("Resistance to FORCE");
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey("Resistance to ICE")){
			if (st.contains(SkillType.ICE)){
				long value = attributes.get("Resistance to ICE");
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey("Resistance to LIGHT")){
			if (st.contains(SkillType.LIGHT)){
				long value = attributes.get("Resistance to LIGHT");
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey("Resistance to LIGHTNING")){
			if (st.contains(SkillType.LIGHTNING)){
				long value = attributes.get("Resistance to LIGHTNING");
				modifiedDamage += oldDamage * (value/100);
			}
		}
		if (attributes.containsKey("Resistance to PHYSICAL")){
			if (st.contains(SkillType.PHYSICAL)){
				long value = attributes.get("Resistance to PHYSICAL");
				modifiedDamage += oldDamage * (value/100.0);
			}
		}
		if (attributes.containsKey("Resistance to MAGIC")){
			if (st.contains(SkillType.MANA)){
				long value = attributes.get("Resistance to MAGIC");
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
		if (attributes.containsKey("MANA Regeneration")){
			long extraRegen = attributes.get("MANA Regeneration");
			event.setAmount((int) (event.getAmount() + extraRegen));
		}	
	}
	
	@EventHandler
	public void onHeroRegainHealthEvent(HeroRegainHealthEvent event){
		Hero hero = event.getHero();
		HashMap<String, Long> attributes = MPMTools.playerAttributes.get(hero.getPlayer());
		if (attributes.containsKey("HEAL Bonus")){
			long extraHealth = attributes.get("HEAL Bonus");
			event.setAmount((double) (event.getAmount() + extraHealth));
		}	
	}
	
	@EventHandler
	public void onPlayerRegainHealthEvent(EntityRegainHealthEvent event){
		if (!(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		
		if (MPMTools.playerAttributes.containsKey(player)){
			if (MPMTools.playerAttributes.get(player).containsKey("HEALTH Regeneration")){
				double oldAmount = event.getAmount();
				double modifier = MPMTools.playerAttributes.get(player).get("HEALTH Regeneration");
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
			if (attributes.containsKey("Negate EXPERIENCE")){
				event.setCancelled(true);
				return;
			}
			
			if (attributes.containsKey("Increase EXPERIENCE")){
				experience = (oldExperience + (oldExperience * attributes.get("Increase EXPERIENCE")/100.0));
			}
			
			if (attributes.containsKey("Decrease EXPERIENCE")){
				experience =  (oldExperience - (oldExperience * attributes.get("Increase EXPERIENCE")/100.0));
			}		
		}
		event.setExpGain(experience);
	}
	
	
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event){
		System.out.println("Player Death Event");
		if (MPMTools.playerAttributes.get(event.getEntity()).containsKey("DEATH DEFYING")){
			System.out.println("pA.cK(DEATH DEFYING)");
			Player player = event.getEntity();
			List<ItemStack> droppedItems = event.getDrops();
			List<ItemStack> keepItems = new ArrayList<ItemStack>();
			
			for (ItemStack item : droppedItems){
				if (item!=null){
					System.out.println("Item != null");
					if (item.hasItemMeta()){
						System.out.println("Item has Meta");
						if (item.getItemMeta().hasLore()){
							System.out.println("Item has Lore");
							if (item.getItemMeta().getLore().contains("DEATH DEFYING")){
								System.out.println("Item has DEATH DEFYING");
								
								keepItems.add(item);
								droppedItems.remove(item);
							}
						}
					}
				}
			}
			returnOnDeath.put(player,  keepItems);
			event.getDrops().clear();
			event.getDrops().addAll(droppedItems);
		}
	}
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event){
		System.out.println("Player Respawn Event");
		Player player = event.getPlayer();
		
		if ((!returnOnDeath.containsKey(player))) return;
		
		List<ItemStack> items = returnOnDeath.get(player);
		for (ItemStack item: items){
			player.getInventory().addItem(item);
		}
		
		returnOnDeath.remove(player);
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if (player.getItemInHand()==null) return;
		
		if (event.getAction()==Action.RIGHT_CLICK_AIR){
			
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
