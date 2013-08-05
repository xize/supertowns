package eu.supertowns.town.events.wilderness;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.material.MaterialData;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class tntRegen implements Listener {

	supertowns plugin;
	coreApi api;
	public tntRegen(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	public int i;

	public static HashMap<Location, MaterialData> list = new HashMap<Location, MaterialData>();

	@EventHandler
	public void doTntRegen(EntityExplodeEvent e) {
		Collections.reverse(e.blockList());
		for(Block block : e.blockList())  {
			if(!api.checkTown(block.getChunk().getX(), block.getChunk().getZ(), block.getWorld())) {
				if(block.getType() == Material.AIR || block.getType() == Material.TNT) {
				} else {
					list.put(block.getLocation(), block.getState().getData());
					block.setType(Material.AIR);
				}
			}
		}
		//plugin.logger("this is the ArrayList " + list.toString(), logType.info);
	}

	public void startTntRegen() {
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			@Override
			public void run() {
				if(list.isEmpty() || list.size() == 0) {

				} else {
					Iterator<Entry<Location, MaterialData>> it = list.entrySet().iterator();
					it.hasNext();
					Map.Entry<Location, MaterialData> its = (Map.Entry<Location, MaterialData>) it.next();
					Location loca = (Location) its.getKey();
					MaterialData blockType = (MaterialData) its.getValue();
					loca.getBlock().setTypeId(blockType.getItemTypeId());
					loca.getBlock().setData(blockType.getData());
					it.remove();
					list.remove(loca);
				}

			}
		}, 0, 1);
	}

	@EventHandler
	public void checkonFallingBlocks(EntityChangeBlockEvent e) {
		if(e.getEntity().getType() == EntityType.FALLING_BLOCK) {
			Iterator<Entry<Location, MaterialData>> it = list.entrySet().iterator();
			if(it.hasNext()) {
				e.setCancelled(true);
			}
			//it.remove();
		}
	}
}
