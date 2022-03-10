##### 1.下载

```
wget https://npm.taobao.org/mirrors/node/v14.13.1/node-v14.13.1-linux-x64.tar.xz
```

##### 2.解压

```
# xz格式，先用xz解压
xz -d node-v14.13.1-linux-x64.tar.xz
# 再用tar解压
tar xvf node-v14.13.1-linux-x64.tar
```

##### 3.配置软链接

```
ln -s /software/node/node-v16.13.1-linux-x64/bin/node /usr/bin/node
ln -s /software/node/node-v16.13.1-linux-x64/bin/npm /usr/bin/npm
```

##### 4.查看安装版本

```
node -v
```

##### 5.安装pm2

安装

```
npm install -g pm2
```

设置软链接

```
ln -s /software/node/node-v16.13.1-linux-x64/bin/pm2 /usr/bin/pm2
```

自启

```
pm2 startup centos
```

启动

```
pm2 start server/add.js
```



其他命令：

pm2 list #查看进程
pm2 stop/reload/restart/delete all #停止/重载/重启/删除 所有进程
pm2 stop/reload/restart/delete 0 #停止/重载/重启/删除 pm2进程列表中进程为0的进程
pm2 logs [--raw] #显示所有进程的日志
pm2 flush #清空所有日志文件
pm2 reloadLogs #重载所有日志
