package io.github.dovecotmc.leadbeyond;

import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.dovecotmc.leadbeyond.common.config.LBConfig;
import io.github.dovecotmc.leadbeyond.common.item.LBItemGroup;
import io.github.dovecotmc.leadbeyond.common.reg.BlockEntityReg;
import io.github.dovecotmc.leadbeyond.common.reg.BlockReg;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import io.github.dovecotmc.leadbeyond.common.reg.LBRegistrate;
import io.github.dovecotmc.leadbeyond.common.reg.SoundReg;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class LeadBeyond implements ModInitializer {
    public static final String MODID = "lead_beyond";
    public static final Logger LOGGER = LoggerFactory.getLogger("Lead Beyond");
    public static LBConfig CONFIG;
    public static LBRegistrate REGISTRATE = LBRegistrate.create(MODID);

	@Override
	public void onInitialize() {
		LBItemGroup.load();

		BlockEntityReg.initialize();
        BlockReg.initialize();
        ItemReg.initialize();
        SoundReg.initialize();
		REGISTRATE.register();
		AutoConfig.register(LBConfig.class, Toml4jConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(LBConfig.class).getConfig();
		LOGGER.info("Initialized.");
	}

    public static @NotNull Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    public static boolean isDevelopment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static <T> void onlyDev(Consumer<T> consumer, T obj) {
        if (isDevelopment()) consumer.accept(obj);
    }
}
