#### 1.拉取镜像

```
docker pull liftoff/gateone
```

#### 2.运行镜像

```
docker run -d -t -p 443:8000 -h [hostname]  --restart=always --name gateone liftoff/gateone gateone
```

#### 3.开放端口

```
firewall-cmd --zone=public --add-port=443/tcp --permanent
firewall-cmd --reload
firewall-cmd --list-all
```



#### 