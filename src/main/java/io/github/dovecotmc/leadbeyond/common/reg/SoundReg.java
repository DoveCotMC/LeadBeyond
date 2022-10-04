package io.github.dovecotmc.leadbeyond.common.reg;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import net.minecraft.sound.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundReg {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            LeadBeyond.MODID);

    public static final RegistryObject<SoundEvent> BEEP_TICKET_VENDOR = SOUNDS.register("beep_ticket_vendor", () ->
            new SoundEvent(LeadBeyond.id("beep_ticket_vendor")));
    public static final RegistryObject<SoundEvent> BEEP_TURNSTILE = SOUNDS.register("beep_turnstile", () ->
            new SoundEvent(LeadBeyond.id("beep_turnstile")));
    public static final RegistryObject<SoundEvent> TICKET_OUT = SOUNDS.register("ticket_out", () ->
            new SoundEvent(LeadBeyond.id("ticket_out")));
}
