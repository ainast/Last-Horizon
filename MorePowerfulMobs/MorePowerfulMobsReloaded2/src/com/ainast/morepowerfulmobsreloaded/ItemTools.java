package com.ainast.morepowerfulmobsreloaded;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemTools {
	
	public static ItemStack getAttributedItem(String type){
		
		int chance = MPMTools.generator.nextInt(100)+1;
		if (chance<10){
			return randomNamedItem(type);
		}else{
			return generateAttributedItem(type);
		}
	
		
	}

	private static ItemStack generateAttributedItem(String type) {
		ItemStack item;
			
		int chance = MPMTools.generator.nextInt(100)+1;
		
		int totalPoints = 0;
		
		if (chance<10){	
			item = new ItemStack(getAMaterial("BEST", type));
			item.getItemMeta().setDisplayName("Best");
			totalPoints = 50;
		}else if (chance<30){
			item = new ItemStack(getAMaterial("BETTER", type));
			item.getItemMeta().setDisplayName("Better");
			totalPoints = 40;
		}
		if (chance<50){
			item = new ItemStack(getAMaterial("GOOD", type));
			item.getItemMeta().setDisplayName("Good");
			totalPoints = 30;
		}else if(chance<75){
			item = new ItemStack(getAMaterial("CHEAP", type));
			item.getItemMeta().setDisplayName("Cheap");
			totalPoints = 20;
		}else{
			item = new ItemStack(getAMaterial("CHEAPEST", type));
			item.getItemMeta().setDisplayName("Cheapest");
			totalPoints = 10;
		}
		
		return item;
	}

	private static Material getAMaterial(String string, String type) {
		int chance = MPMTools.generator.nextInt(2)+1;
		if (chance==1){
			if (type.equals("Mage")){
				if (string.equals("BEST")){
					return Material.DIAMOND_HOE;
				}else if (string.equals("BETTER")){
					return Material.GOLD_HOE;
				}else if (string.equals("GOOD")){
					return Material.IRON_HOE;
				}else if (string.equals("CHEAP")){
					return Material.STONE_HOE;
				}else if (string.equals("CHEAPEST")){
					return Material.WOOD_HOE;
				}
			}else if (type.equals("Fighter")){
				if (string.equals("BEST")){
					return Material.DIAMOND_SWORD;
				}else if (string.equals("BETTER")){
					return Material.GOLD_SWORD;
				}else if (string.equals("GOOD")){
					return Material.IRON_SWORD;
				}else if (string.equals("CHEAP")){
					return Material.STONE_SWORD;
				}else if (string.equals("CHEAPEST")){
					return Material.WOOD_SWORD;
				}			
			}else if (type.equals("Rogue")){
				if (string.equals("BEST")){
					return Material.DIAMOND_SWORD;
				}else if (string.equals("BETTER")){
					return Material.GOLD_SWORD;
				}else if (string.equals("GOOD")){
					return Material.IRON_SWORD;
				}else if (string.equals("CHEAP")){
					return Material.STONE_SWORD;
				}else if (string.equals("CHEAPEST")){
					return Material.WOOD_SWORD;
				}
			}else if (type.equals("Merchant")){
				if (string.equals("BEST")){
					return Material.DIAMOND_AXE;
				}else if (string.equals("BETTER")){
					return Material.GOLD_AXE;
				}else if (string.equals("GOOD")){
					return Material.IRON_AXE;
				}else if (string.equals("CHEAP")){
					return Material.STONE_AXE;
				}else if (string.equals("CHEAPEST")){
					return Material.WOOD_AXE;
				}
			}
		}else{
			if(string.equals("Mage")){
				chance = MPMTools.generator.nextInt(4)+1;
				if (string.equals("BEST")){
					if (chance==1){
						return Material.DIAMOND_HELMET;
					}else if (chance==2){
						return Material.DIAMOND_CHESTPLATE;
					}else if (chance==3){
						return Material.DIAMOND_LEGGINGS;
					}else{
						return Material.DIAMOND_BOOTS;
					}
				}else if (string.equals("BETTER")){
					if (chance==1){
						return Material.GOLD_HELMET;
					}else if (chance==2){
						return Material.GOLD_CHESTPLATE;
					}else if (chance==3){
						return Material.GOLD_LEGGINGS;
					}else{
						return Material.GOLD_BOOTS;
					}
				}else if (string.equals("GOOD")){
					if (chance==1){
						return Material.IRON_HELMET;
					}else if (chance==2){
						return Material.IRON_CHESTPLATE;
					}else if (chance==3){
						return Material.IRON_LEGGINGS;
					}else{
						return Material.IRON_BOOTS;
					}
				}else if (string.equals("CHEAP") || string.equals("CHEAPEST")){
					if (chance==1){
						return Material.LEATHER_HELMET;
					}else if (chance==2){
						return Material.LEATHER_CHESTPLATE;
					}else if (chance==3){
						return Material.LEATHER_LEGGINGS;
					}else{
						return Material.LEATHER_BOOTS;
					}
				}
			}else if (string.equals("Fighter")){
				chance = MPMTools.generator.nextInt(4)+1;
				if (string.equals("BEST")){
					if (chance==1){
						return Material.DIAMOND_HELMET;
					}else if (chance==2){
						return Material.DIAMOND_CHESTPLATE;
					}else if (chance==3){
						return Material.DIAMOND_LEGGINGS;
					}else{
						return Material.DIAMOND_BOOTS;
					}
				}else if (string.equals("BETTER")){
					if (chance==1){
						return Material.GOLD_HELMET;
					}else if (chance==2){
						return Material.GOLD_CHESTPLATE;
					}else if (chance==3){
						return Material.GOLD_LEGGINGS;
					}else{
						return Material.GOLD_BOOTS;
					}
				}else if (string.equals("Iron")){
					if (chance==1){
						return Material.IRON_HELMET;
					}else if (chance==2){
						return Material.IRON_CHESTPLATE;
					}else if (chance==3){
						return Material.IRON_LEGGINGS;
					}else{
						return Material.IRON_BOOTS;
					}
				}else if (string.equals("GOOD")){
					if (chance==1){
						return Material.LEATHER_HELMET;
					}else if (chance==2){
						return Material.LEATHER_CHESTPLATE;
					}else if (chance==3){
						return Material.LEATHER_LEGGINGS;
					}else{
						return Material.LEATHER_BOOTS;
					}
				}
			}else if (string.equals("Rogue")){
				chance = MPMTools.generator.nextInt(4)+1;
				if (string.equals("Diamond")){
					if (chance==1){
						return Material.DIAMOND_HELMET;
					}else if (chance==2){
						return Material.DIAMOND_CHESTPLATE;
					}else if (chance==3){
						return Material.DIAMOND_LEGGINGS;
					}else{
						return Material.DIAMOND_BOOTS;
					}
				}else if (string.equals("Gold")){
					if (chance==1){
						return Material.GOLD_HELMET;
					}else if (chance==2){
						return Material.GOLD_CHESTPLATE;
					}else if (chance==3){
						return Material.GOLD_LEGGINGS;
					}else{
						return Material.GOLD_BOOTS;
					}
				}else if (string.equals("Iron")){
					if (chance==1){
						return Material.IRON_HELMET;
					}else if (chance==2){
						return Material.IRON_CHESTPLATE;
					}else if (chance==3){
						return Material.IRON_LEGGINGS;
					}else{
						return Material.IRON_BOOTS;
					}
				}else if (string.equals("Leather")){
					if (chance==1){
						return Material.LEATHER_HELMET;
					}else if (chance==2){
						return Material.LEATHER_CHESTPLATE;
					}else if (chance==3){
						return Material.LEATHER_LEGGINGS;
					}else{
						return Material.LEATHER_BOOTS;
					}
				}
			}else if (string.equals("Merchant")){
				chance = MPMTools.generator.nextInt(4)+1;
				if (string.equals("Diamond")){
					if (chance==1){
						return Material.DIAMOND_HELMET;
					}else if (chance==2){
						return Material.DIAMOND_CHESTPLATE;
					}else if (chance==3){
						return Material.DIAMOND_LEGGINGS;
					}else{
						return Material.DIAMOND_BOOTS;
					}
				}else if (string.equals("Gold")){
					if (chance==1){
						return Material.GOLD_HELMET;
					}else if (chance==2){
						return Material.GOLD_CHESTPLATE;
					}else if (chance==3){
						return Material.GOLD_LEGGINGS;
					}else{
						return Material.GOLD_BOOTS;
					}
				}else if (string.equals("Iron")){
					if (chance==1){
						return Material.IRON_HELMET;
					}else if (chance==2){
						return Material.IRON_CHESTPLATE;
					}else if (chance==3){
						return Material.IRON_LEGGINGS;
					}else{
						return Material.IRON_BOOTS;
					}
				}else if (string.equals("Leather")){
					if (chance==1){
						return Material.LEATHER_HELMET;
					}else if (chance==2){
						return Material.LEATHER_CHESTPLATE;
					}else if (chance==3){
						return Material.LEATHER_LEGGINGS;
					}else{
						return Material.LEATHER_BOOTS;
					}
				}
			}
		}
		
		return Material.WOOD_SWORD;
	}

	private static Material getMaterial(String string, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	private static ItemStack randomNamedItem(String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
