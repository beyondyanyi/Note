#### 1.登录阿里云

#### 2.获取容器加速地址

#### 3.docker配置加速

```
vi /etc/docker/daemon.json
```

```
{
  "registry-mirrors": ["https://iv3tl8c3.mirror.aliyuncs.com"]
}
```

```
sudo systemctl daemon-reload
sudo systemctl restart docker
```

