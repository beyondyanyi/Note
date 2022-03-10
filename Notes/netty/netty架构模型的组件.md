#### Bootstrap和ServerBootstrap

Netty 应用程序通过设置 bootstrap（引导）类的开始，该类提供了一个 用于应用程序网络层配置的容器。

#### Channel

底层网络传输API必须提供给应用I/O操作的接口，如读、写、绑定等。 

Netty 中的接口 Channel 定义了与 socket 丰富交互的操作集：bind, close, config, connect, isActive, isOpen, isWritable, read, write 等等。

Netty提供大量的Channel实现来专门使用。这些包括AbstractChannel、AbstractNioByteChannel、AbstractNioChannel、EmbeddedChannel、LocalServerChannel、NioSocketChannel等



#### ChannelHandler

ChannelHandler支持很多协议，并且提供用于数据处理的容器。ChannelHandler几乎可以用于所有动作，包括将一个对象转为字节，执行过程中抛出的异常处理。

常用的一个接口是ChannelInboundHandler,这个类型接口到入站事件可以处理应用程序逻辑。当你需要提供响应时，你也可以从ChannelInboundHandler冲刷数据。一句话，业务逻辑经常存活于一个或者多个ChannelInboundHandler。

#### ChannelPipeline

ChannelPipeline提供了一个容器给ChannelHandler链并提供了一个API用于管理沿着链入站和出站事件的流动。每个Channel都有自己的ChannelPipeline,当Channel创建时自动创建的。

ChannelHandler 是如何安装在 ChannelPipeline？ 

主要是实现了ChannelHandler 的抽象 ChannelInitializer。ChannelInitializer子类 通过 ServerBootstrap 进行注册。当它的方法 initChannel() 被调用时，这个对象将安装自定义的 ChannelHandler 集到 pipeline。当这个操作完成时，ChannelInitializer 子类则 从 ChannelPipeline 自动删除自身。

#### EventLoop

EventLoop用于处理Channel的I/O操作。一个单一的EventLoop通常会处理多个Channel事件。一个EventLoopGroup可以含有多于一个的EventLoop和提供了一种迭代用于检索清单中的下一个。

#### ChannelFuture

Netty 所有的 I/O 操作都是异步。因为一个操作可能无法立即返回，我们需要有一种方法在以后确定它的结果。出于这个目的，Netty 提供了接口 ChannelFuture,它的 addListener 方法注册了一个 ChannelFutureListener ，当操作完成时，可以被通知（不管成功与否）。





























