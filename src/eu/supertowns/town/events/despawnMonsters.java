package eu.supertowns.town.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class despawnMonsters implements Listener {
	supertowns plugin;
	coreApi api;
	public despawnMonsters(supertowns plugin, coreApi api) { 
		this.plugin = plugin;
		this.api = api;
	}
	
	@EventHandler
	public void removeMonsters(CreatureSpawnEvent e) {
		if(e.getEntity() instanceof Monster) {
			int x = e.getEntity().getLocation().getChunk().getX();
			int z = e.getEntity().getLocation().getChunk().getZ();
			if(api.checkTown(x, z, e.getLocation().getWorld())) {
				e.getEntity().remove();
				e.setCancelled(true);
			}	
		}
	}
	
	@EventHandler
	public void DisableArrow(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			if(api.checkTown(e.getDamager().getLocation().getChunk().getX(), e.getDamager().getLocation().getChunk().getZ(), e.getDamager().getWorld())) {
				Arrow arrow = (Arrow) e.getDamager();
				if(arrow.getShooter() instanceof Skeleton) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	public void checkTask() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					for(Entity entity : p.getNearbyEntities(100, 128, 100)) {
						if(entity instanceof Monster || entity instanceof Slime) {
							if(api.checkTown(entity.getLocation().getChunk().getX(), entity.getLocation().getChunk().getZ(), entity.getLocation().getWorld())) {
								entity.remove();
							}
						}
					}
				}
			}
			
		}, 1, 20);
	}

}
