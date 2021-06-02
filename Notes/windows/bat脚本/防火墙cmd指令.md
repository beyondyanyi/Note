#### 添加应用程序防火墙

```
netsh advfirewall firewall add rule name="规则名称" dir=in|out action=allow|block|bypass program="程序路径" enable=yes|no

name：是规则名称，必须是唯一，不能是all。
dir：是指方向，in 表示入站，out 表示出站。
action：是对规则的操作，allow 表示允许连接，block 表示阻止连接，bypass 表示只允许安全连接。
program：指定程序的路径。
enable：是否启用规则，默认为 yes 。
```



#### 添加端口的防火墙

```
netsh advfirewall firewall add rule name="规则名称" dir=in action=allow protocol=TCP localport=80

protocol  协议类型 tcp/udp
localport 端口号 1~65535
其他同上
```



#### 删除规则

```
netsh advfirewall firewall delete rule name="规则名称" programe="程序路径"

其他同上
```

