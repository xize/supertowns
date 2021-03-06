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
		if(!con.contains("AdminTownDisband.removeProtection")) {
			plugin.logger("no entry found for config option AdminTownDisband.removeProtection, we change the config now!", logType.servere);
			con.set("AdminTownDisband.removeProtection", true);
			con.save(f);
		}
		if(!con.contains("AdminTownDisband.autoregen")) {
			plugin.logger("no entry found for config option AdminTownDisband.autoregen, we change the config now!", logType.servere);
			con.set("AdminTownDisband.autoregen", true);
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
		if(!con.contains("townprice")) {
			plugin.logger("no entry found for config option townprice, we change the config now!", logType.servere);
			con.set("townprice", 10000);
			con.save(f);
		}
		if(!con.contains("claimprice")) {
			plugin.logger("no entry found for config option claimprice, we change the config now!", logType.servere);
			con.set("claimprice", 500);
			con.save(f);
		}
		if(!con.contains("taxes.enabled")) {
			plugin.logger("no entry found for config option taxes.enabled, we change the config now!", logType.servere);
			con.set("taxes.enabled", true);
			con.save(f);
		}
		if(!con.contains("taxes.price")) {
			plugin.logger("no entry found for config option taxes.price, we change the config now!", logType.servere);
			con.set("taxes.price", 20);
			con.save(f);
		}
		plugin.logger("config.yml is up to date:)", logType.info);
	}
}
