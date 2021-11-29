package pl.mines.xcraftrayx.CraftPvP.Stats;

import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class ItemStackToJson {
	
	/**
	* Converts an {@link org.bukkit.inventory.ItemStack} to a Json string
	* for sending with {@link net.md_5.bungee.api.chat.BaseComponent}'s.
	*
	* @param itemStack the item to convert
	* @return the Json string representation of the item
	*/
	public static String convertItemStackToJsonRegular(ItemStack itemStack) {
	    // First we convert the item stack into an NMS itemstack
	   net.minecraft.server.v1_13_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
	   NBTTagCompound compound = new NBTTagCompound();
	    compound = nmsItemStack.save(compound);

	    return compound.toString();
	}
}
