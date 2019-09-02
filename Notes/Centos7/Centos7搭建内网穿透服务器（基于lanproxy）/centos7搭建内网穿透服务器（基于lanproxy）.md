### ﻿《基于https://github.com/ffay/lanproxy   搭建的内网穿透服务器》

#### 1.准备工作：

```
	服务器一台、lanProxy服务端和客户端(https://seafile.io2c.com/d/3b1b44fee5f74992bb17/)
```



#### 2.vps上下载服务端 proxy-server-xxxx,需要穿透服务的机器上下载客户端proxy-client

#### 3.配置服务端proxy-server-xxx/conf文件夹下 config.properties

```
#这个默认
server.bind=0.0.0.0

#这是客户端连接服务器的端口
server.port=4900

#这是https配置，不用管
server.ssl.enable=true
server.ssl.bind=0.0.0.0
server.ssl.port=4993
server.ssl.jksPath=test.jks
server.ssl.keyStorePassword=123456
server.ssl.keyManagerPassword=123456
server.ssl.needsClientAuth=false

#这个默认
config.server.bind=0.0.0.0

#这是在线配置端口、账户、密码
config.server.port=8090
config.admin.username=user
config.admin.password=pwd
```

#### 4.在vps防火墙上开启4900和8090端口

#### 5.在服务端proxy-server-xxx/bin 下，使用./startup.sh 启动服务

#### 6.任意位置使用浏览器访问http://hostIp:port

​	

```
1)登陆：账户密码为第2步配置的username和password
2)添加客户端：名称随便填，密钥可随机生成可自行填写
3)添加配置：代理名称随便填，公网端口即vps上空闲端口即可(例：60000)，后端ip端口格式为ip:port（例：127.0.0.1:80）
```

#### 7.在vps防火墙上开启60000端口（即第6步使用的端口）

#### 8.配置客户端proxy-client/conf 下config.properties

```
#这里的密钥即第4步配置的密钥
	client.key=dfasdfasdfasd167489e79rwe45r4ew6rwe

​	#这是是https配置，不用管
​	ssl.enable=false
​	ssl.jksPath=test.jks
​	ssl.keyStorePassword=123456
​	
​	#这里是服务器地址	
​	server.host=78.15.15.459

​	#这里是第2步配置的server.port
​	server.port=4900
```

#### 9.在客户机上启动proxy-client/bin/startup.bat （启动需要jdk环境）

```
		 (启动后可以在第6步中看到服务器在线，内网已穿透)
		指向服务器上 ip:60000的请求会转发至后端ip:port上
```

​	