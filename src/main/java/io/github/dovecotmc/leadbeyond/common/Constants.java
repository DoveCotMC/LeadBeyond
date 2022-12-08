package io.github.dovecotmc.leadbeyond.common;

import io.github.dovecotmc.leadbeyond.LeadBeyond;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Constants {
    public static final VoxelShape YZ_NS_SHAPE = Shapes.or(Shapes.box(0.125, 0, 0.5625, 0.1875, 0.25, 0.8125),
            Shapes.box(0.125, 0, 0.1875, 0.1875, 0.25, 0.4375),
            Shapes.box(0.125, 0, 0.1875, 0.1875, 0.25, 0.4375),
            Shapes.box(0.8125, 0, 0.1875, 0.875, 0.25, 0.4375),
            Shapes.box(0.8125, 0, 0.5625, 0.875, 0.25, 0.8125),
            Shapes.box(0, 0.25, 0, 1, 0.5, 1),
            Shapes.box(0, 0.5, 0.375, 0.5, 1.4375, 0.625),
            Shapes.box(0.53125, 0.5, 0.375, 1, 1.4375, 0.625));
    public static final VoxelShape YZ_EW_SHAPE = Shapes.or(Shapes.box(0.1875, 0, 0.125, 0.4375, 0.25, 0.1875),
            Shapes.box(0.5625, 0, 0.125, 0.8125, 0.25, 0.1875),
            Shapes.box(0.5625, 0, 0.8125, 0.8125, 0.25, 0.875),
            Shapes.box(0.1875, 0, 0.8125, 0.4375, 0.25, 0.875),
            Shapes.box(0, 0.25, 0, 1, 0.5, 1),
            Shapes.box(0.375, 0.5, 0, 0.625, 1.4375, 0.5),
            Shapes.box(0.375, 0.5, 0.53125, 0.625, 1.4375, 1));

    public static final VoxelShape PASSWAY_NS_SHAPE = Shapes.or(Shapes.box(-0.125, -0.25, 0, 1.125, -0.125, 1),
            Shapes.box(-0.125, 1.875, 0, 1.125, 2, 1),
            Shapes.box(1, -0.125, 0, 1.125, 1.875, 1),
            Shapes.box(-0.125, -0.125, 0, 0, 1.875, 1));
    public static final VoxelShape PASSWAY_EW_SHAPE = Shapes.or(Shapes.box(0, -0.25, -0.125, 1, -0.125, 1.125),
            Shapes.box(0, 1.875, -0.125, 1, 2, 1.125),
            Shapes.box(0, -0.125, 1, 1, 1.875, 1.125),
            Shapes.box(0, -0.125, -0.125, 1, 1.875, 0));

    public static Item getCurrencyItem() {
        Item ret = Registry.ITEM.get(new ResourceLocation(LeadBeyond.CONFIG.currencyItemId));
        return ret == Items.AIR ? Items.EMERALD : ret;
    }
}
