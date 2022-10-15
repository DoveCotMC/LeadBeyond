package io.github.dovecotmc.leadbeyond.common.reg;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.block.HorizontalCVSBlock;
import io.github.dovecotmc.leadbeyond.common.block.LBSeatBlock;
import io.github.dovecotmc.leadbeyond.common.block.TicketVendorBlock;
import io.github.dovecotmc.leadbeyond.common.block.TurnstileBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockReg {
    public static final VoxelShape yzNsShape = VoxelShapes.union(VoxelShapes.cuboid(0.125, 0, 0.5625, 0.1875, 0.25, 0.8125),
            VoxelShapes.cuboid(0.125, 0, 0.1875, 0.1875, 0.25, 0.4375),
            VoxelShapes.cuboid(0.125, 0, 0.1875, 0.1875, 0.25, 0.4375),
            VoxelShapes.cuboid(0.8125, 0, 0.1875, 0.875, 0.25, 0.4375),
            VoxelShapes.cuboid(0.8125, 0, 0.5625, 0.875, 0.25, 0.8125),
            VoxelShapes.cuboid(0, 0.25, 0, 1, 0.5, 1),
            VoxelShapes.cuboid(0, 0.5, 0.375, 0.5, 1.4375, 0.625),
            VoxelShapes.cuboid(0.53125, 0.5, 0.375, 1, 1.4375, 0.625));
    public static final VoxelShape yzEwShape = VoxelShapes.union(VoxelShapes.cuboid(0.1875, 0, 0.125, 0.4375, 0.25, 0.1875),
            VoxelShapes.cuboid(0.5625, 0, 0.125, 0.8125, 0.25, 0.1875),
            VoxelShapes.cuboid(0.5625, 0, 0.8125, 0.8125, 0.25, 0.875),
            VoxelShapes.cuboid(0.1875, 0, 0.8125, 0.4375, 0.25, 0.875),
            VoxelShapes.cuboid(0, 0.25, 0, 1, 0.5, 1),
            VoxelShapes.cuboid(0.375, 0.5, 0, 0.625, 1.4375, 0.5),
            VoxelShapes.cuboid(0.375, 0.5, 0.53125, 0.625, 1.4375, 1));

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            LeadBeyond.MODID);

    public static final RegistryObject<Block> TICKET_VENDOR = BLOCKS.register("ticket_vendor", () ->
            new TicketVendorBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));
    public static final RegistryObject<Block> TURNSTILE = BLOCKS.register("turnstile", () ->
            new TurnstileBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));
    public static final RegistryObject<Block> RZ_SEAT = BLOCKS.register("rz_seat", () ->
            new LBSeatBlock(AbstractBlock.Settings.copy(Blocks.BLUE_BED)));
    public static final RegistryObject<Block> YZ_SEAT2 = BLOCKS.register("yz_seat2", () ->
            new HorizontalCVSBlock(AbstractBlock.Settings.copy(Blocks.BLUE_BED), state -> switch (state.get(HorizontalCVSBlock.FACING)) {
                default -> VoxelShapes.fullCube();
                case NORTH, SOUTH -> yzNsShape;
                case WEST, EAST -> yzEwShape;
            }));


}
