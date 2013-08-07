package eu.supertowns.town.events.towns;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.material.MaterialData;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;
import eu.supertowns.town.events.wilderness.tntRegen;

public class townHandler {
	supertowns plugin;
	coreApi api;
	public townHandler(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}
	
	public void launch() {
		getListener(new chunkmanager(plugin, api));
		getListener(new despawnMonsters(plugin, api));
		getListener(new pvpCheck(plugin, api));
		getListener(new buildCheck(plugin, api));
		getListener(new mobProtectionCheck(plugin, api));
		getListener(new tntCheck(plugin, api));
		getListener(new tntRegen(plugin, api));
		startTNT(new tntRegen(plugin, api));
		startSpawnScheduler(new despawnMonsters(plugin, api));
	}
	
	public void saveTNTScheduler(tntRegen e) {
		if(!tntRegen.list.isEmpty()) {
			try {
				File f = new File(plugin.getDataFolder() + File.separator + "tntRegen" + File.separator + "storage.yml");
				if(f.exists()) {
					return;
				} else {
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					Iterator<Entry<Location, MaterialData>> it = tntRegen.list.entrySet().iterator();
					ArrayList<String> locs = new ArrayList<String>();
					ArrayList<String> Materialdata = new ArrayList<String>();
					while(it.hasNext()) {
						Map.Entry<Location, MaterialData> its = (Map.Entry<Location, MaterialData>) it.next();
						locs.add(SerializeLocation(its.getKey()));
						Materialdata.add(SerializeMaterialData(its.getValue()));
					}
					con.set("locations", locs);
					con.set("materialData", Materialdata);
					con.save(f);
				}
			} catch(Exception r) {
				r.printStackTrace();
			}
		}
	}
	
	public void startTNT(final tntRegen e) {
		try {
			final File f = new File(plugin.getDataFolder() + File.separator + "tntRegen" + File.separator + "storage.yml");
			if(f.exists()) {
				final FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						Iterator<String> locs = con.getStringList("locations").iterator();
						Iterator<String> data = con.getStringList("materialData").iterator();
						while(locs.hasNext() && data.hasNext()) {
							Location loc = DeSerializeLocation(locs.next());
							MaterialData mdata = DeSerializeMaterialData(data.next());
							tntRegen.list.put(loc, mdata);
						}
						f.delete();
						e.startTntRegen();
					}
					
				}, 10);
			} else {
				e.startTntRegen();
			}
		} catch(Exception r) {
			r.printStackTrace();
		}
	}

	public String SerializeLocation(Location loc) {
		return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ(); 	
	}

	public Location DeSerializeLocation(String locString) {
		String[] loc = locString.split(","); 
		return new Location(Bukkit.getWorld(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2]), Double.parseDouble(loc[3]));
	}

	public String SerializeMaterialData(MaterialData data) {
		return data.getItemType().name() + "," + data.getData();
	}

	public MaterialData DeSerializeMaterialData(String s) {
		String[] splitData = s.split(",");
		return new MaterialData(Material.valueOf(splitData[0]), Byte.parseByte(splitData[1]));
	}
	
	public void getListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	public void startSpawnScheduler(despawnMonsters e) {
		e.checkTask();
	}

}
