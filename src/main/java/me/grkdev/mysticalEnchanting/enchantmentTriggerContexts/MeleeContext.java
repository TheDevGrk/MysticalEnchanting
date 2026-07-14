package me.grkdev.mysticalEnchanting.enchantmentTriggerContexts;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MeleeContext {

    private final Player attacker;
    private final Entity target;
    private final int enchantmentLevel;

    public MeleeContext(Player attacker, Entity target, int enchantmentLevel){
        this.attacker = attacker;
        this.target = target;
        this.enchantmentLevel = enchantmentLevel;
    }

    public Player getAttacker() {
        return attacker;
    }

    public Entity getTarget() {
        return target;
    }

    public int getEnchantmentLevel() {
        return enchantmentLevel;
    }
}
