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
				} else if(args[0].equalsIgnoreCase("claim")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.claim");
				} else if(args[0].equalsIgnoreCase("setspawn")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.setspawn");
				} else if(args[0].equalsIgnoreCase("spawn")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.spawn");
				} else if(args[0].equalsIgnoreCase("accept")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.townaccept");
				} else if(args[0].equalsIgnoreCase("deny")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.towndeny");
				} else if(args[0].equalsIgnoreCase("leave")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.leave");
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("new")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.newtown");
				} else if(args[0].equalsIgnoreCase("spawn")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.spawn");
				} else if(args[0].equalsIgnoreCase("setflag")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.setflag");
				} else if(args[0].equalsIgnoreCase("flag")) {
					sender.sendMessage(ChatColor.RED + "you are not allowed to use this command: /" + cmd.getName() +" "+ args[0] + "\n" + ChatColor.GRAY + "permission: supertowns.command.flags");
				}
			}
		}
	}

}
