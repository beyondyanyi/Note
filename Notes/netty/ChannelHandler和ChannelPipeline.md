##### ChannelPipeline就是ChannelHandler链的容器。

在我们的应用程序中ChannelHandler是许多方面的核心，或许它作为核心的表现并不明显。



##### ChannelOutboundHandler和ChannelInboundHandler

出站：应用程序——》远程主机

入站：远程主机——》应用程序



为了使数据从一端到另一端，一个或多个ChannelHander将以某种方式操作数据。这些ChannelHandler会在程序的“引导”阶段被添加ChannelPipeline中，并且被添加的顺序将决定处理数据的顺序



##### 入站：

如果消息或任何其他入站事件被读到，将从pipeline头部开始，传递到第一个ChannelInboundHandler,处理完后，将会传递到链中的下一个ChannelInboundHandler,最后达到pipeline的尾部，此时入站处理结束。

##### 出站：

出站运动是和入站相同的，从pipeline尾部流过ChannelOutboundHandlers的链，直到它到达头部。超过这点，出站数据将达到网络传输，在这里显示为一个socket，通常这将触发一个写入操作。



在当前的链中，事件可以通过ChannelHandlerContext传递给下一个handler。Netty为此提供了抽象基类ChannelInboundHandlerAdapter和ChannelOutboundHandlerAdapter,用来处理你想要的事件。



当ChannelHandler被添加到的ChannelPipeline它得到一个ChannelHandlerContext,他代表一个ChannelHandler和ChannelPipeline之间的“绑定”。它通常是安全保存对此对象的引用，除了当协议中的使用的是不面向连接。而该对象可以被用来获得底层Channel,它主要用来写出站数据。



实际上，在 Netty 发送消息可以采用两种方式：直接写消息给 Channel 或者写入 ChannelHandlerContext 对象。这两者主要的区别是， 前一种方法会导致消息从 ChannelPipeline的尾部开始，而后者导致消息从 ChannelPipeline 下一个处理器开始。



##### ChannelHandler

有几个适配器类，可以减少编写自定义ChannelHandlers，因为他们提供对应接口的所有方法的默认实现。

（ChannelHandlerAdapter、ChannelInboundHandlerAdapter、ChannelOutboundHandlerAdapter、ChannelDuplexHandlerAdapter）



##### 编码器、解码器

解码：入站消息将从字节转为一个Java对象

编码：从一个Java对象转为字节

ByteToMessageDecoder或MessageToByteEncoder

特殊：ProtobufEncoder和ProtobufDecoder,用于支持谷歌的protocol buffer

所有的编码器/解码器适配类都实现自ChannelInboundHandler或ChannelOutboundHandler



入站数据：channelRead方法/事件被覆盖。这种方法在每个消息入站Channel读入时调用。该方法将调用特定解码器的“解码”方法，并将解码后的消息转发到管道中的下个ChannelInboundHandler



##### channelRead和channelRead0方法的区别：

访问：

channelRead是public,channelRead0是protected

其他：

channelRead会调用channelRead0,channelRead会判断数据类型决定是否执行下一个handler

```
 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        boolean release = true;

        try {
            if (this.acceptInboundMessage(msg)) {
                this.channelRead0(ctx, msg);
            } else {
                release = false;
                ctx.fireChannelRead(msg);
            }
        } finally {
            if (this.autoRelease && release) {
                ReferenceCountUtil.release(msg);
            }

        }

    }
```

