package me.grkdev.mysticalEnchanting.enchantmentTriggerContexts;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockBreakContext {
    private final Player player;
    private final Block block;
    private final int enchantmentLevel;

    public BlockBreakContext(Player player, Block block, int enchantmentLevel) {
        this.player = player;
        this.block = block;
        this.enchantmentLevel = enchantmentLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public int getEnchantmentLevel() {
        return enchantmentLevel;
    }
}
