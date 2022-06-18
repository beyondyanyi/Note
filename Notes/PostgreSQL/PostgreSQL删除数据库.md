##### 删除数据库的方式：

1.使用drop database  SQL语句来删除

2.使用dropdb命令来删除

3.使用pgAdmin工具





### DROP DATABASE 删除数据库

DROP DATABASE 会删除数据库的系统目录项并且删除包含数据的文件目录。

DROP DATABASE 只能由超级管理员或数据库拥有者执行。

DROP DATABASE 命令需要在 PostgreSQL 命令窗口来执行，语法格式如下：

```
DROP DATABASE [ IF EXISTS ] name
```

**参数说明：**

- **IF EXISTS**：如果数据库不存在则发出提示信息，而不是错误信息。
- **name**：要删除的数据库的名称。

例如，我们删除一个 runoobdb 的数据库：

```
postgres=# DROP DATABASE runoobdb;
```

### dropdb 命令删除数据库

dropdb 是 DROP DATABASE 的包装器。

dropdb 用于删除 PostgreSQL 数据库。

dropdb 命令只能由超级管理员或数据库拥有者执行。

dropdb 命令语法格式如下：

```
dropdb [connection-option...] [option...] dbname
```

**参数说明：**

**dbname**：要删除的数据库名。

**options**：参数可选项，可以是以下值：

| 序号 |                         选项 & 描述                          |
| :--- | :----------------------------------------------------------: |
| 1    |      **-e**显示 dropdb 生成的命令并发送到数据库服务器。      |
| 2    |          **-i**在做删除的工作之前发出一个验证提示。          |
| 3    |                **-V**打印 dropdb 版本并退出。                |
| 4    | **--if-exists**如果数据库不存在则发出提示信息，而不是错误信息。 |
| 5    |          **--help**显示有关 dropdb 命令的帮助信息。          |
| 6    |             **-h host**指定运行服务器的主机名。              |
| 7    |     **-p port**指定服务器监听的端口，或者 socket 文件。      |
| 8    |             **-U username**连接数据库的用户名。              |
| 9    |                  **-w**连接时忽略输入密码。                  |
| 10   |                **-W**连接时强制要求输入密码。                |
| 11   | **--maintenance-db=dbname**删除数据库时指定连接的数据库，默认为 postgres，如果它不存在则使用 template1。 |

接下来我们打开一个命令窗口，进入到 PostgreSQL 的安装目录，并进入到 bin 目录，dropdb 名位于 **PostgreSQL安装目录/bin** 下，执行删除数据库的命令：

```
$ cd /Library/PostgreSQL/11/bin/
$ dropdb -h localhost -p 5432 -U postgres runoobdb
password ******
```

以上命令我们使用了超级用户 postgres 登录到主机地址为 localhost，端口号为 5432 的 PostgreSQL 数据库中并删除 runoobdb 数据库。