#### 教程：https://www.cnblogs.com/stulzq/p/9286878.html

​	



##### 1.下载jdk

​	

```
.......x86.tar.gz
```



##### 2.安装

```
mkdir /usr/local/java

tar -zxvf jdk-8xxx-linux-x64.tat.gz -C /usr/local/java
```



##### 3.设置环境变量

```
vi /etc/profile

在末尾添加
export JAVA_HOME=/usr/local/java/jdk1.8.0_171
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
```



##### 4.检查

	#jdk环境
	export JAVA_HOME=/wst/soft/jdk/jdk1.8.0_212
	export JRE_HOME=${JAVA_HOME}/jre
	export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
	export PATH=${JAVA_HOME}/bin:$PATH
	
	source /etc/profile
	jdk软链接：
		ln -s /wst/soft/jdk/jdk1.8.0_212/bin/java /usr/bin/java

