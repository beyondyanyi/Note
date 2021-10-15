## 多级反馈队列调度

方式：
非抢占：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200816185129124.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JiYmJvYmJi,size_16,color_FFFFFF,t_70#pic_center)
最后一个问题，回到队首还是队尾取决于系统对进程的偏好程度。
允许抢占：
当有一个优先级更高的进程就绪时，可以抢占CPU