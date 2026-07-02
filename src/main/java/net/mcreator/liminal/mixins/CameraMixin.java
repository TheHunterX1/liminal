package dev.luuuppi.remove_third_person.mixins;

import net.minecraft.client.CameraType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CameraType.class)
public class CameraMixin {
  @Inject(method = "cycle", at = @At("HEAD"), cancellable = true)
  private void disableViewCycle(CallbackInfoReturnable<CameraType> cir) {
    cir.setReturnValue((CameraType) (Object) this);
  }
}