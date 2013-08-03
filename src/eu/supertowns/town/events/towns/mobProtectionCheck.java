package eu.supertowns.town.events.towns;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class mobProtectionCheck implements Listener {
	supertowns plugin;
	coreApi api;
	public mobProtectionCheck(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	@EventHandler
	public void checkMobs(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof LivingEntity && e.getEntity().getType() != EntityType.PLAYER) {
			if(e.getDamager() instanceof Player) {
				Player damager = (Player) e.getDamager();
				if(api.isEntityInTown(e.getEntity())) {
					String townName = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
					if(!api.isMember(damager, townName)) {
						try {
							File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
							if(f.exists()) {
								FileConfiguration con = YamlConfiguration.loadConfiguration(f);
								if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
									damager.sendMessage(ChatColor.RED + "you are not allowed to damage this mob " + ChatColor.GOLD + e.getEntity().getType().name().toLowerCase().replace("_", " ") + ChatColor.RED + " in the town " + ChatColor.GOLD + townName);
									e.setCancelled(true);
								}
							}
						} catch(Exception r) {
							r.printStackTrace();
						}
					}
				}
			} else if(e.getDamager() instanceof Arrow) {
				Arrow arDamager = (Arrow) e.getDamager();
				if(arDamager.getShooter() instanceof Player) {
					Player damager = (Player) arDamager.getShooter();
					if(api.isEntityInTown(e.getEntity())) {
						String townName = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
						if(api.isMember(damager, townName)) {
							try {
								File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
								if(f.exists()) {
									FileConfiguration con = YamlConfiguration.loadConfiguration(f);
									if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
										damager.sendMessage(ChatColor.RED + "you are not allowed to damage this mob " + ChatColor.GOLD + e.getEntity().getType().name().toLowerCase().replace("_", " ") + ChatColor.RED + " in the town " + ChatColor.GOLD + townName);
										arDamager.remove();
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
		}
	}

}
