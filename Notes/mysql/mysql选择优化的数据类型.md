1.更小的通常更好

2.简单就好

3.尽量避免NULL





##### 1）整数类形：

```
tinyint   8
smallint   16 
mediumint  24
int        32
bigint     64
```

整数类型有可选的unsigned属性，表示不允许负值，这可使蒸熟的上限提高一倍

例如tinyint存储范围为-128~127，tinyint unsigned存储范围为0~255

mysql内部整数计算使用bigint

##### 2）实数类型

```
float 4
double  8
decimal

在数量较大的时候，可以考虑使用bigint代替decimal,将需要存储的货币单位根据小数的位数乘以相应的倍数即可。假设要存储财务数据精确到万分之一分，则可以把所有金额乘以一百万，然后将结果存储在bigint中，这样可以同时避免浮点存储计算不精确和decimal精确计算代价高的问题。
```

mysql内部浮点计算使用double

##### 3）字符串类型

##### 

###### varchar  变长字符串

​	1.比定长类型更节省空间，因为它仅使用必要的空间。（如设置ROW_FORMT=FIXED则除外）
​	2.varchar需要使用1或2个额外字节记录字符串的长度，如果列的最大长度小于或等于255字节，则	    使用1个字节表示，否则使用2个字节

​    例:varchar(10)的列需要使用11个字节的存储空间，varchar(1000)的列则需要1002个字节



###### char    定长字符串

​	当存储char值时，mysql会删除所有的末尾空格。char值会根据需要采用空格进行填充以方便比较

​    char非常适合存储密码的md5值。

​    对于经常变动的数据，char也比varchar更好，因为定长的char类型不容易产生碎片。 

​    对于非常短的列，char也比varchar更有效率（因为varchar多一个额外字节）



##### 4）BLOB和TEXT类型

BLOB 二进制方式存储

tinyblob

smallblob

blob

mediumblob

long blob



TEXT 字符方式存储

tinytext

smalltext

text

mediumtext

longtext



mysql会把每个BLOB和TEXT值当作一个独立的对象处理。

mysql对BLOB和TEXT的排序：只对每个列的最前max_sort_length字节而不是整个字符串做排序



##### 5)枚举类型

<u>枚举列实际存储的是数字，从1开始排</u>

枚举列限定该列值

枚举列和varchar关联时速度没有和数值类型快

例：

create table student (

​	    id        char(20),

​		name  varchar(20),

　　gender enum('男','女','保密')

)charset utf8;



insert into  student values('2',"李四",3);

insert into  student values('3',"王五",'男');

select (gender+1) from student order by gender



##### 6）日期和时间类型

DATETIME和TIMESTAMP



1.DATETIME  8个字节存储空间

```
能保存从1001年到9999年，精度为秒，它把日期封装到格式为YYYYMMDDHHMMSS的整数中，与时区无关。使用8个字节的存储空间


```

2.TIMESTAMP  4个字节存储空间

```
保存1970年~2038年，范围比DATETIME小得多。它和unix时间戳相同。

mysql函数 from_unixtime()函数把Unix时间戳转换为日期

	      unix_timestamp()函数把日期转换为Unix时间戳
timestamp显示的值依赖时区，它把客户端插入的时间从当前时区转化为UTC（世界标准时间）进行存储。查询时，将其又转化为客户端当前时区进行返回。


timestamp特殊属性：默认情况下，如果插入时未指定第一个timestamp列的值，则mysq设置这个列的值为				  当前时间。在插入一行记录时，mysql默认也会更新第一个timestamp列的值。						 timestamp列默认为not null。

两个属性的关键字： 
DEFAULT CURRENT_TIMESTAMP 插入时如未指定值则用当前时间来赋值
									
ON UPDATE CURRENT_TIMESTAMP 在更新记录时，如果为更新该列，使用当前时间来更新该字段
							
```



##### 7）位数据类型

所有位数据类型，不管底层存储格式和处理方式如何，从技术上来说都是字符串类型

1.BIT



数字被转换成二进制
字符将被转换成ascii码
boolean 类型被转换成0或1

2.SET





##### 8.选择标识符

标识符一旦选定某个类型，要确保在所有关联表中都使用相同的类型。混用不同数据类型可能导致性能问题，即使没有性能影响，在比较操作时隐式类型转换也可能导致很难发现的错误。

在可以满足值的范围的需求，并且预留未来增长空间的前提下，应该选择最小的数据类型



整数是最好的选择，如果可以，尽量避免用字符串类型做标识列。

如果存储uuid,则应该移除"-"符号，或者用unhex()函数转换uuid()的值为16字节的数字，并且存储在一个binary(16)列中，检索时可以通过hex()函数来格式化为十六进制字符串

例如：

```
insert into student(id,num) values(uuid(),UNHEX(replace(uuid(),'-',"")))

select hex(num) from student;
```

##### 9.特殊类型数据

ip转int(将带.的ip地址转换为int)

inet_aton()  

例如：

```
select inet_aton("192.168.3.20") from dual
```

int转ip(将带.的ip地址转换为int)

inet_ntoa()

例如：

```
select inet_NTOA("3232236308") from dual
```

