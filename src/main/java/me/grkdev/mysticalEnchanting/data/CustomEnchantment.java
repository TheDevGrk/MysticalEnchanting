package me.grkdev.mysticalEnchanting.data;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.tag.TagKey;
import me.grkdev.mysticalEnchanting.enchantmentTriggerContexts.BlockBreakContext;
import me.grkdev.mysticalEnchanting.enchantmentTriggerContexts.DamageContext;
import me.grkdev.mysticalEnchanting.enchantmentTriggerContexts.MeleeContext;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemType;

import java.util.function.Consumer;


public class CustomEnchantment {


    private final TypedKey<Enchantment> key;
    private final Consumer<EnchantmentRegistryEntry.Builder> builder;
    private final TagKey<ItemType> supportedItemType;

    private final Consumer<BlockBreakContext> onBlockBreak;
    private final Consumer<DamageContext> onDamage;
    private final Consumer<MeleeContext> onMeleeHit;

    public CustomEnchantment(TypedKey<Enchantment> key,
                             Consumer<EnchantmentRegistryEntry.Builder> builder,
                             TagKey<ItemType> supportedItemType, Consumer<BlockBreakContext> onBlockBreak, Consumer<DamageContext> onDamage,
                             Consumer<MeleeContext> onMeleeHit){
        this.key = key;
        this.builder = builder;
        this.supportedItemType = supportedItemType;

        this.onBlockBreak = onBlockBreak;
        this.onDamage = onDamage;
        this.onMeleeHit = onMeleeHit;
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




    public void onMelee(MeleeContext ctx){
        if (this.onMeleeHit != null){
            this.onMeleeHit.accept(ctx);
        }
    }

    public void onBlockBreak(BlockBreakContext ctx){
        if (this.onBlockBreak != null){
            this.onBlockBreak.accept(ctx);
        }
    }

    public void onDamage(DamageContext ctx){
        if (this.onDamage != null){
            this.onDamage.accept(ctx);
        }
    }


}
