package doggytalents;

import doggytalents.base.ObjectLib;
import doggytalents.base.VersionControl;
import doggytalents.client.model.ModelHelper;
import doggytalents.item.ItemBigBone;
import doggytalents.item.ItemCapeColoured;
import doggytalents.item.ItemChewStick;
import doggytalents.item.ItemCommandEmblem;
import doggytalents.item.ItemDT;
import doggytalents.item.ItemDireTreat;
import doggytalents.item.ItemDoggyCharm;
import doggytalents.item.ItemFancyCollar;
import doggytalents.item.ItemRadar;
import doggytalents.item.ItemThrowBone;
import doggytalents.item.ItemTinyBone;
import doggytalents.item.ItemTreat;
import doggytalents.item.ItemTreatBag;
import doggytalents.item.ItemWhistle;
import doggytalents.item.ItemWoolCollar;
import doggytalents.lib.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author ProPercivalalb
 */
public class ModItems {

	public static Item THROW_BONE;
	public static Item COMMAND_EMBLEM;
	public static Item TRAINING_TREAT;
    public static Item SUPER_TREAT;
    public static Item MASTER_TREAT;
    public static Item DIRE_TREAT;
    public static Item BREEDING_BONE;
    public static Item COLLAR_SHEARS;
    public static Item DOGGY_CHARM;
    public static Item RADIO_COLLAR;
    public static Item WOOL_COLLAR;
    public static Item FANCY_COLLAR;
    public static Item RADAR;
    public static Item WHISTLE;
    public static Item TREAT_BAG;
    public static Item CHEW_STICK;
    public static Item CAPE;
    public static Item CAPE_COLOURED;
	public static Item SUNGLASSES;
	public static Item LEATHER_JACKET;
	public static Item TINY_BONE;
	public static Item BIG_BONE;
	
	public static void onRegister(Object registry) {
		DoggyTalents.LOGGER.info("Registering Items");
		THROW_BONE = VersionControl.createObject("ItemThrowBoneWrapper", ItemThrowBone.class).setUnlocalizedName("doggytalents.throwbone").setRegistryName(Reference.MOD_ID + ":throw_bone");
		COMMAND_EMBLEM = VersionControl.createObject("ItemCommandEmblemWrapper", ItemCommandEmblem.class).setUnlocalizedName("doggytalents.commandemblem").setRegistryName(Reference.MOD_ID + ":command_emblem");
		TRAINING_TREAT = new ItemTreat(20).setUnlocalizedName("doggytalents.trainingtreat").setRegistryName(Reference.MOD_ID + ":training_treat");
	    SUPER_TREAT = new ItemTreat(40).setUnlocalizedName("doggytalents.supertreat").setRegistryName(Reference.MOD_ID + ":super_treat");
	    MASTER_TREAT = new ItemTreat(60).setUnlocalizedName("doggytalents.mastertreat").setRegistryName(Reference.MOD_ID + ":master_treat");
	    DIRE_TREAT = new ItemDireTreat().setUnlocalizedName("doggytalents.diretreat").setRegistryName(Reference.MOD_ID + ":dire_treat");
	    BREEDING_BONE = new ItemDT().setUnlocalizedName("doggytalents.breedingbone").setRegistryName(Reference.MOD_ID + ":breeding_bone");
	    COLLAR_SHEARS = new ItemDT().setUnlocalizedName("doggytalents.collarshears").setMaxDamage(16).setRegistryName(Reference.MOD_ID + ":collar_shears");
	    DOGGY_CHARM = VersionControl.createObject("ItemDoggyCharmWrapper", ItemDoggyCharm.class).setUnlocalizedName("doggytalents.doggycharm").setRegistryName(Reference.MOD_ID + ":doggy_charm");
	    RADIO_COLLAR = new ItemDT().setUnlocalizedName("doggytalents.radiocollar").setRegistryName(Reference.MOD_ID + ":radio_collar");
	    WOOL_COLLAR = VersionControl.createObject("ItemWoolCollarWrapper", ItemWoolCollar.class).setUnlocalizedName("doggytalents.woolcollar").setRegistryName(Reference.MOD_ID + ":wool_collar");
	    FANCY_COLLAR = VersionControl.createObject("ItemFancyCollarWrapper", ItemFancyCollar.class).setUnlocalizedName("doggytalents.fancycollar").setRegistryName(Reference.MOD_ID + ":fancy_collar");
	    RADAR = VersionControl.createObject("ItemRadarWrapper", ItemRadar.class).setUnlocalizedName("doggytalents.radar").setRegistryName(Reference.MOD_ID + ":radar");
	    WHISTLE = new ItemWhistle().setUnlocalizedName("doggytalents.whistle").setRegistryName(Reference.MOD_ID + ":whistle");
	    TREAT_BAG = VersionControl.createObject("ItemTreatBagWrapper", ItemTreatBag.class).setUnlocalizedName("doggytalents.treatbag").setRegistryName(Reference.MOD_ID + ":treat_bag");
	    CHEW_STICK = VersionControl.createObject("ItemChewStickWrapper", ItemChewStick.class).setUnlocalizedName("doggytalents.chewstick").setRegistryName(Reference.MOD_ID + ":chew_stick");
	    CAPE = new ItemDT().setUnlocalizedName("doggytalents.cape").setRegistryName(Reference.MOD_ID + ":cape");
	    CAPE_COLOURED = VersionControl.createObject("ItemCapeColouredWrapper", ItemCapeColoured.class).setUnlocalizedName("doggytalents.capecoloured").setRegistryName(Reference.MOD_ID + ":cape_coloured");
	    SUNGLASSES = new ItemDT().setUnlocalizedName("doggytalents.sunglasses").setRegistryName(Reference.MOD_ID + ":sunglasses");
	 	LEATHER_JACKET = new ItemDT().setUnlocalizedName("doggytalents.leatherjacket").setRegistryName(Reference.MOD_ID + ":leather_jacket");
	 	TINY_BONE = new ItemTinyBone().setUnlocalizedName("doggytalents.tinybone").setRegistryName(Reference.MOD_ID + ":tiny_bone");
		BIG_BONE = new ItemBigBone().setUnlocalizedName("doggytalents.bigbone").setRegistryName(Reference.MOD_ID + ":big_bone");
	 	
		ObjectLib.REGISTRY.registerItem(registry, THROW_BONE);
	    ObjectLib.REGISTRY.registerItem(registry, TRAINING_TREAT);
	    ObjectLib.REGISTRY.registerItem(registry, SUPER_TREAT);
	    ObjectLib.REGISTRY.registerItem(registry, MASTER_TREAT);
	    ObjectLib.REGISTRY.registerItem(registry, DIRE_TREAT);
	    ObjectLib.REGISTRY.registerItem(registry, BREEDING_BONE);
	    ObjectLib.REGISTRY.registerItem(registry, COLLAR_SHEARS);
	    ObjectLib.REGISTRY.registerItem(registry, COMMAND_EMBLEM);
	    ObjectLib.REGISTRY.registerItem(registry, DOGGY_CHARM);
	    ObjectLib.REGISTRY.registerItem(registry, RADIO_COLLAR);
	    ObjectLib.REGISTRY.registerItem(registry, WOOL_COLLAR);
	    ObjectLib.REGISTRY.registerItem(registry, FANCY_COLLAR);
	    ObjectLib.REGISTRY.registerItem(registry, RADAR);
	    ObjectLib.REGISTRY.registerItem(registry, WHISTLE);
	    ObjectLib.REGISTRY.registerItem(registry, TREAT_BAG);
	    ObjectLib.REGISTRY.registerItem(registry, CHEW_STICK);
	    ObjectLib.REGISTRY.registerItem(registry, CAPE);
	    ObjectLib.REGISTRY.registerItem(registry, SUNGLASSES);
	    ObjectLib.REGISTRY.registerItem(registry, CAPE_COLOURED);
	    ObjectLib.REGISTRY.registerItem(registry, LEATHER_JACKET);
	    ObjectLib.REGISTRY.registerItem(registry, TINY_BONE);
	    ObjectLib.REGISTRY.registerItem(registry, BIG_BONE);
	}
	
	@SideOnly(Side.CLIENT)
	public static void setItemModels() {
		ModelHelper.setModel(THROW_BONE, 0, "doggytalents:throw_bone");
		ModelHelper.setModel(THROW_BONE, 1, "doggytalents:throw_bone_wet");
		ModelHelper.setModel(THROW_BONE, 2, "doggytalents:throw_stick");
		ModelHelper.setModel(THROW_BONE, 3, "doggytalents:throw_stick_wet");
		ModelHelper.setModel(COMMAND_EMBLEM, 0, "doggytalents:command_emblem");
		ModelHelper.setModel(TRAINING_TREAT, 0, "doggytalents:training_treat");
		ModelHelper.setModel(SUPER_TREAT, 0, "doggytalents:super_treat");
		ModelHelper.setModel(MASTER_TREAT, 0, "doggytalents:master_treat");
		ModelHelper.setModel(DIRE_TREAT, 0, "doggytalents:dire_treat");
		ModelHelper.setModel(BREEDING_BONE, 0, "doggytalents:breeding_bone");
		ModelHelper.setModel(COLLAR_SHEARS, 0, "doggytalents:collar_shears");
		ModelHelper.setModel(DOGGY_CHARM, 0, "doggytalents:doggy_charm");
		ModelHelper.setModel(RADAR, 0, "doggytalents:radar");
		ModelHelper.setModel(RADIO_COLLAR, 0, "doggytalents:radio_collar");
		ModelHelper.setModel(WOOL_COLLAR, 0, "doggytalents:wool_collar");
		for(int i = 0; i < ItemFancyCollar.NO_COLLAR; i++)
			ModelHelper.setModel(FANCY_COLLAR, i, "doggytalents:fancy_collar_" + i);
		ModelHelper.setModel(WHISTLE, 0, "doggytalents:whistle");
		ModelHelper.setModel(TREAT_BAG, 0, "doggytalents:treat_bag");
		ModelHelper.setModel(CHEW_STICK, 0, "doggytalents:chew_stick");
		ModelHelper.setModel(CAPE, 0, "doggytalents:cape");
		ModelHelper.setModel(CAPE_COLOURED, 0, "doggytalents:cape_coloured");
		ModelHelper.setModel(SUNGLASSES, 0, "doggytalents:sunglasses");
		ModelHelper.setModel(LEATHER_JACKET, 0, "doggytalents:leather_jacket");
		ModelHelper.setModel(TINY_BONE, 0, "doggytalents:tiny_bone");
		ModelHelper.setModel(BIG_BONE, 0, "doggytalents:big_bone");
	}
}
