package creeper.jm_more_swords.init;

import creeper.jm_more_swords.item.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item WATER_SWORD = new ItemWaterSword("water_sword", SwordMaterial.WATER);
    public static final Item ICE_SWORD = new ItemIceSword("ice_sword", SwordMaterial.ICE);
    public static final Item WITHER_SWORD = new ItemWitherSword("wither_sword", SwordMaterial.WITHER);
    public static final Item FLAME_SWORD = new ItemFlameSword("flame_sword",SwordMaterial.FLAME);
}
