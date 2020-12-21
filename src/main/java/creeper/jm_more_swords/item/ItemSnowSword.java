package creeper.jm_more_swords.item;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by jin on 2020/12/21 3:24 PM
 */
public class ItemSnowSword extends ItemSwordBase {
    public static final Item.ToolMaterial SNOW = EnumHelper.addToolMaterial("SNOW", 3, 16, 16.0F, 0.0F, 10);

    public ItemSnowSword(String name) {
        super(name, SNOW);
    }


}
