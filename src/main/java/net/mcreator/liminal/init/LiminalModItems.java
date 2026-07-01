/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.liminal.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.BlockItem;

import net.mcreator.liminal.item.PowerCellItem;
import net.mcreator.liminal.item.PowerCell3Item;
import net.mcreator.liminal.item.PowerCell2Item;
import net.mcreator.liminal.item.PowerCell1Item;
import net.mcreator.liminal.LiminalMod;

import java.util.function.Function;

public class LiminalModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(LiminalMod.MODID);
	public static final DeferredItem<Item> HOTEL_CARPET;
	public static final DeferredItem<Item> HOTEL_WALL;
	public static final DeferredItem<Item> HOTEL_DOOR_DOOR;
	public static final DeferredItem<Item> WINDOW;
	public static final DeferredItem<Item> REDACTED_ENTITY_SPAWN_EGG;
	public static final DeferredItem<Item> POWER_CELL;
	public static final DeferredItem<Item> POWER_CELL_1;
	public static final DeferredItem<Item> POWER_CELL_2;
	public static final DeferredItem<Item> POWER_CELL_3;
	public static final DeferredItem<Item> POWER_BLOCK;
	public static final DeferredItem<Item> EXTRACTION_BLOCK;
	static {
		HOTEL_CARPET = block(LiminalModBlocks.HOTEL_CARPET);
		HOTEL_WALL = block(LiminalModBlocks.HOTEL_WALL);
		HOTEL_DOOR_DOOR = doubleBlock(LiminalModBlocks.HOTEL_DOOR_DOOR);
		WINDOW = block(LiminalModBlocks.WINDOW);
		REDACTED_ENTITY_SPAWN_EGG = register("redacted_entity_spawn_egg", properties -> new SpawnEggItem(LiminalModEntities.REDACTED_ENTITY.get(), properties));
		POWER_CELL = register("power_cell", PowerCellItem::new);
		POWER_CELL_1 = register("power_cell_1", PowerCell1Item::new);
		POWER_CELL_2 = register("power_cell_2", PowerCell2Item::new);
		POWER_CELL_3 = register("power_cell_3", PowerCell3Item::new);
		POWER_BLOCK = block(LiminalModBlocks.POWER_BLOCK);
		EXTRACTION_BLOCK = block(LiminalModBlocks.EXTRACTION_BLOCK);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block) {
		return doubleBlock(block, new Item.Properties());
	}

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new DoubleHighBlockItem(block.get(), prop), properties);
	}
}