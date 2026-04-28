package architecture.ego_curios.common.item;

import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;

/**
 * 理解 后背 饰品
 */
public class ComprehensionBackCurioItem extends EgoCurioItem {
	public ComprehensionBackCurioItem(Builder<ComprehensionBackCurioItem> egoCurioBuilder) {
		super(egoCurioBuilder);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		super.registerControllers(controllers);
		controllers.add(
			new AnimationController<>(this, "controller", 20, (a) -> {
				return PlayState.STOP;
			})
		);
	}
}
