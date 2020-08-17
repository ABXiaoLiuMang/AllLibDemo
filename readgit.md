csdn账号：woaiqin2004
qq：2814373873 admin123456
//510000100941  12位太阳码

keytool -list -printcert -jarfile

一、开发分支（dev）上的代码达到上线的标准后，要合并到 master 分支
git checkout -b dev创建并切换分支
git checkout dev
git pull
git checkout master
git merge dev
git push -u origin master

$ git push -u origin master -f 强制覆盖

二、当master代码改动了，需要更新开发分支（dev）上的代码

git checkout master 
git pull 
git checkout dev
git merge master 
git push -u origin dev
--------------------- 
git init
git add xxx
git commit -m 'xxx'
 git remote add origin ssh://software@172.16.0.30/~/yafeng/.git
git push origin master 
git remote show origin
git clone ssh://software@172.16.0.30/~/yafeng/.git


- 查看自己的用户名和邮箱地址：
　　$ git config user.name
　　$ git config user.email
- 修改自己的用户名和邮箱地址：
　　$ git config --global user.name "xxx"
　　$ git config --global user.email "xxx"
admin-dale
git branch -r       #查看远程所有分支
git branch           #查看本地所有分支
git branch -a       #查看本地及远程的所有分支，如下图
git fetch   #将某个远程主机的更新，全部取回本地：
git branch -a  #查看远程分支
git branch  #查看本地分支：
git checkout 分支 #切换分支：
git push origin -d 分支名  #删除远程分支: 
git branch -d 分支名  #删除本地分支
git remote show origin  #查看远程分支和本地分支的对应关系
git remote prune origin #删除远程已经删除过的分支

git reset --hard commit_id //回退到某次提交
git push -f origin master  强制覆盖提交
git remote -v 查看远程仓库地址

打tag
git tag -a V1.0.2 -m "替换bugly后版本"
git push origin V1.0.2

https://www.draw.io/  画图软件

2814373873  13608079349
https://zzcp02.com/#/home

获取应用签名（定位到jdk安装目录的bin文件夹下面）
keytool -list -v -keystore C:\Users\Dell\Desktop\release.keystore.jks
android签名 ->SHA256就是

http://api.hou2008.com/api/v1/wap/location 地理位置

https://blog.csdn.net/xiaxiayige/article/details/80636091  nexus aar


查看自己的用户名和邮箱地址：

　　$ git config user.name

　　$ git config user.email

修改自己的用户名和邮箱地址：

　　$ git config --global user.name "xxx"

　　$ git config --global user.email "xxx"

https://www.jianshu.com/p/cdd80dd15593  tag

login and download:441623442@qq.com  admin@*@123456

重复输入用户名解决方法： git config --global credential.helper store  然后第一次提交会输入，之后在提交就不会在输入了

+项目迁移
我们的目标是把代码整体从Coding迁移到自建的Gitlab服务器，并保留所有分支和Tags记录。

1. clone旧项目到本地目录
git clone git@git.coding.net:username/x-server.git
2. 本地目录添加新的远程主机地址
git remote add gitlab git@gitlab.bmkp.xx:xx_200/x-server.git
上面的命令添加了一个名为gitlab的主机地址。

3. 推送master到新的仓库
git push -u gitlab master
上面命令将本地的master分支推送到gitlab主机，同时指定gitlab为默认主机，后面就可以不加任何参数使用git push了。

4. 推送所有分支
推送前先用git branch -a查看所有远程分支，然后分别checkout到本地。

git checkout -b dev origin/dev
上述命令是checkout远程的dev分支，在本地起名为dev分支，并切换到本地的dev分支。

最后执行命令推送全部分支。

git push --all gitlab
5. 推送所有Tag

git push gitlab --tags
