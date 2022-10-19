1.创建docker-compose.yml

```
version: '2.0'
services:
  nginx:
    restart: always
    image: nginx
    ports:
      - 80:80
      - 443:443
    volumes:
  	  - ./conf/nginx.conf:/etc/nginx/nginx.conf
      - ./html:/usr/share/nginx/html
      - ./logs:/var/log/nginx



```

2.创建目录

```
mkdir ./conf
mkdir ./html
mkdir ./logs
```

3.启动

```
docker-compose up -d
```

