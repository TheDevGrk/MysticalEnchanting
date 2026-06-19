package me.grkdev.mysticalEnchanting.data;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemType;

import java.util.function.Consumer;

public class CustomEnchantment {
    private final TypedKey<Enchantment> key;
    private final Consumer<EnchantmentRegistryEntry.Builder> builder;
    private final TagKey<ItemType> supportedItemType;

    public CustomEnchantment(TypedKey<Enchantment> key, Consumer<EnchantmentRegistryEntry.Builder> builder, TagKey<ItemType> supportedItemType){
        this.key = key;
        this.builder = builder;
        this.supportedItemType = supportedItemType;
    }


    public TypedKey<Enchantment> getKey() {
        return key;
    }

    public Consumer<EnchantmentRegistryEntry.Builder> getBuilder() {
        return builder;
    }

    public TagKey<ItemType> getSupportedItemType() {
        return supportedItemType;
    }


}
