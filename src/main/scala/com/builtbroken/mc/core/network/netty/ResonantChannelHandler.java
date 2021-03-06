package com.builtbroken.mc.core.network.netty;

import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.core.network.packet.*;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author tgame14
 * @since 31/05/14
 */
public class ResonantChannelHandler extends FMLIndexedMessageToMessageCodec<AbstractPacket>
{
    public boolean silenceStackTrace = false; //TODO add command and config

    public ResonantChannelHandler()
    {
        this.addDiscriminator(0, PacketTile.class);
        this.addDiscriminator(1, PacketEntity.class);
        this.addDiscriminator(2, PacketPlayerItem.class);
        this.addDiscriminator(3, PacketPlayerItemMode.class);
        this.addDiscriminator(4, PacketSelectionData.class);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, AbstractPacket packet, ByteBuf target) throws Exception
    {
        try
        {
            packet.encodeInto(ctx, target);
        }
        catch (Exception e)
        {
            if (!silenceStackTrace)
                Engine.instance.logger().error("Failed to encode packet " + packet, e);
            else
                Engine.instance.logger().error("Failed to encode packet " + packet + " E: " + e.getMessage());
        }
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, AbstractPacket packet)
    {
        try
        {
            packet.decodeInto(ctx, source);
        }
        catch (Exception e)
        {
            if (!silenceStackTrace)
                Engine.instance.logger().error("Failed to decode packet " + packet, e);
            else
                Engine.instance.logger().error("Failed to decode packet " + packet + " E: " + e.getMessage());
        }
    }
}
