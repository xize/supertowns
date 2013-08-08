package eu.supertowns.town.api;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;

public class coreApi {
	supertowns plugin;
	public coreApi(supertowns plugin) {
		this.plugin = plugin;
	}
	
	public String getTown(Player p) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				String town = con.getString("town");
				return town;
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public enum flagType {
		pvp,
		fire_spread,
		explosion,
		mob_protection,
		hostile_mob_spawn
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
				} else if(type == flagType.fire_spread) {
					if(con.getString("townFlag.pvp").equalsIgnoreCase("deny")) {
						con.set("townFlag.firespread", "allow");
						con.save(f);
					} else {
						con.set("townFlag.firespread", "deny");
						con.save(f);
					}
				} else if(type == flagType.explosion) {
					if(con.getString("townFlag.tnt").equalsIgnoreCase("deny")) {
						con.set("townFlag.tnt", "allow");
						con.save(f);
					} else {
						con.set("townFlag.tnt", "deny");
						con.save(f);
					}
				} else if(type == flagType.mob_protection) {
					if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
						con.set("townFlag.setMobProtection", "deny");
						con.save(f);
					} else {
						con.set("townFlag.setMobProtection", "allow");
						con.save(f);
					}
				} else if(type == flagType.hostile_mob_spawn) {
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
				} else if(type == flagType.fire_spread) {
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
				} else if(type == flagType.mob_protection) {
					if(con.getString("townFlag.setMobProtection").equalsIgnoreCase("allow")) {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.GREEN + " allow";
					} else {
						return ChatColor.GRAY + type.name() + ":" + ChatColor.RED + " deny";
					}
				} else if(type == flagType.hostile_mob_spawn) {
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
	
	public String getTownNameOnLocation(int x, int z, World w) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + w.getName() + "_x" + x + "_z"+z + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getString("TownName");
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isResident(Player p, String town) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getString("town").equalsIgnoreCase(town)) {
					if(con.getString("type").equalsIgnoreCase("resident")) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isMayor(Player p, String town) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getString("town").equalsIgnoreCase(town)) {
					if(con.getString("type").equalsIgnoreCase("mayor")) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isPlayerInTown(Player p) {
		World world = p.getLocation().getWorld();
		int x = p.getLocation().getChunk().getX();
		int z = p.getLocation().getChunk().getZ();
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + world.getName() + "_x" + x + "_z"+z + ".yml");
			if(f.exists()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isEntityInTown(Entity p) {
		World world = p.getLocation().getWorld();
		int x = p.getLocation().getChunk().getX();
		int z = p.getLocation().getChunk().getZ();
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + world.getName() + "_x" + x + "_z"+z + ".yml");
			if(f.exists()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkTown(int x, int z, World world) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + world.getName() + "_x" + x + "_z"+z + ".yml");
			if(f.exists()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkTownAtNearbyChunks(int x, int z, World w, String townName, CommandSender sender) {
		if(checkTown((x+1), z, w)) {
			try {
				File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
				if(f.exists()) {
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					if(con.getString("mayor").equalsIgnoreCase(sender.getName())) {
						return true;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(checkTown((x-1), z, w)) {
			try {
				File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
				if(f.exists()) {
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					if(con.getString("mayor").equalsIgnoreCase(sender.getName())) {
						return true;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(checkTown(x, (z+1), w)) {
			try {
				File f = new File(plugin.getDataFolder() + File.separator + "Towns"  + File.separator + townName + ".yml");
				if(f.exists()) {
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					if(con.getString("mayor").equalsIgnoreCase(sender.getName())) {
						return true;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(checkTown(x, (z-1), w)) {
			try {
				File f = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + townName + ".yml");
				if(f.exists()) {
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					if(con.getString("mayor").equalsIgnoreCase(sender.getName())) {
						return true;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void startTownJoinMessage(Player p, int x, int z, World world) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + world.getName() + "_x" + x + "_z"+z + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				File f2 = new File(plugin.getDataFolder() + File.separator + "Towns" + File.separator + con.getString("TownName") + ".yml");
				FileConfiguration con2 = YamlConfiguration.loadConfiguration(f2);
				p.sendMessage(ChatColor.GOLD + ".oO___[" + ChatColor.GREEN + "Entering town " + ChatColor.GREEN + ChatColor.UNDERLINE + con.getString("TownName") + ChatColor.GOLD + "]___Oo.\n(Mayor: " + con2.getString("mayor") + ") residents: " + con2.getInt("residentsCount"));
			} else {
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void startTownLeaveMessage(Player p, int x, int z, World world) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "TownBlocks" + File.separator + world.getName() + "_x" + x + "_z"+z + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				p.sendMessage(ChatColor.GREEN + "~leaving town "  + ChatColor.GOLD + ChatColor.UNDERLINE + con.getString("TownName") + ChatColor.GREEN + " you are now entering the wilderness~");
			} else {
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public boolean isMember(Player p, String town) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getString("town").equalsIgnoreCase(town)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isMember(Player p) {
		String town = getTown(p);
		if(town != "null") {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void safeTeleport(Player p, FileConfiguration con) {
		if(p.isInsideVehicle()) {
			p.getVehicle().eject();
			Double x = con.getDouble("townSpawnPoint.X");
			Double y = con.getDouble("townSpawnPoint.Y");
			Double z = con.getDouble("townSpawnPoint.Z");
			int yaw = con.getInt("townSpawnPoint.Yaw");
			World world = Bukkit.getWorld(con.getString("townSpawnPoint.World"));
			Location tpLoc = new Location(world, x, y, z);
			tpLoc.setYaw(yaw);
			Chunk chunk = tpLoc.getChunk();
			if(world != null) {
				p.getWorld().refreshChunk(chunk.getX(), chunk.getZ());
				p.teleport(tpLoc);
			} else {
				p.sendMessage(ChatColor.RED + "fatal error in supertowns, the world does not exists!");
			}
		} else {
			Double x = con.getDouble("townSpawnPoint.X");
			Double y = con.getDouble("townSpawnPoint.Y");
			Double z = con.getDouble("townSpawnPoint.Z");
			int yaw = con.getInt("townSpawnPoint.Yaw");
			World world = Bukkit.getWorld(con.getString("townSpawnPoint.World"));
			Location tpLoc = new Location(world, x, y, z);
			tpLoc.setYaw(yaw);
			Chunk chunk = tpLoc.getChunk();
			if(world != null) {
				p.getWorld().refreshChunk(chunk.getX(), chunk.getZ());
				p.teleport(tpLoc);
			} else {
				p.sendMessage(ChatColor.RED + "fatal error in supertowns, the world does not exists!");
			}
		}
	}

}
