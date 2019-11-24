- https://www.iconfont.cn/ 图标库
- https://blog.csdn.net/fafaws3000/article/details/79422833
- https://blog.csdn.net/lxd_android/article/details/79076312
- http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0623/3097.html

- git代码地址：https://github.com/

- keytool在jdk的bin文件里有，所以找到keytool.exe,定位到该目录，然后运行下面命令
keytool -list -v -keystore C:\Users\Dell\Desktop\release.keystore.jks
android签名 ->SHA256就是


- 注册地址https://bintray.com/signup/oss
- 邮箱登录：441623442dale@gmail.com（）
- 邮箱登录：abxingxing@gmail.com（用这个->谷歌登录）
- key:dd9580acfcc6ff298afedc55df7c06123e3eaadc

- gradlew clean build bintrayUpload -PbintrayUser=abxingxing  -PbintrayKey=dd9580acfcc6ff298afedc55df7c06123e3eaadc  -PdryRun=false


- 1.根目录添加（查看最新对应版本号：https://bintray.com/novoda/maven/bintray-release）
- classpath 'com.novoda:bintray-release:0.8.0'

        android {
         compileSdkVersion 26
             lintOptions {
                 abortOnError false
                 }
                 ......

          }



        publish {
            userOrg = 'daleyangbo'   //组织名称 organization 建议写用户名（bintray.com用户名）
            groupId = 'com.chad.adapter'  //jcenter上的路径
            artifactId = 'ABBaseAdapter'  //项目名称
            publishVersion = '1.0.0'    //版本号
            desc = '适配器'//d项目描述
            website = 'https://github.com'// github 托管地址
            repoName="maven" //你的仓库名称，没有填写默认仓库是maven//这也是很多人上传仓库不对名问题最多情况，
        }

- artifactId要和之前创建maven里面项目的名字相同(创建的package)，否则上传时会报错误

- 在审核通过前可以通过配置自己私有仓地址使用： maven { url 'https://dl.bintray.com/abxingxing/maven'}

        allprojects {
            repositories {
                google()
                jcenter()
                maven { url 'https://dl.bintray.com/abxingxing/maven'}
            }
        }

- addjcenter的时候，vcs应该改为自己的仓库地址，如：https://dl.bintray.com/abxingxing/maven