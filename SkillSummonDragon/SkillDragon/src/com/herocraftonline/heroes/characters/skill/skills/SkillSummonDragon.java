package com.herocraftonline.heroes.characters.skill.skills;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.api.SkillResult;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.Monster;
import com.herocraftonline.heroes.characters.effects.ExpirableEffect;
import com.herocraftonline.heroes.characters.effects.PeriodicExpirableEffect;
import com.herocraftonline.heroes.characters.effects.common.NauseaEffect;
import com.herocraftonline.heroes.characters.effects.common.RootEffect;
import com.herocraftonline.heroes.characters.effects.common.SafeFallEffect;
import com.herocraftonline.heroes.characters.effects.common.StunEffect;
import com.herocraftonline.heroes.characters.skill.ActiveSkill;
import com.herocraftonline.heroes.characters.skill.Skill;
import com.herocraftonline.heroes.characters.skill.SkillType;

public class SkillSummonDragon extends ActiveSkill implements Listener{

	public SkillSummonDragon(Heroes plugin) {
		super(plugin, "SummonDragon");
	    setDescription("THIS BITCH SPAWNS A DRAGON MOTHER FUCKER!");
	    setUsage("/skill SummonDragon");
	    setArgumentRange(0, 0);
	    setIdentifiers(new String[] { "skill SummonDragon", "skill sd" });
	    setTypes(new SkillType[] { SkillType.SILENCABLE, SkillType.SUMMON, SkillType.EARTH});
	}

	public SkillResult use(Hero hero, String[] arg1) {
		hero.addEffect(new DragonSpawn(this, 1000*20));
		return SkillResult.NORMAL;
	}

	public String getDescription(Hero hero) {
		return "THIS BITCH SPAWNS A DRAGON! WTF?";
	}
	
	public void init()
	{
		  super.init();
	}
}

class DragonSpawn extends ExpirableEffect{
	LivingEntity drake;
	long duration;
	
	public DragonSpawn(Skill skill, long duration) {
		super(skill, "SummonDragon", duration);
		this.duration = duration;
	}
	
	public void applyToHero(Hero hero){
		super.applyToHero(hero);
		Player player = hero.getPlayer();
		hero.addEffect( new dragonBreath(skill, "dragonbreath", 1000, 1000*20, drake));
		this.drake = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_DRAGON);
		drake.setPassenger(hero.getPlayer());
		drake.setNoDamageTicks(1000*15);
	}
	
	public void removeFromHero(Hero hero){
		super.removeFromHero(hero);
		drake.eject();
		if (!hero.hasEffect("SafeFaull")){
			hero.addEffect(new SafeFallEffect(skill, "SafeFall", 1000*20));
		}
		drake.remove();
	}
	
	private class dragonBreath extends PeriodicExpirableEffect implements Listener{
		LivingEntity drake;
		Random rnd = new Random();
		public dragonBreath(Skill skill, String name, long period, long duration, LivingEntity drake) {
			super(skill, name, period, duration);
			//Bukkit.broadcastMessage(ChatColor.DARK_RED + "A DEMON HAS BEEN SUMMONED.");
			this.drake = drake;
			Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		}	
		
		
		/*@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
		public void stopDragonDamage(EntityExplodeEvent event)
		{
		  Entity e = event.getEntity();
		  if(e instanceof EnderDragon){
			  event.setCancelled(true);
		  }
		}*/

		@SuppressWarnings("deprecation")
		@Override
		public void tickHero(Hero hero) {
			List<Entity> entitiesNearDrake = hero.getPlayer().getNearbyEntities(45,45,45);
			for (Entity e: entitiesNearDrake){
				if (e instanceof Player){
					Hero target = this.plugin.getCharacterManager().getHero((Player) e);
					if (target.hasParty() && hero.hasParty()){
						if (!target.getParty().isPartyMember(hero)){
							int bob = rnd.nextInt(20)+1;
							if (bob==1 || bob==2){
								hero.getPlayer().getWorld().strikeLightningEffect(hero.getPlayer().getLocation());
								Skill.damageEntity(target.getEntity(), hero.getEntity(), 6, EntityDamageEvent.DamageCause.MAGIC);
							}else if(bob==9999){
								target.getPlayer().getWorld().playSound(target.getPlayer().getLocation(), Sound.ENDERDRAGON_GROWL, 5.0F, 0.0F);
								target.addEffect(new RootEffect(skill, 200));
							}else if (bob==9999){
								target.addEffect(new StunEffect(skill, 200));
							}else if(bob==9999){
								target.addEffect(new NauseaEffect(skill, 3000, "ShitYoPants", "ShitYoPants"));
							}else{
								Player player = hero.getPlayer();
							    Snowball fireball = (Snowball)player.launchProjectile(Snowball.class);
							    fireball.setFireTicks(100);
							    double mult = 1.5;
							    fireball.setVelocity(fireball.getVelocity().multiply(mult));
							    if (rnd.nextInt(15)==3) target.getPlayer().setFireTicks(40);
							    target.getPlayer().getWorld().playSound(target.getPlayer().getLocation(), Sound.FIRE_IGNITE, 5.0F, 0.0F);
							}
						}
					}else{
						if (((CraftPlayer) e).getPlayer()!=hero.getPlayer() && (!(e instanceof EnderDragon))){
							int bob = rnd.nextInt(20)+1;
							if (bob==1 || bob==2){
								hero.getPlayer().getWorld().strikeLightningEffect(target.getPlayer().getLocation());
								Skill.damageEntity(target.getEntity(), hero.getEntity(), 6, EntityDamageEvent.DamageCause.MAGIC);
							}else if(bob==9999){
								target.addEffect(new RootEffect(skill, 200));
							}else if (bob==9999){
								target.addEffect(new StunEffect(skill, 200));
							}else if(bob==9999){
								target.addEffect(new NauseaEffect(skill, 3000, "ShitYoPants", "ShitYoPants"));
							}else{
								Player player = hero.getPlayer();
							    Snowball fireball = (Snowball)player.launchProjectile(Snowball.class);
							    fireball.setFireTicks(100);
							    double mult = 1.5;
							    fireball.setVelocity(fireball.getVelocity().multiply(mult));
							    if (rnd.nextInt(15)==3) target.getPlayer().setFireTicks(40);
							    target.getPlayer().getWorld().playSound(target.getPlayer().getLocation(), Sound.FIRE_IGNITE, 5.0F, 0.0F);
							}
						}
					}
				}
			}
		}

		@Override
		public void tickMonster(Monster monster) {
				
		}
	}
}
