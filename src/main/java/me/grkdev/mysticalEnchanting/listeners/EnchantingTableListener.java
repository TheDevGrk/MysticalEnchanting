package me.grkdev.mysticalEnchanting.listeners;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import io.papermc.paper.registry.tag.Tag;
import me.grkdev.mysticalEnchanting.data.CustomItems;
import me.grkdev.mysticalEnchanting.data.EnchantmentRequirements;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.block.Block;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class EnchantingTableListener implements Listener {
    private static final Map<UUID, int[]> playerOfferLvls = new HashMap<>();
    private static final Map<UUID, Map<Enchantment, Integer>[]> playerAvailableEnchants = new HashMap<>();
    private static final Registry<Enchantment> enchantments = RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT);
    private static final Tag<Enchantment> treasureEnchantments = enchantments.getTag(EnchantmentTagKeys.TREASURE);


    @EventHandler
    public static void onPrepareEnchant(PrepareItemEnchantEvent event){
        final Random random = new Random(event.getEnchanter().getEnchantmentSeed());

        EnchantmentOffer[] offers = event.getOffers();
        ItemStack item = event.getItem();


        // check that offers and item aren't null
        if (offers.length < 3 || offers[0] == null || offers[1] == null || offers[2] == null){
            return;
        }







        // calculate stats based on surroundings
        int fire = 0;
        int wild = 0;
        int earth = 0;
        int air = 0;
        int water = 0;
        int soul = 0;


        int baseModifier = 0; // used to modify the base level of each enchantment slot
        int bookshelves = 0;


        Location loc1 = event.getInventory().getLocation().add(2, 2, 2);
        Location loc2 = event.getInventory().getLocation().subtract(2, 1, 2);

        // get every block in 5x5x3 volume
        for(int x = loc2.getBlockX(); x <= loc1.getBlockX(); x++) {
            for(int z = loc2.getBlockZ(); z <= loc1.getBlockZ(); z++) {
                for(int y = loc2.getBlockY(); y <= loc1.getBlockY(); y++) {



                    Block block = event.getEnchanter().getWorld().getBlockAt(x, y, z);

                    // check books
                    if (block.getType() == Material.CHISELED_BOOKSHELF){
                        ChiseledBookshelf bookshelf = (ChiseledBookshelf) block.getState();

                        for (ItemStack i : bookshelf.getInventory().getContents()){
                            if (i != null){
                                PersistentDataContainer pdc = i.getItemMeta().getPersistentDataContainer();

                                fire += pdc.getOrDefault(CustomItems.fireKey, PersistentDataType.INTEGER, 0);
                                wild += pdc.getOrDefault(CustomItems.wildKey, PersistentDataType.INTEGER, 0);
                                earth += pdc.getOrDefault(CustomItems.earthKey, PersistentDataType.INTEGER, 0);
                                air += pdc.getOrDefault(CustomItems.airKey, PersistentDataType.INTEGER, 0);
                                water += pdc.getOrDefault(CustomItems.waterKey, PersistentDataType.INTEGER, 0);
                                soul += pdc.getOrDefault(CustomItems.soulKey, PersistentDataType.INTEGER, 0);

                            }
                        }

                    }

                    // ! blocks should be able to decrease the level required to enchant items
                    else if (block.getType() == Material.AMETHYST_BLOCK){
                        bookshelves -= 2;
                    }

                    else if (block.getType() == Material.BOOKSHELF && bookshelves < 15){ // ! keep at end of if statements to ensure bookshelves are the last modifier
                        bookshelves++;
                    }
                }
            }
        }


        // all of the custom stats have a range of 0-100
        fire = Math.clamp(fire, 0, 100);
        wild = Math.clamp(wild, 0, 100);
        earth = Math.clamp(earth, 0, 100);
        air = Math.clamp(air, 0, 100);
        water = Math.clamp(water, 0, 100);
        soul = Math.clamp(soul, 0, 100);


        // store all of the stats calculate above in a hash map so that they can be easily passed to the requirementsMet function
        HashMap<String, Integer> stats = new HashMap<>();

        stats.put("fire", fire);
        stats.put("wild", wild);
        stats.put("earth", earth);
        stats.put("air", air);
        stats.put("water", water);
        stats.put("soul", soul);



        // Step 1: calculate the enchantment levels available in each slot (mostly based off of vanilla mechanic)
        // may look confusing but is based off of the wiki

        double base = random.nextInt(1, 9) + Math.floor((double) bookshelves / 2) + ((bookshelves > 0) ? random.nextInt(0, bookshelves) : 0);


        base += baseModifier; // custom logic


        // calculate enchantment level for each slot
        int topLvl = (int) Math.floor(Math.max(base / 3, 1));
        int midLvl = (int) Math.max(Math.floor((base * 2) / 3 + 1), 1);
        int bottomLvl = (int) Math.max(Math.floor(Math.max(base, bookshelves * 2)), 1);


        // set the green level number shown on the right side of each slot (amount of levels needed to enchant using that slot)
        offers[0].setCost(topLvl);
        offers[1].setCost(midLvl);
        offers[2].setCost(bottomLvl);


        // add modifiers to each slot level based off of enchantability and a random 15% change
        // this creates the modified level for each slot which is used to determine which enchants are available to be picked
        // and at what level to have these enchants

        topLvl += 1
                + random.nextInt(event.getItem().getData(DataComponentTypes.ENCHANTABLE).value() / 4 + 1)
                + random.nextInt(event.getItem().getData(DataComponentTypes.ENCHANTABLE).value() / 4 + 1);

        topLvl = Math.round((float) topLvl * (1 + (random.nextFloat() + random.nextFloat() - 1) * .15f));


        midLvl += 1
                + random.nextInt(event.getItem().getData(DataComponentTypes.ENCHANTABLE).value() / 4 + 1)
                + random.nextInt(event.getItem().getData(DataComponentTypes.ENCHANTABLE).value() / 4 + 1);

        midLvl = Math.round((float) midLvl * (1 + (random.nextFloat() + random.nextFloat() - 1) * .15f));


        bottomLvl += 1
                + random.nextInt(event.getItem().getData(DataComponentTypes.ENCHANTABLE).value() / 4 + 1)
                + random.nextInt(event.getItem().getData(DataComponentTypes.ENCHANTABLE).value() / 4 + 1);

        bottomLvl = Math.round((float) bottomLvl * (1 + (random.nextFloat() + random.nextFloat() - 1) * .15f));




        // Step 2: Find which enchantments are able to be put in the etable (based off of vanilla / minecraft wiki with some custom requirements)


        // store the available enchants for each slot (each with dif modified lvl) separately
        LinkedHashMap<Enchantment, Integer> topAvailableEnchantments = new LinkedHashMap<>();
        LinkedHashMap<Enchantment, Integer> midAvailableEnchantments = new LinkedHashMap<>();
        LinkedHashMap<Enchantment, Integer> bottomAvailableEnchantments = new LinkedHashMap<>();

        // weights to be used when selecting an enchant for each slot
        int topWeights = 0;
        int midWeights = 0;
        int bottomWeights = 0;


        // check each enchant to see if it is available for each slot
        for (Enchantment enchantment : enchantments){
            HashMap<String, int[]> statRequirements = EnchantmentRequirements.valueOf(enchantment.getKey().value().toUpperCase()).getStatRequirements();


            // treasure enchants are those that are only found in structures or from villagers
            // also need to check out custom stat requirements (the elemental type stats above)
            if (enchantment.canEnchantItem(item)
                    && !treasureEnchantments.contains(TypedKey.create(RegistryKey.ENCHANTMENT, enchantment.getKey()))
                    && requirementsMet(statRequirements, stats)){


                // add enchantment to whatever slots it works in and count up the weights for each slot
                topWeights = getWeightsAndAssignEnchantment(topLvl, topAvailableEnchantments, topWeights, enchantment);

                midWeights = getWeightsAndAssignEnchantment(midLvl, midAvailableEnchantments, midWeights, enchantment);

                bottomWeights = getWeightsAndAssignEnchantment(bottomLvl, bottomAvailableEnchantments, bottomWeights, enchantment);
            }
        }





        // Step 3: Pick what enchantments to put in each slot
        // based on vanilla mechanics (from wiki) https://minecraft.wiki/w/Enchanting_table_mechanics


        // pick a random number from 0 - the total of all the weights for that slot
        // then subtract the weight of each enchantment from the list IN ORDER and when one makes it negative, pick that enchant
        // this gives each enchantment a (enchant weight) / (total weight) chance of being selected and is how enchants are selected in vanilla

        // top slot
        int randomWeight = random.nextInt(0, topWeights);
        for (Enchantment enchantment : topAvailableEnchantments.keySet()){
            randomWeight -= enchantment.getWeight();

            if (randomWeight < 0){
                offers[0].setEnchantment(enchantment);
                offers[0].setEnchantmentLevel(topAvailableEnchantments.get(enchantment));

                break;
            }
        }

        // middle slot
        randomWeight = random.nextInt(0, midWeights);
        for (Enchantment enchantment : midAvailableEnchantments.keySet()){
            randomWeight -= enchantment.getWeight();

            if (randomWeight < 0){
                offers[1].setEnchantment(enchantment);
                offers[1].setEnchantmentLevel(midAvailableEnchantments.get(enchantment));

                break;
            }
        }

        // bottom slot
        randomWeight = random.nextInt(0, bottomWeights);
        for (Enchantment enchantment : bottomAvailableEnchantments.keySet()){
            randomWeight -= enchantment.getWeight();

            if (randomWeight < 0){
                offers[2].setEnchantment(enchantment);
                offers[2].setEnchantmentLevel(bottomAvailableEnchantments.get(enchantment));

                break;
            }
        }



        // ------------------------------------------------------------------------------------------------------------------


        // assign data to be used in other event (when player actually enchants their item)
        int[] modifiedLvls = {topLvl, midLvl, bottomLvl};


        Map<Enchantment, Integer>[] availableEnchants = new LinkedHashMap[3];
        availableEnchants[0] = topAvailableEnchantments;
        availableEnchants[1] = midAvailableEnchantments;
        availableEnchants[2] = bottomAvailableEnchantments;


        playerOfferLvls.put(event.getEnchanter().getUniqueId(), modifiedLvls);
        playerAvailableEnchants.put(event.getEnchanter().getUniqueId(), availableEnchants);

    }




    private static int getWeightsAndAssignEnchantment(int modifiedLvl, HashMap<Enchantment, Integer> availableEnchantments, int weights, Enchantment enchantment) {
        if (enchantment.getMinModifiedCost(enchantment.getStartLevel()) <= modifiedLvl && enchantment.getMaxModifiedCost(enchantment.getMaxLevel()) >= modifiedLvl){
            // find the highest level of the enchantment whose min cost is less than the modified cost of the slot
            int lvl = enchantment.getStartLevel();
            for (int i = enchantment.getStartLevel(); i <= enchantment.getMaxLevel(); i++){
                if (enchantment.getMinModifiedCost(i) <= modifiedLvl && enchantment.getMaxModifiedCost(i) >= modifiedLvl){
                    lvl = i;
                }
            }

            availableEnchantments.put(enchantment, lvl);
            weights += enchantment.getWeight();
        }
        return weights;
    }



    private static boolean requirementsMet(HashMap<String, int[]> statRequirements, HashMap<String, Integer> stats){
        return (statRequirements.get("fire")[0] <= stats.get("fire") && statRequirements.get("fire")[1] >= stats.get("fire")
                && statRequirements.get("wild")[0] <= stats.get("wild") && statRequirements.get("wild")[1] >= stats.get("wild")
                && statRequirements.get("earth")[0] <= stats.get("earth") && statRequirements.get("earth")[1] >= stats.get("earth")
                && statRequirements.get("air")[0] <= stats.get("air") && statRequirements.get("air")[1] >= stats.get("air")
                && statRequirements.get("water")[0] <= stats.get("water") && statRequirements.get("water")[1] >= stats.get("water")
                && statRequirements.get("soul")[0] <= stats.get("soul") && statRequirements.get("soul")[1] >= stats.get("soul"));
    }



    // ----------------------------------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------------------------------


    @EventHandler
    public static void onCompleteEnchant(EnchantItemEvent event){
        final Random random = new Random(event.getEnchanter().getEnchantmentSeed());


        // remove whatever enchants vanilla intended to add
        event.getEnchantsToAdd().clear();

        // get the available enchant list and the modified level (not the one shown in the etable) from the previous event
        Map<Enchantment, Integer> availableEnchantments = playerAvailableEnchants.get(event.getEnchanter().getUniqueId())[event.whichButton()];
        int modifiedLvl = playerOfferLvls.get(event.getEnchanter().getUniqueId())[event.whichButton()];

        // add the enchantment shown in the etable to the item
        event.getEnchantsToAdd().put(event.getEnchantmentHint(), event.getLevelHint());




        // add any bonus enchants (logic based off of wiki)
        while (random.nextFloat(50) < (modifiedLvl + 1)){
            int weights = 0;


            // find conflicting enchants
            List<Enchantment> enchantmentsToRemove = new ArrayList<>();

            for (Enchantment enchantment : event.getEnchantsToAdd().keySet()){
                for (Enchantment availableEnchantment : availableEnchantments.keySet()){

                    if (availableEnchantment.conflictsWith(enchantment) || availableEnchantment.equals(enchantment)){
                        enchantmentsToRemove.add(availableEnchantment);
                        break;
                    }

                    // count up weights of the not removed enchants
                    weights += availableEnchantment.getWeight();
                }
            }

            // actually remove conflicting enchants - need this to avoid ConcurrentModificationError
            for (Enchantment enchantment : enchantmentsToRemove){
                availableEnchantments.remove(enchantment);
            }


            if (weights == 0 || availableEnchantments.isEmpty()){
                break;
            }




            // select an enchant to add to the list of enchants to add
            int randomWeight = random.nextInt(0, weights);
            for (Enchantment enchantment : availableEnchantments.keySet()){
                randomWeight -= enchantment.getWeight();

                if (randomWeight < 0){
                    // find the highest level of the enchantment whose min cost is less than the modified cost of the slot
                    int lvl = 0;
                    for (int i = enchantment.getStartLevel(); i <= enchantment.getMaxLevel(); i++){
                        if (enchantment.getMinModifiedCost(i) <= modifiedLvl){
                            lvl = i;
                            continue;
                        }

                        break;
                    }

                    event.getEnchantsToAdd().put(enchantment, lvl);

                    break;
                }
            }


            modifiedLvl /= 2;
        }
    }


    // ----------------------------------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------------------------------


    @EventHandler
    public static void onCloseEnchantingTable(InventoryCloseEvent event){
        // clean up hash maps when closing etable
        if (event.getInventory().getType() == InventoryType.ENCHANTING){
            playerOfferLvls.remove(event.getPlayer().getUniqueId());
            playerAvailableEnchants.remove(event.getPlayer().getUniqueId());
        }
    }



}
