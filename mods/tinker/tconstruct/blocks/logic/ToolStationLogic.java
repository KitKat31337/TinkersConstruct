package mods.tinker.tconstruct.blocks.logic;

import mods.tinker.tconstruct.inventory.ToolStationContainer;
import mods.tinker.tconstruct.library.blocks.InventoryLogic;
import mods.tinker.tconstruct.library.crafting.ToolBuilder;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/* Simple class for storing items in the block
 */

public class ToolStationLogic extends InventoryLogic
    implements ISidedInventory
{
	ItemStack previousTool;
	String toolName;

	public ToolStationLogic()
	{
		super(5);
		toolName = "";
	}
	
	public boolean canDropInventorySlot(int slot)
    {
	    if (slot == 0)
	        return false;
        return true;
    }

	@Override
	public ItemStack getStackInSlotOnClosing (int var1)
	{
		return null;
	}

	@Override
	public String getDefaultName ()
	{
		return "toolstation.crafting";
	}

	@Override
	public Container getGuiContainer (InventoryPlayer inventoryplayer, World world, int x, int y, int z)
	{
		return new ToolStationContainer(inventoryplayer, this);
	}

	public void onInventoryChanged ()
	{
		buildTool(toolName);
		if (this.worldObj != null)
		{
			this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.updateTileEntityChunkAndDoNothing(this.xCoord, this.yCoord, this.zCoord, this);
		}
	}

	public void buildTool (String name)
	{
		toolName = name;
		ItemStack tool = ToolBuilder.instance.buildTool(inventory[1], inventory[2], inventory[3], name);
		if (inventory[0] == null)
			inventory[0] = tool;
		else
		{
			NBTTagCompound tags = inventory[0].getTagCompound();
			if (!tags.getCompoundTag("InfiTool").hasKey("Built"))
			{
				inventory[0] = tool;
			}
		}
	}
	
	public void setToolname (String name)
	{
		toolName = name;
		buildTool (name);
	}
	
	public boolean canUpdate()
    {
        return false;
    }

    @Override
    public int[] getSizeInventorySide (int var1)
    {
        return null;
    }

    @Override
    public boolean func_102007_a (int i, ItemStack itemstack, int j)
    {
        return false;
    }

    @Override
    public boolean func_102008_b (int i, ItemStack itemstack, int j)
    {
        return false;
    }
}
