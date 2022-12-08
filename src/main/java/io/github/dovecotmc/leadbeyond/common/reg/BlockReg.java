package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.util.entry.RegistryEntry;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.Constants;
import io.github.dovecotmc.leadbeyond.common.block.*;
import io.github.dovecotmc.leadbeyond.common.item.LBItemGroup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.Shapes;

public class BlockReg {
    public static final RegistryEntry<TicketVendorBlock> TICKET_VENDOR = LeadBeyond.REGISTRATE.get().block("ticket_vendor", TicketVendorBlock::new)
            .item().properties(settings -> settings.tab(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion())
            .addLayer(() -> RenderType::translucent)
            .register();
    public static final RegistryEntry<TurnstileBlock> TURNSTILE = LeadBeyond.REGISTRATE.get().block("turnstile", TurnstileBlock::new)
            .item().properties(settings -> settings.tab(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion())
            .register();
    public static final RegistryEntry<LBSeatBlock> RZ_SEAT = LeadBeyond.REGISTRATE.get().block("rz_seat", LBSeatBlock::new)
            .item().properties(settings -> settings.tab(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL).noOcclusion())
            .register();
    public static final RegistryEntry<HorizontalCVSBlock> YZ_SEAT2 = LeadBeyond.REGISTRATE.get().block("yz_seat2", settings ->
            new HorizontalCVSBlock(settings, state -> switch (state.getValue(HorizontalCVSBlock.FACING)) {
                default -> Shapes.block();
                case NORTH, SOUTH -> Constants.YZ_NS_SHAPE;
                case WEST, EAST -> Constants.YZ_EW_SHAPE;
            }))
            .item().properties(settings -> settings.tab(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL).noOcclusion())
            .register();
    public static final RegistryEntry<HorizontalCVSBlock> PASSWAY = LeadBeyond.REGISTRATE.get().block("passway", settings ->
            new HorizontalCVSBlock(settings, state -> switch (state.getValue(HorizontalCVSBlock.FACING)) {
                default -> Shapes.block();
                case NORTH, SOUTH -> Constants.PASSWAY_NS_SHAPE;
                case WEST, EAST -> Constants.PASSWAY_EW_SHAPE;
            }))
            .item().properties(settings -> settings.tab(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion())
            .register();
    public static final RegistryEntry<DirAwareHorizontalCVSBlock> YZ_TABLE = LeadBeyond.REGISTRATE.get().block("yz_table", settings ->
            new DirAwareHorizontalCVSBlock(settings, state -> switch (state.getValue(HorizontalCVSBlock.FACING)) {
                default -> Shapes.block();
                case NORTH -> Block.box(2, 14, 2, 14, 15, 16);
                case SOUTH -> Block.box(2, 14, 0, 14, 15, 14);
                case EAST -> Block.box(0, 14, 2, 14, 15, 14);
                case WEST -> Block.box(2, 14, 2, 16, 15, 14);
            }))
            .item().properties(settings -> settings.tab(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion())
            .register();
    /*
    public static final RegistryEntry<SlidingDoorBlock> ESCAPEDOOR = LeadBeyond.REGISTRATE.get().block("escapedoor", SlidingDoorBlock::new)
            .transform(BuilderTransformers.slidingDoor("escape"))
            //.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
            .properties(settings -> BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.TERRACOTTA_CYAN).sound(SoundType.NETHERITE_BLOCK).noOcclusion())
            .blockEntity(SlidingDoorTileEntity::new).renderer(() -> SlidingDoorRenderer::new).build()
            .register();
     */

    public static void initialize() {
    }
}
