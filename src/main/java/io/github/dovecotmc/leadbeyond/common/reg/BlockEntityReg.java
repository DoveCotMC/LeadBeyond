package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.block.InnerLEDBlockEntity;
import io.github.dovecotmc.leadbeyond.common.block.TurnstileBlockEntity;

public class BlockEntityReg {
    public static final BlockEntityEntry<TurnstileBlockEntity> TURNSTILE = LeadBeyond.REGISTRATE.get().blockEntity("turnstile", TurnstileBlockEntity::new)
            .validBlock(BlockReg.TURNSTILE)
            .register();
    public static final BlockEntityEntry<InnerLEDBlockEntity> INNER_LED = LeadBeyond.REGISTRATE.get().blockEntity("in_led", InnerLEDBlockEntity::new)
            .validBlock(BlockReg.TURNSTILE)
            .register();

    public static void initialize() {
    }
}
