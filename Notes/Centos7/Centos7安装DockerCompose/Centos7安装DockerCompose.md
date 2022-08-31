1.官方教程

https://docs.docker.com/compose/install/





1.自动下载合适的docker compose版本

```
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

2.提权

```
sudo chmod +x /usr/local/bin/docker-compose
```

3.如果安装后docker-compose不起作用，添加个软链接

```
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
```

4.测试安装

```
docker-compose --version
```







| compose版本 | docker版本 |
| ----------- | ---------- |
| 3.4         | 17.09.0+   |
| 3.3         | 17.06.0+   |
| 3.2         | 17.04.0+   |
| 3.1         | 1.13.1+    |
| 3.0         | 1.13.0+    |
| 2.3         | 17.06.0+   |
| 2.2         | 1.13.0     |
