package me.grkdev.mysticalEnchanting;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import me.grkdev.mysticalEnchanting.data.CustomEnchantment;
import me.grkdev.mysticalEnchanting.data.CustomEnchants;
import org.bukkit.enchantments.Enchantment;

import java.util.HashSet;
import java.util.Set;

class MysticalEnchantingBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(final BootstrapContext context) {
        final LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();

        Set<TypedKey<Enchantment>> customEnchantsSet = new HashSet<>();
        for (CustomEnchantment ench : CustomEnchants.getCustomEnchants()) {
            customEnchantsSet.add(ench.getKey());
        }



            manager.registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {

            for (CustomEnchantment ench : CustomEnchants.getCustomEnchants()){
                event.registry().register(
                        ench.getKey(),
                        builder -> {
                            ench.getBuilder().accept(builder);
                            builder.supportedItems(event.getOrCreateTag(ench.getSupportedItemType()));

                        }
                );


            }

        }));

        manager.registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.ENCHANTMENT), event -> {
            event.registrar().addToTag(EnchantmentTagKeys.IN_ENCHANTING_TABLE, customEnchantsSet);
            event.registrar().addToTag(EnchantmentTagKeys.NON_TREASURE, customEnchantsSet);
        });




    }
}
