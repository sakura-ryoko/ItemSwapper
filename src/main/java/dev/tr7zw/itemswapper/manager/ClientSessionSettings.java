package dev.tr7zw.itemswapper.manager;

import lombok.*;
import net.minecraft.world.item.*;

@Getter
@Setter
public class ClientSessionSettings {
    private boolean enableShulkers = false;
    private boolean enableRefill = false;
    private boolean modDisabled = false;
    private boolean disabledByPlayer = false;
    private boolean bypassAccepted = false;
    private Item lastItem;
    private ItemGroupManager.Page lastPage;

    public void reset() {
        enableShulkers = false;
        enableRefill = false;
        modDisabled = false;
        disabledByPlayer = false;
        bypassAccepted = false;
    }
}
