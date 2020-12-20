package creeper.jm_more_swords.item;

import javafx.geometry.Pos;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemIceSword extends ItemSwordBase {
    public ItemIceSword(String name) {
        super(name, ToolMaterial.DIAMOND);
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
