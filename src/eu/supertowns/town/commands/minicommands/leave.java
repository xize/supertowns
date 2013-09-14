package eu.supertowns.town.commands.minicommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class leave {
	supertowns plugin;
	coreApi api;
	public leave(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	public void onTownLeave(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("leave")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					if(api.isMember(p)) {
						String town = api.getTown(p);
						if(api.isMayor(p, town)) {
							sender.sendMessage(ChatColor.GREEN + "please ask a resident to be a mayor and use /st mayor set <name>");
						} else {
							api.removeResident(p, town);
							Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " has left the town " + town + "!");
						}
					} else {
						p.sendMessage(ChatColor.RED + "you don't belong to any town!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "error only players can leave towns!");
				}
			}
		}
	}
}
