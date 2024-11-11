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

#### 6.更换国内仓库源

vi /etc/docker/daemon.json

```
{
 "registry-mirrors": ["https://docker.mirrors.ustc.edu.cn"]
}
```

刷新

```
sudo systemctl daemon-reload 
sudo systemctl restart docker
sudo docker info
```

#### 7.启动、加入开机启动

```
#启动
sudo systemctl start docker
#设置为开机自启动
sudo systemctl enable docker
```

#### 8.验证成功与否

```
docker ps
```

#### 9.安装docker-compose

```
# 下载
curl -L https://github.com/docker/compose/releases/download/1.25.4/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
# 添加可执行权限
sudo chmod +x /usr/local/bin/docker-compose
# 查看版本
docker-compose --version
```

#### 其他：

卸载docker

```
# 停止docker服务
systemctl stop docker
# 移除docker ce
yum remove docker-ce docker-ce-cli containerd.io
# 删除相关文件
rm -rf /var/lib/docker
rm -rf /var/lib/containerd
```
