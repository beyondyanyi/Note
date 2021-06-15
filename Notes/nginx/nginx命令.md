##nginx 会忽略带下划线的地址

#### linux:

```
nginx安装目录/sbin/nginx -s reload            # 重新载入配置文件
nginx安装目录/sbin/nginx -s reopen            # 重启 Nginx
nginx安装目录/sbin/nginx -s stop              # 停止 Nginx
nginx安装目录/sbin/nginx					#启动Nginx
```

#### windows:

```
nginx.exe -t    测试配置文件
nginx.exe	    开启nginx
nginx.exe -s reload 重载配置文件
nginx.exe -s stop   关闭nginx

```


	

#### 注意事项：

```
1)nginx 目录里面不能出现中文
2)nginx 配置文件里面不要出现 下划线
3)nginx关闭favicon.ico不存在时记录日志
location = /favicon.ico {
  log_not_found off;
  access_log off;
}
```

