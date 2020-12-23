package creeper.jm_more_swords.renderer;

import creeper.jm_more_swords.util.Reference;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WitherPlayerLayerRenderer implements LayerRenderer<EntityPlayer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entities/wither_player_eye");
    private EntityPlayer player;

    public WitherPlayerLayerRenderer(EntityPlayer player)
    {
        this.player = player;
    }


    @Override
    public void doRenderLayer(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        //todo
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
