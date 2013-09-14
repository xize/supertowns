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
	public Material[] AllowedMaterials = {Material.WOOD, 
			Material.GLASS, Material.THIN_GLASS,
			Material.BRICK,
			Material.COBBLESTONE,
			Material.COBBLESTONE_STAIRS,
			Material.NETHER_BRICK,
			Material.NETHERRACK,
			Material.DIRT,
			Material.STONE,
			Material.SAND,
			Material.WOOD_STAIRS,
			Material.WOOD_STEP,
			Material.DIRT,
			Material.GRASS,
			
			};
	
	Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void doTntRegen(EntityExplodeEvent e) {
		for(Block block : e.blockList()) {
				if(block.getType() == Material.CHEST || block.getType() == Material.FURNACE || block.getType() == Material.ANVIL || block.getType() == Material.BED || block.getType() == Material.BREWING_STAND || block.getType() == Material.WOOD_DOOR || block.getType() == Material.IRON_DOOR || block.getType() == Material.TORCH || block.getType() == Material.REDSTONE_WIRE || block.getType() == Material.REDSTONE_COMPARATOR || block.getType() == Material.REDSTONE_TORCH_ON || block.getType() == Material.REDSTONE_TORCH_OFF) {
					e.setCancelled(true);
				} else if(block.getType() == Material.TNT) {
					//leave empty ignore tnt
				} else {
					list.put(block.getLocation(), block.getState().getData());
					bounceBlock(block);
					block.setType(Material.AIR);	
				}
		}
	}

	@SuppressWarnings("deprecation")
	public void bounceBlock(Block b) {
		if(b == null) return;

		if(list.size() > 1500) {
			return;
		}
		
		for(Material mat : AllowedMaterials) {
			if(b.getType() == mat) {
				FallingBlock fb = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
				

				float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
				float y = 2;//(float) -5 + (float)(Math.random() * ((5 - -5) + 1));
				float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));

				fb.setDropItem(false);
				fb.setVelocity(new Vector(x, y, z));
			}
		}
	}


	public void startTntRegen() {
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			@SuppressWarnings("deprecation")
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
	public void removeBlock(EntityChangeBlockEvent e) {
		if(!list.isEmpty()) {
			e.setCancelled(true);
		}
	}
	
	
}
