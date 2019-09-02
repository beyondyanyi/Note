#### 1.在http模块中添加如何模块

```
map $http_upgrade $connection_upgrade {
	default upgrade;
	'' close;
}
```

​	

#### 2.配置websocket转发

	##配置websocket转发
	server{
		listen       80;
	    server_name  localhost;
		##气象websocket
	    location /weather {
	        proxy_pass  http://192.168.1.6:8089/; 
	        proxy_http_version 1.1;
			proxy_read_timeout 500s;
	        proxy_send_timeout 500s;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection $connection_upgrade;
			proxy_set_header Origin '';
	    }
	}

#### 3.html写法

```
websocket = new WebSocket("ws:///localhost/weather//websocket/ID=353535353");
```


