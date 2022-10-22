package io.github.dovecotmc.leadbeyond;

import io.github.dovecotmc.leadbeyond.common.config.LBConfig;
import io.github.dovecotmc.leadbeyond.common.reg.BlockEntityReg;
import io.github.dovecotmc.leadbeyond.common.reg.BlockReg;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import io.github.dovecotmc.leadbeyond.common.reg.SoundReg;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
    public static LBConfig CONFIG;

    public LeadBeyond() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemReg.ITEM.register(eventBus);
        BlockReg.BLOCKS.register(eventBus);
        SoundReg.SOUNDS.register(eventBus);
        BlockEntityReg.BLOCK_ENTITIES.register(eventBus);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(LeadBeyondClient::clientSetup);
        AutoConfig.register(LBConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(LBConfig.class).getConfig();
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Initialized.");
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
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
