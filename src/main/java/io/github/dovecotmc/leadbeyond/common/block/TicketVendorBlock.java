package io.github.dovecotmc.leadbeyond.common.block;

import com.simibubi.create.content.contraptions.wrench.IWrenchable;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.Constants;
import io.github.dovecotmc.leadbeyond.common.item.CardItem;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import io.github.dovecotmc.leadbeyond.common.reg.SoundReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class TicketVendorBlock extends HorizontalFacingBlock
        implements IWrenchable {
    public TicketVendorBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        VoxelShape base = VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
        return switch (dir) {
            case NORTH -> VoxelShapes.union(base,
                    Block.createCuboidShape(0, 4, 12, 16, 16, 16));
            case SOUTH -> VoxelShapes.union(base,
                    Block.createCuboidShape(0, 4, 0, 16, 16, 4));
            case WEST -> VoxelShapes.union(base,
                    Block.createCuboidShape(12, 4, 0, 16, 16, 16));
            case EAST -> VoxelShapes.union(base,
                    Block.createCuboidShape(0, 4, 0, 4, 16, 16));
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;
        if (player.getStackInHand(hand).isOf(Constants.getCurrencyItem())) {
            if (!player.isCreative()) player.getStackInHand(hand).decrement(1);
            if (getStackInOtherHand(player, hand).getItem() instanceof CardItem) {
                world.playSound(null, pos, SoundReg.BEEP_TICKET_VENDOR.get(), SoundCategory.BLOCKS, 1f, 1f);
                NbtCompound nbt = getStackInOtherHand(player, hand).getOrCreateSubNbt("cardInfo");
                nbt.putLong("money", nbt.getLong("money") + 100);
            } else {
                world.playSound(null, pos, SoundReg.TICKET_OUT.get(), SoundCategory.BLOCKS, 1f, 1f);
                player.giveItemStack(ItemReg.TICKET.get().getDefaultStack().copy());
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    private static ItemStack getStackInOtherHand(PlayerEntity player, @NotNull Hand hand) {
        return switch (hand) {
            case MAIN_HAND -> player.getOffHandStack();
            case OFF_HAND -> player.getMainHandStack();
        };
    }
}
