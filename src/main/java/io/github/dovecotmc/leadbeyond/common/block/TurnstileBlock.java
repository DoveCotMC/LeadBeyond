package io.github.dovecotmc.leadbeyond.common.block;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import io.github.dovecotmc.leadbeyond.common.item.CardItem;
import io.github.dovecotmc.leadbeyond.common.item.TicketItem;
import io.github.dovecotmc.leadbeyond.common.item.Ticketable;
import io.github.dovecotmc.leadbeyond.common.reg.BlockEntityReg;
import io.github.dovecotmc.leadbeyond.common.reg.SoundReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TurnstileBlock extends BaseEntityBlock
        implements IWrenchable {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public TurnstileBlock(Properties arg) {
        super(arg);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING).add(OPEN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(OPEN, false);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        ItemStack stack = player.getItemInHand(hand);
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TurnstileBlockEntity turnstile) {
            if (!turnstile.exit) {
                if (stack.getItem() instanceof TicketItem) {
                    CompoundTag nbt = stack.getOrCreateTagElement("ticketInfo");
                    if (!nbt.getBoolean("used")) {
                        nbt.putBoolean("used", true);
                        stack.getOrCreateTagElement("stationInfo").putBoolean("enteredStation", true);
                        turnstile.setTimer(60);
                        world.playSound(null, pos, SoundReg.BEEP_TURNSTILE.get(), SoundSource.BLOCKS, 1f, 1f);
                        return InteractionResult.SUCCESS;
                    }
                } else if (stack.getItem() instanceof CardItem) {
                    CompoundTag nbt = stack.getOrCreateTagElement("cardInfo");
                    if (nbt.getLong("money") >= 100) {
                        nbt.putLong("money", nbt.getLong("money") - 100);
                        stack.getOrCreateTagElement("stationInfo").putBoolean("enteredStation", true);
                        turnstile.setTimer(60);
                        world.playSound(null, pos, SoundReg.BEEP_TURNSTILE.get(), SoundSource.BLOCKS, 1f, 1f);
                        return InteractionResult.SUCCESS;
                    }
                }
            } else {
                if (stack.getItem() instanceof Ticketable) {
                    CompoundTag nbt = stack.getOrCreateTagElement("stationInfo");
                    if (nbt.getBoolean("enteredStation")) {
                        turnstile.setTimer(60);
                        nbt.putBoolean("enteredStation", false);
                        world.playSound(null, pos, SoundReg.BEEP_TURNSTILE.get(), SoundSource.BLOCKS, 1f, 1f);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, @NotNull UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        if (player != null) {
            BlockEntity be = world.getBlockEntity(context.getClickedPos());
            if (be instanceof TurnstileBlockEntity turnstile) {
                turnstile.exit = !turnstile.exit;
                player.displayClientMessage(new TranslatableComponent("message.lead_beyond.set_exit." + turnstile.exit), true);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);
        boolean open = state.getValue(OPEN);
        VoxelShape nsBarrier = Block.box(0, 0, 6, 16, 24, 10);
        VoxelShape ewBarrier = Block.box(6, 0, 0, 10, 24, 16);
        return switch (dir) {
            case SOUTH -> open ? Shapes.or(Block.box(0, 0, 0, 3.5, 14, 16),
                    Block.box(15, 0, 0, 16, 14, 16))
                    : Shapes.or(Block.box(0, 0, 0, 3.5, 24, 16),
                    Block.box(15, 0, 0, 16, 24, 16),
                    nsBarrier);
            case NORTH -> open ? Shapes.or(Block.box(0, 0, 0, 1, 14, 16),
                    Block.box(12.5, 0, 0, 16, 14, 16))
                    : Shapes.or(Block.box(0, 0, 0, 1, 24, 16),
                    Block.box(12.5, 0, 0, 16, 24, 16),
                    nsBarrier);
            case EAST -> open ? Shapes.or(Block.box(0, 0, 0, 16, 14, 1),
                    Block.box(0, 0, 12.5, 16, 14, 16))
                    : Shapes.or(Block.box(0, 0, 0, 16, 24, 1),
                    Block.box(0, 0, 12.5, 16, 24, 16),
                    ewBarrier);
            case WEST -> open ? Shapes.or(Block.box(0, 0, 0, 16, 14, 3.5),
                    Block.box(0, 0, 15, 16, 14, 16))
                    : Shapes.or(Block.box(0, 0, 0, 16, 24, 3.5),
                    Block.box(0, 0, 15, 16, 24, 16),
                    ewBarrier);
            default -> Shapes.block();
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ctx) {
        Direction dir = state.getValue(FACING);
        return switch (dir) {
            case SOUTH -> Shapes.or(Block.box(0, 0, 0, 3.5, 14, 16),
                    Block.box(15, 0, 0, 16, 14, 16));
            case NORTH -> Shapes.or(Block.box(0, 0, 0, 1, 14, 16),
                    Block.box(12.5, 0, 0, 16, 14, 16));
            case EAST -> Shapes.or(Block.box(0, 0, 0, 16, 14, 1),
                    Block.box(0, 0, 12.5, 16, 14, 16));
            case WEST -> Shapes.or(Block.box(0, 0, 0, 16, 14, 3.5),
                    Block.box(0, 0, 15, 16, 14, 16));
            default -> Shapes.block();
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TurnstileBlockEntity(BlockEntityReg.TURNSTILE.get(), pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, BlockEntityReg.TURNSTILE.get(), TurnstileBlockEntity::tick);
    }
}
