package eu.supertowns.town.commands.minicommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class accept {
	supertowns plugin;
	coreApi api;
	public accept(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void acceptTownRequest(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("accept")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					if(invite.invites.containsKey(p.getName())) {
						Player victem = Bukkit.getPlayerExact(invite.invites.get(p.getName()));
						if(victem instanceof Player) {
							victem.sendMessage(ChatColor.GREEN + p.getName() + " has accepted your town request");
							Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " joined the town " + api.getTown(victem));
							api.setResident(p, api.getTown(victem));
							invite.invites.remove(sender.getName());
						} else {
							Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " joined the town " + api.getTown(victem));
							api.setResident(p, api.getTown(victem));
							invite.invites.remove(sender.getName());
						}
						sender.sendMessage(ChatColor.GREEN + "successfully accepted town request");
					} else {
						sender.sendMessage(ChatColor.RED + "you don't have any town invites open!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "a console is not allowed to accept invites!");
				}
			}
		}
	}

}
