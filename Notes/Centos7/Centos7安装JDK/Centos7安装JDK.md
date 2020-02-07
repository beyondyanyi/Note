#### 教程：https://www.cnblogs.com/stulzq/p/9286878.html

​	

	#jdk环境
	export JAVA_HOME=/wst/soft/jdk/jdk1.8.0_212
	export JRE_HOME=${JAVA_HOME}/jre
	export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
	export PATH=${JAVA_HOME}/bin:$PATH
	
	source /etc/profile
	jdk软链接：
		ln -s /wst/soft/jdk/jdk1.8.0_212/bin/java /usr/bin/java