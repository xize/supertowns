package eu.supertowns.town.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.supertowns.town.supertowns;

public class commands implements CommandExecutor {
	
	supertowns plugin;
	public commands(supertowns plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("supertowns")) {
			cmdsupertowns supertowns = new cmdsupertowns(plugin);
			supertowns.execute(sender, cmd, args);
		}
		return false;
	}

}
