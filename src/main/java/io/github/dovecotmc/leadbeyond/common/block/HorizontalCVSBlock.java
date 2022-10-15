package io.github.dovecotmc.leadbeyond.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

import java.util.function.Function;

public class HorizontalCVSBlock extends CustomVoxelShapeBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public HorizontalCVSBlock(Settings settings, Function<BlockState, VoxelShape> shape) {
        super(settings, shape);
        setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH));
    }

    public HorizontalCVSBlock(Settings settings, VoxelShape shape) {
        this(settings, (state -> shape));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
    }
}
