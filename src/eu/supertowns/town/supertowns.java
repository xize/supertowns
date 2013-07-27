package eu.supertowns.town;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import eu.supertowns.town.commands.commandlist;
import eu.supertowns.town.commands.commands;
import eu.supertowns.town.configuration.Config;
import eu.supertowns.town.events.chunkmanager;

public class supertowns extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	private Config config = new Config(this);
	private commandlist cmdlist = new commandlist();
	private commands cmd = new commands(this);
	
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
		this.getServer().getPluginManager().registerEvents(new chunkmanager(this), this);
	}
	
	public void onDisable() {
		logger("has been disabled!", logType.info);
	}

}
