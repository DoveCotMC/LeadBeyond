package io.github.dovecotmc.leadbeyond.common.item;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class TicketItem extends Item
        implements Ticketable {
    public TicketItem(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTagElement("ticketInfo").putBoolean("used", false);
        stack.getOrCreateTagElement("stationInfo").putBoolean("enteredStation", false);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        CompoundTag nbt = stack.getTagElement("ticketInfo");
        if (nbt != null) {
            if (nbt.getBoolean("used"))
                tooltip.add(new TranslatableComponent("tooltip.lead_beyond.ticket.used")
                        .withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) return InteractionResult.PASS;
        AtomicReference<InteractionResult> result = new AtomicReference<>(InteractionResult.PASS);
        LeadBeyond.onlyDev(context1 -> {
            if (context1.getLevel().getBlockState(context1.getClickedPos()).is(Blocks.OAK_DOOR)) {
                CompoundTag nbt = context1.getItemInHand().getOrCreateTagElement("ticketInfo");
                if (!nbt.getBoolean("used")) {
                    nbt.putBoolean("used", true);
                    result.set(InteractionResult.SUCCESS);
                }
            }
        }, context);
        return result.get();
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (this.allowdedIn(group)) {
            stacks.add(getDefaultInstance());
        }
    }
}
