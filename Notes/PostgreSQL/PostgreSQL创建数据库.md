# PostgreSQL 创建数据库

PostgreSQL 创建数据库可以用以下三种方式：

- 1、使用 **CREATE DATABASE** SQL 语句来创建。
- 2、使用 **createdb** 命令来创建。
- 3、使用 **pgAdmin** 工具。

### CREATE DATABASE 创建数据库

CREATE DATABASE 命令需要在 PostgreSQL 命令窗口来执行，语法格式如下：

```
CREATE DATABASE dbname;
```

例如，我们创建一个 runoobdb 的数据库：

```
postgres=# CREATE DATABASE runoobdb;
```

### createdb 命令创建数据库

createdb 是一个 SQL 命令 CREATE DATABASE 的封装。

createdb 命令语法格式如下：

```
createdb [option...] [dbname [description]]
```

**参数说明：**

**dbname**：要创建的数据库名。

**description**：关于新创建的数据库相关的说明。

**options**：参数可选项，可以是以下值：

| 序号 |                     选项 & 描述                     |
| :--- | :-------------------------------------------------: |
| 1    |       **-D tablespace**指定数据库默认表空间。       |
| 2    |     **-e**将 createdb 生成的命令发送到服务端。      |
| 3    |          **-E encoding**指定数据库的编码。          |
| 4    |         **-l locale**指定数据库的语言环境。         |
| 5    |       **-T template**指定创建此数据库的模板。       |
| 6    |      **--help**显示 createdb 命令的帮助信息。       |
| 7    |           **-h host**指定服务器的主机名。           |
| 8    | **-p port**指定服务器监听的端口，或者 socket 文件。 |
| 9    |         **-U username**连接数据库的用户名。         |
| 10   |                **-w**忽略输入密码。                 |
| 11   |           **-W**连接时强制要求输入密码。            |

接下来我们打开一个命令窗口，进入到 PostgreSQL 的安装目录，并进入到 bin 目录，createdb 命令位于 **PostgreSQL安装目录/bin** 下，执行创建数据库的命令：

```
$ cd /Library/PostgreSQL/11/bin/
$ createdb -h localhost -p 5432 -U postgres runoobdb
password ******
```

以上命令我们使用了超级用户 postgres 登录到主机地址为 localhost，端口号为 5432 的 PostgreSQL 数据库中并创建 runoobdb 数据库。

### pgAdmin 工具创建数据库

pgAdmin 工具提供了完整操作数据库的功能：

![img](https://www.runoob.com/wp-content/uploads/2019/05/60962103-5153-4729-824F-1E0692B9C48F.jpg)