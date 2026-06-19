package me.grkdev.mysticalEnchanting.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.EnchantingTable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public class EnchantingTableListener implements Listener {

    @EventHandler
    public static void onOpenEnchantingTable(InventoryOpenEvent event){
        if (event.getInventory().getType() != InventoryType.ENCHANTING || !(event.getPlayer() instanceof Player player)){
            return;
        }


        // get all books inside chiseled bookshelves in enchanting table radius
        ArrayList<ItemStack> books = new ArrayList<>();

        Location loc1 = event.getInventory().getLocation().add(2, 2, 2);
        Location loc2 = event.getInventory().getLocation().subtract(2, 1, 2);

        // get every block in 5x5x3 volume
        for(int x = loc2.getBlockX(); x <= loc1.getBlockX(); x++) {
            for(int z = loc2.getBlockZ(); z <= loc1.getBlockZ(); z++) {
                for(int y = loc2.getBlockY(); y <= loc1.getBlockY(); y++) {


                    Block block = event.getPlayer().getWorld().getBlockAt(x, y, z);
                    if (block.getType() == Material.CHISELED_BOOKSHELF){
                        ChiseledBookshelf bookshelf = (ChiseledBookshelf) block.getState();


                        for (ItemStack i : bookshelf.getInventory().getContents()){
                            if (i != null){
                                books.add(i);
                            }
                        }

                    }
                }
            }
        }



        // ---------------------------
        EnchantingTable enchantingTable = (EnchantingTable) event.getInventory().getLocation().getBlock().getState();
        PersistentDataContainer pdc = enchantingTable.getPersistentDataContainer();


    }
}
