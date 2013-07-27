package eu.supertowns.town.events;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import eu.supertowns.town.supertowns;

public class chunkmanager implements Listener {
	supertowns plugin;
	public chunkmanager(supertowns plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerchunk(PlayerMoveEvent e) {
		Chunk chunkFrom = e.getPlayer().getLocation().getChunk();
		Chunk chunkTo = e.getTo().getChunk();
		if(chunkFrom.getX() != chunkTo.getX() || chunkFrom.getZ() != chunkTo.getZ()) {
			if(checkTown(chunkTo.getX(), chunkTo.getZ(), chunkTo.getWorld()) && !checkTown(chunkFrom.getX(), chunkFrom.getZ(), chunkFrom.getWorld())) {
				startTownJoinMessage(e.getPlayer(), chunkTo.getX(), chunkTo.getZ(), chunkTo.getWorld());
			} else if(!checkTown(chunkTo.getX(), chunkTo.getZ(), chunkTo.getWorld()) && checkTown(chunkFrom.getX(), chunkFrom.getZ(), chunkFrom.getWorld())) {
				startTownLeaveMessage(e.getPlayer(), chunkFrom.getX(), chunkFrom.getZ(), chunkTo.getWorld());
			}
		}
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

}
