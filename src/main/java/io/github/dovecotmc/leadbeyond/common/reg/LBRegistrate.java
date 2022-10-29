package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LBRegistrate extends AbstractRegistrate<LBRegistrate> {
    /**
     * Construct a new Registrate for the given mod ID.
     *
     * @param modid The mod ID for which objects will be registered
     */
    protected LBRegistrate(String modid) {
        super(modid);
    }

    public static NonNullSupplier<LBRegistrate> lazy(String modid) {
        return NonNullSupplier.lazy(() ->
                new LBRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus())
        );
    }
}
