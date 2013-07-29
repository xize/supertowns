package eu.supertowns.town.commands.minicommands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;

public class newtown {
	supertowns plugin;
	public newtown(supertowns plugin) {
		this.plugin = plugin;
	}

	public void createTown(CommandSender sender, String[] args) {
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("new")) {
				Player p = (Player) sender;
				if(Bukkit.getPluginManager().isPluginEnabled("iConomy") && Bukkit.getPluginManager().isPluginEnabled("Vault")) {
					try {
						File fTown = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + args[1] + ".yml");
						File fGlobal = new File(plugin.getDataFolder() + File.separator + "config.yml");
						File chunk = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + p.getWorld().getName() + "_" + p.getLocation().getChunk().getX() + "_" + p.getLocation().getChunk().getZ() + ".yml");
						File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml");
						if(!chunk.exists()) {
							if(!fTown.exists()) {
								FileConfiguration fTowncon = YamlConfiguration.loadConfiguration(fTown);
								FileConfiguration fGlobalCon = YamlConfiguration.loadConfiguration(fGlobal);
								FileConfiguration fChunk = YamlConfiguration.loadConfiguration(chunk);
								FileConfiguration PlayerFile = YamlConfiguration.loadConfiguration(playerFile);
								if(playerFile.exists()) {
									if(PlayerFile.contains("town")) {
										 sender.sendMessage(ChatColor.RED + "warning you can't create a town when you are resident of town: " + PlayerFile.get("town") + " please leave this town in order to create a town!");
										 return;
									} else {
										PlayerFile.set("username", p.getName());
										PlayerFile.set("type", "mayor");
										PlayerFile.set("town", args[1]);
										PlayerFile.save(playerFile);
									}
								} else {
									PlayerFile.set("username", p.getName());
									PlayerFile.set("type", "mayor");
									PlayerFile.set("town", args[1]);
									PlayerFile.save(playerFile);
								}
								fTowncon.set("mayor", sender.getName());
								fTowncon.set("TownName", args[1]);
								fTowncon.set("residentsCount", 1);
								fTowncon.set("townFlag.pvp", "deny");
								fTowncon.set("townFlag.firespread", "deny");
								fTowncon.set("townFlag.tnt", "deny");
								fTowncon.set("townFlag.setMobProtection", "allow");
								fTowncon.set("townFlag.spawnHostileMobs", "deny");
								fTowncon.set("townFlag.teleportation", "allow");
								fTowncon.set("townSpawnPoint.X", p.getLocation().getX());
								fTowncon.set("townSpawnPoint.Y", p.getLocation().getY());
								fTowncon.set("townSpawnPoint.Z", p.getLocation().getZ());
								fTowncon.set("townSpawnPoint.Yaw", p.getLocation().getYaw());
								fTowncon.set("townSpawnPoint.World", p.getLocation().getWorld().getName());
								if(fGlobalCon.getBoolean("taxes.enabled")) {
									fTowncon.set("taxes", fGlobalCon.getInt("taxes.price"));	
								} else {
									fTowncon.set("taxes", 0);
								}
								fTowncon.save(fTown);
								fChunk.set("TownName", args[1]);
								fChunk.save(chunk);
								//do iconomy checks here
								
								//end iconomy checks
								Bukkit.broadcastMessage(ChatColor.GREEN + sender.getName() + " created a town called: " + args[1]);
							} else {
								sender.sendMessage(ChatColor.RED + "this chunk is allready claimed by a other town!");
								return;
							}
						} else {
							sender.sendMessage(ChatColor.RED + "error  this town allready exists please use a other name instead");
							return;
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				} else {
					sender.sendMessage(ChatColor.RED + "supertowns failed to run this command is iConomy and Vault enabled?");
				}
			}
		}
	}

}
