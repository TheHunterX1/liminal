package net.mcreator.liminal.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class HotelWallBlock extends Block {
	public HotelWallBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(-1, 3600000));
	}
}