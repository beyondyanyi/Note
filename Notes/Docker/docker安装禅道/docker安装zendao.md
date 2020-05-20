【https://www.sitven.cn/blog/95】



```bash
docker run --name zentao -p 1000:80 -v /zentao/code:/app/zentaopms -v /zentao/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d --restart=always     zentao_latest:latest
```

