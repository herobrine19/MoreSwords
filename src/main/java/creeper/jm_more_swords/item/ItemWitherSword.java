package creeper.jm_more_swords.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
 * Created by jin on 2020/12/21 3:27 PM
 */
public class ItemWitherSword extends ItemSwordBase
{
    public ItemWitherSword(String name, ToolMaterial material) {
        super(name, material);
    }

    //手持效果
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if(isSelected && entityIn instanceof EntityPlayer)
        {
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 5,false, false));

            if (worldIn instanceof WorldServer)//身边黑色粒子效果
            {
                WorldServer worldserver = (WorldServer)worldIn;
                worldserver.spawnParticle(EnumParticleTypes.REDSTONE, entityIn.posX, entityIn.posY, entityIn.posZ, 120, 0.5, 1.5, 0.5, 0.0001);//这里很奇怪，最后一个速度参数设置超过0.1粒子就变成彩色了
            }

            List<Entity> entityList = ItemSwordBase.getEntityList((EntityPlayer)entityIn, 50);

            for(Entity mob: entityList)
            {
                ((EntityLiving)mob).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 10, true, true));
            }
            stack.damageItem(1, (EntityPlayer)entityIn);
        }
    }

    //左键效果
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        target.setHealth(0.5f);
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 100, true, true));
        stack.damageItem(-1000, attacker);
        return super.hitEntity(stack, target, attacker);
    }

    //右键效果


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        List<Entity> entityList = worldIn.getEntitiesInAABBexcluding(playerIn, playerIn.getEntityBoundingBox().grow(100), Entity::isEntityAlive);
        int mobCount = 0;
        for(Entity mob: entityList)
        {
            if(mob instanceof  EntityLiving){
                mobCount ++;
                ((EntityLiving)mob).setHealth(0.5f);
                ((EntityLiving)mob).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 10, true, true));
            }
        }
        ItemStack stack = playerIn.getHeldItemMainhand();
        stack.damageItem(mobCount, playerIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
