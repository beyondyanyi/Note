#### 1.拉取镜像

```
docker pull mysql:8.0
```





#### 2.在指定目录创建my.cnf

```
[mysqld]
datadir=/usr/local/mysql/data
#使用mysql8以前的密码插件，以便navicat等工具能够正常连接
default_authentication_plugin=mysql_native_password
default-storage-engine=INNODB
character_set_server=utf8mb4
secure_file_priv=/var/lib/mysql
lower_case_table_names = 0
default-time_zone = '+8:00'
[mysqld_safe]
character_set_server=utf8mb4
[mysql]
default-character-set=utf8mb4
[mysql.server]
default-character-set=utf8mb4
[client]
default-character-set=utf8mb4


```

#### 3.在mysql目录下创建docker-compose.yml,并编辑docker-compose.yml

```
version: "3.4"
services:
  mysql8:
    image: mysql:8.0
    container_name: mysql8
    restart: always
    volumes:
      - /software/mysql8/conf/my.cnf:/etc/mysql/my.cnf
      - /software/mysql8/data:/usr/local/mysql/data
      - /software/mysql8/log:/var/log/mysql
    user: root
    ports:
      - "33060:3306"
    environment:
      MYSQL_ROOT_PASSWORD: 'xxxx'
    privileged: true

```

#### 4.启动容器

```
docker compose up -d
```

#### 5.查看mysql启动情况

```
docker compose ps mysql8
```

