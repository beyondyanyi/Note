1.基于执行时间的分析

2.基于等待的分析



##### #.剖析单条查询



##### 1.Profiling

此工具可以用来查询SQL执行状态，System lock和Table lock花多少时间等。

当一条查询提交给服务器时，此工具会记录剖析信息到一张临时表，并且给查询赋予一个从1开始的整数标识符。

```
mysql5.0.3之后支持，mysql5.7后逐渐被废弃，推荐使用performance schema

#打开
set profiling=1;

#关闭(设置profiling_history_size=0同样具有关闭profiling的效果)
set profiling=0;

#语法
show profiles;

show prifile [type] for query [num]

type分类：


结果详解：
#列：
"Status": "query end", 状态
"Duration": "1.751142", 持续时间
"CPU_user": "0.008999", cpu用户
"CPU_system": "0.003999", cpu系统
"Context_voluntary": "98", 上下文主动切换
"Context_involuntary": "0", 上下文被动切换
"Block_ops_in": "8", 阻塞的输入操作
"Block_ops_out": "32", 阻塞的输出操作
"Messages_sent": "0", 消息发出
"Messages_received": "0", 消息接受
"Page_faults_major": "0", 主分页错误
"Page_faults_minor": "0", 次分页错误
"Swaps": "0", 交换次数
"Source_function": "mysql_execute_command", 源功能
"Source_file": "sql_parse.cc", 源文件
"Source_line": "4465" 源代码行

#行
starting：开始
checking permissions：检查权限
Opening tables：打开表
init ： 初始化
System lock ：系统锁
optimizing ： 优化
statistics ： 统计
preparing ：准备
executing ：执行
Sending data ：发送数据
Sorting result ：排序
end ：结束
query end ：查询 结束
closing tables ： 关闭表 ／去除TMP 表
freeing items ： 释放物品
cleaning up ：清理

#使用例：

set profiling=1;                  //打开分析
 
run your sql1;
 
run your sql2;
 
show profiles;                    //查看sql1,sql2的语句分析
 
show profile for query 1;        //查看sql1的具体分析
 
show profile ALL for query 1;    //查看sql1相关的所有分析【主要看i/o与cpu,下边分析中有各项意义介绍】

SHOW profile CPU,BLOCK IO io FOR query 2; 
 
set profiling=0;                  //关闭分析





```

##### 2.performance_schema

MySQL的performance schema 用于监控MySQL server在一个较低级别的运行过程中的资源消耗、资源等待等情况，它具有以下特点:

a、提供了一种在数据库运行时实时检查server的内部执行情况的方法。performance_schema 数据库中的表使用performance_schema存储引擎。该数据库主要关注数据库运行过程中的性能相关的数据，与information_schema不同，information_schema主要关注server运行过程中的元数据信息。

b、performance_schema通过监视server的事件来实现监视server内部运行情况， “事件”就是server内部活动中所做的任何事情以及对应的时间消耗，利用这些信息来判断server中的相关资源消耗在了哪里?一般来说，事件可以是函数调用、操作系统的等待、SQL语句执行的阶段(如sql语句执行过程中的parsing 或 sorting阶段)或者整个SQL语句与SQL语句集合。事件的采集可以方便的提供server中的相关存储引擎对磁盘文件、表I/O、表锁等资源的同步调用信息。

c、performance_schema中的事件与写入二进制日志中的事件(描述数据修改的events)、事件计划调度程序(这是一种存储程序)的事件不同。performance_schema中的事件记录的是server执行某些活动对某些资源的消耗、耗时、这些活动执行的次数等情况。

d、performance_schema中的事件只记录在本地server的performance_schema中，其下的这些表中数据发生变化时不会被写入binlog中，也不会通过复制机制被复制到其他server中。

e、当前活跃事件、历史事件和事件摘要相关的表中记录的信息。能提供某个事件的执行次数、使用时长。进而可用于分析某个特定线程、特定对象(如mutex或file)相关联的活动。

f、performance_schema存储引擎使用server源代码中的“检测点”来实现事件数据的收集。对于performance_schema实现机制本身的代码没有相关的单独线程来检测，这与其他功能(如复制或事件计划程序)不同。

g、收集的事件数据存储在performance_schema数据库的表中。这些表可以使用SELECT语句查询，也可以使用SQL语句更新performance_schema数据库中的表记录(如动态修改performance_schema的setup_*开头的几个配置表，但要注意：配置表的更改会立即生效，这会影响数据收集)。

h、performance_schema的表中的数据不会持久化存储在磁盘中，而是保存在内存中，一旦服务器重启，这些数据会丢失(包括配置表在内的整个performance_schema下的所有数据)。

i、MySQL支持的所有平台中事件监控功能都可用，但不同平台中用于统计事件时间开销的计时器类型可能会有所差异。