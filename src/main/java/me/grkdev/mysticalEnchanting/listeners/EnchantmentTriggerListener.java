package me.grkdev.mysticalEnchanting.listeners;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import me.grkdev.mysticalEnchanting.data.CustomEnchantments;
import me.grkdev.mysticalEnchanting.enchantmentTriggerContexts.BlockBreakContext;
import me.grkdev.mysticalEnchanting.enchantmentTriggerContexts.MeleeContext;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class EnchantmentTriggerListener implements Listener {

    @EventHandler
    public static void onBreakBlock(BlockBreakEvent event){
        for (Enchantment enchantment : event.getPlayer().getInventory().getItemInMainHand().getEnchantments().keySet()){
            event.getPlayer().sendMessage(Component.text(enchantment.getKey().asString()));

            if (enchantment.getKey().asString().startsWith("mystical_enchanting")){
                Objects.requireNonNull(CustomEnchantments.getCustomEnchantment(enchantment))
                        .onBlockBreak(new BlockBreakContext(event.getPlayer(), event.getBlock(), event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(enchantment)));
            }
        }
    }

    @EventHandler
    public static void onPlayerAttack(PrePlayerAttackEntityEvent event){
        if (!event.willAttack()){
            return;
        }


        for (Enchantment enchantment : event.getPlayer().getInventory().getItemInMainHand().getEnchantments().keySet()){
            event.getPlayer().sendMessage(Component.text(enchantment.getKey().asString()));

            if (enchantment.getKey().asString().startsWith("mystical_enchanting")){
                Objects.requireNonNull(CustomEnchantments.getCustomEnchantment(enchantment))
                        .onMelee(new MeleeContext(event.getPlayer(), event.getAttacked(), event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(enchantment)));
            }
        }
    }



}
