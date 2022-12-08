package io.github.dovecotmc.leadbeyond.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TurnstileBlockEntity extends BlockEntity {
    private int timer;
    public boolean exit;

    public TurnstileBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.timer = 0;
        this.exit = false;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public void load(CompoundTag nbt) {
        this.timer = nbt.getInt("timer");
        this.exit = nbt.getBoolean("exit");
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("timer", this.timer);
        nbt.putBoolean("exit", this.exit);
        super.saveAdditional(nbt);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, TurnstileBlockEntity be) {
        if (world.isClientSide()) return;
        if (be.timer > 0) {
            if (!state.getValue(TurnstileBlock.OPEN)) {
                world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1f, 1f);
                world.setBlockAndUpdate(pos, state.setValue(TurnstileBlock.OPEN, true));
            }
            be.timer--;
        } else {
            if (state.getValue(TurnstileBlock.OPEN)) {
                world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1f, 1f);
                world.setBlockAndUpdate(pos, state.setValue(TurnstileBlock.OPEN, false));
            }
        }
        be.setChanged();
    }
}
