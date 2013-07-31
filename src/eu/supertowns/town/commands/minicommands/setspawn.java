package eu.supertowns.town.commands.minicommands;

import org.bukkit.command.CommandSender;

import eu.supertowns.town.supertowns;

public class setspawn {
	supertowns plugin;
	public setspawn(supertowns plugin) {
		this.plugin = plugin;
	}
	
	public void setSpawn(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("setspawn")) {
				
			}
		}
	}

}
