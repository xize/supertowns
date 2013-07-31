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

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class claim {
	supertowns plugin;
	coreApi api;
	public claim(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	public void setClaim(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("claim")) {
				if(sender instanceof Player) {
					if(Bukkit.getPluginManager().isPluginEnabled("Vault") && Bukkit.getPluginManager().isPluginEnabled("iConomy")) {
						Player p = (Player) sender;
						try {
							File playerFile = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml");
							if(playerFile.exists()) {
								FileConfiguration playerCon = YamlConfiguration.loadConfiguration(playerFile);
								if(playerCon.getString("username").equalsIgnoreCase(sender.getName()) && playerCon.getString("type").equalsIgnoreCase("mayor")) {
									String townName = playerCon.getString("town");
									if(api.checkTownAtNearbyChunks(p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ(), p.getWorld(), townName, sender)) {
										File global = new File(plugin.getDataFolder() + File.separator + "config.yml");
										if(!api.checkTown(p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ(), p.getWorld())) {
											FileConfiguration globalCfg = YamlConfiguration.loadConfiguration(global);
											Double costs = globalCfg.getDouble("claimprice");
											RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
											Economy econ = economyProvider.getProvider();
											if(econ.getBalance(p.getName()) == costs || econ.getBalance(p.getName()) > costs) {
												File chunkFile = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + p.getWorld().getName() + "_x" + p.getLocation().getChunk().getX() + "_z" + p.getLocation().getChunk().getZ() + ".yml");
												if(!chunkFile.exists()) {
													FileConfiguration chunkCon = YamlConfiguration.loadConfiguration(chunkFile);
													chunkCon.set("TownName", townName);
													chunkCon.save(chunkFile);
													econ.withdrawPlayer(p.getPlayer().getName(), costs);
													sender.sendMessage(ChatColor.GREEN + "claimed chunk at x:" + p.getLocation().getChunk().getX() + " z:" + p.getLocation().getChunk().getZ() + " world: " + p.getWorld().getName());
												} else {
													sender.sendMessage(ChatColor.RED + "a strange error occuried please sent us a good description what happends console logs");
												}
											} else {
												sender.sendMessage(ChatColor.RED + "you cannot claim this chunk you need atleast " + costs + "$");
											}
										} else {
											sender.sendMessage(ChatColor.RED + "you allready claimed this");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "no chunks where found please get closer to your town instead.");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "you are no mayor in the town " + playerCon.getString("town"));
								}
							} else {
								sender.sendMessage(ChatColor.RED + "you don't belong to any town!");
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "supertowns failed to run this command is iConomy and Vault enabled?");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "cannot find location for non living entitys!");
				}
			}
		}
	}

}
