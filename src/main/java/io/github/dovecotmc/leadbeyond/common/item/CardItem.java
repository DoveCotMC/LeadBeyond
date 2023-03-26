package io.github.dovecotmc.leadbeyond.common.item;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import org.jetbrains.annotations.NotNull;
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

public class CardItem extends Item
        implements Ticketable {
    public CardItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTagElement("cardInfo").putLong("money", 0);
        stack.getOrCreateTagElement("stationInfo").putBoolean("enteredStation", false);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag context) {
        CompoundTag nbt = stack.getTagElement("cardInfo");
        if (nbt != null) {
            tooltip.add(new TranslatableComponent("tooltip.lead_beyond.card.money", nbt.getLong("money"))
                    .withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) return InteractionResult.PASS;
        AtomicReference<InteractionResult> result = new AtomicReference<>(InteractionResult.PASS);
        LeadBeyond.onlyDev(context1 -> {
            if (context1.getLevel().getBlockState(context1.getClickedPos()).is(Blocks.EMERALD_BLOCK)) {
                CompoundTag nbt = context1.getItemInHand().getOrCreateTagElement("cardInfo");
                nbt.putLong("money", nbt.getLong("money") + 114514);
                result.set(InteractionResult.SUCCESS);
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
