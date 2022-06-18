Schema可以看做是一个表的集合

##### 一个模式可以包含视图、索引、数据类型、函数和操作符等

##### 相同的对象名称可以被用于不同的模式中而不会出现冲突，例如schema1和myschema都可以包含名为mytable的表。

##### 

##### 使用模式的优势：

```
1.允许多个用户使用一个数据库并且不会互相干扰
2.将数据库对象组织成逻辑组以便更容易管理
3.第三方应用的对象可以放在独立的模式中，这样它们就不会与其他对象的名称发生冲突
```

模式类似于操作系统层的目录，但是模式不能嵌套





### 语法

我们可以使用 **CREATE SCHEMA** 语句来创建模式，语法格式如下：

```
CREATE SCHEMA myschema.mytable (
...
);
```



##### 删除模式

```
drop schema myschema;
```

##### 删除一个模式以及其中包含的对象

```
drop schema myschema cascade;
```



##### 创建表格

```
create table myschema.plan (
	id varchar(255) not null,
	name varchar(255) not null,
	PRIMARY KEY(id)
)
```

