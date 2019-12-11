#### 1.安装telnet-server

```
yum install telnet-server
```



#### 1.开启xinetd

```
systemctl enable xinetd.service
systemctl start xinetd
```

#### 2.开启telnet

```
systemctl enable telnet.socket
systemctl start telnet.socket
```

#### 3.新增用户，设置密码,分配root权限

```
useradd test
passwd 123456

chmod -v u+w /etc/sudoers

vim /etc/sudoers

##在Allow root to run any commands anywhere
 test     ALL=(ALL)       ALL

chmod -v u-w /etc/sudoers
```

