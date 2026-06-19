package me.grkdev.mysticalEnchanting;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.event.RegistryEvents;
import me.grkdev.mysticalEnchanting.data.CustomEnchantment;
import me.grkdev.mysticalEnchanting.data.CustomEnchants;

class MysticalEnchantingBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(final BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {

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


    }
}
