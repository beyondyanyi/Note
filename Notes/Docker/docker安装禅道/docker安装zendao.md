【https://www.sitven.cn/blog/95】



```bash
docker run --name zentao1 -p 1000:80 -v /zentao/code:/app/zentaopms -v /zentao/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456  -d --restart=always     zentao_latest:latest
```



docker run --name zentao1 -p 1000:80 -v /zentao1/code:/app/zentaopms -v /zentao1/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456  -d --restart=always     zentao_latest:latest



sudo docker run --name zentao -p 1000:80 --network=zentaonet --ip 172.172.172.172 --mac-address 02:42:ac:11:00:00 -v /zentao1/code:/www/zentaopms -v /zentao1/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=p123456 -d easysoft/zentao:12.3.3