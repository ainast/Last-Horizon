package com.ainast.morepowerfulmobsreloaded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.api.events.SkillDamageEvent;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.skill.SkillType;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class MPMTools {
	public static final UUID maxHealthUID = UUID.fromString("f8b0a945-2d6a-4bdb-9a6f-59c285bf1e5d");
	public static final UUID followRangeUID = UUID.fromString("1737400d-3c18-41ba-8314-49a158481e1e");
	public static final UUID knockbackResistanceUID = UUID.fromString("8742c557-fcd5-4079-a462-b58db99b0f2c");
	public static final UUID movementSpeedUID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
	public static final UUID attackDamageUID = UUID.fromString("7bbe3bb1-079d-4150-ac6f-669e71550776");
	
	static JavaPlugin plugin;
	static Random generator = new Random(System.currentTimeMillis());
	
	static HashMap<Player, HashMap<String, Long>> playerAttributes = new HashMap<Player, HashMap<String, Long>>();
	static ArrayList<String> possibleAttributes = new ArrayList<String>();
	
	public MPMTools(JavaPlugin plugin){
		this.plugin = plugin;
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_DARK);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_EARTH);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_FIRE);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_FORCE);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_ICE);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_LIGHT);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_LIGHTNING);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_PHYSICAL);
		possibleAttributes.add(MPMAttributeType.RESISTANCE_TO_MAGIC);
		
		possibleAttributes.add(MPMAttributeType.REDUCE_COOLDOWN_ON_JUMP);
		possibleAttributes.add(MPMAttributeType.REDUCE_COOLDOWN_ON_FIREBALL);
		
		possibleAttributes.add(MPMAttributeType.MAXIMUM_MANA);
		possibleAttributes.add(MPMAttributeType.MAXIMUM_HEALTH);
		
		possibleAttributes.add(MPMAttributeType.HEALTH_REGENERATION);
		possibleAttributes.add(MPMAttributeType.MANA_REGENERATION);
		
		possibleAttributes.add(MPMAttributeType.INCREASE_EXPERIENCE);
		possibleAttributes.add(MPMAttributeType.DECREASE_EXPERIENCE);
		possibleAttributes.add(MPMAttributeType.NEGATE_EXPERIENCE);
		
		possibleAttributes.add(MPMAttributeType.STRENGTH_1);
		possibleAttributes.add(MPMAttributeType.STRENGTH_2);
		possibleAttributes.add(MPMAttributeType.SPEED_1);
		possibleAttributes.add(MPMAttributeType.SPEED_2);
		possibleAttributes.add(MPMAttributeType.SLOW_1);
		possibleAttributes.add(MPMAttributeType.SLOW_2);
		possibleAttributes.add(MPMAttributeType.INVISIBILITY);
		possibleAttributes.add(MPMAttributeType.FIRE_RESISTANCE_1);
		possibleAttributes.add(MPMAttributeType.FIRE_RESISTANCE_2);
		
		possibleAttributes.add(MPMAttributeType.INCREASE_ATTACK_DAMAGE); 
		possibleAttributes.add(MPMAttributeType.DECREASE_ATTACK_DAMAGE);
		possibleAttributes.add(MPMAttributeType.INCREASE_MOVEMENT_SPEED); //possibly remove
		possibleAttributes.add(MPMAttributeType.DECREASE_MOVEMENT_SPEED); //possibly remove
		possibleAttributes.add(MPMAttributeType.INCREASE_KNOCKBACK_RESISTANCE); //possibly remove
		possibleAttributes.add(MPMAttributeType.DECREASE_KNOCKBACK_RESISTANCE); //possibly remove
		
		possibleAttributes.add(MPMAttributeType.DEATH_DEFYING);
		possibleAttributes.add(MPMAttributeType.DEVILS_TAKE);
		possibleAttributes.add(MPMAttributeType.MITCHIRINEKO_MARCH);
		
		possibleAttributes.add(MPMAttributeType.POISONOUS);
		possibleAttributes.add(MPMAttributeType.BLINDING);
		
		possibleAttributes.add(MPMAttributeType.DRAGON_GROWL);
		possibleAttributes.add(MPMAttributeType.CAT_PURR);
		
		possibleAttributes.add(MPMAttributeType.HEAL_BONUS);
		
		possibleAttributes.add(MPMAttributeType.GIVE_RANDOM_ITEM);
	}
	
	public static void calculateAttributes(Player player){
		if (player.getEquipment()==null) return;
		
		playerAttributes.remove(player);
		playerAttributes.put(player, new HashMap<String, Long>());
		
		ArrayList<ItemStack> entityEquipment = new ArrayList<ItemStack>();
		
		if (player.getEquipment().getItemInHand()!=null) entityEquipment.add(player.getEquipment().getItemInHand());
		if (player.getEquipment().getHelmet()!=null) entityEquipment.add(player.getEquipment().getHelmet());
		if (player.getEquipment().getChestplate()!=null) entityEquipment.add(player.getEquipment().getChestplate());
		if (player.getEquipment().getLeggings()!=null) entityEquipment.add(player.getEquipment().getLeggings());
		if (player.getEquipment().getBoots()!=null) entityEquipment.add(player.getEquipment().getBoots());
		
		for (ItemStack item : entityEquipment){
			if (item.hasItemMeta()){
				if (item.getItemMeta().hasLore()){
					int i = 0;
					List<String> itemLore = item.getItemMeta().getLore();
					for (String s : itemLore){
						String[] attribute = s.split(":");
						if (attribute.length>1){
							if (possibleAttributes.contains(attribute[0])){
								HashMap<String, Long> temp = playerAttributes.get(player);
							
								if (playerAttributes.get(player).containsKey(attribute[0])){
									temp.put(attribute[0], Long.parseLong(attribute[1].replace('%', ' ').trim()) + playerAttributes.get(player).get(attribute[0]));
									
								}else if (!(playerAttributes.get(player).containsKey(attribute[0]))){
									temp.put(attribute[0], Long.parseLong(attribute[1].replace('%', ' ').trim()));
								}
								playerAttributes.put(player, temp);
							}
						}else if (attribute.length==1){
							if (possibleAttributes.contains(attribute[0])){
								HashMap<String, Long> temp = playerAttributes.get(player);
								temp.put(attribute[0], Long.MAX_VALUE);
								playerAttributes.put(player, temp);
							}
						}
					}
				}
			}
		}
	}
	
	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin2 = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin2 == null || !(plugin2 instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin2;
	}
	

	public static Heroes getHeroes() {
		Plugin plugin2 = plugin.getServer().getPluginManager().getPlugin("Heroes");
	 
	    // Heroes may not be loaded
	    if (plugin2 == null || !(plugin2 instanceof Heroes)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (Heroes) plugin2;
	}
}
