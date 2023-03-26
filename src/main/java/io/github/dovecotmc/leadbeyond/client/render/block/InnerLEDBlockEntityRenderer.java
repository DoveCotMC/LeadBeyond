package io.github.dovecotmc.leadbeyond.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dovecotmc.leadbeyond.common.block.InnerLEDBlockEntity;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public class InnerLEDBlockEntityRenderer implements BlockEntityRenderer<InnerLEDBlockEntity> {
    private final Font font;

    public InnerLEDBlockEntityRenderer(BlockEntityRendererProvider.@NotNull Context ctx) {
        this.font = ctx.getFont();
    }

    @Override
    public void render(
            @NotNull InnerLEDBlockEntity be,
            float tickDelta,
            @NotNull PoseStack matrices,
            @NotNull MultiBufferSource vertex,
            int light,
            int overlay
    ) {

    }
}
