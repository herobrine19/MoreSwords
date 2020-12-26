package creeper.jm_more_swords.event;

import creeper.jm_more_swords.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class MyEventLoader
{
    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if(player.getHeldItemMainhand().getItem() == ItemInit.ENDER_SWORD)
            {
                World worldIn = player.getEntityWorld();
                if(worldIn instanceof WorldServer)
                {
                    System.out.println(player.getPosition());
                    ((WorldServer)worldIn).spawnParticle(EnumParticleTypes.PORTAL, player.posX, player.posY+1, player.posZ, 500, 0.5, 0.5, 0.5, 1.0);
                }
                event.setCanceled(true);
            }
        }
    }
}
