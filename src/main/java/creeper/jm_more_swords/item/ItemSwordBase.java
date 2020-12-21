package creeper.jm_more_swords.item;


import creeper.jm_more_swords.Main;
import creeper.jm_more_swords.creativetab.CreativeTabsMoreSwords;
import creeper.jm_more_swords.init.ItemInit;
import creeper.jm_more_swords.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemSwordBase extends ItemSword implements IHasModel
{
    public ItemSwordBase(String name, Item.ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsMoreSwords.tabsMoreSwords);

        ItemInit.ITEMS.add(this);
    }


    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

}