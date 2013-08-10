package eu.supertowns.town;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import eu.supertowns.town.api.coreApi;
import eu.supertowns.town.commands.commandlist;
import eu.supertowns.town.commands.commands;
import eu.supertowns.town.configuration.Config;
import eu.supertowns.town.events.towns.townHandler;
import eu.supertowns.town.events.wilderness.tntRegen;

public class supertowns extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	private coreApi api = new coreApi(this);
	private Config config = new Config(this);
	private commandlist cmdlist = new commandlist();
	private commands cmd = new commands(this, new coreApi(this));
	private townHandler eventHandler = new townHandler(this, new coreApi(this));
	
	public void logger(String string, logType logtype) {
		String prefix = "[SuperTowns] ";
		if(logtype == logType.info) {
			log.info(prefix + string);
		} else if(logtype == logType.servere) {
			log.severe(prefix + string);
		}
	}
	
	public void onEnable() {
		config.createConfiguration();
		logger("has been enabled!", logType.info);
		for(String command : cmdlist.getCommandList) {
			getCommand(command).setExecutor(cmd);
		}
		eventHandler.launch();
	}
	
	public void onDisable() {
		logger("has been disabled!", logType.info);
		eventHandler.saveTNTScheduler(new tntRegen(this, new coreApi(this)));
	}
	
	public coreApi getCoreApi() {
		return api;
	}

}
