package pl.mines.xcraftrayx.CraftPvP.LevelRewards;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items
{
	public Items(int level, String item, String name, int amount, short durability, List<String> lore, String[][] enchantments)
	{
		ItemStack itemStack = new ItemStack(Material.getMaterial(item), amount);
		itemStack.setDurability(durability);
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		for(int i = 0; i < enchantments.length; i++)
		{
			if(enchantments[i][0] != null)
			{
				for(Enchantment ench : Enchantment.values())
				{
					if(ench.getName().equals(enchantments[i][0]))
					{
						im.addEnchant(ench, Integer.valueOf(enchantments[i][1]), true);
						break;
					}
				}
			}
		}
		itemStack.setItemMeta(im);
		LevelRewards.items.put(level, itemStack);
	}
}