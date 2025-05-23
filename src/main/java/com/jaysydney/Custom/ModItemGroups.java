package com.jaysydney.Custom;

import com.jaysydney.HerobrineComedyMod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> MOD_ITEMS_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(HerobrineComedyMod.MOD_ID, "mod_items"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SUSPICIOUS_SUBSTANCE))
            .displayName(Text.translatable("itemGroup.herobrinecomedymod"))
            .build();

    public static void intialize() {
        Registry.register(Registries.ITEM_GROUP, MOD_ITEMS_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(MOD_ITEMS_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.SUSPICIOUS_SUBSTANCE);
        });
        HerobrineComedyMod.LOGGER.info("Registering item groups for: " + HerobrineComedyMod.MOD_ID);
    }
}
