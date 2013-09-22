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
		possibleAttributes.add("Resistance to DARK");
		possibleAttributes.add("Resistance to EARTH");
		possibleAttributes.add("Resistance to FIRE");
		possibleAttributes.add("Resistance to FORCE");
		possibleAttributes.add("Resistance to ICE");
		possibleAttributes.add("Resistance to LIGHT");
		possibleAttributes.add("Resistance to LIGHTNING");
		possibleAttributes.add("Resistance to PHYSICAL");
		possibleAttributes.add("Resistance to MAGIC");
		
		possibleAttributes.add("Reduce cooldown on JUMP");
		
		possibleAttributes.add("Maximum MANA");
		possibleAttributes.add("Maximum HEALTH");
		
		possibleAttributes.add("HEALTH Regeneration");
		possibleAttributes.add("MANA Regeneration");
		
		possibleAttributes.add("Increase EXPERIENCE");
		possibleAttributes.add("Decrease EXPERIENCE");
		possibleAttributes.add("Negate EXPERIENCE");
		
		possibleAttributes.add("STRENGTH I");
		possibleAttributes.add("STRENGTH II");
		possibleAttributes.add("SPEED I");
		possibleAttributes.add("SPEED II");
		possibleAttributes.add("SLOW I");
		possibleAttributes.add("SLOW II");
		possibleAttributes.add("INVISIBILITY");
		possibleAttributes.add("FIRE RESISTANCE I");
		possibleAttributes.add("FIRE RESISTANCE II");
		
		possibleAttributes.add("Increase ATTACK DAMAGE");
		possibleAttributes.add("Decrease ATTACK DAMAGE");
		possibleAttributes.add("Increase MOVEMENT SPEED");
		possibleAttributes.add("Decrease MOVEMENT SPEED");
		possibleAttributes.add("Increase KNOCKBACK RESISTANCE");
		possibleAttributes.add("Decrease KNOCKBACK RESISTANCE");
		
		possibleAttributes.add("DEATH DEFYING");
		possibleAttributes.add("MitchiriNeko March");
		
		for (String s : possibleAttributes){
			System.out.println(s);
		}
		
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
							System.out.println(attribute[0] + "\t" + attribute[1] + "\t" + possibleAttributes.contains("DEATH DEFYING"));
							if (possibleAttributes.contains(attribute[0])){
								HashMap<String, Long> temp = playerAttributes.get(player);
							
								if (playerAttributes.get(player).containsKey(attribute[0])){
									temp.put(attribute[0], Long.parseLong(attribute[1]) + playerAttributes.get(player).get(attribute[0]));
									
								}else if (!(playerAttributes.get(player).containsKey(attribute[0]))){
									temp.put(attribute[0], Long.parseLong(attribute[1]));
								}
								playerAttributes.put(player, temp);
							}
						}else if (attribute.length==1){
							System.out.println(attribute[0] + "\t" + possibleAttributes.contains("DEATH DEFYING"));
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
