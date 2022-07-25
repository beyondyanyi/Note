docker安装mysql8.0

#### 1.拉取镜像

```
docker pull mysql:8.0
```

#### 2.创建映射目录

```
mkdir -p /software/mysql8/mysql/data &&
mkdir -p /software/mysql8/mysql/logs &&
mkdir -p /software/mysql8/mysql/conf
```

#### 3.创建配置文件my.cnf

```
vi /software/mysql8/mysql/conf/my.cnf
```

内容：

```
[mysqld]
user=mysql
character-set-server=utf8
default_authentication_plugin=mysql_native_password
secure_file_priv=/var/lib/mysql
expire_logs_days=7
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
max_connections=1000

[client]
default-character-set=utf8

[mysql]
default-character-set=utf8
```

#### 4.启动容器

```
docker run --restart=always --privileged -p 33006:3306 --name mysql8 -v /software/mysql8/mysql/conf/my.cnf:/etc/mysql/my.cnf  -v /software/mysql8/mysql/logs:/var/log/mysql -v /software/mysql8/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0
```

##### 5.进入容器修改root可远程登录（可选）

```
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'xxx';

flush privileges;
```



