package eu.supertowns.town.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.supertowns.town.logType;
import eu.supertowns.town.supertowns;

public class Config {
	supertowns plugin;
	public Config(supertowns plugin) {
		this.plugin = plugin;
	}
	
	public void createConfiguration() {
		 try {
			 File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
			 if(f.exists()) {
				 plugin.logger("has found config.yml, checking for changes;)", logType.info);
				 FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				 configCheck conf = new configCheck(plugin);
				 conf.configcheck(f, con);
			 } else {
				 FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				 FileConfigurationOptions opt = con.options();
				 opt.header("Default config for supertowns!");
				 con.set("townprice", Double.valueOf(10000));
				 con.set("claimprice", Double.valueOf(500));
				 con.set("taxes.enabled", true);
				 con.set("taxes.price", Double.valueOf(20));
				 con.set("townProtect.tnt", true);
				 con.set("townProtect.fire", true);
				 con.set("townProtect.lava", true);
				 con.set("townProtect.water", true);
				 con.set("townProtect.removeHostileMobs", true);
				 con.set("townProtect.protectPeacefullMobs", true);
				 con.set("townProtect.disableInteractionForOutsiders", true);
				 con.set("townDisband.enableAdminAproval", true);
				 con.set("townDisband.forceOtherMayor", true);
				 con.set("townDisband.removeProtection", true);
				 con.set("townDisband.autoregen", true);
				 con.set("wilderness.autoregenExplosives", true);
				 con.set("wilderness.disableTnt", false);
				 con.set("wilderness.disableFirespread", true);
				 con.save(f);
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}

}
