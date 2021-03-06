package com.builtbroken.mc.core.content.parts;

import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.core.References;
import com.builtbroken.mc.core.registry.implement.IRegistryInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by robert on 2/23/2015.
 */
public class ItemParts extends Item implements IRegistryInit
{
    public ItemParts()
    {
        this.setUnlocalizedName(References.PREFIX + "parts");
        this.setTextureName(References.PREFIX + "parts");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public void onRegistered()
    {
        for (EnumParts part : EnumParts.values())
        {
            OreDictionary.registerOre(part.oreName, new ItemStack(Engine.itemCraftingParts, 1, part.ordinal()));
        }
    }

    @Override
    public void onClientRegistered()
    {

    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        return EnumParts.get(stack.getItemDamage()).icon;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List items)
    {
        items.add(new ItemStack(item, 1, 0));
    }

    public static enum EnumParts
    {
        PARTS("random.parts", "part.random"),
        BASIC_CIRCUIT("circuitBasic", "circuit.t1");

        public final String oreName;
        public final String name;
        protected IIcon icon;

        private EnumParts(String oreName, String name)
        {
            this.oreName = oreName;
            this.name = name;
        }

        public static EnumParts get(int meta)
        {
            if(meta >= 0 && meta < values().length)
                return values()[meta];
            return PARTS;
        }
    }
}
