package doggytalents;

import doggytalents.block.BlockDogBath;
import doggytalents.block.BlockDogBed;
import doggytalents.block.BlockFoodBowl;
import doggytalents.block.ItemDogBed;
import doggytalents.client.model.ModelHelper;
import doggytalents.lib.Reference;
import doggytalents.lib.ResourceLib;
import doggytalents.tileentity.TileEntityDogBath;
import doggytalents.tileentity.TileEntityDogBed;
import doggytalents.tileentity.TileEntityFoodBowl;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author ProPercivalalb
 */
@EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {

	public static Block DOG_BED;
	public static Block DOG_BATH;
    public static Block FOOD_BOWL;

    @SubscribeEvent
	public static void onRegisterBlock(RegistryEvent.Register<Block> registry) {
		DoggyTalents.LOGGER.info("Registering Blocks");
		DOG_BED = new BlockDogBed().setTranslationKey("doggytalents.dogbed").setRegistryName(Reference.MOD_ID + ":dog_bed");
		DOG_BATH = new BlockDogBath().setTranslationKey("doggytalents.dogbath").setRegistryName(Reference.MOD_ID + ":dog_bath");
		FOOD_BOWL = new BlockFoodBowl().setTranslationKey("doggytalents.foodbowl").setRegistryName(Reference.MOD_ID + ":food_bowl");

		GameRegistry.registerTileEntity(TileEntityDogBed.class, ResourceLib.get("dog_bed"));
		GameRegistry.registerTileEntity(TileEntityDogBath.class, ResourceLib.get("dog_bath"));
		GameRegistry.registerTileEntity(TileEntityFoodBowl.class, ResourceLib.get("dog_bowl"));
		
		DOG_BED.setHarvestLevel("axe", 0);
		DOG_BATH.setHarvestLevel("pickaxe", 0);
		FOOD_BOWL.setHarvestLevel("pickaxe", 0);
		
		registry.getRegistry().register(DOG_BED);
		registry.getRegistry().register(DOG_BATH);
		registry.getRegistry().register(FOOD_BOWL);
	}
    @SubscribeEvent
	public static void onRegisterItem(RegistryEvent.Register<Item> registry) {
		DoggyTalents.LOGGER.info("Registering ItemBlocks");
		registry.getRegistry().register(new ItemDogBed(DOG_BED).setRegistryName(DOG_BED.getRegistryName()));
		registry.getRegistry().register(makeItemBlock(DOG_BATH));
		registry.getRegistry().register(makeItemBlock(FOOD_BOWL));
	}
	
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void setItemModels(ModelRegistryEvent event) {
		ModelHelper.setModel(DOG_BATH, 0, "doggytalents:dog_bath");
		ModelHelper.setModel(DOG_BED, 0, "doggytalents:dog_bed");
		ModelHelper.setModel(FOOD_BOWL, 0, "doggytalents:food_bowl");
	}
	
	
	private static ItemBlock makeItemBlock(Block block) {
        return (ItemBlock)new ItemBlock(block).setRegistryName(block.getRegistryName());
    }
}
