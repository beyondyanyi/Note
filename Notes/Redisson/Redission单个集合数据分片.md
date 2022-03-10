在集群模式下，Redisson为单个Redis集合类型提供了自动分片的功能。

Redisson提供的所有数据结构都支持在集群环境下使用，但每个数据结构只被保存在一个固定的槽内。[Redisson PRO](https://redisson.pro/)提供的自动分片功能能够将单个数据结构拆分，然后均匀的分布在整个集群里，而不是被挤在单一一个槽里。自动分片功能的优势主要有以下几点：

1. 单个数据结构可以充分利用整个集群内存资源，而不是被某一个节点的内存限制。
2. 将单个数据结构分片以后分布在集群中不同的节点里，不仅可以大幅提高读写性能，还能够保证读写性能随着集群的扩张而自动提升。

Redisson通过自身的分片算法，将一个大集合拆分为若干个片段（**默认231个，分片数量范围是3 - 16834**），然后将拆分后的片段均匀的分布到集群里各个节点里，保证每个节点分配到的片段数量大体相同。比如在默认情况下231个片段分到含有4个主节点的集群里，每个主节点将会分配到大约57个片段，同样的道理如果有5个主节点，每个节点会分配到大约46个片段。

目前支持的数据结构类型和服务包括[集（Set）](https://github.com/redisson/redisson/wiki/7.-分布式集合#732--集set数据分片sharding)、[映射（Map）](https://github.com/redisson/redisson/wiki/7.-分布式集合#数据分片功能sharding)、[BitSet](https://github.com/redisson/redisson/wiki/6.-分布式对象#641-bitset数据分片sharding分布式roaringbitmap)、[布隆过滤器（Bloom Filter）](https://github.com/redisson/redisson/wiki/6.-分布式对象#681-布隆过滤器数据分片sharding)、[Spring Cache](https://github.com/redisson/redisson/wiki/14.-第三方框架整合#1421-spring-cache---本地缓存和数据分片)和[Hibernate Cache](https://github.com/redisson/redisson/wiki/14.-第三方框架整合#1431-hibernate二级缓存---本地缓存和数据分片)。

*该功能仅限于[Redisson PRO](https://redisson.pro/)版本。*