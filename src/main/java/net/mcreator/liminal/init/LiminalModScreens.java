/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.liminal.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.liminal.client.gui.PowerCellGUIScreen;

@EventBusSubscriber(Dist.CLIENT)
public class LiminalModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(LiminalModMenus.POWER_CELL_GUI.get(), PowerCellGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}