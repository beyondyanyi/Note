##### 触发器的定义：

当对某一表进行诸如UPDATE、INSERT 、DELETE这些操作时，mysql就会自动执行触发器所定义的SQL语句，从而确保对数据的处理必须符合这些SQL语句所定义的规则。



##### 触发器经常用于加强数据的完整性约束和业务规则等。触发器创建语法四要素：

1.监视地点（table）

2.监视事件(insert/update/delete)

3.触发时间(after/before)

4.触发事件(insert/update/delete)





##### 创建触发器并定义触发器事件

```
create trigger 触发器名
after/before insert/update/delete on 名
for each row #这句话在mysql是固定的
begin 
	sql语句；
end;
```





##### 删除触发器

```
drop trigger if exists test_trigger;
或
drop trigger if exists 'my_table'.test_trigger;

或通过
show triggers;
查询不到对应的触发器可直接删除文件
rm /usr/local/mysql-5.5.15-osx10.6-x86/data/my_table/test_trigger.TRN
```



##### 实例：

```
#创建触发器
create trigger triggeryi
before insert on student 
for each row
begin
set  NEW.name='哇哇哇哇';
end


#删除触发器
drop trigger if exists triggeryi;



#插入触发trigger
insert into student values('55555','33333',now());
```

