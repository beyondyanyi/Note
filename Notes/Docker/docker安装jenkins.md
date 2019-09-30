#### 1.拉取jenkins镜像

```
docker pull jenkins/jenkins
```

#### 2.创建jenkins映射文件夹

```
mkdir /yanyi/jenkins
```

#### 3.运行容器

```
sudo docker run -itd -u root -p 8000:8080 -p 50000:50000 --name jenkins --privileged=true  -v /yanyi/jenkins:/var/jenkins_home jenkins/jenkins

参数详解：
	-itd
	-u 以指定用户运行
	-p 端口映射
	--name 容器名
	--privileged 以最高权限运行
	-v 文件夹映射
```

#### 4.开放端口

```
#开放端口
firewall-cmd --zone=public --add-port=8000/tcp --permanent
firewall-cmd --zone=public --add-port=50000/tcp --permanent
#重载生效
firewall-cmd --reload
```

