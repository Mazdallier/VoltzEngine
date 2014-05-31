package resonant.core.content.debug

import resonant.lib.content.module.prefab.TileElectric
import universalelectricity.api.core.grid.electric.EnergyStorageHandler
import net.minecraft.block.material.Material

/**
 * @since 31/05/14
 * @author tgame14
 */
class STileInfiniteEnergy(mat: Material) extends TileElectric(mat)
{
    this.electricNode
    this.setEnergyHandler(new EnergyStorageHandler(Double.MaxValue))
    this.getEnergyHandler.setMaxExtract(Double.MaxValue)
    this.ioMap = 728

    override def updateEntity () : Unit =
    {
        super.updateEntity()
        this.getEnergyHandler.setEnergy(Double.MaxValue)
        produce
    }



}
