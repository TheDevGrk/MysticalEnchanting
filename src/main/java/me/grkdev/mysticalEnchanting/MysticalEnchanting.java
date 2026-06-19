package me.grkdev.mysticalEnchanting;

import me.grkdev.mysticalEnchanting.listeners.EnchantingTableListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticalEnchanting extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // ------------------------------------------------------------------------------------------------------------------------------------------------------
        // Register Listeners
        Bukkit.getPluginManager().registerEvents(new EnchantingTableListener(), this);

        // ------------------------------------------------------------------------------------------------------------------------------------------------------

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
    Design Ideas
    There will be different stats that are altered by the type of books, the blocks, the biome, structure, altitude, etc. around the etable
    Each enchantment will require certain ranges of these stats in order to have a chance of appearing in the etable
    Stat examples: Infernal, Nature, Ice, etc.
    These stats may or may not be visible to the player, unsure
    There will be Easter egg enchantments that require very specific conditions because they have very specific ranges that can only be achieved with those
    very specific conditions
    */
}
