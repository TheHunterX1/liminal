/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.liminal.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mcreator.liminal.LiminalMod;

@EventBusSubscriber
public class LiminalModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LiminalMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(LiminalModBlocks.HOTEL_CARPET.get().asItem());
			tabData.accept(LiminalModBlocks.HOTEL_DOOR_DOOR.get().asItem());
			tabData.accept(LiminalModBlocks.WINDOW.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(LiminalModItems.REDACTED_ENTITY_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(LiminalModItems.POWER_CELL.get());
			tabData.accept(LiminalModItems.POWER_CELL_1.get());
			tabData.accept(LiminalModItems.POWER_CELL_2.get());
			tabData.accept(LiminalModItems.POWER_CELL_3.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(LiminalModBlocks.POWER_BLOCK.get().asItem());
		}
	}
}