package eu.supertowns.town.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class handler {
	supertowns plugin;
	coreApi api;
	public handler(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void launch() {
		getListener(new chunkmanager(plugin, api));
		getListener(new despawnMonsters(plugin, api));
		getListener(new pvpCheck(plugin, api));
		getListener(new buildCheck(plugin, api));
		getListener(new mobProtectionCheck(plugin, api));
		startSpawnScheduler(new despawnMonsters(plugin, api));
	}
	
	public void getListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	public void startSpawnScheduler(despawnMonsters e) {
		e.checkTask();
	}

}
