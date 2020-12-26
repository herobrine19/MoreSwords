package creeper.jm_more_swords.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by jin on 2020/12/26 3:17 PM
 */
public class ItemLightSword extends ItemSwordBase
{

    public ItemLightSword(String name, ToolMaterial material) {
        super(name, material);
    }

    //手持效果
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (isSelected && entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;
            player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 5, false, false));
            player.setFire(0);  // 禁止玩家自己着火
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        List<Entity> entityList = ItemSwordBase.getEntityList(playerIn, 50);
        int mobCount = 0;
        for(Entity mob:entityList)
        {
            EntityLightningBolt lightning = new EntityLightningBolt(worldIn, mob.posX, mob.posY, mob.posZ, false);
            worldIn.spawnEntity(lightning);
            mobCount++;
        }
        playerIn.getHeldItemMainhand().damageItem(mobCount, playerIn);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
