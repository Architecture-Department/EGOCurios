package architecture.ego_curios.client.model;

import architecture.goldenboughs_lib.client.model.BasicGeoModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoAnimatable;

public class GeoCurioModel<T extends Item & GeoAnimatable> extends BasicGeoModel<T> {

	public GeoCurioModel(ResourceLocation pathName) {
		super(getPath(pathName));
	}

	public GeoCurioModel(ResourceLocation modelPath, ResourceLocation textureName, ResourceLocation animationsName) {
		super(getPath(modelPath), getPath(textureName), getPath(animationsName));
	}

	public static ResourceLocation getPath(ResourceLocation pathName) {
		return pathName.withPrefix("curio/");
	}
}
