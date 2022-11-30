package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.block.TurnstileBlockEntity;

public class BlockEntityReg {
	public static final BlockEntityEntry<TurnstileBlockEntity> TURNSTILE = LeadBeyond.REGISTRATE
			.blockEntity("turnstile", TurnstileBlockEntity::new)
			.validBlock(BlockReg.TURNSTILE)
			.register();

	public static void initialize() {
	}
}
