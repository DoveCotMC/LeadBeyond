package io.github.dovecotmc.leadbeyond.common.item;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LBItemGroup {
    public static final CreativeModeTab INSTANCE = new CreativeModeTab(LeadBeyond.MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return ItemReg.TICKET.get().getDefaultInstance();
        }
    };
}
