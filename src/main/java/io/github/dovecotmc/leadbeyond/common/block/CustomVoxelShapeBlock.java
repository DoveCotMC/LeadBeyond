package io.github.dovecotmc.leadbeyond.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.function.Function;

public class CustomVoxelShapeBlock extends Block {
    private final Function<BlockState, VoxelShape> shape;

    public CustomVoxelShapeBlock(Settings settings, Function<BlockState, VoxelShape> shape) {
        super(settings);
        this.shape = shape;
    }

    public CustomVoxelShapeBlock(Settings settings, VoxelShape shape) {
        super(settings);
        this.shape = (state -> shape);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape.apply(state);
    }
}
