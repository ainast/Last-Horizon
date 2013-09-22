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
}
