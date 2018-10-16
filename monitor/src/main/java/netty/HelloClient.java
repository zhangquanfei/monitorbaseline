package netty;
//HelloClient

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

public class HelloClient {
    public static EventLoopGroup group=null;
    public static Bootstrap boostrap=null;
    static{
        group = new NioEventLoopGroup(4);
        boostrap = new Bootstrap();
        boostrap.channel(NioSocketChannel.class);
        boostrap.group(group);
        boostrap.option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch)
                            throws Exception {
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder
                                (Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new StringEncoder());


                    }
                });
    }

    public Object client(String[] ipAndPort,String data) {
        Object result = null;
        try {

            ChannelFuture future = boostrap.connect(ipAndPort[0], Integer.parseInt(ipAndPort[1])).sync();
            String person = "张三\r\n";

            future.channel().writeAndFlush(data+"\r\n");

            future.channel().closeFuture().sync();

            result = ClientHandler.channel.attr(AttributeKey.valueOf("ChannelKey")).get();

            System.out.println("result:" + result);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        return result;

    }

    public static void main(String[] args) {}


}
