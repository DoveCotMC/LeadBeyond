package io.github.dovecotmc.leadbeyond.common.block;

import com.simibubi.create.content.contraptions.components.actors.SeatBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;

public class LBSeatBlock extends SeatBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private final VoxelShape northShape;
    private final VoxelShape southShape;
    private final VoxelShape westShape;
    private final VoxelShape eastShape;

    public LBSeatBlock(@NotNull Settings settings) {
        super(settings.nonOpaque(), DyeColor.BLUE, true);
        // Preventing lag
        this.northShape = makeNorthShape();
        this.southShape = makeSouthShape();
        this.westShape = makeWestShape();
        this.eastShape = makeEastShape();
        setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> stateManager) {
        super.appendProperties(stateManager.add(FACING));
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
                .with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, false);
    }

    @Override
    public @NotNull VoxelShape getOutlineShape(@NotNull BlockState state, @NotNull BlockView world, @NotNull BlockPos pos, @NotNull ShapeContext ctx) {
        return switch (state.get(FACING)) {
            default -> VoxelShapes.fullCube();
            case NORTH -> northShape;
            case SOUTH -> southShape;
            case WEST -> westShape;
            case EAST -> eastShape;
        };
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockView world, @NotNull BlockPos pos, @NotNull ShapeContext ctx) {
        return getOutlineShape(state, world, pos, ctx);
    }

    // Generated using Blockbench
    private static VoxelShape makeNorthShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0625, 0.375, 0.875, 0.25, 0.625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0, 0.1875, 0.875, 0.0625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0625, 0.375, 0.1875, 0.25, 0.625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.1875, 0.1875, 0.0625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.4375, 0.8125, 0.0625, 0.5625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.25, 0, 0.875, 0.5, 0.75));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.375, 0.578125, 0.875, 1.375, 0.828125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0.1875, 0.125, 0.6875, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0.03125, 0.125, 0.4375, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.6875, 0.0625, 0.125, 0.8125, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.6875, 0.0625, 1, 0.8125, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.25, 0.1875, 1, 0.6875, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.25, 0.03125, 1, 0.4375, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.6875, 0.8125, 0.84375, 1.125, 0.84375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.5625, 0.80625, 0.15625, 1.078125, 0.8375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.84375, 0.5625, 0.80625, 0.90625, 1.078125, 0.8375));
        return shape;
    }

    // Generated using Blockbench
    private static VoxelShape makeSouthShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0625, 0.375, 0.1875, 0.25, 0.625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.1875, 0.1875, 0.0625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0625, 0.375, 0.875, 0.25, 0.625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0, 0.1875, 0.875, 0.0625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.4375, 0.8125, 0.0625, 0.5625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.25, 0.25, 0.875, 0.5, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.375, 0.171875, 0.875, 1.375, 0.421875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.25, 0.1875, 1, 0.6875, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.25, 0.8125, 1, 0.4375, 0.96875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.6875, 0.1875, 1, 0.8125, 0.9375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.6875, 0.1875, 0.125, 0.8125, 0.9375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0.1875, 0.125, 0.6875, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0.8125, 0.125, 0.4375, 0.96875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.6875, 0.15625, 0.84375, 1.125, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.84375, 0.5625, 0.1625, 0.90625, 1.078125, 0.19375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.5625, 0.1625, 0.15625, 1.078125, 0.19375));
        return shape;
    }

    // Generate using Blockbench
    private static VoxelShape makeEastShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.8125, 0.625, 0.25, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.8125, 0.8125, 0.0625, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.125, 0.625, 0.25, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.8125, 0.0625, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.1875, 0.5625, 0.0625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.25, 0.125, 1, 0.5, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.171875, 0.375, 0.125, 0.421875, 1.375, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.25, 0, 0.8125, 0.6875, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.25, 0, 0.96875, 0.4375, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.6875, 0, 0.9375, 0.8125, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.6875, 0.875, 0.9375, 0.8125, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.25, 0.875, 0.8125, 0.6875, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.25, 0.875, 0.96875, 0.4375, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.6875, 0.15625, 0.1875, 1.125, 0.84375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1625, 0.5625, 0.09375, 0.19375, 1.078125, 0.15625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1625, 0.5625, 0.84375, 0.19375, 1.078125, 0.90625));
        return shape;
    }

    // Generate using Blockbench
    private static VoxelShape makeWestShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.125, 0.625, 0.25, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.8125, 0.0625, 0.1875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.8125, 0.625, 0.25, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.8125, 0.8125, 0.0625, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.1875, 0.5625, 0.0625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0.125, 0.75, 0.5, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.578125, 0.375, 0.125, 0.828125, 1.375, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.25, 0.875, 0.8125, 0.6875, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.03125, 0.25, 0.875, 0.1875, 0.4375, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.6875, 0.875, 0.8125, 0.8125, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.6875, 0, 0.8125, 0.8125, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.25, 0, 0.8125, 0.6875, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.03125, 0.25, 0, 0.1875, 0.4375, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.6875, 0.15625, 0.84375, 1.125, 0.84375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.80625, 0.5625, 0.84375, 0.8375, 1.078125, 0.90625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.80625, 0.5625, 0.09375, 0.8375, 1.078125, 0.15625));
        return shape;
    }
}
