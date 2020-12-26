package creeper.jm_more_swords.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

/**
 * Created by jin on 2020/12/26 10:12 AM
 */
public class ItemFlameSword extends ItemSwordBase
{
    public ItemFlameSword(String name, ToolMaterial material) {
        super(name, material);
    }

    //手持效果
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && entityIn instanceof EntityPlayer)
        {
            // 抗火buff
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 5, 5,false, false));

            if (worldIn instanceof WorldServer)//身边火焰粒子效果
            {
                WorldServer worldserver = (WorldServer)worldIn;
                worldserver.spawnParticle(EnumParticleTypes.FLAME, entityIn.posX, entityIn.posY+0.1, entityIn.posZ, 10, 0.2, 0.5, 0.2, 0.009);//这里很奇怪，最后一个速度参数设置超过0.1粒子就变成彩色了
            }

            //靠近的生物着火
            List<Entity> entityList = ItemSwordBase.getEntityList((EntityPlayer)entityIn, 3);
            for (Entity mob: entityList)
            {
                if(mob instanceof EntityLiving)
                {
                    mob.setFire(1);
                }
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    //左键效果
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        target.setFire(10);
        return super.hitEntity(stack, target, attacker);
    }


    //右键召唤烈焰人
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        EntityLiving blaze = new EntityBlaze(worldIn);
        blaze.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
        if(!worldIn.isRemote)
        {
            worldIn.spawnEntity(blaze);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
