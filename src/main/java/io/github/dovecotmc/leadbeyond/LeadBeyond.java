package io.github.dovecotmc.leadbeyond;

import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

@Mod(LeadBeyond.MODID)
public class LeadBeyond {
    public static final String MODID = "lead_beyond";
    public static final Logger LOGGER = LoggerFactory.getLogger("Lead Beyond");

    public LeadBeyond() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemReg.ITEM.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
    }

    public static @NotNull Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    public static boolean isDevelopment() {
        return FMLEnvironment.naming.equals("mcp");
    }

    public static <T> void onlyDev(Consumer<T> consumer, T obj) {
        if (isDevelopment()) consumer.accept(obj);
    }
}
