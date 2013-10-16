package com.ainast.morepowerfulmobsreloaded;

import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class MobEvents implements Listener {

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled=true)
	public void creatureSpawnEvent(CreatureSpawnEvent event){
		if (event.getSpawnReason()==SpawnReason.CUSTOM) return;

		Location location = event.getLocation();
		
		RegionManager regionManager = MPMTools.getWorldGuard().getRegionManager( location.getWorld() );
		ApplicableRegionSet set = regionManager.getApplicableRegions( location );
		LinkedList< String > parentNames = new LinkedList< String >();
		LinkedList< String > regions = new LinkedList< String >();
		
		for ( ProtectedRegion region : set ) {
			String id = region.getId();
			regions.add( id );
			ProtectedRegion parent = region.getParent();
				while ( parent != null ) {
					parentNames.add( parent.getId());
					parent = parent.getParent();
				}
		}
		
		for ( String name : parentNames ){
			regions.remove( name );
		}
 
		int rnd = MPMTools.generator.nextInt(100)+1;
			
			if (regions.size()>0){
				LivingEntity entity = event.getEntity();
				if (regions.getFirst().equals("region1")){
					System.out.println("Found Region 1");
					//this random variable gives the chance of a named mob spawning rather than regular.
					//also, if the mob has a name, it won't attempt to change it.
					if (rnd<=100){
						System.out.println("Mob is custom name Visible");
						MobRegions.region1(entity);
					}
				}
			}
	}
	
	@EventHandler
	public void onCustomMobDeathEvent(EntityDeathEvent event){
		System.out.println("Entity Death Event");
		EntityType entityType = event.getEntityType();
	
		if (entityType==EntityType.ZOMBIE || entityType==EntityType.SKELETON){
			System.out.println("Mob is Zombie");
			String name = event.getEntity().getCustomName();
			
			if (name.contains("Corrupted Resident")){
				System.out.println("Mob is corrupted Resident");
				event.getDrops().clear();
				Location location = event.getEntity().getLocation();
				event.getEntity().getLocation().getWorld().dropItem(location, MPMMobTypes.getCorruptedGuardDrop());				
			}
		}
	}
}
