package resonant.lib.utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import resonant.api.IRemovable;
import resonant.api.IRemovable.ICustomRemoval;
import resonant.api.IRemovable.IPickup;
import resonant.api.IRemovable.ISneakPickup;
import resonant.api.IRemovable.ISneakWrenchable;
import resonant.api.IRemovable.IWrenchable;
import resonant.lib.utility.inventory.InventoryUtility;
import universalelectricity.api.vector.VectorWorld;

/** Interaction handler for the player allowing the detection of block interaction without the block
 * detecting it.
 * 
 * @author Darkguardsman */
public class PlayerInteractionHandler
{
    @ForgeSubscribe
    public void onPlayInteract(PlayerInteractEvent event)
    {
        if (event.entityPlayer != null)
        {
            VectorWorld vec = new VectorWorld(event.entityPlayer.worldObj, event.x, event.y, event.z);
            //int block_id = vec.getBlockID(); For later use
            //int block_meta = vec.getBlockMetadata();
            //Block block = Block.blocksList[block_id];
            TileEntity tile = vec.getTileEntity();
            
            
            if (event.action == Action.RIGHT_CLICK_BLOCK)
            {
                //Handle IRemovable allow more uniform removal of blocks
                if (tile instanceof IRemovable)
                {
                    boolean do_drop = false;
                    List<ItemStack> drops = ((IRemovable) tile).getRemovedItems(event.entityPlayer);

                    if (tile instanceof ICustomRemoval)
                    {
                        do_drop = ((ICustomRemoval) tile).canBeRemoved(event.entityPlayer);
                    }
                    else if (tile instanceof ISneakWrenchable)
                    {
                        do_drop = event.entityPlayer.isSneaking() && WrenchUtility.isHoldingWrench(event.entityPlayer);
                    }
                    else if (tile instanceof IWrenchable)
                    {
                        do_drop = WrenchUtility.isHoldingWrench(event.entityPlayer);
                    }
                    else if (tile instanceof ISneakPickup)
                    {
                        do_drop = event.entityPlayer.isSneaking();
                    }
                    else
                    {
                        do_drop = tile instanceof IPickup;
                    }

                    if (do_drop)
                    {
                        //Not sure if we need to cancel but there is nothing to right click after this
                        if (event.isCancelable())
                            event.setCanceled(true);

                        //Drop all items
                        if (drops != null && !drops.isEmpty())
                        {
                            for (ItemStack item : drops)
                            {
                                if (!event.entityPlayer.inventory.addItemStackToInventory(item))
                                    InventoryUtility.dropItemStack(vec, item);
                            }
                        }

                        vec.setBlock(0);
                    }
                }
            }
        }
    }
}
