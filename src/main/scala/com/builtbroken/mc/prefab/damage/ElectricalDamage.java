package com.builtbroken.mc.prefab.damage;

/**
 * Electrical based damage source
 *
 * @author Darkguardsman
 */
public class ElectricalDamage extends AbstractDamageSource
{
    //TODO replace code calclavia removed, add Factory to setup damage type correctly
    //TODO add armor handling so metal armors cause more not less damage
    //TODO add power armor support to charge and damage the armor
    //TODO damage electric items in the player's inventory
    //TODO destory some weak items
    //TODO catch player on fire
	public ElectricalDamage()
	{
		super("electrocution");
		this.setDifficultyScaled();
	}
}