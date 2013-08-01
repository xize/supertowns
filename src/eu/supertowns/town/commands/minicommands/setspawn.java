package eu.supertowns.town.commands.minicommands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class setspawn {
	coreApi api;
	supertowns plugin;
	public setspawn(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void setSpawn(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("setspawn")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					String town = api.getTown(p);
					if(town != "null") {
						if(api.isMayor(p, api.getTownNameOnLocation(p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ(), p.getWorld()))) {
							String townName = api.getTownNameOnLocation(p.getLocation().getChunk().getX(), p.getLocation().getChunk().getZ(), p.getWorld());
							try {
								File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
								if(f.exists()) {
									FileConfiguration con = YamlConfiguration.loadConfiguration(f);
									Double x = p.getLocation().getX();
									Double y = p.getLocation().getY();
									Double z = p.getLocation().getZ();
									float yaw = p.getLocation().getYaw();
									String world = p.getWorld().getName();
									con.set("townSpawnPoint.X", x);
									con.set("townSpawnPoint.Y", y);
									con.set("townSpawnPoint.Z", z);
									con.set("townSpawnPoint.Yaw", yaw);
									con.set("townSpawnPoint.World", world);
									con.save(f);
									sender.sendMessage(ChatColor.GREEN + "successfully changed spawn of town " + townName + "!");
								} else {
									sender.sendMessage(ChatColor.RED + "a weird error has been ocuried, please check your console!");
								}
							} catch(Exception e) {
								e.printStackTrace();
							}
						} else {
							sender.sendMessage(ChatColor.RED + "you are not the mayor of this town!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "you cannot claim a spawn point in the wilderness!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "cannot set a spawnpoint location when the entity is not a player entity");
				}
			}
		}
	}

}
