##### 1.集群模式

集群模式除了适用于Redis集群环境，<u>也适用于任何云计算服务商提供的集群模式</u>，例如[AWS ElastiCache集群版](http://docs.aws.amazon.com/AmazonElastiCache/latest/UserGuide/Clusters.html)、[Azure Redis Cache](https://azure.microsoft.com/en-us/services/cache/)和[阿里云（Aliyun）的云数据库Redis版](https://cn.aliyun.com/product/kvstore)。



###### 1）程序化配置集群的用法：

```
Config config = new Config();
config.useClusterServers()
    .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
    //可以用"rediss://"来启用SSL连接
    .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
    .addNodeAddress("redis://127.0.0.1:7002");

RedissonClient redisson = Redisson.create(config);
```

 Redis集群组态的最低要求是必须有三个主节点。Redisson的集群模式的使用方法如下：

```
ClusterServersConfig clusterConfig = config.useClusterServers();
```

`ClusterServersConfig` 类的设置参数如下：



###### nodeAddresses（添加节点地址）

可以通过`host:port`的格式来添加Redis集群节点的地址。多个节点可以一次性批量添加。



###### scanInterval（集群扫描间隔时间）

默认值： `1000`

对Redis集群节点状态扫描的时间间隔。单位是毫秒。



###### slots（分片数量）

默认值： `231` 用于指定数据分片过程中的分片数量。支持数据分片/框架结构有：[集（Set）](https://github.com/redisson/redisson/wiki/7.-分布式集合#732--集set数据分片sharding)、[映射（Map）](https://github.com/redisson/redisson/wiki/7.-分布式集合#711-映射map的元素淘汰eviction本地缓存localcache和数据分片sharding)、[BitSet](https://github.com/redisson/redisson/wiki/6.-分布式对象#641-bitset数据分片sharding分布式roaringbitmap)、[Bloom filter](https://github.com/redisson/redisson/wiki/6.-分布式对象#681-布隆过滤器数据分片sharding), [Spring Cache](https://github.com/redisson/redisson/wiki/14.-第三方框架整合#1421-spring-cache---本地缓存和数据分片)和[Hibernate Cache](https://github.com/redisson/redisson/wiki/14.-第三方框架整合#1431-hibernate二级缓存---本地缓存和数据分片)等.



###### readMode（读取操作的负载均衡模式）

默认值： `SLAVE`（只在从服务节点里读取）

注：在从服务节点里读取的数据说明已经至少有两个节点保存了该数据，确保了数据的高可用性。

设置读取操作选择节点的模式。 可用值为：

 `SLAVE` - 只在从服务节点里读取。 

`MASTER` - 只在主服务节点里读取。

 `MASTER_SLAVE` - 在主从服务节点里都可以读取。



###### subscriptionMode（订阅操作的负载均衡模式）

默认值：`SLAVE`（只在从服务节点里订阅）

设置订阅操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里订阅。 `MASTER` - 只在主服务节点里订阅。



###### loadBalancer（负载均衡算法类的选择）

默认值： `org.redisson.connection.balancer.RoundRobinLoadBalancer`

在多Redis服务节点的环境里，可以选用以下几种负载均衡方式选择一个节点： 

`org.redisson.connection.balancer.WeightedRoundRobinBalancer` - 权重轮询调度算法 `org.redisson.connection.balancer.RoundRobinLoadBalancer` - 轮询调度算法 

`org.redisson.connection.balancer.RandomLoadBalancer` - 随机调度算法



###### subscriptionConnectionMinimumIdleSize（从节点发布和订阅连接的最小空闲连接数）

默认值：`1`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的最小保持连接数（长连接）。Redisson内部经常通过发布和订阅来实现许多功能。长期保持一定数量的发布订阅连接是必须的。



###### subscriptionConnectionPoolSize（从节点发布和订阅连接池大小）

默认值：`50`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。



###### slaveConnectionMinimumIdleSize（从节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时读取反映速度。



###### slaveConnectionPoolSize（从节点连接池大小）

默认值：`64`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）连接的连接池最大容量。连接池的连接数量自动弹性伸缩。



###### masterConnectionMinimumIdleSize（主节点最小空闲连接数）

默认值：`32`

多节点的环境里，**每个** 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。



###### masterConnectionPoolSize（主节点连接池大小）

默认值：`64`

多主节点的环境里，**每个** 主节点的连接池最大容量。连接池的连接数量自动弹性伸缩。



###### idleConnectionTimeout（连接空闲超时，单位：毫秒）

默认值：`10000`

如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。



###### connectTimeout（连接超时，单位：毫秒）

默认值：`10000`

同任何节点建立连接时的等待超时。时间单位是毫秒。



###### timeout（命令等待超时，单位：毫秒）

默认值：`3000`

等待节点回复命令的时间。该时间从命令发送成功时开始计时。



###### retryAttempts（命令失败重试次数）

默认值：`3`

如果尝试达到 **retryAttempts（命令失败重试次数）** 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 **timeout（命令等待超时）** 计时。



###### retryInterval（命令重试发送时间间隔，单位：毫秒）

默认值：`1500`

在某个节点执行相同或不同命令时，**连续** 失败 **failedAttempts（执行失败最大次数）** 时，该节点将被从可用节点列表里清除，直到 **reconnectionTimeout（重新连接时间间隔）** 超时以后再次尝试。



###### password（密码）

默认值：`null`

用于节点身份验证的密码。



###### subscriptionsPerConnection（单个连接最大订阅数量）

默认值：`5`

每个连接的最大订阅数量。



###### clientName（客户端名称）

默认值：`null`

在Redis节点里显示的客户端名称。



###### sslEnableEndpointIdentification（启用SSL终端识别）

默认值：`true`

开启SSL终端识别能力。



###### sslProvider（SSL实现方式）

默认值：`JDK`

确定采用哪种方式（JDK或OPENSSL）来实现SSL连接。



###### sslTruststore（SSL信任证书库路径）

默认值：`null`

指定SSL信任证书库的路径。



###### sslTruststorePassword（SSL信任证书库密码）

默认值：`null`

指定SSL信任证书库的密码。



###### sslKeystore（SSL钥匙库路径）

默认值：`null`

指定SSL钥匙库的路径。



###### sslKeystorePassword（SSL钥匙库密码）

默认值：`null`

指定SSL钥匙库的密码。



###### 2）通过YAML文件配置集群模式

配置集群模式可以通过指定一个YAML格式的文件来实现。以下是YAML格式的配置文件样本。文件中的字段名称必须与`ClusterServersConfig`和`Config`对象里的字段名称相符。



```yaml
---
clusterServersConfig:
  idleConnectionTimeout: 10000
  connectTimeout: 10000
  timeout: 3000
  retryAttempts: 3
  retryInterval: 1500
  password: null
  subscriptionsPerConnection: 5
  clientName: null
  loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
  slaveSubscriptionConnectionMinimumIdleSize: 1
  slaveSubscriptionConnectionPoolSize: 50
  slaveConnectionMinimumIdleSize: 32
  slaveConnectionPoolSize: 64
  masterConnectionMinimumIdleSize: 32
  masterConnectionPoolSize: 64
  readMode: "SLAVE"
  nodeAddresses:
  - "redis://127.0.0.1:7004"
  - "redis://127.0.0.1:7001"
  - "redis://127.0.0.1:7000"
  scanInterval: 1000
threads: 0
nettyThreads: 0
codec: !<org.redisson.codec.JsonJacksonCodec> {}
"transportMode":"NIO"
```



##### 2.云托管模式

1)程序化配置云托管模式的方法如下：

```
Config config = new Config();
config.useReplicatedServers()
    .setScanInterval(2000) // 主节点变化扫描间隔时间
    //可以用"rediss://"来启用SSL连接
    .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
    .addNodeAddress("redis://127.0.0.1:7002");

RedissonClient redisson = Redisson.create(config);
```

云托管模式设置：

```
ReplicatedServersConfig replicatedConfig = config.useReplicatedServers();
```

ReplicatedServersConfig类的设置参数：



###### nodeAddresses（节点地址）

可以通过`host:port`的格式来指定云托管模式的多个Redis集群节点的地址。多个节点可以一次性批量添加。所有的主从节点必须在配置阶段全部体现出来。



###### scanInterval（主节点变化扫描间隔时间）

默认值： `1000`

对主节点变化节点状态扫描的时间间隔。单位是毫秒。



###### loadBalancer（负载均衡算法类的选择）

默认值： `org.redisson.connection.balancer.RoundRobinLoadBalancer`

在使用多个Elasticache Redis服务节点的环境里，可以选用以下几种负载均衡方式选择一个节点： `org.redisson.connection.balancer.WeightedRoundRobinBalancer` - 权重轮询调度算法 `org.redisson.connection.balancer.RoundRobinLoadBalancer` - 轮询调度算法 `org.redisson.connection.balancer.RandomLoadBalancer` - 随机调度算法



###### dnsMonitoringInterval（DNS监控间隔，单位：毫秒）

默认值：`5000`

用来指定检查节点DNS变化的时间间隔。使用的时候应该确保JVM里的DNS数据的缓存时间保持在足够低的范围才有意义。用`-1`来禁用该功能。



###### subscriptionConnectionMinimumIdleSize（从节点发布和订阅连接的最小空闲连接数）

默认值：`1`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的最小保持连接数（长连接）。Redisson内部经常通过发布和订阅来实现许多功能。长期保持一定数量的发布订阅连接是必须的。



###### subscriptionConnectionPoolSize（从节点发布和订阅连接池大小）

默认值：`50`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。



###### slaveConnectionMinimumIdleSize（从节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时读取反映速度。



###### slaveConnectionPoolSize（从节点连接池大小）

默认值：`64`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）连接的连接池最大容量。连接池的连接数量自动弹性伸缩。



###### masterConnectionMinimumIdleSize（主节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。



###### masterConnectionPoolSize（主节点连接池大小）

默认值：`64`

主节点的连接池最大容量。连接池的连接数量自动弹性伸缩。



###### idleConnectionTimeout（连接空闲超时，单位：毫秒）

默认值：`10000`

如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。



###### readMode（读取操作的负载均衡模式）

默认值： `SLAVE`（只在从服务节点里读取）

注：在从服务节点里读取的数据说明已经至少有两个节点保存了该数据，确保了数据的高可用性。

设置读取操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里读取。 `MASTER` - 只在主服务节点里读取。 `MASTER_SLAVE` - 在主从服务节点里都可以读取。



###### subscriptionMode（订阅操作的负载均衡模式）

默认值：`SLAVE`（只在从服务节点里订阅）

设置订阅操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里订阅。 `MASTER` - 只在主服务节点里订阅。



###### connectTimeout（连接超时，单位：毫秒）

默认值：`10000`

同任何节点建立连接时的等待超时。时间单位是毫秒。



###### timeout（命令等待超时，单位：毫秒）

默认值：`3000`

等待节点回复命令的时间。该时间从命令发送成功时开始计时。



###### retryAttempts（命令失败重试次数）

默认值：`3`

如果尝试达到 **retryAttempts（命令失败重试次数）** 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 **timeout（命令等待超时）** 计时。



###### retryInterval（命令重试发送时间间隔，单位：毫秒）

默认值：`1500`

在某个节点执行相同或不同命令时，**连续** 失败 **failedAttempts（执行失败最大次数）** 时，该节点将被从可用节点列表里清除，直到 **reconnectionTimeout（重新连接时间间隔）** 超时以后再次尝试。



###### database（数据库编号）

默认值：`0`

尝试连接的数据库编号。



###### password（密码）

默认值：`null`

用于节点身份验证的密码。



###### subscriptionsPerConnection（单个连接最大订阅数量）

默认值：`5`

每个连接的最大订阅数量。



###### clientName（客户端名称）

默认值：`null`

在Redis节点里显示的客户端名称。



###### sslEnableEndpointIdentification（启用SSL终端识别）

默认值：`true`

开启SSL终端识别能力。



###### sslProvider（SSL实现方式）

默认值：`JDK`

确定采用哪种方式（JDK或OPENSSL）来实现SSL连接。



###### sslTruststore（SSL信任证书库路径）

默认值：`null`

指定SSL信任证书库的路径。



###### sslTruststorePassword（SSL信任证书库密码）

默认值：`null`

指定SSL信任证书库的密码。



###### sslKeystore（SSL钥匙库路径）

默认值：`null`

指定SSL钥匙库的路径。



###### sslKeystorePassword（SSL钥匙库密码）

默认值：`null`

指定SSL钥匙库的密码。



2）通过YAML文件配置集群模式

配置云托管模式可以通过指定一个YAML格式的文件来实现。以下是YAML格式的配置文件样本。文件中的字段名称必须与`ReplicatedServersConfig `和`Config`对象里的字段名称相符。

```yaml
---
replicatedServersConfig:
  idleConnectionTimeout: 10000
  connectTimeout: 10000
  timeout: 3000
  retryAttempts: 3
  retryInterval: 1500
  password: null
  subscriptionsPerConnection: 5
  clientName: null
  loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
  slaveSubscriptionConnectionMinimumIdleSize: 1
  slaveSubscriptionConnectionPoolSize: 50
  slaveConnectionMinimumIdleSize: 32
  slaveConnectionPoolSize: 64
  masterConnectionMinimumIdleSize: 32
  masterConnectionPoolSize: 64
  readMode: "SLAVE"
  nodeAddresses:
  - "redis://127.0.0.1:2812"
  - "redis://127.0.0.1:2815"
  - "redis://127.0.0.1:2813"
  scanInterval: 1000
threads: 0
nettyThreads: 0
codec: !<org.redisson.codec.JsonJacksonCodec> {}
"transportMode":"NIO"
```



##### 3.单Redis节点模式

edisson的单Redis节点模式的使用方法如下：

 `SingleServerConfig singleConfig = config.useSingleServer();`

`SingleServerConfig` 类的设置参数如下：



###### address（节点地址）

可以通过`host:port`的格式来指定节点地址。

###### subscriptionConnectionMinimumIdleSize（发布和订阅连接的最小空闲连接数）

默认值：`1`

用于发布和订阅连接的最小保持连接数（长连接）。Redisson内部经常通过发布和订阅来实现许多功能。长期保持一定数量的发布订阅连接是必须的。

###### subscriptionConnectionPoolSize（发布和订阅连接池大小）

默认值：`50`

用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### connectionMinimumIdleSize（最小空闲连接数）

默认值：`32`

最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。

###### connectionPoolSize（连接池大小）

默认值：`64`

在启用该功能以后，Redisson将会监测DNS的变化情况。

###### dnsMonitoringInterval（DNS监测时间间隔，单位：毫秒）

默认值：`5000`

监测DNS的变化情况的时间间隔。

###### idleConnectionTimeout（连接空闲超时，单位：毫秒）

默认值：`10000`

如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。

###### connectTimeout（连接超时，单位：毫秒）

默认值：`10000`

同节点建立连接时的等待超时。时间单位是毫秒。

###### timeout（命令等待超时，单位：毫秒）

默认值：`3000`

等待节点回复命令的时间。该时间从命令发送成功时开始计时。

###### retryAttempts（命令失败重试次数）

默认值：`3`

如果尝试达到 **retryAttempts（命令失败重试次数）** 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 **timeout（命令等待超时）** 计时。

###### retryInterval（命令重试发送时间间隔，单位：毫秒）

默认值：`1500`

在某个节点执行相同或不同命令时，**连续** 失败 **failedAttempts（执行失败最大次数）** 时，该节点将被从可用节点列表里清除，直到 **reconnectionTimeout（重新连接时间间隔）** 超时以后再次尝试。

###### database（数据库编号）

默认值：`0`

尝试连接的数据库编号。

###### password（密码）

默认值：`null`

用于节点身份验证的密码。

###### subscriptionsPerConnection（单个连接最大订阅数量）

默认值：`5`

每个连接的最大订阅数量。

###### clientName（客户端名称）

默认值：`null`

在Redis节点里显示的客户端名称。

###### sslEnableEndpointIdentification（启用SSL终端识别）

默认值：`true`

开启SSL终端识别能力。

###### sslProvider（SSL实现方式）

默认值：`JDK`

确定采用哪种方式（JDK或OPENSSL）来实现SSL连接。

###### sslTruststore（SSL信任证书库路径）

默认值：`null`

指定SSL信任证书库的路径。

###### sslTruststorePassword（SSL信任证书库密码）

默认值：`null`

指定SSL信任证书库的密码。

###### sslKeystore（SSL钥匙库路径）

默认值：`null`

指定SSL钥匙库的路径。

###### sslKeystorePassword（SSL钥匙库密码）

默认值：`null`

指定SSL钥匙库的密码。



###### 2)通过YAML文件配置集群模式

配置单节点模式可以通过指定一个YAML格式的文件来实现。以下是YAML格式的配置文件样本。文件中的字段名称必须与`SingleServerConfig`和`Config`对象里的字段名称相符。

```yaml
---
singleServerConfig:
  idleConnectionTimeout: 10000
  connectTimeout: 10000
  timeout: 3000
  retryAttempts: 3
  retryInterval: 1500
  password: null
  subscriptionsPerConnection: 5
  clientName: null
  address: "redis://127.0.0.1:6379"
  subscriptionConnectionMinimumIdleSize: 1
  subscriptionConnectionPoolSize: 50
  connectionMinimumIdleSize: 32
  connectionPoolSize: 64
  database: 0
  dnsMonitoringInterval: 5000
threads: 0
nettyThreads: 0
codec: !<org.redisson.codec.JsonJacksonCodec> {}
"transportMode":"NIO"
```



##### 4.哨兵模式

配置Redis哨兵服务的官方文档在[这里](http://redis.cn/topics/sentinel.html)。



###### 1）程序化配置Redisson的哨兵模式的使用方法如下：

 `SentinelServersConfig sentinelConfig = config.useSentinelServers();`

`SentinelServersConfig` 类的设置参数如下：



###### dnsMonitoringInterval（DNS监控间隔，单位：毫秒）

默认值：`5000`

用来指定检查节点DNS变化的时间间隔。使用的时候应该确保JVM里的DNS数据的缓存时间保持在足够低的范围才有意义。用`-1`来禁用该功能。

###### masterName（主服务器的名称）

主服务器的名称是哨兵进程中用来监测主从服务切换情况的。

###### addSentinelAddress（添加哨兵节点地址）

可以通过`host:port`的格式来指定哨兵节点的地址。多个节点可以一次性批量添加。

###### readMode（读取操作的负载均衡模式）

默认值： `SLAVE`（只在从服务节点里读取）

注：在从服务节点里读取的数据说明已经至少有两个节点保存了该数据，确保了数据的高可用性。

设置读取操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里读取。 `MASTER` - 只在主服务节点里读取。 `MASTER_SLAVE` - 在主从服务节点里都可以读取。

###### subscriptionMode（订阅操作的负载均衡模式）

默认值：`SLAVE`（只在从服务节点里订阅）

设置订阅操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里订阅。 `MASTER` - 只在主服务节点里订阅。

###### loadBalancer（负载均衡算法类的选择）

默认值： `org.redisson.connection.balancer.RoundRobinLoadBalancer`

在使用多个Redis服务节点的环境里，可以选用以下几种负载均衡方式选择一个节点： `org.redisson.connection.balancer.WeightedRoundRobinBalancer` - 权重轮询调度算法 `org.redisson.connection.balancer.RoundRobinLoadBalancer` - 轮询调度算法 `org.redisson.connection.balancer.RandomLoadBalancer` - 随机调度算法

###### subscriptionConnectionMinimumIdleSize（从节点发布和订阅连接的最小空闲连接数）

默认值：`1`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的最小保持连接数（长连接）。Redisson内部经常通过发布和订阅来实现许多功能。长期保持一定数量的发布订阅连接是必须的。

###### subscriptionConnectionPoolSize（从节点发布和订阅连接池大小）

默认值：`50`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### slaveConnectionMinimumIdleSize（从节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时读取反映速度。

###### slaveConnectionPoolSize（从节点连接池大小）

默认值：`64`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）连接的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### masterConnectionMinimumIdleSize（主节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。

###### masterConnectionPoolSize（主节点连接池大小）

默认值：`64`

主节点的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### idleConnectionTimeout（连接空闲超时，单位：毫秒）

默认值：`10000`

如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。

###### connectTimeout（连接超时，单位：毫秒）

默认值：`10000`

同任何节点建立连接时的等待超时。时间单位是毫秒。

###### timeout（命令等待超时，单位：毫秒）

默认值：`3000`

等待节点回复命令的时间。该时间从命令发送成功时开始计时。

###### retryAttempts（命令失败重试次数）

默认值：`3`

如果尝试达到 **retryAttempts（命令失败重试次数）** 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 **timeout（命令等待超时）** 计时。

###### retryInterval（命令重试发送时间间隔，单位：毫秒）

默认值：`1500`

在某个节点执行相同或不同命令时，**连续** 失败 **failedAttempts（执行失败最大次数）** 时，该节点将被从可用节点列表里清除，直到 **reconnectionTimeout（重新连接时间间隔）** 超时以后再次尝试。

###### database（数据库编号）

默认值：`0`

尝试连接的数据库编号。

###### password（密码）

默认值：`null`

用于节点身份验证的密码。

###### subscriptionsPerConnection（单个连接最大订阅数量）

默认值：`5`

每个连接的最大订阅数量。

###### clientName（客户端名称）

默认值：`null`

在Redis节点里显示的客户端名称。

###### sslEnableEndpointIdentification（启用SSL终端识别）

默认值：`true`

开启SSL终端识别能力。

###### sslProvider（SSL实现方式）

默认值：`JDK`

确定采用哪种方式（JDK或OPENSSL）来实现SSL连接。

###### sslTruststore（SSL信任证书库路径）

默认值：`null`

指定SSL信任证书库的路径。

###### sslTruststorePassword（SSL信任证书库密码）

默认值：`null`

指定SSL信任证书库的密码。

###### sslKeystore（SSL钥匙库路径）

默认值：`null`

指定SSL钥匙库的路径。

###### sslKeystorePassword（SSL钥匙库密码）

默认值：`null`

指定SSL钥匙库的密码。



###### 2）通过yaml文件配置集群模式

配置哨兵模式可以通过指定一个YAML格式的文件来实现。以下是YAML格式的配置文件样本。文件中的字段名称必须与`SentinelServersConfig`和`Config`对象里的字段名称相符。

```
---
sentinelServersConfig:
  idleConnectionTimeout: 10000
  connectTimeout: 10000
  timeout: 3000
  retryAttempts: 3
  retryInterval: 1500
  password: null
  subscriptionsPerConnection: 5
  clientName: null
  loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
  slaveSubscriptionConnectionMinimumIdleSize: 1
  slaveSubscriptionConnectionPoolSize: 50
  slaveConnectionMinimumIdleSize: 32
  slaveConnectionPoolSize: 64
  masterConnectionMinimumIdleSize: 32
  masterConnectionPoolSize: 64
  readMode: "SLAVE"
  sentinelAddresses:
  - "redis://127.0.0.1:26379"
  - "redis://127.0.0.1:26389"
  masterName: "mymaster"
  database: 0
threads: 0
nettyThreads: 0
codec: !<org.redisson.codec.JsonJacksonCodec> {}
"transportMode":"NIO"
```



##### 5.主从模式



###### 1）程序化配置主从模式的用法：

```
Config config = new Config();
config.useMasterSlaveServers()
    //可以用"rediss://"来启用SSL连接
    .setMasterAddress("redis://127.0.0.1:6379")
    .addSlaveAddress("redis://127.0.0.1:6389", "redis://127.0.0.1:6332", "redis://127.0.0.1:6419")
    .addSlaveAddress("redis://127.0.0.1:6399");

RedissonClient redisson = Redisson.create(config);
```



介绍配置Redis主从服务组态的文档在[这里](http://redis.cn/topics/replication.html). Redisson的主从模式的使用方法如下： `MasterSlaveServersConfig masterSlaveConfig = config.useMasterSlaveServers();`

`MasterSlaveServersConfig` 类的设置参数如下：

###### dnsMonitoringInterval（DNS监控间隔，单位：毫秒）

默认值：`5000`

用来指定检查节点DNS变化的时间间隔。使用的时候应该确保JVM里的DNS数据的缓存时间保持在足够低的范围才有意义。用`-1`来禁用该功能。

###### masterAddress（主节点地址）

可以通过`host:port`的格式来指定主节点地址。

###### addSlaveAddress（添加从主节点地址）

可以通过`host:port`的格式来指定从节点的地址。多个节点可以一次性批量添加。

###### readMode（读取操作的负载均衡模式）

默认值： `SLAVE`（只在从服务节点里读取）

注：在从服务节点里读取的数据说明已经至少有两个节点保存了该数据，确保了数据的高可用性。

设置读取操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里读取。 `MASTER` - 只在主服务节点里读取。 `MASTER_SLAVE` - 在主从服务节点里都可以读取。

###### subscriptionMode（订阅操作的负载均衡模式）

默认值：`SLAVE`（只在从服务节点里订阅）

设置订阅操作选择节点的模式。 可用值为： `SLAVE` - 只在从服务节点里订阅。 `MASTER` - 只在主服务节点里订阅。

###### loadBalancer（负载均衡算法类的选择）

默认值： `org.redisson.connection.balancer.RoundRobinLoadBalancer`

在使用多个Redis服务节点的环境里，可以选用以下几种负载均衡方式选择一个节点： `org.redisson.connection.balancer.WeightedRoundRobinBalancer` - 权重轮询调度算法 `org.redisson.connection.balancer.RoundRobinLoadBalancer` - 轮询调度算法 `org.redisson.connection.balancer.RandomLoadBalancer` - 随机调度算法

###### subscriptionConnectionMinimumIdleSize（从节点发布和订阅连接的最小空闲连接数）

默认值：`1`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的最小保持连接数（长连接）。Redisson内部经常通过发布和订阅来实现许多功能。长期保持一定数量的发布订阅连接是必须的。

###### subscriptionConnectionPoolSize（从节点发布和订阅连接池大小）

默认值：`50`

多从节点的环境里，**每个** 从服务节点里用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### slaveConnectionMinimumIdleSize（从节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时读取反映速度。

###### slaveConnectionPoolSize（从节点连接池大小）

默认值：`64`

多从节点的环境里，**每个** 从服务节点里用于普通操作（**非** 发布和订阅）连接的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### masterConnectionMinimumIdleSize（主节点最小空闲连接数）

默认值：`32`

多从节点的环境里，**每个** 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。

###### masterConnectionPoolSize（主节点连接池大小）

默认值：`64`

主节点的连接池最大容量。连接池的连接数量自动弹性伸缩。

###### idleConnectionTimeout（连接空闲超时，单位：毫秒）

默认值：`10000`

如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。

###### connectTimeout（连接超时，单位：毫秒）

默认值：`10000`

同任何节点建立连接时的等待超时。时间单位是毫秒。

###### timeout（命令等待超时，单位：毫秒）

默认值：`3000`

等待节点回复命令的时间。该时间从命令发送成功时开始计时。

###### retryAttempts（命令失败重试次数）

默认值：`3`

如果尝试达到 **retryAttempts（命令失败重试次数）** 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 **timeout（命令等待超时）** 计时。

###### retryInterval（命令重试发送时间间隔，单位：毫秒）

默认值：`1500`

在某个节点执行相同或不同命令时，**连续** 失败 **failedAttempts（执行失败最大次数）** 时，该节点将被从可用节点列表里清除，直到 **reconnectionTimeout（重新连接时间间隔）** 超时以后再次尝试。

###### database（数据库编号）

默认值：`0`

尝试连接的数据库编号。

###### password（密码）

默认值：`null`

用于节点身份验证的密码。

###### subscriptionsPerConnection（单个连接最大订阅数量）

默认值：`5`

每个连接的最大订阅数量。

###### clientName（客户端名称）

默认值：`null`

在Redis节点里显示的客户端名称。

###### sslEnableEndpointIdentification（启用SSL终端识别）

默认值：`true`

开启SSL终端识别能力。

###### sslProvider（SSL实现方式）

默认值：`JDK`

确定采用哪种方式（JDK或OPENSSL）来实现SSL连接。

###### sslTruststore（SSL信任证书库路径）

默认值：`null`

指定SSL信任证书库的路径。

###### sslTruststorePassword（SSL信任证书库密码）

默认值：`null`

指定SSL信任证书库的密码。

###### sslKeystore（SSL钥匙库路径）

默认值：`null`

指定SSL钥匙库的路径。

###### sslKeystorePassword（SSL钥匙库密码）

默认值：`null`

指定SSL钥匙库的密码。



2）通过yaml文件配置集群模式

配置主从模式可以通过指定一个YAML格式的文件来实现。以下是YAML格式的配置文件样本。文件中的字段名称必须与`MasterSlaveServersConfig`和`Config`对象里的字段名称相符。

```
---
masterSlaveServersConfig:
  idleConnectionTimeout: 10000
  connectTimeout: 10000
  timeout: 3000
  retryAttempts: 3
  retryInterval: 1500
  failedAttempts: 3
  password: null
  subscriptionsPerConnection: 5
  clientName: null
  loadBalancer: !<org.redisson.connection.balancer.RoundRobinLoadBalancer> {}
  slaveSubscriptionConnectionMinimumIdleSize: 1
  slaveSubscriptionConnectionPoolSize: 50
  slaveConnectionMinimumIdleSize: 32
  slaveConnectionPoolSize: 64
  masterConnectionMinimumIdleSize: 32
  masterConnectionPoolSize: 64
  readMode: "SLAVE"
  slaveAddresses:
  - "redis://127.0.0.1:6381"
  - "redis://127.0.0.1:6380"
  masterAddress: "redis://127.0.0.1:6379"
  database: 0
threads: 0
nettyThreads: 0
codec: !<org.redisson.codec.JsonJacksonCodec> {}
"transportMode":"NIO"
```