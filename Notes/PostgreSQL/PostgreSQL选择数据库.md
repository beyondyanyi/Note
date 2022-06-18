### 数据库的命令窗口

PostgreSQL 命令窗口中，我们可以命令提示符后面输入 SQL 语句：

```
postgres=#
```

使用 **\l** 用于查看已经存在的数据库：

```
postgres=# \l
                             List of databases
   Name    |  Owner   | Encoding | Collate | Ctype |   Access privileges   
-----------+----------+----------+---------+-------+-----------------------
 postgres  | postgres | UTF8     | C       | C     | 
 runoobdb  | postgres | UTF8     | C       | C     | 
 template0 | postgres | UTF8     | C       | C     | =c/postgres          +
           |          |          |         |       | postgres=CTc/postgres
 template1 | postgres | UTF8     | C       | C     | =c/postgres          +
           |          |          |         |       | postgres=CTc/postgres
(4 rows)
```

接下来我们可以使用 **\c + 数据库名** 来进入数据库：

```
postgres=# \c runoobdb
You are now connected to database "runoobdb" as user "postgres".
runoobdb=# 
```

### 系统命令行窗口

在系统的命令行查看，我么可以在连接数据库后面添加数据库名来选择数据库：

```
$ psql -h localhost -p 5432 -U postgres runoobdb
Password for user postgres: ****
psql (11.3)
Type "help" for help.
You are now connected to database "runoobdb" as user "postgres".
runoobdb=# 
```