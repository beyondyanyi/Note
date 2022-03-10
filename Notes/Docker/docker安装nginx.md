最近项目上想要把项目部署到docker容器中，然后研究试错了好久，才终于成功，于是记录下来。

#### 首先下载nginx镜像



```
docker pull nginx
```

#### 创建挂载的目录，我是放在/data/nginx里面，可自行更改



```
mkdir -p /data/nginx/conf #存放配置文件
mkdir -p /data/nginx/logs
mkdir -p /data/nginx/html
mkdir -p /data/nginx/conf.d
```

#### 因为不能挂载文件，只能挂载一个文件夹，所以我们要先创建一个测试test容器的nginx，然后复制配置文件到挂载的目录上



```
##启动测试容器
docker run --name test -d nginx

##复制配置文件
docker cp test:/etc/nginx/nginx.conf /data/docker/nginx/conf/
docker cp test:/etc/nginx/conf.d/default.conf  /data/docker/nginx/conf.d

##如果不知道配置文件在docker里面的目录位置,可以进去看一下
docker exec -it test /bin/bash
```

#### 然后运行你自己的nginx



```
docker run --name nginx --privileged -it -p 80:80 -v /data/nginx/conf/nginx.conf:/etc/nginx/nginx.conf:ro -v /data/nginx/conf.d:/etc/nginx/conf.d:ro -v /data/nginx/html:/usr/share/nginx/html:rw  -v/data/nginx/logs:/var/log/nginx -d nginx
```

[![img](https://img2020.cnblogs.com/blog/1886651/202104/1886651-20210421114315773-1293584127.png)](https://img2020.cnblogs.com/blog/1886651/202104/1886651-20210421114315773-1293584127.png)

#### 最后把我们的放到html文件夹解压,重启nginx即可

[![img](https://img2020.cnblogs.com/blog/1886651/202104/1886651-20210421113056745-497805570.png)](https://img2020.cnblogs.com/blog/1886651/202104/1886651-20210421113056745-497805570.png)



```
##在html文件夹解压我们上传的dist文件
unzip dist.zip

##重启Jenkins
docker restart b0ba
```

#### 最后就去访问我们的ip和端口，试试看，也可以在本机测试是否成功



```
curl ip:port
```