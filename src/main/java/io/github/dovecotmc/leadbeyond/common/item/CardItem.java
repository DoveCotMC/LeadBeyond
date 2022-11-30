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

public class CardItem extends Item
		implements Ticketable {
	public CardItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getDefaultStack() {
		ItemStack stack = super.getDefaultStack();
		stack.getOrCreateSubNbt("cardInfo").putLong("money", 0);
		stack.getOrCreateSubNbt("stationInfo").putBoolean("enteredStation", false);
		return stack;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		NbtCompound nbt = stack.getSubNbt("cardInfo");
		if (nbt != null) {
			tooltip.add(new TranslatableText("tooltip.lead_beyond.card.money", nbt.getLong("money"))
					.formatted(Formatting.YELLOW));
		}
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (context.getWorld().isClient())
			return ActionResult.PASS;
		AtomicReference<ActionResult> result = new AtomicReference<>(ActionResult.PASS);
		LeadBeyond.onlyDev(context1 -> {
			if (context1.getWorld().getBlockState(context1.getBlockPos()).isOf(Blocks.EMERALD_BLOCK)) {
				NbtCompound nbt = context1.getStack().getOrCreateSubNbt("cardInfo");
				nbt.putLong("money", nbt.getLong("money") + 114514);
				result.set(ActionResult.SUCCESS);
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
