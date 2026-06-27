package tjallo.disableelytraflight.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique
    private long lastMessageTime = 0;

    @Unique
    private static final long MESSAGE_COOLDOWN_MS = 2000;

    @Inject(method = "canGlide", at = @At("HEAD"), cancellable = true)
    private void disableElytraFlight(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        Level level = self.level();

        if (level.dimension() == Level.END) {
            return;
        }

        if (self instanceof Player player) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastMessageTime > MESSAGE_COOLDOWN_MS) {
                if (!player.onGround() && !player.isInWater()) {
                    player.displayClientMessage(
                            Component.literal("§c§lElytra flight is disabled outside The End!"),
                            true
                    );
                    lastMessageTime = currentTime;
                }
            }
        }

        cir.setReturnValue(false);
    }
}
