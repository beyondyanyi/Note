订阅主题
mosquitto_sub -h 192.168.0.1 -p 1883 -u root -P root -t topicname
推送消息
mosquitto_pub -h 192.168.0.1 -p 1883 -u root -P root -t topicname -m "发送的消息内容"



#### 启动重启

1.1 查看mosquitto的进程
命令：ps -aux | grep mosquitto

1.2 杀掉进程#
命令：kill -9 xxx

1.3 启动#
命令：mosquitto -c /etc/mosquitto/mosquitto.conf -d

mosquitto [-c config file] [ -d | --daemon ] [-p port number] [-v]

-c 后面跟的是启动mosquitto可以调整的参数，比如是否开启基本认证，端口是什么，SSL单向和双向的认证配置等等。
-d 表示MQTT mosquitto将在后台运行。
-p 代表当前的mosquitto服务实例启动以后，其监听端口号，这个配置的覆盖[-c config file] 指定的配置文件中的端口
-v 代码调试模式（verbose）可以输出更多的信息









```bash

```

### mosquitto_pub 命令参数说明



1. -d 打印debug信息
2. -f 将指定文件的内容作为发送消息的内容
3. -h 指定要连接的域名 默认为localhost
4. -i 指定要给哪个clientId的用户发送消息
5. -I 指定给哪个clientId前缀的用户发送消息
6. -m 消息内容
7. -n 发送一个空（null）消息
8. -p 连接端口号
9. -q 指定QoS的值（0,1,2）
10. -t 指定topic
11. -u 指定broker访问用户
12. -P 指定broker访问密码
13. -V 指定MQTT协议版本
14. --will-payload 指定一个消息，该消息当客户端与broker意外断开连接时发出。该参数需要与--will-topic一起使用
15. --will-qos Will的QoS值。该参数需要与--will-topic一起使用
16. --will-retain 指定Will消息被当做一个retain消息（即消息被广播后，该消息被保留起来）。该参数需要与--will-topic一起使用
17. --will-topic 用户发送Will消息的topic

### mosquitto_sub 命令参数说明

1. -c 设定‘clean session’为无效状态，这样一直保持订阅状态，即便是已经失去连接，如果再次连接仍旧能够接收的断开期间发送的消息。
2. -d 打印debug信息
3. -h 指定要连接的域名 默认为localhost
4. -i 指定clientId
5. -I 指定clientId前缀
6. -k keepalive 每隔一段时间，发PING消息通知broker，仍处于连接状态。 默认为60秒。
7. -q 指定希望接收到QoS为什么的消息 默认QoS为0
8. -R 不显示陈旧的消息
9. -t 订阅topic
10. -v 打印消息
11. --will-payload 指定一个消息，该消息当客户端与broker意外断开连接时发出。该参数需要与--will-topic一起使用
12. --will-qos Will的QoS值。该参数需要与--will-topic一起使用
13. --will-retain 指定Will消息被当做一个retain消息（即消息被广播后，该消息被保留起来）。该参数需要与--will-topic一起使用
14. --will-topic 用户发送Will消息的topic