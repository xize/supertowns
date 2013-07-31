package eu.supertowns.town.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class commands implements CommandExecutor {
	
	coreApi api;
	supertowns plugin;
	public commands(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("supertowns")) {
			cmdsupertowns supertowns = new cmdsupertowns(plugin, api);
			supertowns.execute(sender, cmd, args);
		}
		return false;
	}

}
