package creeper.jm_more_swords.item;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.List;

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
        if(isSelected && entityIn instanceof EntityPlayer)
        {
            if (worldIn instanceof WorldServer)//身边黑色粒子效果
            {
                WorldServer worldserver = (WorldServer)worldIn;
                //worldserver.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entityIn.posX, entityIn.posY, entityIn.posZ, 100, 1, 1, 1, 0.001);
                worldserver.spawnParticle(EnumParticleTypes.REDSTONE, entityIn.posX, entityIn.posY, entityIn.posZ, 120, 1, 1.5, 1, 0.0001);//这里很奇怪，最后一个速度参数设置超过0.1粒子就变成彩色了
            }

            List<Entity> entityList = worldIn.getEntitiesInAABBexcluding(entityIn, entityIn.getEntityBoundingBox().grow(50), new Predicate<Entity>() {
                @Override
                public boolean apply(@Nullable Entity input) {
                    if(input instanceof EntityMob)
                        return true;
                    if(input instanceof EntityLiving)
                    {
                        EntityLivingBase target = ((EntityLiving) input).getAttackTarget();
                        if(target==entityIn)  // 如果攻击目标是玩家
                            return true;
                    }
                    return false;
                }
            });

            for(Entity mob: entityList)
            {
                ((EntityLiving)mob).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 10, true, true));
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        target.setHealth(0.5f);
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 10, true, true));
        return super.hitEntity(stack, target, attacker);
    }
}
