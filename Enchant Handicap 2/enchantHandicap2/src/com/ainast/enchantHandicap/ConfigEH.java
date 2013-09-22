package com.ainast.enchantHandicap;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;

public class ConfigEH {

	private String quote;
	private String author;
	private String adminmessage;
	private boolean transparent;
	private boolean disableAnvils;
	private double cost;
	private boolean canUseBannedEnchants;
	private boolean disableEnchanting;
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public Map<Enchantment, Integer> allowedEnchants = new HashMap();
	
	public ConfigEH(String quote, String author, String adminmessage, String transparent, String disableEnchanting, String arrow_damage, String arrow_fire, String arrow_infinite, String arrow_knockback, String damage_all, String damage_arthropods, String damage_undead, String dig_speed, String durability, String fire_aspect, String knockback, String loot_bonus_blocks, String loot_bonus_mobs, String oxygen, String protection_environmental, String protection_explosions, String protection_fall, String protection_fire, String protection_projectile, String silk_touch, String water_worker, String thorns){
		setQuote(quote);
		setAuthor(author);
		setAdminmessage(adminmessage);
		setTransparent(transparent);
		
		if(disableEnchanting.equals("true")){
			setDisableEnchanting(true);
		}else setDisableEnchanting(false);
		
		this.allowedEnchants.put(Enchantment.ARROW_DAMAGE, Integer.valueOf(arrow_damage));
	    this.allowedEnchants.put(Enchantment.ARROW_FIRE, Integer.valueOf(arrow_fire));
	    this.allowedEnchants.put(Enchantment.ARROW_INFINITE, Integer.valueOf(arrow_infinite));
	    this.allowedEnchants.put(Enchantment.ARROW_KNOCKBACK, Integer.valueOf(arrow_knockback));
	    this.allowedEnchants.put(Enchantment.DAMAGE_ALL, Integer.valueOf(damage_all));
	    this.allowedEnchants.put(Enchantment.DAMAGE_ARTHROPODS, Integer.valueOf(damage_arthropods));
	    this.allowedEnchants.put(Enchantment.DAMAGE_UNDEAD, Integer.valueOf(damage_undead));
	    this.allowedEnchants.put(Enchantment.DIG_SPEED, Integer.valueOf(dig_speed));
	    this.allowedEnchants.put(Enchantment.DURABILITY, Integer.valueOf(durability));
	    this.allowedEnchants.put(Enchantment.FIRE_ASPECT, Integer.valueOf(fire_aspect));
	    this.allowedEnchants.put(Enchantment.KNOCKBACK, Integer.valueOf(knockback));
	    this.allowedEnchants.put(Enchantment.LOOT_BONUS_BLOCKS, Integer.valueOf(loot_bonus_blocks));
	    this.allowedEnchants.put(Enchantment.LOOT_BONUS_MOBS, Integer.valueOf(loot_bonus_mobs));
	    this.allowedEnchants.put(Enchantment.OXYGEN, Integer.valueOf(oxygen));
	    this.allowedEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, Integer.valueOf(protection_environmental));
	    this.allowedEnchants.put(Enchantment.PROTECTION_EXPLOSIONS, Integer.valueOf(protection_explosions));
	    this.allowedEnchants.put(Enchantment.PROTECTION_FALL, Integer.valueOf(protection_fall));
	    this.allowedEnchants.put(Enchantment.PROTECTION_FIRE, Integer.valueOf(protection_fire));
	    this.allowedEnchants.put(Enchantment.PROTECTION_PROJECTILE, Integer.valueOf(protection_projectile));
	    this.allowedEnchants.put(Enchantment.SILK_TOUCH, Integer.valueOf(silk_touch));
	    this.allowedEnchants.put(Enchantment.WATER_WORKER, Integer.valueOf(water_worker));
	    this.allowedEnchants.put(Enchantment.THORNS, Integer.valueOf(thorns));
	  
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAdminmessage() {
		return adminmessage;
	}

	public void setAdminmessage(String adminmessage) {
		this.adminmessage = adminmessage;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(String transparent) {
		boolean value;
		
		if (transparent.equalsIgnoreCase("true")){
			value = true;
		}else value = false;
		
		this.transparent = value;
	}

	public boolean isDisableAnvils() {
		return disableAnvils;
	}

	public void setDisableAnvils(boolean disableAnvils) {
		this.disableAnvils = disableAnvils;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isCanUseBannedEnchants() {
		return canUseBannedEnchants;
	}

	public void setCanUseBannedEnchants(boolean canUseBannedEnchants) {
		this.canUseBannedEnchants = canUseBannedEnchants;
	}

	public boolean isEnchantingDisabled() {
		return disableEnchanting;
	}

	public void setDisableEnchanting(boolean disableEnchanting) {
		this.disableEnchanting = disableEnchanting;
	}
}
