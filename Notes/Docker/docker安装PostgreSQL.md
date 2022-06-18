1.拉取最新镜像

```
docker pull postgres
```

2.运行镜像

```shell
docker run --name postgres2 --privileged=true -e POSTGRES_PASSWORD=password -p 5432:5432 -v pgdata:/var/lib/postgresql/data -d postgres
```

3.开放端口并测试连接

```

```

