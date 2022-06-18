##### not null 

指示某列不能存储null值

##### unique 

确保某列的值都是唯一的

##### primary key  

not null 和unique的结合

##### foreign key

外键

##### check

CHECK 约束保证列中的所有值满足某一条件，即对输入一条记录要进行检查

##### exclusion

EXCLUSION 约束确保如果使用指定的运算符在指定列或表达式上比较任意两行，至少其中一个运算符比较将返回 false 或 null。







##### 删除约束

删除约束必须知道约束名称，已经知道名称来删除约束很简单，如果不知道名称，则需要找到系统生成的名称，使用 **\d 表名** 可以找到这些信息。

通用语法如下：

```
ALTER TABLE table_name DROP CONSTRAINT some_name;
```