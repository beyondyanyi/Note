具体gitlab文档可参考官方文档http://你的gitlab地址/help/raketasks/backup_restore.md

#### 1.拉取gitlab社区版镜像

```
#docker pull twang2218/gitlab-ce-zh:11.1.4
```

#### 2.在宿主机建立相关映射目录

```
#mkdir /5w/docker_gitlab/data
#mkdir /5w/docker_gitlab/etc
#mkdir /5w/docker_gitlab/log
```

#### 3.运行容器

```
#sudo docker run \
	--detach \
	--publish 2222:22 \
	--publish 80:80 \
	--publish 8443:443 \
	--hostname 124.70.134.134 \
	-v /5w/docker_gitlab/etc:/etc/gitlab \
	-v /5w/docker_gitlab/log:/var/log/gitlab \
	-v /5w/docker_gitlab/data:/var/opt/gitlab \
	-v /etc/localtime:/etc/localtime:ro \
	--name gitlab \
	--restart always \
	--privileged=true twang2218/gitlab-ce-zh:11.1.4

```

#### 4.修改备份文件保存路径

```
修改备份文件在容器中保存的时间（去掉前面#，604800秒为7天）
#vi /5w/docker_gitlab/etc/gitlab.rb

gitlab_rails['backup_keep_time'] = 604800

重载配置
#docker exec gitlab gitlab-ctl reconfigure
```

#### 5.备份

```
1)手动备份
宿主机（备份文件默认在/5w/docker_gitlab/data/backups下，如在gitlab.rb中配置，则是配置的路径）：
#docker exec gitlab gitlab-rake gitlab:backup:create
容器内：
#gitlab-rake gitlab:backup:create


2）备份脚本(备份到宿主机并移动到指定目录下)
#touch /script/docker_gitlab_back.sh

#！ /bin/bash
docker exec gitlab gitlab-rake gitlab:backup:create
mv /5w/docker_gitlab/data/backups/* /backup/gitlab/

3)自动备份，将脚本写入定时任务
#crontab -e 

##每天凌晨3点自动执行docker_gitlab备份操作，备份默认在/5w/docker_gitlab/data/backups
0 3 * * * bash /script/docker_gitlab_backup.sh

#service crond reload //重新载入配置
```

#### 6.docker_gitlab恢复

```
1)拷贝最新的备份文件到新建的docker gitlab容器里根目录下/   
	#docker exec -it gitlab bash
	#docker cp /test/test.txt  gitlab:/
2)进入容器并运行恢复命令
	#gitlab-rake gitlab:backup:restore BACKUP=备份文件编号（去掉后缀_gitlab_backup.tar）
3)退出容器
	#exit
4)重启容器
	#docker restart gitlab
```

#### 7.其他gitlab命令

```
修改配置后重载
docker exec gitlab gitlab-ctl reconfigure

#配置文件路径
/宿主机映射目录/etc/gitlab.rb

#停止服务
docker exec gitlab gitlab-ctl stop

#停止相关数据连接服务
docker exec gitlab gitlab-ctl stop unicorn
docker exec gitlab gitlab-ctl stop sidekiq

#启动服务
docker exec gitlab gitlab-ctl start

```

