**1) 获取远程主机的系统类型及开放端口**

```
nmap -sS -P0 -sV -O <target>
```

这里的 < target > 可以是单一 IP, 或主机名，或域名，或子网

-sS TCP SYN 扫描 (又称半开放,或隐身扫描)
-P0 允许你关闭 ICMP pings.
-sV 打开系统版本检测
-O 尝试识别远程操作系统

其它选项:

-A 同时打开操作系统指纹和版本检测
-v 详细输出扫描情况.

**2) 列出开放了指定端口的主机列表**

```
nmap -sT -p 80 -oG – 192.168.1.* | grep open
```

**3) 在网络寻找所有在线主机**

```
nmap -sP 192.168.0.*
```

或者也可用以下[命令](https://www.linuxcool.com/):

```
nmap -sP 192.168.0.0/24
```

指定 subnet

**4) Ping 指定范围内的 IP 地址**

```
nmap -sP 192.168.1.100-254
```

**5) 在某段子网上查找未占用的 IP**

```
nmap -T4 -sP 192.168.2.0/24 && egrep “00:00:00:00:00:00″ /proc/net/arp
```

**6) 在局域网上扫找 Conficker 蠕虫病毒**

```
nmap -PN -T4 -p139,445 -n -v –script=smb-check-vulns –script-args safe=1 192.168.0.1-254
```

**7) 扫描网络上的恶意接入点 （rogue APs）.**

```
nmap -A -p1-85,113,443,8080-8100 -T4 –min-hostgroup 50 –max-rtt-timeout
2000 –initial-rtt-timeout 300 –max-retries 3 –host-timeout 20m
–max-scan-delay 1000 -oA wapscan 10.0.0.0/8
```

**8 ) 使用诱饵扫描方法来扫描主机端口**

```
sudo nmap -sS 192.168.0.10 -D 192.168.0.2
```

**9) 为一个子网列出反向DNS记录**

```
nmap -R -sL 209.85.229.99/27 | awk ‘{if($3==”not”)print”(“$2″) no PTR”;else print$3″ is “$2}’ | grep ‘(‘
```

**10) 显示网络上共有多少台 [Linux](https://www.linuxprobe.com/) 及 Win 设备?**

```
sudo nmap -F -O 192.168.0.1-255 | grep “Running: ” > /tmp/os; echo “$(cat /tmp/os | grep Linux \
| wc -l) Linux device(s)”; echo “$(cat /tmp/os | grep Windows | wc -l) Window(s) device”
```