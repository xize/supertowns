package eu.supertowns.town.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

import eu.supertowns.town.logType;
import eu.supertowns.town.supertowns;

public class configCheck {
	supertowns plugin;
	public configCheck(supertowns plugin) {
		this.plugin = plugin;
	}
	public void configcheck(File f, FileConfiguration con) throws IOException {
		if(!con.contains("townProtect.tnt")) {
			plugin.logger("no entry found for config option townProtect.tnt, we change the config now!", logType.servere);
			con.set("townProtect.tnt", true);
			con.save(f);
		}
		if(!con.contains("townProtect.fire")) {
			plugin.logger("no entry found for config option townProtect.fire, we change the config now!", logType.servere);
			con.set("townProtect.fire", true);
			con.save(f);
		}
		if(!con.contains("townProtect.lava")) {
			plugin.logger("no entry found for config option townProtect.lava, we change the config now!", logType.servere);
			con.set("townProtect.lava", true);
			con.save(f);
		}
		if(!con.contains("townProtect.water")) {
			plugin.logger("no entry found for config option townProtect.water, we change the config now!", logType.servere);
			con.set("townProtect.water", true);
			con.save(f);
		}
		if(!con.contains("townProtect.removeHostileMobs")) {
			plugin.logger("no entry found for config option townProtect.removeHostileMobs, we change the config now!", logType.servere);
			con.set("townProtect.removeHostileMobs", true);
			con.save(f);
		}
		if(!con.contains("townProtect.protectPeacefullMobs")) {
			plugin.logger("no entry found for config option townProtect.protectPeacefullMobs, we change the config now!", logType.servere);
			con.set("townProtect.protectPeacefullMobs", true);
			con.save(f);
		}
		if(!con.contains("townProtect.disableInteractionForOutsiders")) {
			plugin.logger("no entry found for config option townProtect.disableInteractionForOutsiders, we change the config now!", logType.servere);
			con.set("townProtect.disableInteractionForOutsiders", true);
			con.save(f);
		}
		if(!con.contains("townDisband.enableAdminAproval")) {
			plugin.logger("no entry found for config option townDisband.enableAdminAproval, we change the config now!", logType.servere);
			con.set("townDisband.enableAdminAproval", true);
			con.save(f);
		}
		if(!con.contains("townDisband.forceOtherMayor")) {
			plugin.logger("no entry found for config option townDisband.forceOtherMayor, we change the config now!", logType.servere);
			con.set("townDisband.forceOtherMayor", true);
			con.save(f);
		}
		if(!con.contains("townDisband.removeProtection")) {
			plugin.logger("no entry found for config option townDisband.removeProtection, we change the config now!", logType.servere);
			con.set("townDisband.removeProtection", true);
			con.save(f);
		}
		if(!con.contains("townDisband.autoregen")) {
			plugin.logger("no entry found for config option townDisband.autoregen, we change the config now!", logType.servere);
			con.set("townDisband.autoregen", true);
			con.save(f);
		}
		if(!con.contains("wilderness.autoregenExplosives")) {
			plugin.logger("no entry found for config option wilderness.autoregenExplosives, we change the config now!", logType.servere);
			con.set("wilderness.autoregenExplosives", true);
			con.save(f);
		}
		if(!con.contains("wilderness.disableTnt")) {
			plugin.logger("no entry found for config option wilderness.disableTnt, we change the config now!", logType.servere);
			con.set("wilderness.disableTnt", true);
			con.save(f);
		}
		if(!con.contains("wilderness.disableFirespread")) {
			plugin.logger("no entry found for config option wilderness.disableFirespread, we change the config now!", logType.servere);
			con.set("wilderness.disableFirespread", true);
			con.save(f);
		}
		plugin.logger("config.yml is up to date:)", logType.info);
	}
}
