package io.github.dovecotmc.leadbeyond.common.block;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import io.github.dovecotmc.leadbeyond.common.Constants;
import io.github.dovecotmc.leadbeyond.common.item.CardItem;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import io.github.dovecotmc.leadbeyond.common.reg.SoundReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TicketVendorBlock extends HorizontalDirectionalBlock
        implements IWrenchable {
    public TicketVendorBlock(Properties settings) {
        super(settings);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ctx) {
        Direction dir = state.getValue(FACING);
        VoxelShape base = Shapes.box(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
        return switch (dir) {
            case NORTH -> Shapes.or(base,
                    Block.box(0, 4, 12, 16, 16, 16));
            case SOUTH -> Shapes.or(base,
                    Block.box(0, 4, 0, 16, 16, 4));
            case WEST -> Shapes.or(base,
                    Block.box(12, 4, 0, 16, 16, 16));
            case EAST -> Shapes.or(base,
                    Block.box(0, 4, 0, 4, 16, 16));
            default -> Shapes.block();
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        if (player.getItemInHand(hand).is(Constants.getCurrencyItem())) {
            if (!player.isCreative()) player.getItemInHand(hand).shrink(1);
            if (getStackInOtherHand(player, hand).getItem() instanceof CardItem) {
                world.playSound(null, pos, SoundReg.BEEP_TICKET_VENDOR.get(), SoundSource.BLOCKS, 1f, 1f);
                CompoundTag nbt = getStackInOtherHand(player, hand).getOrCreateTagElement("cardInfo");
                nbt.putLong("money", nbt.getLong("money") + 100);
            } else {
                world.playSound(null, pos, SoundReg.TICKET_OUT.get(), SoundSource.BLOCKS, 1f, 1f);
                player.addItem(ItemReg.TICKET.get().getDefaultInstance().copy());
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    private static ItemStack getStackInOtherHand(Player player, @NotNull InteractionHand hand) {
        return switch (hand) {
            case MAIN_HAND -> player.getOffhandItem();
            case OFF_HAND -> player.getMainHandItem();
        };
    }
}
