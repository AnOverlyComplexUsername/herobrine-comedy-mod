package com.jaysydney.Custom;
import com.jaysydney.Custom.blocks.ElivFumoBlock;
import com.jaysydney.Custom.blocks.NetherReactorCoreBlock;
import com.jaysydney.HerobrineComedyMod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final NetherReactorCoreBlock NETHER_REACTOR_CORE = (NetherReactorCoreBlock) register(
            "nether_reactor_core",
            NetherReactorCoreBlock::new,
            AbstractBlock.Settings.create().hardness(3f).resistance(6f).
                    sounds(BlockSoundGroup.IRON).luminance(NetherReactorCoreBlock::getLuminance),
            true
    );

    public static final ElivFumoBlock ELIV_FUMO_BLOCK = (ElivFumoBlock) register(
      "eliv_fumo_block",
            ElivFumoBlock::new,
            AbstractBlock.Settings.create().hardness(1f).resistance(1f).
                    sounds(BlockSoundGroup.WOOL).nonOpaque().burnable(),
            true
    );




    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(HerobrineComedyMod.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HerobrineComedyMod.MOD_ID, name));
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MOD_ITEMS_GROUP_KEY).register((itemGroup) -> {

        itemGroup.add(ModBlocks.NETHER_REACTOR_CORE.asItem());
        itemGroup.add(ModBlocks.ELIV_FUMO_BLOCK.asItem());
    });}

}