package io.github.dovecotmc.leadbeyond;

import io.github.dovecotmc.leadbeyond.common.reg.BlockReg;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class LeadBeyondClient {
    public static void clientSetup(final FMLClientSetupEvent event) {
        RenderLayers.setRenderLayer(BlockReg.TICKET_VENDOR.get(), RenderLayer.getTranslucent());
    }
}
