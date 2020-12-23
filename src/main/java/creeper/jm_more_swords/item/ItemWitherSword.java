package creeper.jm_more_swords.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by jin on 2020/12/21 3:27 PM
 */
public class ItemWitherSword extends ItemSwordBase
{
    public ItemWitherSword(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if(isSelected)
            entityIn.setVelocity(0.1, 0.1, 0.1);
    }
}
