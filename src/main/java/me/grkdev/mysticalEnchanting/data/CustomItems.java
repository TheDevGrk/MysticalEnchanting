package me.grkdev.mysticalEnchanting.data;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import static me.grkdev.mysticalEnchanting.MysticalEnchanting.plugin;

public class CustomItems {
    private static final NamespacedKey key = new NamespacedKey(plugin, "itemKey");

    public static final NamespacedKey fireKey = new NamespacedKey(plugin, "fire_modifier");
    public static final NamespacedKey wildKey = new NamespacedKey(plugin, "wild_modifier");
    public static final NamespacedKey earthKey = new NamespacedKey(plugin, "earth_modifier");
    public static final NamespacedKey airKey = new NamespacedKey(plugin, "air_modifier");
    public static final NamespacedKey waterKey = new NamespacedKey(plugin, "water_modifier");
    public static final NamespacedKey soulKey = new NamespacedKey(plugin, "soul_modifier");




    private static ItemStack getBaseCustomItem(){
        ItemStack item = ItemStack.of(Material.MUSIC_DISC_13);
        item.unsetData(DataComponentTypes.JUKEBOX_PLAYABLE);
//        item.setData(DataComponentTypes.ITEM_MODEL, Material.SPIDER_EYE.getDefaultData(DataComponentTypes.ITEM_MODEL));
        item.setData(DataComponentTypes.RARITY, ItemRarity.COMMON);

        return item;
    }

    public static ItemStack infernalBook(){
        ItemStack book = ItemStack.of(Material.BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.customName(Component.text("Infernal Book", NamedTextColor.DARK_RED));
        meta.setRarity(ItemRarity.EPIC);

        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "mystical_enchanting:infernal_book");
        meta.getPersistentDataContainer().set(fireKey, PersistentDataType.INTEGER, 10);

        book.setItemMeta(meta);

        return book;
    }
}
