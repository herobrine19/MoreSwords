package creeper.jm_more_swords.event;

import creeper.jm_more_swords.init.ItemInit;
import creeper.jm_more_swords.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/items/ice_sword");

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
    public static void onPlayerHoldSwordRender(RenderLivingEvent.Post event)
    {
        EntityLivingBase player = event.getEntity();
        if (player instanceof EntityPlayer)
        {
            ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
            if (heldItem.getItem() == ItemInit.WITHER_SWORD)
            {
                //Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
                //Minecraft.getMinecraft().getRenderManager().renderEntity(player, player.posX, player.posY, player.posZ, player.rotationYaw, event.getPartialRenderTick(), true);
//                Tessellator tessellator = Tessellator.getInstance();
//                BufferBuilder bufferBuilder = tessellator.getBuffer();
//                GlStateManager.pushMatrix();
////                GlStateManager.scale(4, 4, 4);
////                GlStateManager.translate(player.posX, player.posY, player.posZ);
//                Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
                GlStateManager.color(1, 1, 1);
//                bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
//                for (int i = 0; i < index_list.length; i += 4) {
//                    for (int j = 0; j < 4; j++) {
//                        int k = index_list[i+j];
//                        bufferBuilder.pos(vertex_list[k][0], vertex_list[k][1], vertex_list[k][2]).endVertex();
//                    }
//                }
//                Tessellator.getInstance().draw();
//                GlStateManager.popMatrix();
            }
        }
    }
}
