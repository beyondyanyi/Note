#### 1.拉取镜像

```
 docker pull emqx/emqx:v4.0.0
```

#### 2.运行镜像

```
docker run -d --name emqx -p 1884:1883 -p 8081:8081 -p 8083:8083 -p 8883:8883 -p 8084:8084 -p 18083:18083 --privileged   emqx/emqx:v4.0.0 
```

#### 3.关闭匿名认证

```bash
进入容器内部
vi /opt/emqx/etc/emqx.conf

allow_anonymous false
```

#### 4.配置mysql认证

```bash
1）配置 etc/plugins/emqx_auth_mysql.conf

## 服务器地址
auth.mysql.server = 127.0.0.1:3306
## 连接池大小
auth.mysql.pool = 8
auth.mysql.username = emqx
auth.mysql.password = public
auth.mysql.database = mqtt
auth.mysql.query_timeout = 5s

2)创建默认表
CREATE TABLE `mqtt_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(35) DEFAULT NULL,
  `is_superuser` tinyint(1) DEFAULT 0,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mqtt_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


3）插入默认数据
INSERT INTO `mqtt_user` ( `username`, `password`, `salt`)
VALUES
	('emqx', 'efa1f375d76194fa51a3556a97e641e61685f914d446979da50a551a4333ffd7', NULL);

4）配置加盐加密方式（可选）
# etc/plugins/emqx_auth_mysql.conf

auth.mysql.password_hash = sha256


##注意：MySQL 8.0 及以后版本使用了 `caching_sha2_password` 作为默认身份验证插件，受限于客户端驱动你必须将其更改为 `mysql_native_password` 插件：
ALTER USER 'your_username'@'your_host' IDENTIFIED WITH mysql_native_password BY 'your_password';
```



##### 5.配置tls认证

```
https://www.emqx.cn/blog/enable-two-way-ssl-for-emqx

docker cp 58c:/opt/emqx/etc/certs/emqx.pem /download
docker cp 58c:/opt/emqx/etc/certs/emqx.key /download
docker cp 58c:/opt/emqx/etc/certs/ca.pem /download
```



