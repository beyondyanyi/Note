在linux中rz 和 sz 命令允许开发者与主机通过串口进行传递文件了

```
yum install lrzsz
```







sz：将选定的文件发送（send）到本地机器
rz：运行该命令会弹出一个文件选择窗口，从本地选择文件上传到Linux服务器





从服务端发送文件到客户端：

sz filename

从客户端上传文件到服务端：

rz