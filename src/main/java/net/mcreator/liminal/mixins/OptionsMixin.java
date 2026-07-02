package dev.luuuppi.remove_third_person.mixins;

import net.minecraft.client.CameraType;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionsMixin {
  @Inject(method = "setCameraType", at = @At("HEAD"), cancellable = true)
  private void disableManualPerspectiveChange(CameraType pPointOfView, CallbackInfo ci) {
    ci.cancel();
  }
}