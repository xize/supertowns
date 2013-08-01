package eu.supertowns.town.events;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class chunkmanager implements Listener {
	coreApi api;
	supertowns plugin;
	public chunkmanager(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	@EventHandler
	public void playerchunk(PlayerMoveEvent e) {
		Chunk chunkFrom = e.getPlayer().getLocation().getChunk();
		Chunk chunkTo = e.getTo().getChunk();
		if(chunkFrom.getX() != chunkTo.getX() || chunkFrom.getZ() != chunkTo.getZ()) {
			if(api.checkTown(chunkTo.getX(), chunkTo.getZ(), chunkTo.getWorld()) && !api.checkTown(chunkFrom.getX(), chunkFrom.getZ(), chunkFrom.getWorld())) {
				api.startTownJoinMessage(e.getPlayer(), chunkTo.getX(), chunkTo.getZ(), chunkTo.getWorld());
			} else if(!api.checkTown(chunkTo.getX(), chunkTo.getZ(), chunkTo.getWorld()) && api.checkTown(chunkFrom.getX(), chunkFrom.getZ(), chunkFrom.getWorld())) {
				api.startTownLeaveMessage(e.getPlayer(), chunkFrom.getX(), chunkFrom.getZ(), chunkTo.getWorld());
			}
		}
	}

}
