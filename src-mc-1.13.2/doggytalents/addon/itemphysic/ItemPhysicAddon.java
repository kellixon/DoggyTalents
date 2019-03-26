package doggytalents.addon.itemphysic;

import doggytalents.ModBlocks;
import doggytalents.ModItems;
import doggytalents.addon.AddonEvent;
import doggytalents.addon.AddonManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @author ProPercivalalb
 */
public class ItemPhysicAddon {

	private static ItemPhysicAPI API;

	@SubscribeEvent
	public void onPost(AddonEvent.Post event) throws Exception {
		if(!AddonManager.areModsLoaded(ItemPhysicLib.MOD_ID, ItemPhysicLib.LIB_ID))
			return;
		
		API = new ItemPhysicAPI();

		API.addSortingBlocks(ItemPhysicLib.BURNING_ITEMS_FIELD, ModBlocks.DOG_BED);
		API.addSortingItems(ItemPhysicLib.BURNING_ITEMS_FIELD, ModItems.BREEDING_BONE, ModItems.DIRE_TREAT, ModItems.MASTER_TREAT, ModItems.SUPER_TREAT, 
				ModItems.TRAINING_TREAT, ModItems.COLLAR_SHEARS, ModItems.THROW_BONE, ModItems.RADAR, ModItems.WOOL_COLLAR, ModItems.TREAT_BAG, ModItems.CHEW_STICK);
			
		API.addSortingItems(ItemPhysicLib.SWIMMING_ITEMS_FIELD, ModItems.BREEDING_BONE, ModItems.DIRE_TREAT, ModItems.MASTER_TREAT, ModItems.SUPER_TREAT, 
				ModItems.TRAINING_TREAT, ModItems.COLLAR_SHEARS, ModItems.THROW_BONE, ModItems.WOOL_COLLAR, ModItems.TREAT_BAG, ModItems.CHEW_STICK);
	}

}
