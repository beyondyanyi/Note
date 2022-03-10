##### 1.程序化配置方法

```
Config config = new Config();
config.setTransportMode(TransportMode.EPOLL);
//可以用"rediss://"来启用SSL连接
config.useClusterServers().addNodeAddress("redis://127.0.0.1:7181");
```



##### 2.文件配置方法

yaml格式配置文件

```
Redisson的配置文件可以是或YAML格式。 
也通过调用config.fromYAML方法并指定一个File实例来实现读取YAML格式的配置：

Config config = Config.fromYAML(new File("config-file.yaml"));
RedissonClient redisson = Redisson.create(config);

调用config.toYAML方法可以将一个Config配置实例序列化为一个含有YAML数据类型的字符串：
Config config = new Config();
// ... 省略许多其他的设置
String jsonFormat = config.toYAML();
```



##### 3.常用设置



##### codec(编码)

默认值：org.redisson.codec.JsonJacksonCodec



Redisson的对象编码类是用于将对象进行序列化和反序列化，以实现对该对象在Redis里的读取和存储。Redisson提供了以下几种的对象编码应用，以供大家选择：

| 编码类名称                                      | 说明                                                         |
| ----------------------------------------------- | ------------------------------------------------------------ |
| `org.redisson.codec.JsonJacksonCodec`           | [Jackson JSON](https://github.com/FasterXML/jackson) 编码 **默认编码** |
| `org.redisson.codec.AvroJacksonCodec`           | [Avro](http://avro.apache.org/) 一个二进制的JSON编码         |
| `org.redisson.codec.SmileJacksonCodec`          | [Smile](http://wiki.fasterxml.com/SmileFormatSpec) 另一个二进制的JSON编码 |
| `org.redisson.codec.CborJacksonCodec`           | [CBOR](http://cbor.io/) 又一个二进制的JSON编码               |
| `org.redisson.codec.MsgPackJacksonCodec`        | [MsgPack](http://msgpack.org/) 再来一个二进制的JSON编码      |
| `org.redisson.codec.IonJacksonCodec`            | [Amazon Ion](https://amzn.github.io/ion-docs/) 亚马逊的Ion编码，格式与JSON类似 |
| `org.redisson.codec.KryoCodec`                  | [Kryo](https://github.com/EsotericSoftware/kryo) 二进制对象序列化编码 |
| `org.redisson.codec.SerializationCodec`         | JDK序列化编码                                                |
| `org.redisson.codec.FstCodec`                   | [FST](https://github.com/RuedigerMoeller/fast-serialization) 10倍于JDK序列化性能而且100%兼容的编码 |
| `org.redisson.codec.LZ4Codec`                   | [LZ4](https://github.com/jpountz/lz4-java) 压缩型序列化对象编码 |
| `org.redisson.codec.SnappyCodec`                | [Snappy](https://github.com/xerial/snappy-java) 另一个压缩型序列化对象编码 |
| `org.redisson.client.codec.JsonJacksonMapCodec` | 基于Jackson的映射类使用的编码。可用于避免序列化类的信息，以及用于解决使用`byte[]`遇到的问题。 |
| `org.redisson.client.codec.StringCodec`         | 纯字符串编码（无转换）                                       |
| `org.redisson.client.codec.LongCodec`           | 纯整长型数字编码（无转换）                                   |
| `org.redisson.client.codec.ByteArrayCodec`      | 字节数组编码                                                 |
| `org.redisson.codec.CompositeCodec`             | 用来组合多种不同编码在一起                                   |



##### threads(线程池数量)

默认值：当前处理器核数*2

这个线程池数量被所有RTopic对象监听器，RRemoteService调用者和RExecutorService任务共同共享



##### nettyThreads(Netty线程池数量)

默认值：当前处理器核数*2

这个线程池数量是在一个Redisson实例内，被其创建的所有分布式数据类型和服务，以及底层客户端所一同共享的线程池里保存的线程数量



##### executor(线程池)

单独提供一个用来执行所有`RTopic`对象监听器，`RRemoteService`调用者和`RExecutorService`任务的线程池（ExecutorService）实例。



##### eventLoopGroup

用于特别指定一个EventLoopGroup. EventLoopGroup是用来处理所有通过Netty与Redis服务之间的连接发送和接受的消息。每一个Redisson都会在默认情况下自己创建管理一个EventLoopGroup实例。因此，如果在同一个JVM里面可能存在多个Redisson实例的情况下，采取这个配置实现多个Redisson实例共享一个EventLoopGroup的目的。



只有`io.netty.channel.epoll.EpollEventLoopGroup`或`io.netty.channel.nio.NioEventLoopGroup`才是允许的类型。



##### transportMode(传输模式)

默认值：TransportMode.NIO



可选参数：

​	TransportMode.NIO,

​	TransportMode.EPOLL -需要依赖里有netty-transport-native-epoll包（Linux）

​    TransportMode.KQUEUE -需要依赖里有netty-transport-native-kqueue包（macOS）



##### lockWatchdogTimeout(监控锁的看门狗超时，单位：毫秒)

默认值：30000

监控锁的看门狗超时时间单位为毫秒。该参数只适用于分布式锁的加锁请求中未明确使用`leaseTimeout`参数的情况。如果该看门口未使用`lockWatchdogTimeout`去重新调整一个分布式锁的`lockWatchdogTimeout`超时，那么这个锁将变为失效状态。这个参数可以用来避免由Redisson客户端节点宕机或其他原因造成死锁的情况。





##### keepPubSubOrder（保持订阅发布顺序）

默认值：`true`

通过该参数来修改是否按订阅发布消息的接收顺序出来消息，如果选否将对消息实行并行处理，该参数只适用于订阅发布消息的情况。



##### performanceMode（高性能模式）

默认值：`HIGHER_THROUGHPUT`

用来指定高性能引擎的行为。由于该变量值的选用与使用场景息息相关（`NORMAL`除外）我们建议对每个参数值都进行尝试。

*该参数仅限于[Redisson PRO](https://redisson.pro/)版本。*



可选模式：
`HIGHER_THROUGHPUT` - 将高性能引擎切换到 **高通量** 模式。

`LOWER_LATENCY_AUTO` - 将高性能引擎切换到 **低延时** 模式并自动探测最佳设定。 

`LOWER_LATENCY_MODE_1` - 将高性能引擎切换到 **低延时** 模式并调整到预设模式1。 `LOWER_LATENCY_MODE_2` - 将高性能引擎切换到 **低延时** 模式并调整到预设模式2。

`NORMAL` - 将高性能引擎切换到 **普通** 模式

