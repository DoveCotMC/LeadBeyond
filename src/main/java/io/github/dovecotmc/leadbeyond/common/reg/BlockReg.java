package io.github.dovecotmc.leadbeyond.common.reg;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.Constants;
import io.github.dovecotmc.leadbeyond.common.block.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.shape.VoxelShapes;
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
    public static final RegistryObject<Block> RZ_SEAT = BLOCKS.register("rz_seat", () ->
            new LBSeatBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL)));
    public static final RegistryObject<Block> YZ_SEAT2 = BLOCKS.register("yz_seat2", () ->
            new HorizontalCVSBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL), state -> switch (state.get(HorizontalCVSBlock.FACING)) {
                default -> VoxelShapes.fullCube();
                case NORTH, SOUTH -> Constants.YZ_NS_SHAPE;
                case WEST, EAST -> Constants.YZ_EW_SHAPE;
            }));
    public static final RegistryObject<Block> PASSWAY = BLOCKS.register("passway", () ->
            new HorizontalCVSBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK), state -> switch (state.get(HorizontalCVSBlock.FACING)) {
                default -> VoxelShapes.fullCube();
                case NORTH, SOUTH -> Constants.PASSWAY_NS_SHAPE;
                case WEST, EAST -> Constants.PASSWAY_EW_SHAPE;
            }));
    public static final RegistryObject<Block> YZ_TABLE = BLOCKS.register("yz_table", () ->
            new DirAwareHorizontalCVSBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), state -> switch (state.get(HorizontalCVSBlock.FACING)) {
                default -> VoxelShapes.fullCube();
                case NORTH -> Block.createCuboidShape(2, 14, 2, 14, 15, 16);
                case SOUTH -> Block.createCuboidShape(2, 14, 0, 14, 15, 14);
                case EAST -> Block.createCuboidShape(0, 14, 2, 14, 15, 14);
                case WEST -> Block.createCuboidShape(2, 14, 2, 16, 15, 14);
            }));
}
