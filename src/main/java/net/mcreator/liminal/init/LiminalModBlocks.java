/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.liminal.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.liminal.block.*;
import net.mcreator.liminal.LiminalMod;

import java.util.function.Function;

public class LiminalModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(LiminalMod.MODID);
	public static final DeferredBlock<Block> HOTEL_CARPET;
	public static final DeferredBlock<Block> HOTEL_WALL;
	public static final DeferredBlock<Block> HOTEL_DOOR_DOOR;
	public static final DeferredBlock<Block> WINDOW;
	public static final DeferredBlock<Block> POWER_BLOCK;
	public static final DeferredBlock<Block> EXTRACTION_BLOCK;
	static {
		HOTEL_CARPET = register("hotel_carpet", HotelCarpetBlock::new);
		HOTEL_WALL = register("hotel_wall", HotelWallBlock::new);
		HOTEL_DOOR_DOOR = register("hotel_door_door", HotelDoorDoorBlock::new);
		WINDOW = register("window", WindowBlock::new);
		POWER_BLOCK = register("power_block", PowerBlockBlock::new);
		EXTRACTION_BLOCK = register("extraction_block", ExtractionBlockBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}