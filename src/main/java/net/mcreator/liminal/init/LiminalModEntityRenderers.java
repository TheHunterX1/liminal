/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.liminal.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.liminal.client.renderer.RedactedEntityRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class LiminalModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(LiminalModEntities.REDACTED_ENTITY.get(), RedactedEntityRenderer::new);
	}
}