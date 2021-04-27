#!/bin/bash

server_ip=121.37.161.87
client_ip=27.17.236.74
# 生成文件目标路径
dir=/mqtt/CA
 
# 创建自签名根证书
sudo openssl req -new -x509 -days 100 -extensions v3_req -keyout ca.key -out $dir/ca.crt -subj "/CN=www.emqx.io" -config $dir/conf/ca-openssl.cnf 

# 生成服务器端的key和证书
if [ ! -d $dir/server ];then
   sudo mkdir $dir/server
fi
sudo openssl genrsa -out $dir/server/server.key 2048
sudo openssl req -new -key $dir/server/server.key -out $dir/server/server.csr -subj "/CN=$server_ip" -config $dir/conf/server-openssl.cnf
sudo openssl x509 -req -in $dir/server/server.csr -CA $dir/ca.crt -CAkey $dir/ca.key -CAcreateserial -out $dir/server/server.crt -days 3650 -sha256 -extensions v3_req -extfile $dir/conf/server-openssl.cnf

# 生成客户端key和证书
if [ ! -d $dir/client ];then
   sudo mkdir $dir/client
fi
sudo openssl genrsa -out $dir/client/client.key 2048
sudo openssl req -new -key $dir/client/client.key -out $dir/client/client.csr -subj "/CN=$client_ip"
sudo openssl x509 -req -in $dir/client/client.csr -CA $dir/ca.crt -CAkey $dir/ca.key -CAcreateserial -out $dir/client/client.crt -days 3650 -sha256

