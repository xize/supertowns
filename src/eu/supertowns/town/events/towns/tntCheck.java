package eu.supertowns.town.events.towns;

import java.io.File;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class tntCheck implements Listener {
	supertowns plugin;
	coreApi api;
	public tntCheck(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	@EventHandler
	public void checkExplosion(ExplosionPrimeEvent e) {
		if(e.getEntity() instanceof TNTPrimed) {
			if(api.isEntityInTown(e.getEntity())) {
				String town = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + town + ".yml");
					if(f.exists()) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("townFlag.tnt").equalsIgnoreCase("deny")) {
							e.setCancelled(true);
						}
					}
				} catch(Exception r) {
					r.printStackTrace();
				}
			}
		} else if(e.getEntity() instanceof Minecart) {
			if(e.getEntityType() == EntityType.MINECART_TNT) {
				if(api.isEntityInTown(e.getEntity())) {
					String town = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
					try {
						File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + town + ".yml");
						if(f.exists()) {
							FileConfiguration con = YamlConfiguration.loadConfiguration(f);
							if(con.getString("townFlag.tnt").equalsIgnoreCase("deny")) {
								e.setCancelled(true);
							}
						}
					} catch(Exception r) {
						r.printStackTrace();
					}
				}
			} else {
				return;
			}
		} else if(e.getEntity() instanceof WitherSkull) {
			if(api.isEntityInTown(e.getEntity())) {
				String town = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + town + ".yml");
					if(f.exists()) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("townFlag.tnt").equalsIgnoreCase("deny")) {
							e.setCancelled(true);
						}
					}
				} catch(Exception r) {
					r.printStackTrace();
				}
			}
		} else {
			return;
		}	
	}

	@EventHandler
	public void tnt2(EntityExplodeEvent e) {
		for(Block block : e.blockList()) {
			if(api.checkTown(block.getChunk().getX(), block.getChunk().getZ(), block.getWorld())) {
				String town = api.getTownNameOnLocation(block.getChunk().getX(), block.getChunk().getZ(), block.getWorld());
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + town + ".yml");
					if(f.exists()) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("townFlag.tnt").equalsIgnoreCase("deny")) {
							e.setCancelled(true);
						}
					}
				} catch(Exception r) {
					r.printStackTrace();
				}
			}
		}

	}
}