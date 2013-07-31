package eu.supertowns.town.commands.minicommands;

import java.io.File;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import eu.supertowns.town.logType;
import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class spawn {
	coreApi api;
	supertowns plugin;
	public spawn(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void teleportToTownSpawn(CommandSender sender, String[] args) {
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("spawn")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					try {
						File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + args[1] + ".yml");
						if(f.exists()) {
							FileConfiguration con = YamlConfiguration.loadConfiguration(f);
							plugin.logger("before enum", logType.info);
							if(con.getString("townSpawnPoint.status").equalsIgnoreCase("public")) {
								plugin.logger("near enum", logType.info);
								if(api.isMember(sender, args[1])) {
									sender.sendMessage(ChatColor.GREEN + "teleporting to town " + con.getString("TownName"));
									api.safeTeleport(p, con);
								} else {
									if(con.getDouble("townSpawnPoint.touristPrice") == 0) {
										sender.sendMessage(ChatColor.GREEN + "teleporting to town " + con.getString("TownName"));
										api.safeTeleport(p, con);
									} else {
										if(Bukkit.getPluginManager().isPluginEnabled("iConomy") && Bukkit.getPluginManager().isPluginEnabled("Vault")) {
											RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
											Economy econ = economyProvider.getProvider();
											if(econ.getBalance(sender.getName()) == con.getDouble("townSpawnPoint.touristPrice") || econ.getBalance(sender.getName()) > con.getDouble("townSpawnPoint.touristPrice")) {
												econ.withdrawPlayer(sender.getName(), con.getDouble("townSpawnPoint.touristPrice"));
												sender.sendMessage(ChatColor.GREEN + "teleporting to town " + con.getString("TownName") + " teleport costs " + con.getDouble("townSpawnPoint.touristPrice") + "$");
												api.safeTeleport(p, con);
											} else {
												sender.sendMessage(ChatColor.RED + "you don't have money for this teleport!");
												return;
											}
										} else {
											plugin.logger("warning you need iconomy and vault installed for this!", logType.servere);
											sender.sendMessage(ChatColor.GREEN + "teleporting to town " + con.getString("TownName"));
											api.safeTeleport(p, con);
										}
									}
								}
							} else if(con.getString("townSpawnPoint.status").equalsIgnoreCase("private")) {
								sender.sendMessage(ChatColor.RED + "this town has privated his doors!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "the town " + args[1] + " does not exists");
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				} else {
					sender.sendMessage(ChatColor.RED + "a console cannot teleport to a town spawn");
				}
			}
		}
	}

}
