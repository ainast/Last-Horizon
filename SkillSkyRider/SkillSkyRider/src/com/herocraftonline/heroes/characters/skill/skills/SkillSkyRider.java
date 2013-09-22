package com.herocraftonline.heroes.characters.skill.skills;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.api.SkillResult;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.effects.ExpirableEffect;
import com.herocraftonline.heroes.characters.skill.ActiveSkill;
import com.herocraftonline.heroes.characters.skill.Skill;
import com.herocraftonline.heroes.characters.skill.SkillType;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;

public class SkillSkyRider extends ActiveSkill
{
  public SkillSkyRider(Heroes plugin)
  {
    super(plugin, "SkyRider");
    setUsage("/skill skyrider");
    setIdentifiers(new String[] { "skill skyrider" });
    setDescription("Your horse jumps further for 20s.");
    setArgumentRange(0, 0);
    setTypes(new SkillType[] { SkillType.SILENCABLE, SkillType.BUFF });
  }

  public void init()
  {
    super.init();

  }

  public String getDescription(Hero arg0) {
    return "Your horse jumps further for 20s.";
  }

  public SkillResult use(Hero hero, String[] arg1) {
    Player player = hero.getPlayer();
    if (player.isInsideVehicle()) {
      Entity vehicle = player.getVehicle();
      if ((vehicle instanceof Horse)) {
        Horse horse = (Horse)vehicle;
        hero.addEffect(new SkyRider(this, 10*1000, horse));
        broadcastExecuteText(hero);
        return SkillResult.NORMAL;
      }
    }
    return SkillResult.FAIL;
  }
}


class SkyRider extends ExpirableEffect implements Listener
{
  private Horse horse;
  private Player player;
  private boolean canJumpHigh = false;
  private double oldJumpStrength;

  public SkyRider(Skill skill, long duration, Horse horse) {
    super(skill, "SkyRider", duration);
    this.horse = horse;
    this.player = ((Player)horse.getPassenger());
    this.oldJumpStrength = horse.getJumpStrength();
    Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
  }

  public void applyToHero(Hero hero) {
    super.applyToHero(hero);
    this.horse.setNoDamageTicks(20*10);
    this.player.sendMessage("Horse can jump far as fuck now.");
    this.canJumpHigh = true;
  }

public void removeFromHero(Hero hero) {
    super.removeFromHero(hero);
    this.canJumpHigh = false;
    horse.setJumpStrength(this.oldJumpStrength);
    this.player.sendMessage("Horse can no longer jump far :-(");
  }

  @EventHandler
  public void onHorseJumpEvent(HorseJumpEvent event) {
    if ((this.canJumpHigh) && 
      (this.horse.isOnGround())) {
      this.horse.setVelocity(this.horse.getVelocity().multiply(25));
      this.horse.setJumpStrength(400000.0D);
      this.player.getWorld().playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 0.0F);
    }
  }
}