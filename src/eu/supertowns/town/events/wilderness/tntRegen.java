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
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.material.MaterialData;

import eu.supertowns.town.logType;
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
	public void spreads(BlockFromToEvent e) {
		if(e.getBlock().getType() == Material.STATIONARY_WATER || e.getBlock().getType() == Material.STATIONARY_LAVA) {
			Iterator<Entry<Location, MaterialData>> it = list.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<Location, MaterialData> its = (Map.Entry<Location, MaterialData>) it.next();
				Location loca = (Location) its.getKey();
				Block block = loca.getBlock();
				if(e.getBlock().equals(block)) {
					if(block.getType() == Material.STATIONARY_WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.WATER || block.getType() == Material.LAVA) {
						e.setCancelled(true);	
					}
				}
			}
		}
	}

	@EventHandler
	public void checkonFallingBlocks(EntityChangeBlockEvent e) {
		if(e.getBlock().getType() == Material.SAND || e.getBlock().getType() == Material.GRAVEL || e.getBlock().getType() == Material.ANVIL) {
			Iterator<Entry<Location, MaterialData>> it = list.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<Location, MaterialData> its = (Map.Entry<Location, MaterialData>) it.next();
				Location loca = (Location) its.getKey();
				Block block = loca.getBlock();
				double listx = (double) block.getLocation().getX();
				double listy = (double) block.getLocation().getY();
				double listz = (double) block.getLocation().getZ();
				double blockx = (double) e.getBlock().getLocation().getX();
				double blocky = (double) e.getBlock().getLocation().getY();
				double blockz = (double) e.getBlock().getLocation().getZ();
				plugin.logger("eventBlock(x:" + blockx + " y:" + blocky + " z:" + blockz + ")", logType.info);
				plugin.logger("listBlock(x:" + listx + " y:" + listy + " z:" + listz + ")", logType.info);
				if(e.getBlock().equals(block)) {
					if(block.getType() == Material.SAND || block.getType() == Material.GRAVEL || block.getType() == Material.ANVIL) {
						e.setCancelled(true);	
					}
				}
			}
		}
	}
}
