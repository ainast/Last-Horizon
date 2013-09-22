package com.ainast.enchantHandicap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class enchantHandicap extends JavaPlugin implements Listener {

	private Logger log;
	private ConfigEH config;
	
	public void onEnable(){
		
		this.log = getLogger();
		
		//SEND ENABLE MESSAGE TO BUKKIT.
		log.info("enchantHandicap Enabled");
		
		//COPY DEFAULT VALUES FROM CONFIGURATION
		
		config = new ConfigEH(
								getConfig().getString("quote"),
								getConfig().getString("name"),
								getConfig().getString("adminmessage"),
								getConfig().getString("transparent"),
								getConfig().getString("disableEnchanting"),
								getConfig().getString("ARROW_DAMAGE"),
								getConfig().getString("ARROW_FIRE"),
								getConfig().getString("ARROW_INFINITE"),
								getConfig().getString("ARROW_KNOCKBACK"),
								getConfig().getString("DAMAGE_ALL"),
								getConfig().getString("DAMAGE_ARTHROPODS"),
								getConfig().getString("DAMAGE_UNDEAD"),
								getConfig().getString("DIG_SPEED"),
								getConfig().getString("DURABILITY"),
								getConfig().getString("FIRE_ASPECT"),
								getConfig().getString("KNOCKBACK"),
								getConfig().getString("LOOT_BONUS_BLOCKS"),
								getConfig().getString("LOOT_BONUS_MOBS"),
								getConfig().getString("OXYGEN"),
								getConfig().getString("PROTECTION_ENVIRONMENTAL"),
								getConfig().getString("PROTECTION_EXPLOSIONS"),
								getConfig().getString("PROTECTION_FALL"),
								getConfig().getString("PROTECTION_FIRE"),
								getConfig().getString("PROTECTION_PROJECTILE"),
								getConfig().getString("SILK_TOUCH"),
								getConfig().getString("WATER_WORKER"),
								getConfig().getString("THORNS"));
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onEnchantItemEvent(EnchantItemEvent event){
		if(!config.isEnchantingDisabled()){
			testEnchantments(event);
		}else{
			event.setCancelled(true);
			event.getEnchanter().getOpenInventory().close();
			event.getEnchanter().sendMessage(ChatColor.RED + "enchanting has been disabled");
			
		}
	}	

	@SuppressWarnings("rawtypes")
	public void testEnchantments(EnchantItemEvent event){
	
		boolean foundBannedEnchant = false;
		Map<Enchantment, Integer> itemEnchants = event.getEnchantsToAdd();
		Map<Enchantment, Integer> itemEnchantsReturn = new HashMap<Enchantment, Integer>();
		Player player = event.getEnchanter();
		
		Set set = itemEnchants.entrySet();
		Iterator it = set.iterator();
		int cost = 0;
		
		while(it.hasNext()){
			//FOR EVERY ENCHANT INSIDE OF THE itemEnchants
			
			String itemSingleEnchant = it.next().toString();
			int itemEnchantLevel = Character.getNumericValue(itemSingleEnchant.charAt(itemSingleEnchant.length()-1));
			String itemEnchantName = itemSingleEnchant.substring(0, itemSingleEnchant.length()-2);
			
			Set set2 = config.allowedEnchants.entrySet();	
			Iterator it2 = set2.iterator();
			
			while(it2.hasNext()){
				//FOR EVERY ENCHANT INSIDE OF THE allowedEnchants
				
				String allowedSingleEnchant = it2.next().toString();
				int allowedEnchantLevel = Character.getNumericValue(allowedSingleEnchant.charAt(allowedSingleEnchant.length()-1));
				String allowedEnchantName = allowedSingleEnchant.substring(0, allowedSingleEnchant.length()-2);
				
				if(itemEnchantName.equals(allowedEnchantName)){
					it.remove();
					if (itemEnchantLevel > allowedEnchantLevel){
						foundBannedEnchant = true;
						if (allowedEnchantLevel>0){
							itemEnchantsReturn.put(returnEnchantment(allowedEnchantName), allowedEnchantLevel);
						}else{
							//FIGURE OUT WHY I DO THIS
							//I THINK I DO THIS BECAUSE IF IT REACHES THIS CONDITION THEN ALL FUCKING HELL HAS BROKEN LOOSE.
							event.setCancelled(true);
						}
						cost = (int) (cost + config.getCost());
					}else{
						itemEnchantsReturn.put(returnEnchantment(itemEnchantName),  itemEnchantLevel);
					}
				}
			}
		}
		itemEnchants.clear();
		itemEnchants.putAll(itemEnchantsReturn);
		
		if ((cost>event.getExpLevelCost())){
			
		}else event.setExpLevelCost(event.getExpLevelCost() - cost);
		
		
		if (foundBannedEnchant) printMessageToPlayer(player);		
		
	}
	
	public void onDisable(){
		//SEND DISABLE MESSAGE TO BUKKIT.
		log.info("enchantHandicap Disabled.");
	}
	

	  private void printMessageToPlayer(Player player)
	  {
	    if (!config.isTransparent()) {
	      player.sendMessage(" ");
	      player.sendMessage(ChatColor.GOLD + "\"" + config.getQuote() + "\"");
	      player.sendMessage(ChatColor.GOLD + config.getAuthor());
	      player.sendMessage("");
	      player.sendMessage(ChatColor.RED + config.getAdminmessage());
	    }
	  }
	
		  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		    if (cmd.getName().equalsIgnoreCase("maxenchants")) {
		      displayAllowedEnchantments((Player)sender);
		      return true;
		    }
		    
		    return false;
		  }

	  @SuppressWarnings("rawtypes")
	private void displayAllowedEnchantments(Player player)
	  {
	    Set set = config.allowedEnchants.entrySet();
	    Iterator it = set.iterator();

	    player.sendMessage(ChatColor.GOLD + "Maximum Enchantment Levels");
	    player.sendMessage(ChatColor.GOLD + "-------------------------------------------");
	    String text = new String();
	    while (it.hasNext())
	    {
	      String theEnchant = seperator(it.next());
	      text = text + ChatColor.GOLD + "[" + ChatColor.DARK_GREEN + theEnchant + ChatColor.GOLD + "]" + ChatColor.GRAY + ", ";
	    }

	    player.sendMessage(text);
	  }

	  private String seperator(Object obj)
	  {
	    String allowedEnchantName = obj.toString();
	    String[] token = allowedEnchantName.split(" ");
	    token = token[1].split("]");
	    String theEnchant = token[0];

	    int maxLevel = Character.getNumericValue(allowedEnchantName.charAt(allowedEnchantName.length() - 1));

	    return theEnchant + ":=" + ChatColor.DARK_PURPLE + maxLevel;
	  }
	  
	  private Enchantment returnEnchantment(String allowedEnchantName) {
		    String[] token = allowedEnchantName.split(" ");
		    token = token[1].split("]");
		    String theEnchant = token[0];

		    if (theEnchant.equals("ARROW_DAMAGE")) return Enchantment.ARROW_DAMAGE;
		    if (theEnchant.equals("ARROW_FIRE")) return Enchantment.ARROW_FIRE;
		    if (theEnchant.equals("ARROW_INFINITE")) return Enchantment.ARROW_INFINITE;
		    if (theEnchant.equals("ARROW_KNOCKBACK")) return Enchantment.ARROW_KNOCKBACK;
		    if (theEnchant.equals("DAMAGE_ALL")) return Enchantment.DAMAGE_ALL;
		    if (theEnchant.equals("DAMAGE_ARTHROPODS")) return Enchantment.DAMAGE_ARTHROPODS;
		    if (theEnchant.equals("DAMAGE_UNDEAD")) return Enchantment.DAMAGE_UNDEAD;
		    if (theEnchant.equals("DIG_SPEED")) return Enchantment.DIG_SPEED;
		    if (theEnchant.equals("DURABILITY")) return Enchantment.DURABILITY;
		    if (theEnchant.equals("FIRE_ASPECT")) return Enchantment.FIRE_ASPECT;
		    if (theEnchant.equals("KNOCKBACK")) return Enchantment.KNOCKBACK;
		    if (theEnchant.equals("LOOT_BONUS_BLOCKS")) return Enchantment.LOOT_BONUS_BLOCKS;
		    if (theEnchant.equals("LOOT_BONUS_MOBS")) return Enchantment.LOOT_BONUS_MOBS;
		    if (theEnchant.equals("OXYGEN")) return Enchantment.OXYGEN;
		    if (theEnchant.equals("WATER_WORKER")) return Enchantment.WATER_WORKER;
		    if (theEnchant.equals("PROTECTION_ENVIRONMENTAL")) return Enchantment.PROTECTION_ENVIRONMENTAL;
		    if (theEnchant.equals("PROTECTION_FALL")) return Enchantment.PROTECTION_FALL;
		    if (theEnchant.equals("PROTECTION_PROJECTILE")) return Enchantment.PROTECTION_PROJECTILE;
		    if (theEnchant.equals("PROTECTION_FIRE")) return Enchantment.PROTECTION_FIRE;
		    if (theEnchant.equals("SILK_TOUCH")) return Enchantment.SILK_TOUCH;
		    if (theEnchant.equals("THORNS")) return Enchantment.THORNS;
		    
		    return Enchantment.SILK_TOUCH;
		  }
}
