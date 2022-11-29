package io.github.dovecotmc.leadbeyond.common.item;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.jetbrains.annotations.Nullable;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class TicketItem extends Item
		implements Ticketable {
	public TicketItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getDefaultStack() {
		ItemStack stack = super.getDefaultStack();
		stack.getOrCreateSubNbt("ticketInfo").putBoolean("used", false);
		stack.getOrCreateSubNbt("stationInfo").putBoolean("enteredStation", false);
		return stack;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		NbtCompound nbt = stack.getSubNbt("ticketInfo");
		if (nbt != null) {
			if (nbt.getBoolean("used"))
				tooltip.add(new TranslatableText("tooltip.lead_beyond.ticket.used")
						.formatted(Formatting.AQUA));
		}
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (context.getWorld().isClient())
			return ActionResult.PASS;
		AtomicReference<ActionResult> result = new AtomicReference<>(ActionResult.PASS);
		LeadBeyond.onlyDev(context1 -> {
			if (context1.getWorld().getBlockState(context1.getBlockPos()).isOf(Blocks.OAK_DOOR)) {
				NbtCompound nbt = context1.getStack().getOrCreateSubNbt("ticketInfo");
				if (!nbt.getBoolean("used")) {
					nbt.putBoolean("used", true);
					result.set(ActionResult.SUCCESS);
				}
			}
		}, context);
		return result.get();
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
		if (this.isIn(group)) {
			stacks.add(getDefaultStack());
		}
	}
}
