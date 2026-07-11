package me.grkdev.mysticalEnchanting.data;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.List;

public class CustomEnchants {




    // ------------------------------------------------------------------------------------------------------------------------
    // INITIALIZE ALL CUSTOM ENCHANTMENTS ADDED BY THE PLUGIN


    private static final CustomEnchantment FROSTBITE = new CustomEnchantment(EnchantmentKeys.create(Key.key("mystical_enchanting:frostbite")),
            b -> b.description(Component.text("Frostbite"))
                    .weight(15)
                    .maxLevel(10)
                    .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(15, 1) )
                    .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(35, 1))
                    .anvilCost(1)
                    .activeSlots(EquipmentSlotGroup.MAINHAND),
            ItemTypeTagKeys.ENCHANTABLE_WEAPON);
    public static Enchantment frostbite = null;



    // ------------------------------------------------------------------------------------------------------------------------






    private static final List<CustomEnchantment> customEnchants = List.of(FROSTBITE);

    public static List<CustomEnchantment> getCustomEnchants() {
        return customEnchants;
    }

    public static void setCustomEnchants(){
        RegistryAccess registryAccess = RegistryAccess.registryAccess();


        // set all static Enchantment type vars for access in other classes
        frostbite = registryAccess.getRegistry(RegistryKey.ENCHANTMENT).get(CustomEnchants.FROSTBITE.getKey());
    }
}
