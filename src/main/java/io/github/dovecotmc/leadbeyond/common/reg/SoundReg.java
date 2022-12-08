package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.util.entry.RegistryEntry;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;

public class SoundReg {
    public static final RegistryEntry<SoundEvent> BEEP_TICKET_VENDOR = LeadBeyond.REGISTRATE.get().simple("beep_ticket_vendor", Registry.SOUND_EVENT_REGISTRY, () ->
            new SoundEvent(LeadBeyond.id("beep_ticket_vendor")));
    public static final RegistryEntry<SoundEvent> BEEP_TURNSTILE = LeadBeyond.REGISTRATE.get().simple("beep_turnstile", Registry.SOUND_EVENT_REGISTRY, () ->
            new SoundEvent(LeadBeyond.id("beep_turnstile")));
    public static final RegistryEntry<SoundEvent> TICKET_OUT = LeadBeyond.REGISTRATE.get().simple("ticket_out", Registry.SOUND_EVENT_REGISTRY, () ->
            new SoundEvent(LeadBeyond.id("ticket_out")));

    public static void initialize() {
    }
}
