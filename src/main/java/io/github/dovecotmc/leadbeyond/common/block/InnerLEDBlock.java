package io.github.dovecotmc.leadbeyond.common.block;

import io.github.dovecotmc.leadbeyond.common.reg.BlockEntityReg;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InnerLEDBlock extends DirAwareHorizontalCVSBlock implements EntityBlock {
    private static final VoxelShape NORTH_FACED_SHAPE = Shapes.box(-0.0625, 0.1875, 0.875, 1.0625, 0.4375, 1);
    private static final VoxelShape SOUTH_FACED_SHAPE = Shapes.box(-0.0625, 0.1875, 0, 1.0625, 0.4375, 0.125);
    private static final VoxelShape EAST_FACED_SHAPE = Shapes.box(0.875, 0.1875, -0.0625, 1, 0.4375, 1.0625);
    private static final VoxelShape WEST_FACED_SHAPE = Shapes.box(0, 0.1875, -0.0625, 0.125, 0.4375, 1.0625);

    public InnerLEDBlock(Properties settings) {
        super(settings, state -> {
            switch (state.getValue(HorizontalCVSBlock.FACING)) {
                case EAST -> {
                    return EAST_FACED_SHAPE;
                }
                case WEST -> {
                    return WEST_FACED_SHAPE;
                }
                case SOUTH -> {
                    return SOUTH_FACED_SHAPE;
                }
                case NORTH -> {
                    return NORTH_FACED_SHAPE;
                }
                default -> {
                    return Shapes.block();
                }
            }
        });
    }

    @Override
    public @NotNull InteractionResult use(
            @NotNull BlockState state,
            Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull InteractionHand hand,
            @NotNull BlockHitResult hit
    ) {
        if (!level.isClientSide()) {
            ItemStack handStack = player.getItemInHand(hand);
            if (handStack.is(Items.NAME_TAG)) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof InnerLEDBlockEntity led) {
                    if (handStack.hasCustomHoverName()) {
                        led.setContent(handStack.getHoverName());
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new InnerLEDBlockEntity(BlockEntityReg.INNER_LED.get(), pos, state);
    }
}
