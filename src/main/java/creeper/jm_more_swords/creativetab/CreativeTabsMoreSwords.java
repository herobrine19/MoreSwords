package creeper.jm_more_swords.creativetab;

import creeper.jm_more_swords.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by jin on 2020/12/21 2:54 PM
 */
public class CreativeTabsMoreSwords extends CreativeTabs
{

    public static CreativeTabsMoreSwords tabsMoreSwords = new CreativeTabsMoreSwords();

    public CreativeTabsMoreSwords() {
        super("moreSwords");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.ICE_SWORD);
    }
}
