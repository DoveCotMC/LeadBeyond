package io.github.dovecotmc.leadbeyond.common.item;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.reg.ItemReg;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class LBItemGroup {
	public static final ItemGroup INSTANCE = FabricItemGroupBuilder.create(LeadBeyond.id(LeadBeyond.MODID))
			.icon(() -> new ItemStack(ItemReg.TICKET.get())).build();

	public static void load() {
	}
}
