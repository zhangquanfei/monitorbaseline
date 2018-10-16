package netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    public static Channel channel = null;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
        System.out.println("连接成功");
        System.out.println(channel.id());
        // System.out.println("连接成功");
        //String person = "张三\r\n";

//			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
//			buf.writeBytes(person.getBytes(Charset.defaultCharset()));
       // this.channel.writeAndFlush(person);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("11111");
        System.out.println("form server "+msg.toString());
        ctx.channel().attr(AttributeKey.valueOf("ChannelKey")).set(msg.toString());
        //this.channel.attr(AttributeKey.valueOf("ChannelKey")).set(msg.toString());
        ctx.channel().close();
    }



}
