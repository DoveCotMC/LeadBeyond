package io.github.dovecotmc.leadbeyond.common.block;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InnerLEDBlockEntity extends BlockEntity {
    private @Nullable Component content;

    public InnerLEDBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        content = null;
    }

    public @Nullable Component getContent() {
        return content;
    }

    public void setContent(@Nullable Component content) {
        this.content = content;
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        String string = tag.getString("Content");
        if (!string.equals("")) {
            content = loadLine(string);
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        String string = Component.Serializer.toJson(content);
        tag.putString("Content", string);
    }

    private Component loadLine(String line) {
        Component component = this.deserializeTextSafe(line);
        if (this.level instanceof ServerLevel) {
            try {
                return ComponentUtils.updateForEntity(this.createCommandSourceStack(null), component, null, 0);
            } catch (CommandSyntaxException ignored) {
            }
        }
        return component;
    }

    private Component deserializeTextSafe(String text) {
        try {
            Component component = Component.Serializer.fromJson(text);
            if (component != null) {
                return component;
            }
        } catch (Exception ignored) {}
        return TextComponent.EMPTY;
    }

    public CommandSourceStack createCommandSourceStack(@javax.annotation.Nullable ServerPlayer player) {
        String string = player == null ? "LED" : player.getName().getString();
        Component component = player == null ? new TextComponent("LED") : player.getDisplayName();
        return new CommandSourceStack(
                CommandSource.NULL,
                Vec3.atCenterOf(this.worldPosition),
                Vec2.ZERO,
                (ServerLevel)this.level,
                2, string,
                component,
                this.level.getServer(),
                player
        );
    }
}
