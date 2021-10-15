1.在主库建立一个备份账号，用于从库复制
	GRANT REPLICATION SLAVE,FILE ON *.* TO 'master'@'%' IDENTIFIED BY 'password';
	

	查看用户是否建立：
		select user,authentication_string,host from user;
		
	如有密码策略限制：
		SHOW VARIABLES LIKE 'validate_password%'; 
		set global validate_password_policy=LOW; 
		set global validate_password_length=6;

2.修改主库mysql配置文件
	在mysqld下新增：
		server-id=1 #服务器id (主从必须不一样)，爱写啥写啥，别太离谱就行
		log-bin=mysql-bin  #打开日志(主机需要打开)，这个mysql-bin也可以自定义，这里也可以加上路径
		#作为主机的配置
		binlog-do-db=demo_master  #要给从机同步的库
		binlog-ignore-db=mysql  #不给从机同步的库(多个写多行)
		binlog-ignore-db=sys
		expire_logs_days=7  #自动清理 7 天前的log文件,可根据需要修改

	修改后重启mysql
	用此命令查看是否修改生效：
			show variables like '%log_bin%';
			show master status;


​		
3.修改从库mysql配置文件
​	在mysqld下新增:
​		server_id=2
​		#如果库名相同，使用这个
​		replicate-do-db=kintech_pd
​		
​		

	在从库配置复制：
		change master to master_host='122.51.191.222',
		master_user='master',
		master_password='password',
		master_log_file='mysql-bin.000001',
		master_log_pos=4745;


		start slave;##启动同步
		stop slave;
	
		show slave status;##查看同步状态