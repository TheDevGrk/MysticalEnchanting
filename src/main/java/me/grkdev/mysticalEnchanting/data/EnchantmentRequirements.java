package me.grkdev.mysticalEnchanting.data;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Set;

// ! possible future improvement: change from int arrays to something to better hold the min max

public enum EnchantmentRequirements {
    // vanilla enchants
    AQUA_AFFINITY(makeStatRequirementsHashMap(new int[]{0, 3}, new int[]{2, 100}, new int[]{0, 100}, new int[]{3, 100}, new int[]{5, 100}, new int[]{0, 100})),
    BANE_OF_ARTHROPODS(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    BINDING_CURSE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    BLAST_PROTECTION(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    BREACH(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    CHANNELING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    DENSITY(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    DEPTH_STRIDER(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    EFFICIENCY(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    FEATHER_FALLING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    FIRE_ASPECT(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    FIRE_PROTECTION(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    FLAME(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    FORTUNE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    FROST_WALKER(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    IMPALING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    INFINITY(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    KNOCKBACK(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    LOOTING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    LOYALTY(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    LUCK_OF_THE_SEA(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    LUNGE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    LURE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    MENDING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    MULTISHOT(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    PIERCING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    POWER(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    PROJECTILE_PROTECTION(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    PROTECTION(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    PUNCH(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    QUICK_CHARGE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    RESPIRATION(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    RIPTIDE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    SHARPNESS(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    SILK_TOUCH(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    SMITE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    SOUL_SPEED(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    SWEEPING_EDGE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    SWIFT_SNEAK(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    THORNS(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    UNBREAKING(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    VANISHING_CURSE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),
    WIND_BURST(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),

    // custom enchants
    FROSTBITE(makeStatRequirementsHashMap(new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100}, new int[]{0, 100})),


    ;

    private final HashMap<String, int[]> statRequirements;

    private EnchantmentRequirements (HashMap<String, int[]> statRequirements){
        this.statRequirements = statRequirements;
    }

    public HashMap<String, int[]> getStatRequirements(){
        return statRequirements;
    }

    private static HashMap<String, int[]> makeStatRequirementsHashMap(int[] fireRange, int[] wildRange, int[] earthRange, int[] airRange, int[] waterRange, int[] soulRange){
        HashMap<String, int[]> map = new HashMap<String, int[]>();

        map.put("fire", fireRange);
        map.put("wild", wildRange);
        map.put("earth", earthRange);
        map.put("air", airRange);
        map.put("water", waterRange);
        map.put("soul", soulRange);

        return map;
    }
}
