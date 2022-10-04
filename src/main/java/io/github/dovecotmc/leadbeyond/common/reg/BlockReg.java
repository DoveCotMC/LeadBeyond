package io.github.dovecotmc.leadbeyond.common.reg;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.block.TicketVendorBlock;
import io.github.dovecotmc.leadbeyond.common.block.TurnstileBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockReg {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            LeadBeyond.MODID);

    public static final RegistryObject<Block> TICKET_VENDOR = BLOCKS.register("ticket_vendor", () ->
            new TicketVendorBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));
    public static final RegistryObject<Block> TURNSTILE = BLOCKS.register("turnstile", () ->
            new TurnstileBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));
}
