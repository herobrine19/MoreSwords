package creeper.jm_more_swords.item;


import com.google.common.base.Predicate;
import creeper.jm_more_swords.Main;
import creeper.jm_more_swords.creativetab.CreativeTabsMoreSwords;
import creeper.jm_more_swords.init.ItemInit;
import creeper.jm_more_swords.util.IHasModel;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import javax.annotation.Nullable;
import java.util.List;

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

    public static List<Entity> getEntityList(EntityLivingBase player, int distance)
    {
        List<net.minecraft.entity.Entity> entityList = player.getEntityWorld().getEntitiesInAABBexcluding(player, player.getEntityBoundingBox().grow(distance), new Predicate<net.minecraft.entity.Entity>() {
            @Override
            public boolean apply(@Nullable net.minecraft.entity.Entity input) {
                if(input instanceof EntityMob)
                    return true;
                if(input instanceof EntityLiving)
                {
                    EntityLivingBase target = ((EntityLiving) input).getAttackTarget();
                    if(target==player)  // 如果攻击目标是玩家
                        return true;
                }
                return false;
            }
        });
        return entityList;
    }

}