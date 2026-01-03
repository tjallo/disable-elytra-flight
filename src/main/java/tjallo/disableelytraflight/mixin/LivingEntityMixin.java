package tjallo.disableelytraflight.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    /**
     * Prevents elytra flight by always returning false when checking
     * if an entity can start fall flying.
     */
    @Inject(method = "canGlide", at = @At("HEAD"), cancellable = true)
    private void disableElytraFlight(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}