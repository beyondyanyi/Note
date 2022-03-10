#### 1.清理旧的Docker

```bash
sudo yum remove docker \
	docker-client \
    docker-client-latest \
    docker-common \
    docker-latest \
    docker-latest-logrotate \
    docker-logrotate \
    docker-selinux \
    docker-engine-selinux \
    docker-engine
```

#### 2.更新yum包

```
sudo yum update
```

#### 3.安装需要的软件包

```
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```

#### 4.设置yum源

```
  sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

```

#### 5.安装docker

```
sudo yum install docker-ce-17.12.0.ce
```

#### 6.启动、加入开机启动

```
sudo systemctl start docker
sudo systemctl enable docker
```

#### 7.验证成功与否

```
docker ps
```

