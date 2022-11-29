package io.github.dovecotmc.leadbeyond.common.reg;

import com.simibubi.create.content.curiosities.deco.SlidingDoorBlock;
import com.simibubi.create.content.curiosities.deco.SlidingDoorRenderer;
import com.simibubi.create.content.curiosities.deco.SlidingDoorTileEntity;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.tterrag.registrate.util.entry.RegistryEntry;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.Constants;
import io.github.dovecotmc.leadbeyond.common.block.DirAwareHorizontalCVSBlock;
import io.github.dovecotmc.leadbeyond.common.block.HorizontalCVSBlock;
import io.github.dovecotmc.leadbeyond.common.block.LBSeatBlock;
import io.github.dovecotmc.leadbeyond.common.block.TicketVendorBlock;
import io.github.dovecotmc.leadbeyond.common.block.TurnstileBlock;
import io.github.dovecotmc.leadbeyond.common.item.LBItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.shape.VoxelShapes;

public class BlockReg {
	public static final RegistryEntry<TicketVendorBlock> TICKET_VENDOR = LeadBeyond.REGISTRATE
			.block("ticket_vendor", TicketVendorBlock::new)
			.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque())
			.addLayer(() -> RenderLayer::getTranslucent)
			.register();
	public static final RegistryEntry<TurnstileBlock> TURNSTILE = LeadBeyond.REGISTRATE
			.block("turnstile", TurnstileBlock::new)
			.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque())
			.register();
	public static final RegistryEntry<LBSeatBlock> RZ_SEAT = LeadBeyond.REGISTRATE
			.block("rz_seat", LBSeatBlock::new)
			.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.copy(Blocks.BLUE_WOOL).nonOpaque())
			.register();
	public static final RegistryEntry<HorizontalCVSBlock> YZ_SEAT2 = LeadBeyond.REGISTRATE
			.block("yz_seat2", settings -> new HorizontalCVSBlock(settings,
					state -> switch (state.get(HorizontalCVSBlock.FACING)) {
						default -> VoxelShapes.fullCube();
						case NORTH, SOUTH -> Constants.YZ_NS_SHAPE;
						case WEST, EAST -> Constants.YZ_EW_SHAPE;
					}))
			.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.copy(Blocks.BLUE_WOOL).nonOpaque())
			.register();
	public static final RegistryEntry<HorizontalCVSBlock> PASSWAY = LeadBeyond.REGISTRATE
			.block("passway", settings -> new HorizontalCVSBlock(settings,
					state -> switch (state.get(HorizontalCVSBlock.FACING)) {
						default -> VoxelShapes.fullCube();
						case NORTH, SOUTH -> Constants.PASSWAY_NS_SHAPE;
						case WEST, EAST -> Constants.PASSWAY_EW_SHAPE;
					}))
			.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque())
			.register();
	public static final RegistryEntry<DirAwareHorizontalCVSBlock> YZ_TABLE = LeadBeyond.REGISTRATE
			.block("yz_table", settings -> new DirAwareHorizontalCVSBlock(settings,
					state -> switch (state.get(HorizontalCVSBlock.FACING)) {
						default -> VoxelShapes.fullCube();
						case NORTH -> Block.createCuboidShape(2, 14, 2, 14, 15, 16);
						case SOUTH -> Block.createCuboidShape(2, 14, 0, 14, 15, 14);
						case EAST -> Block.createCuboidShape(0, 14, 2, 14, 15, 14);
						case WEST -> Block.createCuboidShape(2, 14, 2, 16, 15, 14);
					}))
			.item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque())
			.register();
	public static final RegistryEntry<SlidingDoorBlock> ESCAPEDOOR = LeadBeyond.REGISTRATE
			.block("escapedoor", SlidingDoorBlock::new)
			.transform(BuilderTransformers.slidingDoor("escape"))
			// .item().properties(settings -> settings.group(LBItemGroup.INSTANCE)).build()
			.properties(settings -> AbstractBlock.Settings.of(Material.METAL)
					.mapColor(MapColor.TERRACOTTA_CYAN).sounds(BlockSoundGroup.NETHERITE)
					.nonOpaque())
			.blockEntity(SlidingDoorTileEntity::new).renderer(() -> SlidingDoorRenderer::new).build()
			.register();

	public static void initialize() {
	}
}
