`RedissonClient`、`RedissonReactiveClient`和`RedissonRxClient`实例本身和Redisson提供的所有分布式对象都是线程安全的。





Redisson为每个操作都提供了自动重试策略，当某个命令执行失败时，Redisson会自动进行重试。自动重试策略可以通过修改`retryAttempts`（默认值：3）参数和`retryInterval`（默认值：1000毫秒）参数来进行优化调整。当等待时间达到`retryInterval`指定的时间间隔以后，将自动重试下一次。全部重试失败以后将抛出错误。





Redisson框架提供的几乎所有对象都包含了`同步`和`异步`相互匹配的方法。这些对象都可以通过`RedissonClient`接口获取。同时还为大部分Redisson对象提供了满足[`异步流处理标准`](http://reactive-streams.org/)的程序接口`RedissonReactiveClient`。除此外还提供了`RxJava2`规范的`RedissonRxClient`程序接口。



以下是关于使用`RAtomicLong`对象的范例：

```
RedissonClient client = Redisson.create(config);
RAtomicLong longObject = client.getAtomicLong('myLong');
// 同步执行方式
longObject.compareAndSet(3, 401);
// 异步执行方式
RFuture<Boolean> result = longObject.compareAndSetAsync(3, 401);

RedissonReactiveClient client = Redisson.createReactive(config);
RAtomicLongReactive longObject = client.getAtomicLong('myLong');
// 异步流执行方式
Mono<Boolean> result = longObject.compareAndSet(3, 401);
RedissonRxClient client = Redisson.createRx(config);
RAtomicLongRx longObject= client.getAtomicLong("myLong");
// RxJava2方式
Flowable<Boolean result = longObject.compareAndSet(3, 401);
```

### 异步执行方式

几乎所有的Redisson对象都实现了一个异步接口，异步接口提供的方法名称与其同步接口的方法名称相互匹配。例如：

```
// RAtomicLong接口继承了RAtomicLongAsync接口
RAtomicLongAsync longObject = client.getAtomicLong("myLong");
RFuture<Boolean> future = longObject.compareAndSetAsync(1, 401);
```

异步执行的方法都会返回一个实现了`RFuture`接口的对象。该对象同时提供了`java.util.concurrent.CompletionStage`和`java.util.concurrent.Future`两个异步接口。

```
future.whenComplete((res, exception) -> {
    // ...
});
// 或者
future.thenAccept(res -> {
    // 处理返回
}).exceptionally(exception -> {
    // 处理错误
});
```



### 异步流执行方式

Redisson为大多数分布式数据结构提供了满足[Reactor](http://projectreactor.io/)项目的[异步流处理标准](http://reactive-streams.org/)的程序接口。该接口通过两种方式实现：

1. 基于[Project Reactor](http://projectreactor.io/)标准的实现方式。使用范例如下：

```
RedissonReactiveClient client = Redisson.createReactive(config);
RAtomicLongReactive atomicLong = client.getAtomicLong("myLong");
Mono<Boolean> cs = longObject.compareAndSet(10, 91);
Mono<Long> get = longObject.get();

Publisher<Long> getPublisher = longObject.get();
```

1. 基于[RxJava2](https://github.com/ReactiveX/RxJava)标准的实现方式。使用范例如下：

```
RedissonRxClient client = Redisson.createRx(config);
RAtomicLongRx atomicLong = client.getAtomicLong("myLong");
Single<Boolean> cs = longObject.compareAndSet(10, 91);
Single<Long> get = longObject.get();
```