#### 1.拉取镜像

```
docker pull redis
```



#### 2.从官网获取redis.conf

```
1)从官网获取 [redis.conf](http://download.redis.io/redis-stable/redis.conf) 配置文件 	

2)修改默认配置文件 		
bind 127.0.0.1 #注释掉这部分，这是限制redis只能本地访问
protected-mode no #默认yes，开启保护模式，限制为本地访问
daemonize no#默认no，改为yes意为以守护进程方式启动，可后台运行，除非kill进程（可选），改为yes会使配置文件方式启动redis失败
dir  ./ #输入本地redis数据库存放文件夹（可选）
appendonly yes #redis持久化（可选）
```

#### 3.docker运行redis容器

```
docker run -p 6380:6379 --name redis -v /redis/conf/redis.conf:/etc/redis/redis.conf -v /redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
```

