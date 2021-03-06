package doggytalents.item;

import doggytalents.configuration.ConfigHandler;
import doggytalents.entity.EntityDog;
import doggytalents.entity.ai.DogLocationManager;
import doggytalents.entity.ai.DogLocationManager.DogLocation;
import doggytalents.entity.features.DogGenderFeature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * @author ProPercivalalb
 **/
public class ItemRadarCreative extends Item {
	
	public ItemRadarCreative(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if(!worldIn.isRemote) {
			DogLocationManager locationManager = DogLocationManager.getHandler(worldIn);

			DimensionType playerDimID = playerIn.dimension; //Get current player dimension
			String playerDimName = DimensionType.getKey(playerDimID).toString();

			if (locationManager.locations.isEmpty()) {
				playerIn.sendMessage(new TextComponentTranslation("dogradar.errornull"));
			}
			
			boolean noRadioCollar = false;
			
			for(DogLocation loc : locationManager.locations) { //For every entry, find the dog's location and tell the player where it is
				EntityDog dog = loc.getDog((EntityPlayerMP)playerIn);
				DogGenderFeature genderUtil = new DogGenderFeature(dog);


				if(dog == null || loc.dim == playerDimID && dog.canInteract(playerIn)){
					String translateStr;

					// Angle between -pi and pi
					double angle = MathHelper.atan2(loc.x - playerIn.posX, loc.z - playerIn.posZ);

					if (angle < -Math.PI + Math.PI / 8)
						translateStr = "dogradar.north";
					else if (angle < -Math.PI + 3 * Math.PI / 8)
						translateStr = "dogradar.north.west";
					else if (angle < -Math.PI + 5 * Math.PI / 8)
						translateStr = "dogradar.west";
					else if (angle < -Math.PI + 7 * Math.PI / 8)
						translateStr = "dogradar.south.west";
					else if (angle < -Math.PI + 9 * Math.PI / 8)
						translateStr = "dogradar.south";
					else if (angle < -Math.PI + 11 * Math.PI / 8)
						translateStr = "dogradar.south.east";
					else if (angle < -Math.PI + 13 * Math.PI / 8)
						translateStr = "dogradar.east";
					else if (angle < -Math.PI + 15 * Math.PI / 8)
						translateStr = "dogradar.north.east";
					else
						translateStr = "dogradar.north";


					float f = (float) (loc.x - playerIn.posX);
					float f1 = (float) (loc.y - playerIn.posY);
					float f2 = (float) (loc.z - playerIn.posZ);

					playerIn.sendMessage(new TextComponentTranslation(translateStr, loc.name, (int) Math.ceil(MathHelper.sqrt(f * f + f1 * f1 + f2 * f2))));
				}
			}

			if(noRadioCollar) {
				playerIn.sendMessage(new TextComponentTranslation("dogradar.errornoradio"));
			}
			
			if(ConfigHandler.COMMON.debugMode()) playerIn.sendMessage(new TextComponentString("Size: " + locationManager.locations.size())); //Display the total number of entries, debugging purposes only

		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}

	/*	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote) {
			DogLocationManager locationManager = DogLocationManager.getHandler(worldIn);


			int playerDimId = playerIn.dimension;

			for (DogLocation loc : locationManager.locations) {

				if (loc.dim == playerDimId) {
					EntityDog dog = loc.getDog();

					if (dog == null ? !loc.hasRadarCollar : !dog.hasRadarCollar() || !dog.isOwner(playerIn)) continue;

					String translateStr = "dogradar.error";

					// Angle between -pi and pi
					double angle = MathHelper.atan2(loc.x - playerIn.posX, loc.z - playerIn.posZ);

					if (angle < -Math.PI + Math.PI / 8)
						translateStr = "dogradar.north";
					else if (angle < -Math.PI + 3 * Math.PI / 8)
						translateStr = "dogradar.north.west";
					else if (angle < -Math.PI + 5 * Math.PI / 8)
						translateStr = "dogradar.west";
					else if (angle < -Math.PI + 7 * Math.PI / 8)
						translateStr = "dogradar.south.west";
					else if (angle < -Math.PI + 9 * Math.PI / 8)
						translateStr = "dogradar.south";
					else if (angle < -Math.PI + 11 * Math.PI / 8)
						translateStr = "dogradar.south.east";
					else if (angle < -Math.PI + 13 * Math.PI / 8)
						translateStr = "dogradar.east";
					else if (angle < -Math.PI + 15 * Math.PI / 8)
						translateStr = "dogradar.north.east";
					else
						translateStr = "dogradar.north";


					float f = (float) (loc.x - playerIn.posX);
					float f1 = (float) (loc.y - playerIn.posY);
					float f2 = (float) (loc.z - playerIn.posZ);

					playerIn.sendMessage(new TextComponentTranslation(translateStr, loc.name, (int) Math.ceil(MathHelper.sqrt(f * f + f1 * f1 + f2 * f2))));
				}
			}


			playerIn.sendMessage(new TextComponentString("Size: " + locationManager.locations.size()));


			for (DogLocation loc : locationManager.locations) {
				if (loc.dim == playerDimId) continue;
				EntityDog dog = loc.getDog();
				DogGenderUtil genderUtil = new DogGenderUtil(dog);

				if (dog == null ? !loc.hasRadarCollar : !dog.hasRadarCollar() || !dog.isOwner(playerIn)) continue;
				if(loc.dim != playerDimId) playerIn.sendMessage(new TextComponentTranslation("dogradar.notindim", loc.name, playerDimId, genderUtil.getGenderSubject(), loc.dim));
			}
		}
		*//*if(!worldIn.isRemote) {
			for(Entity entity : worldIn.loadedEntityList) {
				if(entity instanceof EntityDog) {
					EntityDog dog = (EntityDog)entity;

					if(dog.hasRadarCollar() && dog.canInteract(playerIn)) {
						StringBuilder builder = new StringBuilder();
						builder.append(dog.getName());
						builder.append(" is ");
						builder.append((int)Math.ceil(dog.getDistance(playerIn)));
						builder.append(" blocks away ");
						if(playerIn.posZ > dog.posZ)
							builder.append("north");
						else
							builder.append("south");

						if(playerIn.posX < dog.posX)
							builder.append(", east");
						else
								builder.append(", west");

						playerIn.sendMessage(new TextComponentString(builder.toString()));
					}
				}
			}
		}*//*
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}*/
}
