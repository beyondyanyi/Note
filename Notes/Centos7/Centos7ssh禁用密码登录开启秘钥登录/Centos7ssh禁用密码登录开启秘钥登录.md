#### 1.生成秘钥(公钥（xxx.pub）、私钥(xxx))

```
ssh-keygen -t rsa
```

#### 2.下载私钥

```
下载xxx
```

#### 3.安装公钥

```
cat xxx.pub >> /root/.ssh/authorized_keys
```

#### 4.设置公钥权限及文件夹权限

```
chmod  600 /root/.ssh/authorized_keys
chmod  700 /root/.ssh/
```

#### 5.配置ssh文件

```
vim /etc/ssh/sshd_config

RSAAuthentication yes       #RSA身份验证:yes开启no关闭
PubkeyAuthentication yes    #公钥身份验证:yes开启no关闭
PermitRootLogin yes  		#允许root登录:yes开启no关闭
PasswordAuthentication yes   #密码登录方式:yes开启no关闭  （注意，这里先秘钥登录成功再关闭）
```



#### 5.重启ssh服务

```
systemctl restart sshd
```





原文[<https://blog.csdn.net/laidbacklzs/article/details/90714158>]