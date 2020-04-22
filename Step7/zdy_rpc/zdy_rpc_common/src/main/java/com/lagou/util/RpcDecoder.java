package com.lagou.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @ClassName RpcDecoder
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/22 5:28 PM
 * @Version 1.0
 **/
public class RpcDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Class<?> clazz;
    private Serializer serializer;

    public RpcDecoder(Class<?> clazz, Serializer serializer) {

        this.clazz = clazz;
        this.serializer = serializer;

    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {

        //获取消息头所标识的消息体字节数组长度
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        //若当前可以获取到的字节数小于实际长度,则直接返回,直到当前可以获取到的字节数等于实际长度
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        //读取完整的消息体字节数组
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        //将字节数组反序列化为java对象(SerializerEngine参考序列化与反序列化章节)
        Object obj = serializer.deserialize(clazz, data);

        out.add(obj);
    }
}
