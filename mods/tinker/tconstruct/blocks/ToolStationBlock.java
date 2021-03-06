package mods.tinker.tconstruct.blocks;

import java.util.List;

import mods.tinker.tconstruct.TConstruct;
import mods.tinker.tconstruct.blocks.logic.PartCrafterLogic;
import mods.tinker.tconstruct.blocks.logic.PatternChestLogic;
import mods.tinker.tconstruct.blocks.logic.PatternShaperLogic;
import mods.tinker.tconstruct.blocks.logic.ToolStationLogic;
import mods.tinker.tconstruct.client.block.TableRender;
import mods.tinker.tconstruct.library.TConstructRegistry;
import mods.tinker.tconstruct.library.blocks.InventoryBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ToolStationBlock extends InventoryBlock
{	
	public ToolStationBlock(int id, Material material)
	{
		super(id, material);
		this.setCreativeTab(TConstructRegistry.blockTab);
		this.setHardness(2f);
	}

	/* Rendering */
	@Override
	public String[] getTextureNames()
	{
		String[] textureNames = { 
			"toolstation_top",
			"toolstation_side",
			"toolstation_bottom",
			"partbuilder_oak_top",
			"partbuilder_oak_side",
			"partbuilder_oak_bottom",
			"partbuilder_spruce_top",
			"partbuilder_spruce_side",
			"partbuilder_spruce_bottom",
			"partbuilder_birch_top",
			"partbuilder_birch_side",
			"partbuilder_birch_bottom",
			"partbuilder_jungle_top",
			"partbuilder_jungle_side",
			"partbuilder_jungle_bottom",
			"patternchest_top",
			"patternchest_side",
			"patternchest_bottom",
			"stenciltable_oak_top",
			"stenciltable_oak_side",
			"stenciltable_oak_bottom",
			"stenciltable_spruce_top",
			"stenciltable_spruce_side",
			"stenciltable_spruce_bottom",
			"stenciltable_birch_top",
			"stenciltable_birch_side",
			"stenciltable_birch_bottom",
			"stenciltable_jungle_top",
			"stenciltable_jungle_side",
			"stenciltable_jungle_bottom"};
		
		return textureNames;
	}
	
	@Override
	public Icon getIcon (int side, int meta)
	{
		if (meta <= 4)
		{
			return icons[meta*3+getTextureIndex(side)];
		}
		else if (meta <= 9)
		{
			return icons[15+getTextureIndex(side)];
		}
		else
		{
			return icons[meta*3+getTextureIndex(side)-12];
		}
	}

	public int getTextureIndex (int side)
	{
		if (side == 0)
			return 2;
		if (side == 1)
			return 0;

		return 1;
	}

	@Override
	public boolean renderAsNormalBlock ()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube ()
	{
		return false;
	}

	@Override
	public int getRenderType ()
	{
		return TableRender.tabelModelID;
	}

	@Override
	public boolean shouldSideBeRendered (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool (World world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		if (metadata == 5)
			return AxisAlignedBB.getAABBPool().getAABB((double) x + this.minX, (double) y + this.minY, (double) z + this.minZ, (double) x + this.maxX, (double) y + this.maxY - 0.125, (double) z + this.maxZ);
		return AxisAlignedBB.getAABBPool().getAABB((double) x + this.minX, (double) y + this.minY, (double) z + this.minZ, (double) x + this.maxX, (double) y + this.maxY, (double) z + this.maxZ);
	}

	@Override
	public TileEntity createTileEntity (World world, int metadata)
	{
		switch (metadata)
		{
		case 0:
			return new ToolStationLogic();
		case 1:
			return new PartCrafterLogic();
		case 2:
			return new PartCrafterLogic();
		case 3:
			return new PartCrafterLogic();
		case 4:
			return new PartCrafterLogic();
		case 5:
			return new PatternChestLogic();
		case 6:
			return new PatternChestLogic();
		case 7:
			return new PatternChestLogic();
		case 8:
			return new PatternChestLogic();
		case 9:
			return new PatternChestLogic();
		case 10:
			return new PatternShaperLogic();
		case 11:
			return new PatternShaperLogic();
		case 12:
			return new PatternShaperLogic();
		case 13:
			return new PatternShaperLogic();
		/*case 14:
			return new CastingTableLogic();*/
		default:
			return null;
		}
	}

	@Override
	public Integer getGui (World world, int x, int y, int z, EntityPlayer entityplayer)
	{
		int md = world.getBlockMetadata(x, y, z);
		if (md == 0)
			return 0;
		else if (md < 5)
			return 1;
		else if (md < 10)
			return 2;
		else
			return 3;

		//return -1;
	}

	@Override
	public Object getModInstance ()
	{
		return TConstruct.instance;
	}

	@Override
	public void getSubBlocks (int id, CreativeTabs tab, List list)
	{
		for (int iter = 0; iter < 6; iter++)
		{
			list.add(new ItemStack(id, 1, iter));
		}

		for (int iter = 10; iter < 14; iter++)
		{
			list.add(new ItemStack(id, 1, iter));
		}
	}
}
