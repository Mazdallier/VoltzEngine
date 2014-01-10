package calclavia.lib.prefab.poison;

import java.util.EnumSet;

import net.minecraft.entity.EntityLivingBase;
import universalelectricity.api.vector.Vector3;
import calclavia.lib.CustomDamageSource;
import calclavia.lib.prefab.potion.CustomPotionEffect;

public class PoisonRadiation extends Poison
{
	public static final Poison INSTANCE = new PoisonRadiation("radiation");
	public static final CustomDamageSource damageSource = (CustomDamageSource) new CustomDamageSource("radiation").setDamageBypassesArmor();
	public static boolean disabled = false;

	public PoisonRadiation(String name)
	{
		super(name);
	}

	@Override
	public boolean isEntityProtected(Vector3 emitPosition, EntityLivingBase entity, int amplifier)
	{
		return (emitPosition != null ? this.getAntiPoisonBlockCount(entity.worldObj, emitPosition, new Vector3(entity)) <= amplifier : false) && super.isEntityProtected(emitPosition, entity, amplifier);
	}

	@Override
	protected void doPoisonEntity(Vector3 emitPosition, EntityLivingBase entity, int amplifier)
	{
		if (!PoisonRadiation.disabled)
		{
			entity.addPotionEffect(new CustomPotionEffect(PotionRadiation.INSTANCE.getId(), 20 * 15 * (amplifier + 1), amplifier, null));
		}
	}

}
