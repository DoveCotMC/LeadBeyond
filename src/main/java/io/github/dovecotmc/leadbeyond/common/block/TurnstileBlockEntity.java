package io.github.dovecotmc.leadbeyond.common.block;

import io.github.dovecotmc.leadbeyond.common.reg.BlockEntityReg;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TurnstileBlockEntity extends BlockEntity {
    private int timer;
    public boolean exit;

    public TurnstileBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityReg.TURNSTILE.get(), pos, state);
        this.timer = 0;
        this.exit = false;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.timer = nbt.getInt("timer");
        this.exit = nbt.getBoolean("exit");
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("timer", this.timer);
        nbt.putBoolean("exit", this.exit);
        super.writeNbt(nbt);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TurnstileBlockEntity be) {
        if (world.isClient()) return;
        if (be.timer > 0) {
            if (!state.get(TurnstileBlock.OPEN)) {
                world.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1f, 1f);
                world.setBlockState(pos, state.with(TurnstileBlock.OPEN, true));
            }
            be.timer--;
        } else {
            if (state.get(TurnstileBlock.OPEN)) {
                world.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1f, 1f);
                world.setBlockState(pos, state.with(TurnstileBlock.OPEN, false));
            }
        }
        be.markDirty();
    }
}
