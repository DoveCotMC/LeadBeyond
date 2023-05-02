package io.github.dovecotmc.leadbeyond.common.compat.impl.sittable;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import io.github.dovecotmc.leadbeyond.common.reg.BlockReg;
import net.minecraft.core.Vec3i;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;

public class SittableCompat {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new SittableCompat());
    }

    @SubscribeEvent
    public void onSittableRegister(SittableRegisterEvent e) {
        e.registerSittable(new SittableRegistry(BlockReg.RZ_SEAT.get(), (s, p, h) -> Optional.of(new Vec3(.5, .5, .5))));
        e.registerSittable(new SittableRegistry(BlockReg.YZ_SEAT2.get(), (s, p, h) -> {
            final Optional<Vec3> defaultVec = Optional.of(new Vec3(.75, .5, .75));
            if (h.isEmpty()) return defaultVec;
            final Vec3i r = h.get().getDirection().getNormal();
            if (r.getY() != 0) return defaultVec;
            return Optional.of(new Vec3(.5, .5, .5).add((r.getX() - r.getZ()) / 4.0, .0, (r.getZ() + r.getX()) / 4.0));
        }));
    }
}
