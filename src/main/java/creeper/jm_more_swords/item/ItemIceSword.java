package creeper.jm_more_swords.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemIceSword extends ItemSwordBase {

    public static final Item.ToolMaterial ICE = EnumHelper.addToolMaterial("ICE", 3, 16, 16.0F, 0.0F, 10);

    public ItemIceSword(String name) {
        super(name, ICE);
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        BlockPos pos = target.getPosition();
        World world = target.getEntityWorld();

        for(int x=-1; x<=1; x++)
        {
            for(int y=-1; y<=1; y++)
            {
                for(int z=-1; z<=1; z++)
                {
                    if(world.getBlockState(pos.add(x, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.add(x, y, z)).getBlock() == Blocks.WATER)
                        world.setBlockState(pos.add(x, y, z), Blocks.ICE.getDefaultState());
                }
            }
        }
        return super.hitEntity(stack, target, attacker);
    }


}
