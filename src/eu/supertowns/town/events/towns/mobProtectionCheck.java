package eu.supertowns.town.events.towns;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

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
					if(!api.isMember(damager, townName) || !api.isMayor(damager, townName)) {
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
						if(!api.isMember(damager, townName) || !api.isMayor(damager, townName)) {
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
		} else if(e.getDamager() instanceof ThrownPotion) {
			ThrownPotion pot = (ThrownPotion) e.getDamager();
			if(pot.getShooter() instanceof Player) {
				Player thrower = (Player) pot.getShooter();
				if(api.isEntityInTown(e.getEntity())) {
					String townName = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
					if(!api.isMember(thrower, townName) || !api.isMayor(thrower, townName)) {
						try {
							File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
							if(f.exists()) {
								FileConfiguration con = YamlConfiguration.loadConfiguration(f);
								if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
									thrower.sendMessage(ChatColor.RED + "you are not allowed to damage this mob " + ChatColor.GOLD + e.getEntity().getType().name().toLowerCase().replace("_", " ") + ChatColor.RED + " in the town " + ChatColor.GOLD + townName);
									pot.remove();
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
	
	@EventHandler
	public void checkPlayerOnLeash(PlayerLeashEntityEvent e) {
		if(api.isEntityInTown(e.getEntity())) {
			String townName = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
			if(!api.isMember(e.getPlayer(), townName) || !api.isMayor(e.getPlayer(), townName)) {
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
					if(f.exists()) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
							e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to leash the mob " + ChatColor.GOLD + ChatColor.UNDERLINE + e.getEntity().getType().name().replace("_", " ") + ChatColor.RED + " in the town of " + townName);
							e.setCancelled(true);
						}
					}
				} catch(Exception r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	@EventHandler
	public void checkPlayerUnleash(PlayerUnleashEntityEvent e) {
		if(api.isEntityInTown(e.getEntity())) {
			String townName = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
			if(!api.isMember(e.getPlayer(), townName) || !api.isMayor(e.getPlayer(), townName)) {
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
					if(f.exists()) {
						FileConfiguration con = YamlConfiguration.loadConfiguration(f);
						if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
							e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to unleash the mob " + ChatColor.GOLD + ChatColor.UNDERLINE + e.getEntity().getType().name().replace("_", " ") + ChatColor.RED + " in the town of " + townName);
							e.setCancelled(true);
						}
					}
				} catch(Exception r) {
					r.printStackTrace();
				}
			}
		}
	}
	
	@EventHandler
	public void leashKnotEvent(HangingBreakByEntityEvent e) {
		if(e.getEntity().getType() == EntityType.LEASH_HITCH) {
			if(e.getRemover() instanceof Player) {
				Player p = (Player) e.getRemover();
				if(api.isEntityInTown(e.getEntity())) {
					String townName = api.getTownNameOnLocation(e.getEntity().getLocation().getChunk().getX(), e.getEntity().getLocation().getChunk().getZ(), e.getEntity().getWorld());
					if(!api.isMember(p.getPlayer(), townName) || !api.isMayor(p.getPlayer(), townName)) {
						try {
							File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
							if(f.exists()) {
								FileConfiguration con = YamlConfiguration.loadConfiguration(f);
								if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
									p.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to unleash the " + ChatColor.GOLD + ChatColor.UNDERLINE + e.getEntity().getType().name().replace("_", " ") + ChatColor.RED + " in the town of " + townName);
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
