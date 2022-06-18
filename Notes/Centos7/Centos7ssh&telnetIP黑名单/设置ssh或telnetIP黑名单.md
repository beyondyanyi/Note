#### 1.添加ip黑名单

```
vi /etc/hosts.deny

sshd:192.168.1.16:deny
```



#### 2.添加ip白名单（白名单优先级最高）

```
vi /etc/hosts.allow

sshd:192.168.1.16:allow
```

