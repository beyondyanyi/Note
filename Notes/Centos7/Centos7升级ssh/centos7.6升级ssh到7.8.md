#### 1.开启telnet、

```
##安装相关软件
yum install -y telnet telnet-server lrzsz wget xinetd vim
##自启动
systemctl enable telnet.socket
##启动
systemctl start telnet.socket
##自启动
systemctl enable xinetd
##启动
systemctl start xinetd
##防火墙规则
firewall-cmd --zone=public --add-port=23/tcp --permanent
##重载防火墙
firewall-cmd --reload
```

#### 2.新建用户

```
##新增用户
useradd test
##修改密码
echo "123456" | passwd --stdin test
```



#### 3.分配用户权限

```
##查看sudoers位置
whereis sudoers
##查看suoders权限
ls -l /etc/sudoers
##新增写权限
chmod -v u+w /etc/sudoers
##编辑文件，将新建用户加入
vim /etc/sudoers
(加入test用户  ALL=(ALL)  ALL)、
##收回写权限
chmod -v u-w /etc/sudoers
```

#### 4.升级ssh

```
##安装相关
yum install -y gcc openssl-devel pam-devel rpm-build
##下载
wget https://openbsd.hk/pub/OpenBSD/OpenSSH/portable/openssh-7.8p1.tar.gz
##解压
tar -zxvf openssh-7.8p1.tar.gz
##进入文件夹，编译
./configure --prefix=/usr --sysconfdir=/etc/ssh --with-pam --with-zlib --with-md5-passwords --with-tcp-wrappers
##安装
make && make install
##查看ssh版本
ssh -V
##关闭selinux
setenforce 0

vim /etc/selinux/config
(SELINUX=disabled)
##修改配置
vim /etc/ssh/sshd_config
（PermitRootLogin yes）
##重启ssh服务
service sshd restart

```

