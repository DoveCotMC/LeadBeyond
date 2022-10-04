package io.github.dovecotmc.leadbeyond.common.reg;

import com.mojang.datafixers.DSL;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.block.TurnstileBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityReg {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
            LeadBeyond.MODID);

    public static final RegistryObject<BlockEntityType<TurnstileBlockEntity>> TURNSTILE = BLOCK_ENTITIES.register("turnstile", () ->
            BlockEntityType.Builder.create(TurnstileBlockEntity::new, BlockReg.TURNSTILE.get()).build(DSL.remainderType()));
}
