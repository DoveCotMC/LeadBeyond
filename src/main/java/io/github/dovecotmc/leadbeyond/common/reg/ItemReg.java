package io.github.dovecotmc.leadbeyond.common.reg;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.item.CardItem;
import io.github.dovecotmc.leadbeyond.common.item.LBItemGroup;
import io.github.dovecotmc.leadbeyond.common.item.TicketItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemReg {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS,
            LeadBeyond.MODID);

    public static final RegistryObject<Item> CARD = ITEM.register("card", () ->
            new CardItem(new Item.Settings().group(LBItemGroup.INSTANCE).maxCount(1)));
    public static final RegistryObject<Item> TICKET = ITEM.register("ticket", () ->
            new TicketItem(new Item.Settings().group(LBItemGroup.INSTANCE).maxCount(1)));
}
