package com.ainast.morepowerfulmobsreloaded;

import java.util.ArrayList;
import java.util.List;

import me.egordm.simpleattributes.API.SimpleAttributesAPI;
import me.egordm.simpleattributes.Attributes.AttributeType;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TieredItems {
	public static ItemStack getTier1DentedPlateMail(){
		/*
		 * Includes:
		 * 		Resistance to Physical: 3-7%
		 * 		Max HP: 3-27
		 * 		Knockback Resistance: 0.1-0.3%
		 * 		Mana Regeneration: -3
		 * 		Slow 1 Effect
		 */	
		ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta itemMeta = item.getItemMeta();
		
		int percent = 0;
		

		List<String> lore = new ArrayList<String>();
		
		percent = MPMTools.generator.nextInt(7-5)+5;
		lore.add("Resistance to Physical: " + percent);
		
		percent = MPMTools.generator.nextInt(27-3)+3;
		lore.add("Maximum HEALTH: " + percent);
		
		lore.add("MANA Regeneration: -3");
		
		//lore.add(MPMAttributeType.SLOW_1);
		

		itemMeta.setDisplayName("Dented Plate Mail");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		
		percent = MPMTools.generator.nextInt(30-10)+10;  //divide by 100 on next line to get percentage.
		item = SimpleAttributesAPI.addItemAttribute(item, "Dented Plate Mail" ,  AttributeType.GENERIC_KNOCKBACK_RESISTANCE, percent/100.0);

		percent = 25;
		item = SimpleAttributesAPI.addItemAttribute(item, "Dented Plate Mail" ,  AttributeType.GENERIC_MOVEMENT_SPEED, percent/100.0);
		
		return item;
	}
}
