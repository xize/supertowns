package eu.supertowns.town.commands.minicommands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class invite {
	supertowns plugin;
	coreApi api;
	public invite(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	HashMap<String, String> invites = new HashMap<String, String>();

	public void playerInvite(final CommandSender sender, String[] args) {
		if(args.length == 2) {
			final Player victem = (Player) Bukkit.getPlayer(args[1]);
			final Player p = (Player) sender;
			if(victem instanceof Player) {
				if(p instanceof Player) {
					//check if the sender is mayor
					String town = api.getTown(p);
					if(api.isMayor(p, town)) {
						if(!api.isMember(victem)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

								@Override
								public void run() {
									if(invites.containsKey(victem.getName())) {
										Player p = (Player) sender;
										if(victem instanceof Player) {
											victem.sendMessage(ChatColor.RED + "town invite from " + p.getName() + " has been canceled");
										}
										if(p instanceof Player) {
											p.sendMessage(ChatColor.RED + "your town invite to " + victem.getName() + " has been canceled");
										}
										invites.remove(victem.getName());
									}
								}

							}, 1200L);
							invites.put(victem.getName(), sender.getName());
							p.sendMessage(ChatColor.GREEN + "town invite sent to " + victem.getName());
							victem.sendMessage(ChatColor.GREEN + sender.getName() + " has invited you to " + town + "\ntype /st accept - to be resident of the town\ntype /st deny - to deny the invite");
						} else {
							sender.sendMessage(ChatColor.RED + "the player " + victem.getName() + " allready belongs to a town");	
						}
					} else {
						sender.sendMessage(ChatColor.RED + "you don't own any town!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "console can't run this command");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "the player " + args[0] + " is not online!");
			}
		}
	}

}
