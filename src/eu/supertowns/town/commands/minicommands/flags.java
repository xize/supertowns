package eu.supertowns.town.commands.minicommands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;
import eu.supertowns.town.commands.cmdsupertowns;

public class flags {
	supertowns plugin;
	coreApi api;
	public flags(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	public void setFlag(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			String townName = api.getTown(p);
			if(api.isMayor(p, townName)) {
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("setFlag")) {
						try {
							flagType type = flagType.valueOf(args[1]);
							setFlag(townName, type);
							sender.sendMessage(ChatColor.GREEN + "successfully toggled the flag " + ChatColor.GOLD + ChatColor.UNDERLINE + type.name() + ChatColor.GREEN + " for " + townName);
						} catch(Exception e) {
							sender.sendMessage(ChatColor.RED + "unknown flag please use /st flags list to see in all flag types");
						}
					} else if(args[0].equalsIgnoreCase("flag")) {
						if(args[1].equalsIgnoreCase("list")) {
							cmdsupertowns header = new cmdsupertowns(plugin, api);
							StringBuilder build = new StringBuilder();
							for(flagType type : flagType.values()) {
								build.append(returnFlags(townName, type) + ", ");
							}
							String[] flags = build.toString().split(",");
							sender.sendMessage(header.getPrefix(townName + " available flags"));
							sender.sendMessage(ChatColor.RED + "flags: " + flags[0] + flags[1] + flags[2] + flags[3] + flags[4]);
						}
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "you are not a mayor of any town!");
		}
	}

	public enum flagType {
		pvp,
		fireSpread,
		explosion,
		mobProtection,
		hostileMobspawn
	}
	
	public void setFlag(String townName, flagType type) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(type == flagType.pvp) {
					if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
						con.set("townFlag.pvp", "allow");
						con.save(f);
					} else {
						con.set("townFlag.pvp", "deny");
						con.save(f);
					}
				} else if(type == flagType.fireSpread) {
					if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
						con.set("townFlag.firespread", "allow");
						con.save(f);
					} else {
						con.set("townFlag.firespread", "deny");
						con.save(f);
					}
				} else if(type == flagType.explosion) {
					if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
						con.set("townFlag.tnt", "allow");
						con.save(f);
					} else {
						con.set("townFlag.tnt", "deny");
						con.save(f);
					}
				} else if(type == flagType.mobProtection) {
					if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
						con.set("townFlag.setMobProtection", "deny");
						con.save(f);
					} else {
						con.set("townFlag.setMobProtection", "allow");
						con.save(f);
					}
				} else if(type == flagType.hostileMobspawn) {
					if(con.getString("townFlag.spawnHostileMobs").equalsIgnoreCase("deny")) {
						con.set("townFlag.spawnHostileMobs", "allow");
						con.save(f);
					} else {
						con.set("townFlag.spawnHostileMobs", "deny");
						con.save(f);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String returnFlags(String townName, flagType type) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(type == flagType.pvp) {
					if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.RED + " deny";
					} else {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.GREEN + " allow";
					}
				} else if(type == flagType.fireSpread) {
					if(con.getString("townFlag.firespread").equalsIgnoreCase("deny")) {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.RED + " deny";
					} else {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.GREEN + " allow";
					}
				} else if(type == flagType.explosion) {
					if(con.getString("townFlag.tnt").equalsIgnoreCase("deny")) {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.RED + " deny";
					} else {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.GREEN + " allow";
					}
				} else if(type == flagType.mobProtection) {
					if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.GREEN + " allow";
					} else {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.RED + " deny";
					}
				} else if(type == flagType.hostileMobspawn) {
					if(con.getString("townFlag.spawnHostileMobs").equalsIgnoreCase("deny")) {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.RED + " deny";
					} else {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.GREEN + " allow";
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
