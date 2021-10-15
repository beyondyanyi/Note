## 先来先服务（FCFS）

方式：顾名思义，按照进程就绪的先后顺序使用CPU,不可抢占
优点：公平，实现简单
缺点：长进程后面的短进程需要等待很长时间，不利于用户体验
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200816182516817.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JiYmJvYmJi,size_16,color_FFFFFF,t_70#pic_center)
改变调度顺序：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200816182547601.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JiYmJvYmJi,size_16,color_FFFFFF,t_70#pic_center)