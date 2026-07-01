/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.liminal.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.liminal.block.entity.WindowBlockEntity;
import net.mcreator.liminal.block.entity.PowerBlockBlockEntity;
import net.mcreator.liminal.LiminalMod;

@EventBusSubscriber
public class LiminalModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, LiminalMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WindowBlockEntity>> WINDOW = register("window", LiminalModBlocks.WINDOW, WindowBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PowerBlockBlockEntity>> POWER_BLOCK = register("power_block", LiminalModBlocks.POWER_BLOCK, PowerBlockBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, WINDOW.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, POWER_BLOCK.get(), SidedInvWrapper::new);
	}
}