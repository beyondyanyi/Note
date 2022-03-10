##### 1.通用对象桶（Object Bucket）

Redisson的分布式RBucket java对象是一种通用对象桶可以用来存放任何类型的对象。除了同步接口外，还提供了异步（Async）、反射式(Reactive)和RxJava2标准的接口

```
RBucket<AnyObject> bucket = redisson.getBucket("anyObject");
bucket.set(new AnyObject(1));
AnyObject obj = bucket.get();
bucket.trySet(new AnyObject(3));
bucket.compareAndSet(new AnyObject(4), new AnyObject(5));
bucket.getAndSet(new AnyObject(6));
```

还可以通过[RBuckets](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBuckets.html)接口实现批量操作多个[RBucket](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBucket.html)对象：

```
RBuckets buckets = redisson.getBuckets();
List<RBucket<V>> foundBuckets = buckets.find("myBucket*");
Map<String, V> loadedBuckets = buckets.get("myBucket1", "myBucket2", "myBucket3");
Map<String, Object> map = new HashMap<>();
map.put("myBucket1", new MyObject());
map.put("myBucket2", new MyObject());
// 利用Redis的事务特性，同时保存所有的通用对象桶，如果任意一个通用对象桶已经存在则放弃保存其他所有数据。
buckets.trySet(map);
// 同时保存全部通用对象桶。
buckets.set(map);
```



###### RBucket接口的方法

```
set(V var1)   //设置桶存储的对象
set(V var1,long var2,TimeUnit var4)  //设置桶存储的对象，设置操作的超时时间var2

boolean trySet(V var1);  //尝试设置桶的新值
boolean trySet(V var1,long var2,TimeUnit var4)  //尝试设置桶的新值，设置超时时间

boolean compareAndSet(V var1,V var2) //原子替换桶的新值为var2

long size()   //桶存储对象的大小

V get()     //返回桶存储的对象
V getAndDelete()  //返回并删除桶存储的对象

V getAndSet(V var1)   //返回桶的旧值，设置新值
V getAndSet(V var1,long var2,TimeUnit var4)  //返回桶的旧值，设置新值，设置操作的超时时间var2

```

#####  2.二进制流（Binary Stream）

Redisson的分布式[`RBinaryStream`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBinaryStream.html) Java对象同时提供了`InputStream`接口和`OutputStream`接口的实现。流的最大容量受Redis主节点的内存大小限制。

```
RBinaryStream stream = redisson.getBinaryStream("anyStream");
byte[] content = ...
stream.set(content);
InputStream is = stream.getInputStream();
byte[] readBuffer = new byte[512];
is.read(readBuffer);
OutputStream os = stream.getOuputStream();
byte[] contentToWrite = ...
os.write(contentToWrite);
```

###### RBinaryStream接口的方法

```
AsynchronousByteChannel getAsynchronousChannel(); //获取异步通道

SeekableByteChannel getChannel();  //获取通道

InputStream getInputStream(); //获取输入流

OutputStream getOutputStream();  //获取输出流
```



##### 3.地理空间对象桶（Geospatial Bucket）

Redisson的分布式[`RGeo`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RGeo.html) Java对象是一种专门用来储存与地理位置有关的对象桶。除了同步接口外，还提供了异步（[Async](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RGeoAsync.html)）、反射式（[Reactive](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RGeoReactive.html)）和[RxJava2](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RGeoRx.html)标准的接口。



```
RGeo<String> geo = redisson.getGeo("test");
geo.add(new GeoEntry(13.361389, 38.115556, "Palermo"),
        new GeoEntry(15.087269, 37.502669, "Catania"));
geo.addAsync(37.618423, 55.751244, "Moscow");
Double distance = geo.dist("Palermo", "Catania", GeoUnit.METERS);
geo.hashAsync("Palermo", "Catania");
Map<String, GeoPosition> positions = geo.pos("test2", "Palermo", "test3", "Catania", "test1");
List<String> cities = geo.radius(15, 37, 200, GeoUnit.KILOMETERS);
Map<String, GeoPosition> citiesWithPositions = geo.radiusWithPosition(15, 37, 200, GeoUnit.KILOMETERS);
```



RGeo接口的方法：

```
	long add(double var1, double var3, V var5);

    long add(GeoEntry... var1);

    Boolean addIfExists(double var1, double var3, V var5);

    long addIfExists(GeoEntry... var1);

    boolean tryAdd(double var1, double var3, V var5);

    long tryAdd(GeoEntry... var1);

    Double dist(V var1, V var2, GeoUnit var3);

    Map<V, String> hash(V... var1);

    Map<V, GeoPosition> pos(V... var1);

    List<V> search(GeoSearchArgs var1);

    long storeSearchTo(String var1, GeoSearchArgs var2);



    long storeSortedSearchTo(String var1, GeoSearchArgs var2);

 
```





##### 4.BitSet

Redisson的分布式[`RBitSet`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBitSet.html)Java对象采用了与`java.util.BiteSet`类似结构的设计风格。可以理解为它是一个分布式的可伸缩式位向量。需要注意的是`RBitSet`的大小受Redis限制，最大长度为`4 294 967 295`。除了同步接口外，还提供了异步（[Async](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBitSetAsync.html)）、反射式（[Reactive](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBitSetReactive.html)）和[RxJava2](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBitSetRx.html)标准的接口。

```
RBitSet set = redisson.getBitSet("simpleBitset");
set.set(0, true);
set.set(1812, false);
set.clear(0);
set.addAsync("e");
set.xor("anotherBitset");
```

#####  BitSet数据分片（Sharding）（分布式RoaringBitMap）

基于Redis的Redisson集群分布式BitSet通过`RClusteredBitSet`接口，为集群状态下的Redis环境提供了BitSet数据分片的功能。通过优化后更加有效的分布式RoaringBitMap算法，突破了原有的BitSet大小限制，达到了集群物理内存容量大小。在[这里](https://www.bookstack.cn/read/redisson-wiki-zh/$5.-单个集合数据分片（Sharding）)可以获取更多的内部信息。

```
RClusteredBitSet set = redisson.getClusteredBitSet("simpleBitset");
set.set(0, true);
set.set(1812, false);
set.clear(0);
set.addAsync("e");
set.xor("anotherBitset");
```



##### 5.原子整长形(AtomicLong)

Redisson的分布式整长形[`RAtomicLong`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicLong.html)对象和Java中的`java.util.concurrent.atomic.AtomicLong`对象类似。除了同步接口外，还提供了异步（[Async](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicLongAsync.html)）、反射式（[Reactive](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicLongReactive.html)）和[RxJava2](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicLongRx.html)标准的接口。

```
RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
atomicLong.set(3);
atomicLong.incrementAndGet();
atomicLong.get();
```



##### 6.原子双精度浮点（AtomicDouble）

Redisson还提供了分布式原子双精度浮点[`RAtomicDouble`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicDouble.html)，弥补了Java自身的不足。除了同步接口外，还提供了异步（[Async](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicDoubleAsync.html)）、反射式（[Reactive](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicDoubleReactive.html)）和[RxJava2](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RAtomicDoubleRx.html)标准的接口。

```
RAtomicDouble atomicDouble = redisson.getAtomicDouble("myAtomicDouble");
atomicDouble.set(2.81);
atomicDouble.addAndGet(4.11);
atomicDouble.get();
```



##### 7.话题（订阅分发）

Redisson的分布式话题[`RTopic`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RTopic.html对象实现了发布、订阅的机制。除了同步接口外，还提供了异步（[Async](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RTopicAsync.html)）、反射式（[Reactive](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RTopicReactive.html)）和[RxJava2](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RTopicRx.html)标准的接口。

```
RTopic topic = redisson.getTopic("anyTopic");
topic.addListener(SomeObject.class, new MessageListener<SomeObject>() {
    @Override
    public void onMessage(String channel, SomeObject message) {
        //...
    }
});
// 在其他线程或JVM节点
RTopic topic = redisson.getTopic("anyTopic");
long clientsReceivedMessage = topic.publish(new SomeObject());
```

在Redis节点故障转移（主从切换）或断线重连以后，所有的话题监听器将自动完成话题的重新订阅。



##### 模糊话题

Redisson的模糊话题[`RPatternTopic`](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RPatternTopic.html)对象可以通过正式表达式来订阅多个话题。除了同步接口外，还提供了异步（[Async](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RPatternTopicAsync.html)）、反射式（[Reactive](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RPatternTopicReactive.html)）和[RxJava2](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RPatternTopicRx.html)标准的接口。

```
// 订阅所有满足`topic1.*`表达式的话题
RPatternTopic topic1 = redisson.getPatternTopic("topic1.*");
int listenerId = topic1.addListener(Message.class, new PatternMessageListener<Message>() {
    @Override
    public void onMessage(String pattern, String channel, Message msg) {
         Assert.fail();
    }
});
```



##### 8.布隆过滤器（Bloom Filter）

Redisson利用Redis实现了Java分布式[布隆过滤器（Bloom Filter）](http://static.javadoc.io/org.redisson/redisson/3.10.0/org/redisson/api/RBloomFilter.html)。所含最大比特数量为`2^32`。

```
RBloomFilter<SomeObject> bloomFilter = redisson.getBloomFilter("sample");
// 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
bloomFilter.tryInit(55000000L, 0.03);
bloomFilter.add(new SomeObject("field1Value", "field2Value"));
bloomFilter.add(new SomeObject("field5Value", "field8Value"));
bloomFilter.contains(new SomeObject("field1Value", "field8Value"));
```

