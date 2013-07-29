package eu.supertowns.town.permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class permission {
	
	public void getPermissionError(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("supertowns")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: supertowns.commands.help");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.commands.help");
				}
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("new")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.newtown");
				}
			}
		}
	}

}
