package eu.supertowns.town.commands.minicommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;
import eu.supertowns.town.api.coreApi.flagType;
import eu.supertowns.town.commands.cmdsupertowns;

public class flags {
	supertowns plugin;
	coreApi api;
	public flags(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	public void setFlag(CommandSender sender, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			String townName = api.getTown(p);
			if(api.isMayor(p, townName)) {
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("setFlag")) {
						try {
							flagType type = flagType.valueOf(args[1].toLowerCase());
							api.setFlag(townName, type);
							sender.sendMessage(ChatColor.GREEN + "successfully toggled the flag " + ChatColor.GOLD + ChatColor.UNDERLINE + type.name() + ChatColor.GREEN + " for " + townName);
						} catch(Exception e) {
							sender.sendMessage(ChatColor.RED + "unknown flag please use /st flags list to see in all flag types");
						}
					} else if(args[0].equalsIgnoreCase("flag")) {
						if(args[1].equalsIgnoreCase("list")) {
							cmdsupertowns header = new cmdsupertowns(plugin, api);
							StringBuilder build = new StringBuilder();
							for(flagType type : flagType.values()) {
								build.append(api.returnFlags(townName, type) + ", ");
							}
							String[] flags = build.toString().split(",");
							sender.sendMessage(header.getPrefix(townName + " available flags"));
							sender.sendMessage(ChatColor.RED + "flags: " + flags[0] + flags[1] + flags[2] + flags[3] + flags[4]);
						}
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "you are not a mayor of any town!");
		}
	}

}
