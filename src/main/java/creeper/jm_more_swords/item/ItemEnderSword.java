package creeper.jm_more_swords.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.lwjgl.Sys;

import java.util.List;

/**
 * Created by jin on 2020/12/26 12:03 PM
 */
public class ItemEnderSword extends ItemSwordBase
{
    public static final int ACCUMULATION = 2000;
    public static final int MAX_MOTION = 100;

    public ItemEnderSword(String name, ToolMaterial material) {
        super(name, material);
    }

    //手持效果
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected && entityIn instanceof EntityPlayer)
        {
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 5, 5, false, false));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 5, false, false));

            if(worldIn instanceof WorldServer)
            {
                WorldServer worldServer = (WorldServer)worldIn;
                //worldServer.spawnParticle(EnumParticleTypes.PORTAL, entityIn.posX, entityIn.posY, entityIn.posZ, 50, 1, 1, 1, 0.001);
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    //左键效果
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        World world = target.getEntityWorld();
        if(world instanceof WorldServer)
        {
            ((WorldServer)world).spawnParticle(EnumParticleTypes.PORTAL, target.posX, target.posY+1, target.posZ, 500, 0.1, 1, 0.1, 1);
        }
        if(target instanceof EntityLiving){
            ((EntityLiving)target).setNoAI(true);
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);

//        Vec3d vec3d= playerIn.getLookVec();
//        //System.out.println(vec3d);
//        //BlockPos pos = playerIn.getPosition().add(vec3d.x*10, vec3d.y*10, vec3d.z*10);
//        //playerIn.setPosition(pos.getX(), pos.getY(), pos.getZ());
//        int distance = 10;
//        playerIn.motionX = vec3d.x*distance;
//        playerIn.motionY = vec3d.y*distance;
//        playerIn.motionZ = vec3d.z*distance;

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
            onPlayerStoppedUsing(stack, player.getEntityWorld(), player, ACCUMULATION);
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
            EntityPlayer player = (EntityPlayer)entityLiving;
            Vec3d vec3d = player.getLookVec();
            double distance = (ACCUMULATION-timeLeft)*3.0/ACCUMULATION * MAX_MOTION;
            //System.out.println("distance:"+distance);
            //System.out.println("timeleft:"+timeLeft);
            if(worldIn instanceof WorldServer)
            {
                ((WorldServer)worldIn).spawnParticle(EnumParticleTypes.PORTAL, player.posX, player.posY+1, player.posZ, (int)(distance+1)*500, distance/5, distance/5, distance/5, 1.0);
            }
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 5, false, false));
            player.motionX = vec3d.x*distance;
            player.motionY = vec3d.y*distance;
            player.motionZ = vec3d.z*distance;
            stack.damageItem((int)(distance+1), entityLiving);

        }
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }
}
