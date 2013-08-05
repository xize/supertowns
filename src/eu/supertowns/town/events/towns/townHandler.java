package eu.supertowns.town.events.towns;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;
import eu.supertowns.town.events.wilderness.tntRegen;

public class townHandler {
	supertowns plugin;
	coreApi api;
	public townHandler(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void launch() {
		getListener(new chunkmanager(plugin, api));
		getListener(new despawnMonsters(plugin, api));
		getListener(new pvpCheck(plugin, api));
		getListener(new buildCheck(plugin, api));
		getListener(new mobProtectionCheck(plugin, api));
		getListener(new tntCheck(plugin, api));
		getListener(new tntRegen(plugin, api));
		startTNT(new tntRegen(plugin, api));
		startSpawnScheduler(new despawnMonsters(plugin, api));
	}
	
	public void getListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	public void startSpawnScheduler(despawnMonsters e) {
		e.checkTask();
	}
	
	public void startTNT(tntRegen e) {
		e.startTntRegen();
	}

}
