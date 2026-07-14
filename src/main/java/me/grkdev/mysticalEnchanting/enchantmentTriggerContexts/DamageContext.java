package me.grkdev.mysticalEnchanting.enchantmentTriggerContexts;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;

public class DamageContext {
    private final Player player;
    private final DamageSource damageSource;
    private final int enchantmentLevel;

    public DamageContext(Player player, DamageSource damageSource, int enchantmentLevel) {
        this.player = player;
        this.damageSource = damageSource;
        this.enchantmentLevel = enchantmentLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public int getEnchantmentLevel() {
        return enchantmentLevel;
    }
}
