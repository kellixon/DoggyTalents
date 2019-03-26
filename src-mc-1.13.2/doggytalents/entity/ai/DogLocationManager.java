package doggytalents.entity.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import doggytalents.DoggyTalentsMod;
import doggytalents.configuration.ConfigHandler;
import doggytalents.entity.EntityAbstractDog;
import doggytalents.entity.EntityDog;
import doggytalents.lib.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.storage.WorldSavedDataStorage;
import net.minecraftforge.common.DimensionManager;

public class DogLocationManager extends WorldSavedData {
	
	public List<DogLocation> locations;
	
	public DogLocationManager(String name) {
		super(name);
		this.locations = new ArrayList<DogLocation>();
	}
	
	/**
	 * Gets DogLocationHandler uses Overworld WorldSaveData as that should always be loaded
	 */
	public static DogLocationManager getHandler(World world) {
		WorldSavedDataStorage storage = world.getMapStorage();
		//World overworld = DimensionManager.getWorld(0);
		DogLocationManager locationManager = (DogLocationManager)storage.func_212426_a(world.dimension.getType(), DogLocationManager::new, "dog_locations");
		
    	if (locationManager == null) {
            locationManager = new DogLocationManager("dog_locations");
			storage.func_212424_a(world.dimension.getType(), "dog_locations", locationManager);
			if(ConfigHandler.CONFIG.debugMode()) System.out.println("DATA DECLARED: "+storage.func_212426_a(world.dimension.getType(), DogLocationManager::new, "dog_locations"));
		}
    	
    	return locationManager;
	}
	
	public void addOrUpdateLocation(EntityDog dog) {
		DogLocation temp = new DogLocation(dog);
		for(int i = 0; i < this.locations.size(); i++) {
			DogLocation loc = this.locations.get(i);
			
			if (loc.equals(temp)) {
				this.locations.set(i, temp);
				this.markDirty();
				return;
			}
		}
		if(ConfigHandler.CONFIG.debugMode()) System.out.println("ADDED NEW DATA: " + temp);
		this.locations.add(temp);
		this.markDirty();
	}
	
	/**
	 * This should be run whenever a dog is loaded back into a world after being unloaded at some point
	 */
	public void updateEntityId(EntityAbstractDog dog) {
		/**
		DimensionType dogDim = dog.world.dimension.getType();
		
		// Matches this dog with it's saved DogLocation
		for (int i = 0; i < this.locations.size(); i++) {
			DogLocation loc = this.locations.get(i);
            if (loc) {
            	
            	loc.entityId = dog.getUniqueID();
                if(ConfigHandler.CONFIG.debugMode()) System.out.println("RELOADED DATA: " + dog);
            	return;
            }
		}**/
	}

	public void removeDog(EntityDog dog) {
		if(ConfigHandler.CONFIG.debugMode()) System.out.println("REMOVED DATA: "+ dog);

		DogLocation temp = new DogLocation(dog);
		this.locations.remove(temp);
		this.markDirty();
	}

	public boolean axisCoordEqual(double a, double b) {
		return Math.abs(a - b) < 1e-4;
	}

	@Override
	public void read(NBTTagCompound nbt) {
		NBTTagList nbtlist = nbt.getList("dog_locations", 10);
		
		for (int i = 0; i < nbtlist.size(); i++) {
			this.locations.add(new DogLocation((NBTTagCompound)nbtlist.get(i)));
		}
	}

	@Override
	public NBTTagCompound write(NBTTagCompound compound) {
		NBTTagCompound base = new NBTTagCompound();
		
		NBTTagList nbtlist = new NBTTagList();
		
		for (DogLocation location : this.locations) {
			nbtlist.add(location.writeToNBT(new NBTTagCompound()));
		}
		
		base.setTag("dog_locations", nbtlist);
		
		return base;
	}
	
	public class DogLocation {

		public double x, y, z;
		public DimensionType dim;
		public UUID entityId;

		// Dog Data
		public String name;
		public String gender;
		public boolean hasRadarCollar;
		
		public DogLocation(NBTTagCompound nbt) {
			this.readFromNBT(nbt);
		}
		
		public DogLocation(EntityDog dog) {
			this.x = dog.posX;
			this.y = dog.posY;
			this.z = dog.posZ;
			this.dim = dog.world.dimension.getType();
			this.entityId = dog.getUniqueID();
			
			this.name = dog.getName().getUnformattedComponentText();
			this.gender = dog.getGender();
			this.hasRadarCollar = dog.hasRadarCollar();
		}

		public void readFromNBT(NBTTagCompound compound) {
			this.x = compound.getDouble("x");
			this.y = compound.getDouble("y");
			this.z = compound.getDouble("z");
			this.dim = DimensionType.byName(new ResourceLocation(compound.getString("dim")));
			this.entityId = compound.getUniqueId("entityId");
			
			this.name = compound.getString("name");
			this.gender = compound.getString("gender");
			this.hasRadarCollar = compound.getBoolean("collar");
		}

		public NBTTagCompound writeToNBT(NBTTagCompound compound) {
			compound.setDouble("x", this.x);
			compound.setDouble("y", this.y);
			compound.setDouble("z", this.z);
			compound.setString("dim", DimensionType.func_212678_a(this.dim).toString());
			compound.setUniqueId("entityId", this.entityId);
			
			compound.setString("name", this.name);
			compound.setString("gender", this.gender);
			compound.setBoolean("collar", this.hasRadarCollar);
			return compound;
		}
		
		
		public EntityDog getDog(EntityPlayerMP player) {
			if(this.entityId == null) {
				return null;
			}
			
			World world = DimensionManager.getWorld(player.getServer(), this.dim, false, false);
			Entity entity = ((WorldServer)world).getEntityFromUuid(this.entityId);
			
			if(entity instanceof EntityDog) 
				return (EntityDog)entity;
			
			return null;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DogLocation)) return false;
			
			DogLocation other = (DogLocation)obj;
			
			return this.entityId != null && other.entityId != null && this.entityId.equals(other.entityId);
		}
		
		/*@Override
		public int hashCode() {
			return this.entityId;
		}*/
		
		@Override
		public String toString() {
			return String.format("DogLocation [id=%s, x=%f,y=%f, z=%f, dim=%s]", this.entityId.toString(), this.x, this.y, this.z, this.dim.toString());
		}
	}
}
