package eu.supertowns.town.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.commands.minicommands.help;
import eu.supertowns.town.permissions.permission;

public class cmdsupertowns {
	
	supertowns plugin;
	public cmdsupertowns(supertowns plugin) {
		this.plugin = plugin;
	}
	
	public String getPrefix(String header) {
		String head = ChatColor.GOLD + ".oO___[" + header + "]___Oo.";
		return head;
	}
	
	public String getAdminColour(String s) {
		return ChatColor.RED + s + ChatColor.GRAY + " ";
	}
	
	public String getDefaultColour(String s) {
		return ChatColor.DARK_GRAY + s + ChatColor.GRAY + " ";
	}
	
	public boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(args.length == 0) {
			if(sender.hasPermission("supertowns.command.help")) {
				help Help = new help(this);
				Help.showHelp(sender);
			} else {
				permission perm = new permission();
				perm.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}

}
