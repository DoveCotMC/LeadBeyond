package io.github.dovecotmc.leadbeyond.common.block;

import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CustomVoxelShapeBlock extends Block {
    private final Function<BlockState, VoxelShape> shape;

    public CustomVoxelShapeBlock(Properties settings, Function<BlockState, VoxelShape> shape) {
        super(settings);
        this.shape = shape;
    }

    public CustomVoxelShapeBlock(Properties settings, VoxelShape shape) {
        super(settings);
        this.shape = (state -> shape);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shape.apply(state);
    }
}
