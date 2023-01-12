package io.github.dovecotmc.leadbeyond.common.util.phys;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class Pos2i {
    private int x, y;

    public Pos2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BlockPos toBlockPos(int height) {
        return new BlockPos(x, height, y);
    }

    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", x);
        tag.putInt("y", y);
        return tag;
    }

    public static void writeToTag(CompoundTag tag, Pos2i pos, String name) {
        CompoundTag sub = new CompoundTag();
        sub.putInt("x", pos.x);
        sub.putInt("y", pos.y);
        tag.put(name, tag);
    }

    public static Pos2i readFromTag(CompoundTag tag, String name) {
        CompoundTag sub = tag.getCompound(name);
        int x = sub.getInt("x");
        int y = sub.getInt("y");
        return new Pos2i(x, y);
    }
}
