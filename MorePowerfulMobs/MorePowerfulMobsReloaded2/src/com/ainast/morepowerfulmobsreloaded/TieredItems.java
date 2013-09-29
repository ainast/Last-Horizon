package com.ainast.morepowerfulmobsreloaded;

import java.util.ArrayList;
import java.util.List;

import me.egordm.simpleattributes.API.SimpleAttributesAPI;
import me.egordm.simpleattributes.Attributes.AttributeType;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class TieredItems {

	public static ItemStack getTier1CastingWant(){
		ItemStack item = new ItemStack(Material.BLAZE_ROD);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
		itemMeta.setDisplayName(ChatColor.MAGIC + "[T1] Casting Wand");
		lore.add(ChatColor.ITALIC.GOLD + "I'm not sure about this...");
		lore.add(MPMAttributeType.DEATH_DEFYING);
		lore.add(MPMAttributeType.REDUCE_COOLDOWN_ON_FIREBALL + ": 1");
		lore.add(MPMAttributeType.MANA_REGENERATION + ": 35");
		lore.add(MPMAttributeType.MAXIMUM_MANA + ": 1");
		lore.add(MPMAttributeType.MAXIMUM_HEALTH + ": 1");
		
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);

		return item;
	}
	
	public static ItemStack getTier1MrAmazingsSpecialHat(){
		ItemStack item = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(MPMAttributeType.INVISIBILITY);
		lore.add(MPMAttributeType.DEATH_DEFYING);
		lore.add(MPMAttributeType.MANA_REGENERATION + ": -10");		
		itemMeta.setDisplayName(ChatColor.GOLD + "[T1] Mr. Amazing's Special Hat");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		LeatherArmorMeta lim = (LeatherArmorMeta) item.getItemMeta();
		lim.setColor(Color.fromBGR(255, 105, 180));
		item.setItemMeta(lim);
		return item;
	}
	
	public static ItemStack getTier1CursedRunningShoes(){
		ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "With Love, Frank");
		lore.add(MPMAttributeType.DEATH_DEFYING);
		lore.add(MPMAttributeType.MAXIMUM_HEALTH + ": 1");
		lore.add(MPMAttributeType.MAXIMUM_MANA + ": 1");
		itemMeta.setDisplayName(ChatColor.GOLD + "[T1] Cursed Running Shoes");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		LeatherArmorMeta lim = (LeatherArmorMeta) item.getItemMeta();
		lim.setColor(Color.BLACK);
		item.setItemMeta(lim);
		item = SimpleAttributesAPI.addItemAttribute(item, "[T1] Pristine Plate Mail" ,  AttributeType.GENERIC_MOVEMENT_SPEED, .4);
		return item;
	}
	
	public static ItemStack getBloodSword(){
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta itemMeta = item.getItemMeta();
		int percent = 0;
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.ITALIC + " " + ChatColor.DARK_PURPLE + "Life is Hell!");
		lore.add(MPMAttributeType.DEVILS_TAKE);
		lore.add(MPMAttributeType.MAXIMUM_HEALTH + ": 1");
		lore.add(MPMAttributeType.MAXIMUM_MANA + ": 1");
		itemMeta.setDisplayName(ChatColor.YELLOW + "[TX] Blood Sword");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		item = SimpleAttributesAPI.addItemAttribute(item, "[T1] Pristine Plate Mail" ,  AttributeType.GENERIC_MOVEMENT_SPEED, -0.1);
		item = SimpleAttributesAPI.addItemAttribute(item, "[TX] Blood Sword" ,  AttributeType.GENERIC_ATTACK_DAMAGE, 45/100);
		return item;
	}
	
	public static ItemStack getTier1PristinePlateMail(){
		ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		int percent = 0;
		percent = MPMTools.generator.nextInt(7-3)+3;
		lore.add(MPMAttributeType.DEATH_DEFYING);
		lore.add(MPMAttributeType.RESISTANCE_TO_PHYSICAL+": " + percent + "%");
		percent = MPMTools.generator.nextInt(37-13)+13;
		lore.add(MPMAttributeType.MAXIMUM_HEALTH + ":" + percent);
		lore.add(MPMAttributeType.MANA_REGENERATION + ":" + -3);
		percent = MPMTools.generator.nextInt(5-1)+1;
		itemMeta.setLore(lore);
		itemMeta.setDisplayName(ChatColor.GOLD + "[T1] Pristine Plate Mail");
		item.setItemMeta(itemMeta);
		item = SimpleAttributesAPI.addItemAttribute(item, "[T1] Pristine Plate Mail" ,  AttributeType.GENERIC_KNOCKBACK_RESISTANCE, percent/100.0);
		item = SimpleAttributesAPI.addItemAttribute(item, "[T1] Pristine Plate Mail" ,  AttributeType.GENERIC_MOVEMENT_SPEED, -0.01);
		
		return item;
	}
	
	/* [T1] Plate Mail
	 * Includes:
	 * 		Resistance to Physical: 3-7%
	 * 		Max HP: 3-27
	 * 		Knockback Resistance: .1-.4%
	 * 		Mana Regeneration: -3
	 * 		Slow 1 Effect
	 */
	public static ItemStack getTier1PlateMail(){
		ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta itemMeta = item.getItemMeta();
		int percent = 0;
		List<String> lore = new ArrayList<String>();
		percent = MPMTools.generator.nextInt(7-3)+3;
		lore.add(MPMAttributeType.RESISTANCE_TO_PHYSICAL + ": " + percent + "%");
		percent = MPMTools.generator.nextInt(32-8)+8;
		lore.add(MPMAttributeType.MAXIMUM_HEALTH + ": " + percent);		
		percent = MPMTools.generator.nextInt(4-1)+1;
		lore.add(MPMAttributeType.MANA_REGENERATION + ": -3");
		itemMeta.setDisplayName(ChatColor.BLUE + "[T1] Plate Mail");
		itemMeta.setLore(lore);
		percent = MPMTools.generator.nextInt(4-1)+1;
		item.setItemMeta(itemMeta);
		item = SimpleAttributesAPI.addItemAttribute(item, "[T1] Plate Mail" ,  AttributeType.GENERIC_KNOCKBACK_RESISTANCE, percent/100.0);
		item = SimpleAttributesAPI.addItemAttribute(item, "[T1] Plate Mail" ,  AttributeType.GENERIC_MOVEMENT_SPEED, -0.01);
		return item;
	}
	
	
	/* [T1] Dented Plate Mail
	 * Includes:
	 * 		Resistance to Physical: 3-7%
	 * 		Max HP: 3-27
	 * 		Knockback Resistance: 0.1-0.3%
	 * 		Mana Regeneration: -3
	 * 		Slow 1 Effect
	 */	
	public static ItemStack getTier1DentedPlateMail(){

		ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta itemMeta = item.getItemMeta();
		
		int percent = 0;
		

		List<String> lore = new ArrayList<String>();
		
		percent = MPMTools.generator.nextInt(7-5)+5;
		lore.add("Resistance to Physical: " + percent + "%");
		
		percent = MPMTools.generator.nextInt(27-3)+3;
		lore.add("Maximum HEALTH: " + percent);
		
		lore.add("MANA Regeneration: -3");
		
		//lore.add(MPMAttributeType.SLOW_1);
		

		itemMeta.setDisplayName(ChatColor.GREEN + "[T1] Dented Plate Mail");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		percent = MPMTools.generator.nextInt(30-10)+10;  //divide by 100 on next line to get percentage.
		item = SimpleAttributesAPI.addItemAttribute(item, "Dented Plate Mail" ,  AttributeType.GENERIC_KNOCKBACK_RESISTANCE, percent/100.0);

		percent = -2;
		item = SimpleAttributesAPI.addItemAttribute(item, "Dented Plate Mail" ,  AttributeType.GENERIC_MOVEMENT_SPEED, -0.01);
		
		return item;
	}
}
