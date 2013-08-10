package eu.supertowns.town.commands.minicommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class deny {
	supertowns plugin;
	coreApi api;
	public deny(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void denyTown(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("deny")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					if(invite.invites.containsKey(p.getName())) {
						Player victem = Bukkit.getPlayerExact(invite.invites.get(p.getName()));
						if(victem instanceof Player) {
							victem.sendMessage(ChatColor.RED + p.getName() + " has denied your town request");
							invite.invites.remove(sender.getName());
						} else {
							invite.invites.remove(sender.getName());
						}
						sender.sendMessage(ChatColor.RED + "successfully denied the town request");
					} else {
						sender.sendMessage(ChatColor.RED + "you don't have any town invites open!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "a console is not allowed to perform this command");
				}
			}
		}
	}

}
