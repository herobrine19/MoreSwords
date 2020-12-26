package creeper.jm_more_swords.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class SwordMaterial
{
    public static final Item.ToolMaterial ICE = EnumHelper.addToolMaterial("ICE", 3, 1000, 16.0F, 3.0F, 100).setRepairItem(new ItemStack(Blocks.ICE));
    public static final Item.ToolMaterial WATER = EnumHelper.addToolMaterial("WATER", 3, 1000, 16.0F, 3.0F, 100).setRepairItem(new ItemStack(Items.WATER_BUCKET));
    public static final Item.ToolMaterial WITHER = EnumHelper.addToolMaterial("WITHER", 3, 1000000, 16.0F, 3.0F, 100).setRepairItem(new ItemStack(Blocks.SKULL));
    public static final Item.ToolMaterial FLAME = EnumHelper.addToolMaterial("FLAME", 3, 1000, 16.0F, 3.0F, 100).setRepairItem(new ItemStack(Items.BLAZE_POWDER));
    public static final Item.ToolMaterial ENDER = EnumHelper.addToolMaterial("ENDER", 3, 1000, 16.0F, 3.0F, 100).setRepairItem(new ItemStack(Items.ENDER_PEARL));
}
