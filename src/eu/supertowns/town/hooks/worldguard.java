package eu.supertowns.town.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class worldguard {
	
	public boolean isWgEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			return true;
		}
		return false;
	}
	
	public boolean isInRegion(Player p) {
		if(isWgEnabled()) {
			WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
			for(ProtectedRegion region : wg.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
				if(!region.isMember(p.getName()) || !region.isOwner(p.getName())) {
					return true;
				}
			}
		}
		return false;
	}

}
