package creeper.jm_more_swords.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemIceSword extends ItemSwordBase {

    public static final int ACCUMULATION = 100;  //最大蓄力时间
    public static final int MAX_DISTANCE = 50;  //最远冰冻距离


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
        return ACCUMULATION;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        if (count == 1) {
            onPlayerStoppedUsing(stack, player.getEntityWorld(), player, 1);
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
        if(entityLiving instanceof EntityPlayer)
        {
            int distance = (int)((ACCUMULATION-timeLeft)*1.0/ACCUMULATION * MAX_DISTANCE +1);
            List<Entity> entityList = ItemSwordBase.getEntityList(entityLiving, distance);
            for(Entity mob:entityList)
            {
                AxisAlignedBB bb = mob.getEntityBoundingBox();
                Iterable<BlockPos> posIterable = BlockPos.getAllInBox((int)Math.round(bb.minX), (int)Math.round(bb.minY), (int)Math.round(bb.minZ),
                        (int)Math.round(bb.maxX), (int)Math.round(bb.maxY), (int)Math.round(bb.maxZ));
                for(BlockPos pos: posIterable)
                {
                    if(worldIn.getBlockState(pos).getBlock() == Blocks.AIR || worldIn.getBlockState(pos).getBlock() == Blocks.WATER)
                        worldIn.setBlockState(pos, Blocks.ICE.getDefaultState());
                }
            }
            stack.damageItem(distance, entityLiving);
        }

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }
}
