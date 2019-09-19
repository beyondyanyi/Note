
@rem author by yanyi beyondyanyi@gmail.com
color 0f

rem 备份文件根路径(可随意修改)
set location=F:\mysql_backup\
rem 数据库用户密码
set username=root
set password=root
rem 要备份的数据库

rem 备份文件存放路径（当前日期）
set dist=%location%%date:~0,4%%date:~5,2%%date:~8,2%\
set "dist=%dist: =0%"
rem 生成文件名
set filename=%time:~0,2%-%time:~3,2%-%time:~6,2%
set "filename=%filename: =0%"


rem 判断文件夹是否存在
if exist %dist% {
	echo 备份路径为+%dist%
} else {
	md %dist%
}


rem 调用mysqldump执行备份操作
::cmms
mysqldump -u%username% -p%password% cmms> %dist%cmms-%filename%.sql
::road
mysqldump -u%username% -p%password% road> %dist%road-%filename%.sql
::weather
mysqldump -u%username% -p%password% weather> %dist%weather-%filename%.sql
echo "备份成功"
rem 延时1秒自动关闭
choice /t 1 /d y /n >nul
