#### 1.拉取镜像

```
docker pull eclipse-mosquitto
```

#### 2.创建映射目录

```
mkdir -p /software/mqtt-ordinary/config
mkdir -p /software/mqtt-ordinary/data
mkdir -p /software/mqtt-ordinary/log
```

#### 3.初始化配置文件

```
1）vi /software/mqtt-ordinaryo/config/mosquitto.conf


输入内容

persistence true
persistence_location /mosquitto/data
log_dest file /mosquitto/log/mosquitto.log

2）目录授权
chmod -R 777 /mosquitto

```

#### 4.创建容器

```
docker run -it --name=mosquitto --privileged \
-p 1883:1883 -p 9001:9001 \
-v /software/mqtt-ordinary/config/mosquitto.conf:/mosquitto/config/mosquitto.conf \
-v /software/mqtt-ordinary/data:/mosquitto/data \
-v /software/mqtt-ordinary/log:/mosquitto/log \
-d eclipse-mosquitto

```

#### 5.配置权限

```
vi /software/mqtt-ordinary/config/mosquitto.conf

# 关闭匿名模式
allow_anonymous false
# 指定密码文件
password_file /mosquitto/config/pwfile.conf
```

#### 6.进入容器配置

```
docker exec -it xxxxx sh

#对于passworf_file，可以复制一份模板，或者创建一个空文件
touch /mosquitto/config/pwfile.conf
chmod -R 777 /mosquitto/config/pwfile.conf

# 使用mosquitto_passwd命令创建用户，第一个test是用户名，第二个test2020是密码
mosquitto_passwd -b /mosquitto/config/pwfile.conf admin password
```







最终配置

```
persistence true
listener 8883
persistence_location /mosquitto/data
log_dest file /mosquitto/log/mosquitto.log
allow_anonymous false
# 指定密码文件
password_file /mosquitto/config/pwfile.conf
```

