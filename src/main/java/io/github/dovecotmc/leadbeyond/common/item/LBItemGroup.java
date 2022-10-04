package io.github.dovecotmc.leadbeyond.common.item;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class LBItemGroup {
    public static final ItemGroup INSTANCE = new ItemGroup(LeadBeyond.MODID) {
        @Override
        public ItemStack createIcon() {
            return ItemReg.TICKET.get().getDefaultStack();
        }
    };
}
