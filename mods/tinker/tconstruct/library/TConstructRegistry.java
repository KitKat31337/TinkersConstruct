package mods.tinker.tconstruct.library;

import java.util.ArrayList;
import java.util.HashMap;

import mods.tinker.tconstruct.library.tools.ToolCore;
import mods.tinker.tconstruct.library.tools.ToolMaterial;
import mods.tinker.tconstruct.library.util.TabTools;
import net.minecraft.item.Item;

/** A registry to store any relevant API work
 * 
 * @author mDiyo
 */

public class TConstructRegistry
{
	public static TConstructRegistry instance = new TConstructRegistry();
	public static ArrayList<ToolCore> tools = new ArrayList<ToolCore>(20);
	public static HashMap<Integer, ToolMaterial> toolMaterials = new HashMap<Integer, ToolMaterial>(40);
    public static HashMap<String, ToolMaterial> toolMaterialStrings = new HashMap<String, ToolMaterial>(40);
	
	public static TabTools toolTab;
	public static TabTools materialTab;
	public static TabTools blockTab;
	public static Item toolRod;

	//Tools
	public static void addToolMapping (ToolCore tool)
	{
		tools.add(tool);
	}

	public static ArrayList<ToolCore> getToolMapping ()
	{
		return tools;
	}

	//Materials
	public static void addToolMaterial (int materialID, String materialName, int craftingTier, int harvestLevel, int durability, int miningspeed, int attack, float handleModifier, int reinforced, float shoddy)
	{
		addToolMaterial(materialID, materialName, craftingTier, harvestLevel, durability, miningspeed, attack, handleModifier, reinforced, shoddy, "", "");
	}
	
	public static void addToolMaterial (int materialID, String materialName, int craftingTier, int harvestLevel, int durability, int miningspeed, int attack, float handleModifier, int reinforced, float shoddy, String style, String ability)
	{
		ToolMaterial mat = toolMaterials.get(materialID);
		if (mat == null)
		{
		    mat = new ToolMaterial(materialName, craftingTier, harvestLevel, durability, miningspeed, attack, handleModifier, reinforced, shoddy, style, ability);
			toolMaterials.put(materialID, mat);
			toolMaterialStrings.put(materialName, mat);
		}
		else
			throw new RuntimeException("[TConstruct] Material ID " + materialID + " is already occupied by " + mat.materialName);
	}

	public static ToolMaterial getMaterial (int key)
	{
		return (toolMaterials.get(key));
	}
	
	public static ToolMaterial getMaterial (String key)
    {
        return (toolMaterialStrings.get(key));
    }
}
