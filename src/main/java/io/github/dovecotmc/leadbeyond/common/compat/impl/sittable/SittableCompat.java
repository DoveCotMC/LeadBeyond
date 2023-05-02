package io.github.dovecotmc.leadbeyond.common.compat.impl.sittable;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import io.github.dovecotmc.leadbeyond.common.reg.BlockReg;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SittableCompat {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new SittableCompat());
    }

    @SubscribeEvent
    public void onSittableRegister(SittableRegisterEvent e) {
        e.registerSittable(new SittableRegistry(BlockReg.RZ_SEAT.get(), (s, p) -> .5));
    }
}
