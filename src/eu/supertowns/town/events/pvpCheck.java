package eu.supertowns.town.events;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class pvpCheck implements Listener {
	supertowns plugin;
	coreApi api;
	public pvpCheck(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	@EventHandler
	public void checkOnPvp(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player attacker = (Player) e.getDamager();
			if(api.isPlayerInTown(p)) {
				//get town name so we can see in the flags
				String townName = api.getTownNameOnLocation(p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ(), p.getWorld());
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
					if(f.exists()) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
							attacker.sendMessage(ChatColor.RED + "you are not allowed to pvp in the town of: " + ChatColor.GOLD + townName);
							e.setCancelled(true);
						}
					} else {
						return;
					}
				} catch(Exception r) {
					r.printStackTrace();
				}
			}
		} else if(e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
			Player p = (Player) e.getEntity();
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.getShooter() instanceof Player) {
				Player attacker = (Player) arrow.getShooter();
				if(api.isPlayerInTown(p)) {
					//get town name so we can see in the flags
					String townName = api.getTownNameOnLocation(p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ(), p.getWorld());
					try {
						File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
						if(f.exists()) {
							FileConfiguration con = YamlConfiguration.loadConfiguration(f);
							if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
								attacker.sendMessage(ChatColor.RED + "you are not allowed to pvp in the town of: " + ChatColor.GOLD + townName);
								arrow.remove();
								e.setCancelled(true);
							}
						} else {
							return;
						}
					} catch(Exception r) {
						r.printStackTrace();
					}
				}
			}
		}
	}

}
