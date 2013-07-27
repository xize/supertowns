package eu.supertowns.town.commands.minicommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import eu.supertowns.town.commands.cmdsupertowns;

public class help {
	cmdsupertowns design;
	public help(cmdsupertowns design) {
		this.design = design;
	}
	
	public void showHelp(CommandSender sender) {
		sender.sendMessage(design.getPrefix("SuperTowns help"));
		sender.sendMessage(design.getDefaultColour("Default:") + "/supertowns help " + ChatColor.WHITE + ": shows help");
		sender.sendMessage(design.getDefaultColour("Default:") + "/supertowns town " + ChatColor.WHITE + ": shows all town related commands");
		sender.sendMessage(design.getDefaultColour("Default:") + "/supertowns new <town name>" + ChatColor.WHITE + ": allows you to create a town");
		sender.sendMessage(design.getDefaultColour("Default:") + "/supertowns del <town name> " + ChatColor.WHITE + ": allows to delete your town");
		sender.sendMessage(design.getDefaultColour("Default:") + "/supertowns flags " + ChatColor.WHITE + ": shows all toggle flags for your own town");
		if(sender.hasPermission("supertowns.command.admin")) {
			sender.sendMessage(design.getAdminColour("Admin:") + "/supertowns admin " + ChatColor.WHITE + ": shows all admin related help");
		}
	}

}
