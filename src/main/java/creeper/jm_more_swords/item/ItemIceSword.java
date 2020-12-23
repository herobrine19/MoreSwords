package creeper.jm_more_swords.item;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemIceSword extends ItemSwordBase {

    public static final int XULI = 60;


    public ItemIceSword(String name, ToolMaterial material) {
        super(name, material);
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return XULI;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        if (count == 50) {
            player.setVelocity(0.1, 0.1, 0.1);
        }
        super.onUsingTick(stack, player, count);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW ;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

        List<Entity> entityList = worldIn.getEntitiesInAABBexcluding(entityLiving, entityLiving.getEntityBoundingBox().grow(10), new Predicate<Entity>() {
            @Override
            public boolean apply(@Nullable Entity input) {
                return input instanceof EntityMob;
            }
        });
        for(Entity mob:entityList)
        {
            BlockPos pos = mob.getPosition();
            if(worldIn.getBlockState(pos).getBlock() == Blocks.AIR || worldIn.getBlockState(pos).getBlock() == Blocks.WATER)
                worldIn.setBlockState(pos, Blocks.ICE.getDefaultState());
            if(worldIn.getBlockState(pos.add(0,1,0)).getBlock() == Blocks.AIR || worldIn.getBlockState(pos.add(0,1,0)).getBlock() == Blocks.WATER)
                worldIn.setBlockState(pos.add(0, 1, 0), Blocks.ICE.getDefaultState());
        }
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }
}
