package creeper.jm_more_swords.util.handlers;

import creeper.jm_more_swords.init.BlockInit;
import creeper.jm_more_swords.init.ItemInit;
import creeper.jm_more_swords.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegisterHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for (Item item : ItemInit.ITEMS)
        {
            if (item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }
        for (Block block : BlockInit.BLOCKS)
        {
            if (block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onTextureRegister(TextureStitchEvent.Pre event)
    {

    }


    public static void initRegisteries()
    {
//        SoundsHandler.registerSounds();
//        StructureChurchPieces.registerPieces();
    }

    public static void preInitRegistries()
    {
//        EntityInit.registerEntities();
//        BiomeInit.registerBiomes();
//        DimensionInit.registerDimensions();
//        DimensionInit.registerWorldGenerators();
//        PotionInit.registerPotions();
//        MagicFormulaRegistry.registerAllFormula();
    }
}
