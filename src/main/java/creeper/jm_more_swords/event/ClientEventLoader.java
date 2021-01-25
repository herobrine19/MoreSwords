package creeper.jm_more_swords.event;

import creeper.jm_more_swords.init.ItemInit;
import creeper.jm_more_swords.renderer.WitherPlayerLayerRenderer;
import creeper.jm_more_swords.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.Buffer;
import java.util.Iterator;

@Mod.EventBusSubscriber
public class ClientEventLoader
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/steve_eye.png");
//    private static final ModelBiped PLAYER = new ModelPlayer(0, false);

    private static int[] index_list = new int[]{
            0, 2, 3, 1,
            0, 4, 6, 2,
            0, 1, 5, 4,
            4, 5, 7, 6,
            1, 3, 7, 5,
            2, 6, 7, 3,
    };

    private static float vertex_list[][] = new float[][]{
            {-0.5f, -0.5f, -0.5f},
            {0.5f, -0.5f, -0.5f},
            {-0.5f, 0.5f, -0.5f},
            {0.5f, 0.5f, -0.5f},
            {-0.5f, -0.5f, 0.5f},
            {0.5f, -0.5f, 0.5f},
            {-0.5f, 0.5f, 0.5f},
            {0.5f, 0.5f, 0.5f},
    };

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onPlayerHoldSwordRender(RenderLivingEvent.Pre event)
    {
        EntityLivingBase player = event.getEntity();
        if (player instanceof EntityPlayer)
        {
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            if (heldItem.getItem() == ItemInit.WITHER_SWORD)
            {
                GlStateManager.color(0, 0, 0);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onPlayerHoldSwordRenderPost(RenderLivingEvent.Post event)
    {
        if (!(event.getEntity() instanceof EntityPlayer))
            return;

        NBTTagCompound compound = event.getEntity().getEntityData();
        if (compound.hasKey(Reference.MODID + "_add_eye_layer"))
            return;
        else
        {
            compound.setBoolean(Reference.MODID + "_add_eye_layer", true);
            event.getRenderer().addLayer(new WitherPlayerLayerRenderer((RenderPlayer) event.getRenderer()));
        }
    }
}
