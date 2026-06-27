package tjallo.disableelytraflight;

//? if fabric {
import net.fabricmc.api.ModInitializer;

public class DisableElytraFlightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DisableElytraFlightCommon.init();
    }
}
//?}
