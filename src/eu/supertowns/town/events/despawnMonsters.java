package eu.supertowns.town.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import eu.supertowns.town.supertowns;

public class despawnMonsters implements Listener {
	supertowns plugin;
	chunkmanager chunks;
	public despawnMonsters(supertowns plugin, chunkmanager chunks) { 
		this.plugin = plugin;
		this.chunks = chunks;
	}
	
	@EventHandler
	public void removeMonsters(CreatureSpawnEvent e) {
		if(e.getEntity() instanceof Monster) {
			int x = e.getEntity().getLocation().getChunk().getX();
			int z = e.getEntity().getLocation().getChunk().getZ();
			if(chunks.checkTown(x, z, e.getLocation().getWorld())) {
				e.getEntity().remove();
				e.setCancelled(true);
			}	
		}
	}
	
	public void checkTask() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					for(Entity entity : p.getNearbyEntities(10, 10, 10)) {
						if(entity instanceof Monster) {
							if(chunks.checkTown(entity.getLocation().getChunk().getX(), entity.getLocation().getChunk().getZ(), entity.getLocation().getWorld())) {
								entity.remove();
							}
						}
					}
				}
			}
			
		}, 1, 20);
	}

}
