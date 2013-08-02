package eu.supertowns.town.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class buildCheck implements Listener {
	supertowns plugin;
	coreApi api;
	public buildCheck(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	@EventHandler
	public void blockDestroyCheck(BlockBreakEvent e) {
		if(api.isPlayerInTown(e.getPlayer())) {
			String townName = api.getTownNameOnLocation(e.getBlock().getChunk().getX(), e.getBlock().getChunk().getZ(), e.getBlock().getWorld());
			if(townName.equalsIgnoreCase("null")) {
				return;
			}
			if(!api.isMember(e.getPlayer(), townName) || !api.isMayor(e.getPlayer(), townName)) {
				e.getPlayer().sendMessage(ChatColor.RED + "you are permitted to destroy blocks in the town of " + ChatColor.GOLD + townName);
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void blockDestroyCheck(BlockPlaceEvent e) {
		if(api.isPlayerInTown(e.getPlayer())) {
			String townName = api.getTownNameOnLocation(e.getBlock().getChunk().getX(), e.getBlock().getChunk().getZ(), e.getBlock().getWorld());
			if(townName.equalsIgnoreCase("null")) {
				return;
			}
			if(!api.isMember(e.getPlayer(), townName) || !api.isMayor(e.getPlayer(), townName)) {
				e.getPlayer().sendMessage(ChatColor.RED + "you are permitted to build in the town of " + ChatColor.GOLD + townName);
				e.setCancelled(true);
			}
		}
	}

}
