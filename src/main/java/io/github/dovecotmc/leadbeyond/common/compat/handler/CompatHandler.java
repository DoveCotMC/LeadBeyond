package io.github.dovecotmc.leadbeyond.common.compat.handler;

import io.github.dovecotmc.leadbeyond.common.compat.impl.sittable.SittableCompat;
import net.minecraftforge.fml.ModList;

public class CompatHandler {
    public static void initialize() {
        if (loaded("sittable")) SittableCompat.init();
    }

    private static boolean loaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
