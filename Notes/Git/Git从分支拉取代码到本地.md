[原文：https://blog.csdn.net/carfge/article/details/79691360]



#### 1.找一个空文件夹

#### 2.初始化

```
git init
```

#### 3.与仓库建立连接（https://xxxxxxxxx.git为远程仓库地址）

```
git remote add origin https://xxxxxxxxx.git
```

#### 4.把远程分支拉到本地(vue-edition为远程仓库分支名)

```
git fetch origin vue-edition
```

#### 5.在本地创建分支并切换到该分支(第一个vue-edition为本地分支名，第二个vue-edition为远程分支名)

```
git checkout -b vue-edition origin/vue-edition
```

#### 6.把分支上的内容拉取到本地(vue-edition为远程分支名)

```
git pull origin vue-edition
```

#### 7.提交到分支

```
git add.
git commit -m "xxx"
git push origin vue-edition
```

