#### 1.搜索gitlab镜像

```
docker search gitlab
```

#### 2.拉取镜像文件(可以提前配置阿里镜像服务，提高拉取速度)

```
拉取原版：
docker pull docker.io/gitlab/gitlab-ce
或者选择中文社区版
docker pull twang2218/gitlab-ce-zh:latest
```

#### 3.创建相关目录（etc、log、data）,用于docker映射

```
mkdir -p /yanyi/gitlab/etc
mkdir -p /yanyi/gitlab/log
mkdir -p /yanyi/gitlab/data
```

#### 4.运行gitlab容器(要加上sudo)

```
sudo docker run \
--detach \
--publish 2222:22 \
--publish 8099:80 \
--publish 8443:443 \
--hostname 192.168.124.30 \
-v /yanyi/gitlab/etc:/etc/gitlab \
-v /yanyi/gitlab/log:/var/log/gitlab \
-v /yanyi/gitlab/data:/var/opt/gitla \
-v /etc/localtime:/etc/localtime:ro \
--name gitlab \
--restart always \
--privileged=true twang2218/gitlab-ce-zh:latest 
```

#### 5.修改ip

```
vi /yanyi/gitlab/etc/gitlab.rb
```

```
external_url 'http://你的机器ip或域名'
```

