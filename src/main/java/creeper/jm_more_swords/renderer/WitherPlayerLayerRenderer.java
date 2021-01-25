package creeper.jm_more_swords.renderer;

import creeper.jm_more_swords.init.ItemInit;
import creeper.jm_more_swords.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WitherPlayerLayerRenderer implements LayerRenderer<EntityPlayer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/steve_eye.png");
    private final RenderPlayer playerRenderer;


    public WitherPlayerLayerRenderer(RenderPlayer playerRenderer)
    {
        this.playerRenderer = playerRenderer;
    }


    @Override
    public void doRenderLayer(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        //todo
        ItemStack heldItem = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
        if (heldItem.getItem() == ItemInit.WITHER_SWORD)
        {
            GlStateManager.pushMatrix();
            this.playerRenderer.bindTexture(TEXTURE);
            if (entitylivingbaseIn.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            int tick = entitylivingbaseIn.ticksExisted % 40;
            tick = tick > 20 ? 40 - tick : tick;
            GlStateManager.color(tick / 20.0f, tick / 20.0f, tick / 20.0f);
            this.playerRenderer.getMainModel().bipedHead.renderWithRotation(0.001F);
            GlStateManager.popMatrix();
        }

    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
