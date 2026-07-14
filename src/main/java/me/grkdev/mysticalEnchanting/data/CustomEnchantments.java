package me.grkdev.mysticalEnchanting.data;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.List;

public class CustomEnchantments {




    // ------------------------------------------------------------------------------------------------------------------------
    // INITIALIZE ALL CUSTOM ENCHANTMENTS ADDED BY THE PLUGIN


    private static final CustomEnchantment FROSTBITE = new CustomEnchantment(EnchantmentKeys.create(Key.key("mystical_enchanting:frostbite")),
            b -> b.description(Component.text("Frostbite"))
                    .weight(15)
                    .maxLevel(4)
                    .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(15, 5) )
                    .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(25, 5))
                    .anvilCost(1)
                    .activeSlots(EquipmentSlotGroup.MAINHAND),
            ItemTypeTagKeys.ENCHANTABLE_WEAPON,
            null,
            null,
            (ctx) -> { // enchantment functionality
                if (ctx.getTarget() instanceof LivingEntity target){

                    target.setFreezeTicks(ctx.getEnchantmentLevel() * 70);
                }
    });
    public static Enchantment frostbite = null;



    // ------------------------------------------------------------------------------------------------------------------------








    private static final List<CustomEnchantment> CUSTOM_ENCHANTMENTS = List.of(FROSTBITE);

    public static List<CustomEnchantment> getCustomEnchantments() {
        return CUSTOM_ENCHANTMENTS;
    }



    // gets the CustomEnchantment object for the passed Enchantment object
    public static CustomEnchantment getCustomEnchantment(Enchantment enchantment){
        for (CustomEnchantment customEnchantment : CUSTOM_ENCHANTMENTS){
            if (customEnchantment.getKey().value().equals(enchantment.getKey().value())){
                return customEnchantment;
            }
        }

        return null;
    }






    // ------------------------------------------------------------------------------------------------------------------------


    // initialize all Enchantment constants in onEnable()
    public static void setCustomEnchants(){
        RegistryAccess registryAccess = RegistryAccess.registryAccess();


        // set all static Enchantment type vars for access in other classes
        frostbite = registryAccess.getRegistry(RegistryKey.ENCHANTMENT).get(FROSTBITE.getKey());
    }
}
