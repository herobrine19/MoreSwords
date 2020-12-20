package creeper.jm_more_swords.init;

import creeper.jm_more_swords.item.ItemIceSword;
import creeper.jm_more_swords.item.ItemWaterSword;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item WATER_SWORD = new ItemWaterSword("water_sword");
    public static final Item ICE_SWORD = new ItemIceSword("ice_sword");
}
