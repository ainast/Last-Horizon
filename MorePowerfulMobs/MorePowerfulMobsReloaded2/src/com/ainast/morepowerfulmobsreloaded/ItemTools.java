package com.ainast.morepowerfulmobsreloaded;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemTools {
	
	public static ItemStack getAttributedItem(String type){
		
		int chance = MPMTools.generator.nextInt(100)+1;
		if (chance<10){
			return getTier2Item();
		}else if (chance<30){
			return getTier1Item();
		}else{
			return getTier0Item();
		}
	}

	private static ItemStack getTier0Item() {
		// TODO Auto-generated method stub
		return null;
	}

	private static ItemStack getTier1Item() {
		// TODO Auto-generated method stub
		return null;
	}

	private static ItemStack getTier2Item() {
		// TODO Auto-generated method stub
		return null;
	}

	public static short durabilityModifier(Material type) {
		if (type.equals(Material.DIAMOND_AXE)){
			return 150;
		}else if (type.equals(Material.DIAMOND_BARDING)){
			return 150;
		}else if (type.equals(Material.DIAMOND_BOOTS)){
			return 150;
		}else if (type.equals(Material.DIAMOND_CHESTPLATE)){
			return 150;
		}else if (type.equals(Material.DIAMOND_LEGGINGS)){
			return 150;
		}else if (type.equals(Material.DIAMOND_BOOTS)){
			return 150;
		}else if (type.equals(Material.DIAMOND_SWORD)){
			return 150;
		}else if (type.equals(Material.DIAMOND_PICKAXE)){
			return 150;
		}else if (type.equals(Material.DIAMOND_SPADE)){
			return 150;
		}else if (type.equals(Material.GOLD_AXE)){
			return 150;
		}else if (type.equals(Material.GOLD_BARDING)){
			return 150;
		}else if (type.equals(Material.GOLD_BOOTS)){
			return 150;
		}else if (type.equals(Material.GOLD_CHESTPLATE)){
			return 150;
		}else if (type.equals(Material.GOLD_LEGGINGS)){
			return 150;
		}else if (type.equals(Material.GOLD_BOOTS)){
			return 150;
		}else if (type.equals(Material.GOLD_SWORD)){
			return 150;
		}else if (type.equals(Material.GOLD_PICKAXE)){
			return 150;
		}else if (type.equals(Material.GOLD_SPADE)){
			return 150;
		}else if (type.equals(Material.IRON_AXE)){
			return 150;
		}else if (type.equals(Material.IRON_BARDING)){
			return 150;
		}else if (type.equals(Material.IRON_BOOTS)){
			return 150;
		}else if (type.equals(Material.IRON_CHESTPLATE)){
			return 150;
		}else if (type.equals(Material.IRON_LEGGINGS)){
			return 150;
		}else if (type.equals(Material.IRON_BOOTS)){
			return 150;
		}else if (type.equals(Material.IRON_SWORD)){
			return 150;
		}else if (type.equals(Material.IRON_PICKAXE)){
			return 150;
		}else if (type.equals(Material.IRON_SPADE)){
			return 150;
		}else if (type.equals(Material.STONE_AXE)){
			return 150;
		}else if (type.equals(Material.STONE_SWORD)){
			return 150;
		}else if (type.equals(Material.STONE_PICKAXE)){
			return 150;
		}else if (type.equals(Material.STONE_SPADE)){
			return 150;
		}else if (type.equals(Material.WOOD_AXE)){
			return 150;
		}else if (type.equals(Material.WOOD_SWORD)){
			return 150;
		}else if (type.equals(Material.WOOD_PICKAXE)){
			return 150;
		}else if (type.equals(Material.WOOD_SPADE)){
			return 150;
		}
		
		
		
		return 0;
	}
}
