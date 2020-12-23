package creeper.jm_more_swords.item;

import ibxm.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

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
            //((EntityPlayer)entityIn).addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 2, 0, false, false));
            String command0 = "/gamerule sendCommandFeedback false";
            String command = "/particle smoke ~ ~ ~ 1 1 1 0.01 100";
            String command1 = "/gamerule sendCommandFeedback true";
            //String command = "/particle flame ~ ~1 ~ 0.2 0.2 0.2 0 1000";

            if (worldIn instanceof WorldServer)
            {
                WorldServer worldserver = (WorldServer)worldIn;


                //worldserver.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entityIn.posX, entityIn.posY, entityIn.posZ, 100, 1, 1, 1, 0.001);
                worldserver.spawnParticle(EnumParticleTypes.REDSTONE, entityIn.posX, entityIn.posY, entityIn.posZ, 150, 1, 1.5, 1, 0.0001);//这里很奇怪，最后一个速度参数设置超过0.1粒子就变成彩色了




            }

            //if(!worldIn.isRemote)
                //FMLCommonHandler.instance().getMinecraftServerInstance().commandManager.executeCommand(entityIn, command0);
                //FMLCommonHandler.instance().getMinecraftServerInstance().commandManager.executeCommand(entityIn, command);
                //FMLCommonHandler.instance().getMinecraftServerInstance().commandManager.executeCommand(entityIn, command1);
        }
    }
}
