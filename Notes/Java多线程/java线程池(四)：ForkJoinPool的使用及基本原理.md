##### 核心：分治法和工作窃取【https://www.jianshu.com/p/91e504c910c9】



ForkJoinPool是自java7开始，jvm提供的一个用于并行执行的任务框架。其主旨是将大任务分成若干小任务，之后再并行对这些小任务进行计算，最终汇总这些任务的结果。得到最终的结果。其广泛用在java8的stream中。



##### 1.分治法：

分治法的基本思想是将一个规模为N的问题分解为K个规模较小的子问题，这些子问题的相互独立且与原问题的性质相同，求出子问题的解之后，将这些解合并，就可以得到原有问题的解。



![img](https://upload-images.jianshu.io/upload_images/3237432-9d288a21343bbd58.png?imageMogr2/auto-orient/strip|imageView2/2/w/366/format/webp)

##### 2.工作窃取



工作窃取是指当某个线程的任务队列中没有可执行任务的时候，从其他线程的任务队列中窃取任务来执行，以充分利用工作线程的计算能力，减少线程由于获取不到任务而造成的空闲浪费。



FIFO: 先进先出

​	first in first out

LIFO: 后进先出

​	last in first out





##### 3.使用

![img](https://upload-images.jianshu.io/upload_images/3237432-587dc1abecefc06b.png?imageMogr2/auto-orient/strip|imageView2/2/w/703/format/webp)

ForkJoinTask及其实现类

基本使用的是带返回值的RecursiveTask和不带返回值的RecursiveAction