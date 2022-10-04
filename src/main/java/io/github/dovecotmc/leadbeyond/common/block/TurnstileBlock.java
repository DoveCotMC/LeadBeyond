package io.github.dovecotmc.leadbeyond.common.block;

import com.simibubi.create.content.contraptions.wrench.IWrenchable;
import com.simibubi.create.content.contraptions.wrench.WrenchItem;
import io.github.dovecotmc.leadbeyond.common.item.CardItem;
import io.github.dovecotmc.leadbeyond.common.item.TicketItem;
import io.github.dovecotmc.leadbeyond.common.reg.BlockEntityReg;
import io.github.dovecotmc.leadbeyond.common.reg.SoundReg;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TurnstileBlock extends BlockWithEntity
        implements IWrenchable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    public TurnstileBlock(Settings arg) {
        super(arg);
        setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(OPEN, false));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING).add(OPEN);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(OPEN, false);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;
        ItemStack stack = player.getStackInHand(hand);
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TurnstileBlockEntity turnstile) {
            if (!turnstile.exit) {
                if (stack.getItem() instanceof TicketItem) {
                    NbtCompound nbt = stack.getOrCreateSubNbt("ticketInfo");
                    if (!nbt.getBoolean("used")) {
                        nbt.putBoolean("used", true);
                        turnstile.setTimer(60);
                        world.playSound(null, pos, SoundReg.BEEP_TURNSTILE.get(), SoundCategory.BLOCKS, 1f, 1f);
                        return ActionResult.SUCCESS;
                    }
                } else if (stack.getItem() instanceof CardItem) {
                    NbtCompound nbt = stack.getOrCreateSubNbt("cardInfo");
                    if (nbt.getLong("money") >= 100) {
                        nbt.putLong("money", nbt.getLong("money") - 100);
                        turnstile.setTimer(60);
                        world.playSound(null, pos, SoundReg.BEEP_TURNSTILE.get(), SoundCategory.BLOCKS, 1f, 1f);
                        return ActionResult.SUCCESS;
                    }
                }
            } else if (!(stack.getItem() instanceof WrenchItem)) {
                turnstile.setTimer(60);
                world.playSound(null, pos, SoundReg.BEEP_TURNSTILE.get(), SoundCategory.BLOCKS, 1f, 1f);
                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public ActionResult onWrenched(BlockState state, @NotNull ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            BlockEntity be = world.getBlockEntity(context.getBlockPos());
            if (be instanceof TurnstileBlockEntity turnstile) {
                turnstile.exit = !turnstile.exit;
                player.sendMessage(new TranslatableText("message.lead_beyond.set_exit." + turnstile.exit), true);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        boolean open = state.get(OPEN);
        VoxelShape nsBarrier = Block.createCuboidShape(0, 0, 6, 16, 24, 10);
        VoxelShape ewBarrier = Block.createCuboidShape(6, 0, 0, 10, 24, 16);
        return switch (dir) {
            case SOUTH -> open ? VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 3.5, 14, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 14, 16))
                    : VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 3.5, 24, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 24, 16),
                    nsBarrier);
            case NORTH -> open ? VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 1, 14, 16),
                    Block.createCuboidShape(12.5, 0, 0, 16, 14, 16))
                    : VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 1, 24, 16),
                    Block.createCuboidShape(12.5, 0, 0, 16, 24, 16),
                    nsBarrier);
            case EAST -> open ? VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 14, 1),
                    Block.createCuboidShape(0, 0, 12.5, 16, 14, 16))
                    : VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 24, 1),
                    Block.createCuboidShape(0, 0, 12.5, 16, 24, 16),
                    ewBarrier);
            case WEST -> open ? VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 14, 3.5),
                    Block.createCuboidShape(0, 0, 15, 16, 14, 16))
                    : VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 24, 3.5),
                    Block.createCuboidShape(0, 0, 15, 16, 24, 16),
                    ewBarrier);
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        return switch (dir) {
            case SOUTH -> VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 3.5, 14, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 14, 16));
            case NORTH -> VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 1, 14, 16),
                    Block.createCuboidShape(12.5, 0, 0, 16, 14, 16));
            case EAST -> VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 14, 1),
                    Block.createCuboidShape(0, 0, 12.5, 16, 14, 16));
            case WEST -> VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 14, 3.5),
                    Block.createCuboidShape(0, 0, 15, 16, 14, 16));
            default -> VoxelShapes.fullCube();
        };
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TurnstileBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockEntityReg.TURNSTILE.get(), TurnstileBlockEntity::tick);
    }
}
