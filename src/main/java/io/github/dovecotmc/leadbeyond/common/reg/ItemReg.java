package io.github.dovecotmc.leadbeyond.common.reg;

import com.tterrag.registrate.util.entry.RegistryEntry;
import io.github.dovecotmc.leadbeyond.LeadBeyond;
import io.github.dovecotmc.leadbeyond.common.item.CardItem;
import io.github.dovecotmc.leadbeyond.common.item.LBItemGroup;
import io.github.dovecotmc.leadbeyond.common.item.TicketItem;

public class ItemReg {
    public static final RegistryEntry<CardItem> CARD = LeadBeyond.REGISTRATE.get().item("card", CardItem::new)
            .properties(settings -> settings.group(LBItemGroup.INSTANCE).maxCount(1))
            .register();
    public static final RegistryEntry<TicketItem> TICKET = LeadBeyond.REGISTRATE.get().item("ticket", TicketItem::new)
            .properties(settings -> settings.group(LBItemGroup.INSTANCE).maxCount(1))
            .register();

    public static void initialize() {
    }
}
