#### Boostraping出现在Netty配置程序的过程中，Boostrapping在给服务器绑定指定窗口或者要连接客户端的时候会使用到





##### Boostrapping有以下两种类型：

```
客户端Boostrap

服务端ServerBootstrap
```

不管程序使用哪种协议，创建的是一个客户端还是服务器，Boostrap都是必须要用到的



##### Boostrap和ServerBootstrap之间的差异：

| 分类                | Bootstrap            | ServerBootstrap |
| ------------------- | -------------------- | --------------- |
| 网络功能            | 连接到远程主机和端口 | 绑定本地端口    |
| EventLoopGroup 数量 | 1                    | 2               |



一个ServerBoostrap可以认为有2个Channel集合，第一个集合包含一个单例的ServerChannel,代表持有一个绑定了本地端口的socket;第二个集合包含所有创建的Channel,处理服务器所接收到的客户端进来的连接。





































