package eu.supertowns.town.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.events.chunkmanager;

public class supertownsPlugin extends JavaPlugin {
	supertowns plugin;
	private supertownsPlugin(supertowns plugin) {
		this.plugin = plugin;
	}
	
	protected final chunkmanager chunk = new chunkmanager(plugin);
	
	public boolean isPlayerInTown(Player p) {
		if(chunk.isPlayerInTown(p)) {
			return true;
		}
		return false;
	}
	
	public boolean isPlayerInWild(Player p) {
		if(!chunk.isPlayerInTown(p)) {
			return true;
		}
		return false;
	}
}
