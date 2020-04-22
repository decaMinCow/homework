package com.lagou.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName RpcEncoder
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/22 4:00 PM
 * @Version 1.0
 **/
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> clazz;
    private Serializer serializer;

    public RpcEncoder(Class<?> clazz, Serializer serializer) {

        this.clazz = clazz;
        this.serializer = serializer;

    }

    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {

        if (clazz != null && clazz.isInstance(msg)) {

            byte[] bytes = serializer.serialize(msg);
            byteBuf.writeInt(bytes.length);
            byteBuf.writeBytes(bytes);

        }

    }

}