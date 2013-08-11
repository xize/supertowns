package eu.supertowns.town.events.wilderness;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import eu.supertowns.town.supertowns;
import eu.supertowns.town.api.coreApi;

public class tntRegen implements Listener {
	supertowns plugin;
	coreApi api;
	public tntRegen(supertowns plugin, coreApi api) {
		this.plugin = plugin;
		this.api = api;
	}

	public static HashMap<Location, MaterialData> list = new HashMap<Location, MaterialData>();

	Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void doTntRegen(EntityExplodeEvent e) {
		for(Block block : e.blockList())  {
			if(block.getType() == Material.AIR || block.getType() == Material.VINE || block.getType() == Material.LADDER || block.getType() == Material.MINECART || block.getType() == Material.FURNACE || block.getType() == Material.CHEST) {
				e.setCancelled(true);
			} else if(block.getType() == Material.TNT) {

			} else {
				list.put(block.getLocation(), block.getState().getData());
				bounceBlock(block);
				block.setType(Material.AIR);
			}
		}
		//plugin.logger("this is the ArrayList " + list.toString(), logType.info);
	}

	public void bounceBlock(Block b) {
		if(b == null) return;

		if(list.size() > 1500) {
			return;
		}
		FallingBlock fb = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
		if(fb.getMaterial() == Material.TNT || fb.getMaterial() == Material.SAND || fb.getMaterial() == Material.GRAVEL || fb.getMaterial() == Material.ANVIL) {
			fb.setDropItem(false);
			return;
		}

		float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
		float y = 2;//(float) -5 + (float)(Math.random() * ((5 - -5) + 1));
		float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));

		fb.setDropItem(false);
		fb.setVelocity(new Vector(x, y, z));
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
					loca.getBlock().getWorld().playEffect(loca.getBlock().getLocation(), Effect.STEP_SOUND, loca.getBlock().getType());
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
		if(!list.isEmpty()) {
			if(e.getEntity() instanceof FallingBlock) {
				FallingBlock fb = (FallingBlock) e.getEntity();
				for(Location loc : list.keySet()) {
					if(fb.getLocation().getBlock().getLocation().equals(loc)) {
						//plugin.logger("found location match", logType.info);
						e.setCancelled(true);
					} else if(fb.getMaterial() != Material.SAND || fb.getMaterial() != Material.GRAVEL || fb.getMaterial() != Material.ANVIL) {
						//plugin.logger("block projecttile has been canceled this is the material: " + fb.getMaterial().name(), logType.info);
						e.setCancelled(true);
					}
				}
			}
		}
	}


}
