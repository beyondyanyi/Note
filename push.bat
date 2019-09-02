git add .
set/p msg=请输入提交备注：
git commit -m %msg%
git push -u origin master