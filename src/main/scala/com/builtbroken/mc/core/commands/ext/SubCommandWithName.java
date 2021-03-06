package com.builtbroken.mc.core.commands.ext;

import com.builtbroken.mc.prefab.commands.AbstractCommand;
import com.builtbroken.mc.prefab.commands.SubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by robert on 2/18/2015.
 */
public class SubCommandWithName extends SubCommand
{
    public SubCommandWithName(String name)
    {
        super(name);
    }

    @Override
    public boolean handleEntityPlayerCommand(EntityPlayer player, String[] args)
    {
        if(args.length > 0)
        {
            return handleEntityPlayerCommand(player, args[0], removeFront(args));
        }
        return false;
    }

    @Override
    public boolean handleConsoleCommand(ICommandSender sender, String[] args)
    {
        if(args.length > 0)
        {
            return handleConsoleCommand(sender, args[0], removeFront(args));
        }
        return false;
    }

    public boolean handleEntityPlayerCommand(EntityPlayer player, String user, String[] args)
    {
        return false;
    }

    public boolean handleConsoleCommand(ICommandSender sender, String user, String[] args)
    {
        return false;
    }
}
