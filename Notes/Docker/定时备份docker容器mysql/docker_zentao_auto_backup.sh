###Docker
#!/bin/bash

#author by beyondyanyi@gmail.com

#依赖  ：1.在本机上要装sshpass: yum install sshpass(用于远程备份不需要输入密码)  2.从window上传本脚本时，需要运行 sed -i 's/\r//'  xxx.sh 转换行符  3.docker里面执行 要去掉 -it

#当前时间
sysdate=$(date +"%Y-%m-%d-%H%M%S")

#备份的mysql 数据库连接用户
mysql_user=root
#备份的mysql 数据库连接密码
mysql_pwd=123456
#备份的mysql 数据库名
mysql_db=zentao

#docker容器名
docker_name=zentao

#本地备份路径
local_path=/backup/


#从容器中备份出来，并压缩
docker exec -it ${docker_name} mysqldump -u${mysql_user} -p${mysql_pwd} ${mysql_db} | gzip >  ${local_path}${sysdate}_${mysql_db}.sql.gz