docker run --name mysqlserver -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d -i -p 3306:3306 mysql:latest





#### docker安装mysql

##### 1.拉取镜像

```
docker pull mysql:5.7
```

##### 2.创建映射目录

```
mkdir -p /mysql/conf 
mkdir -p /mysql/data 
mkdir -p /mysql/log
```

##### 3.进入conf目录创建my.cnf

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

##### 4.创建mysql容器

```
docker run --name mysql57 -p 3307:3306 -v /mysql/conf/my.cnf:/etc/mysql/my.cnf -v /mysql/data:/var/lib/mysql -v /mysql/log:/var/log/mysql -e MYSQL_ROOT_PASSWORD=xxx --restart=always -d mysql:5.7

```

