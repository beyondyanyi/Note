## 沙箱包含哪些要素

### 1. **权限**

权限是指允许代码执行的操作。包含三部分：`权限类型`、`权限名`和`允许的操作`。`权限类型`是实现了权限的Java类名，是必需的。`权限名`一般就是对哪类资源进行操作的资源定位（比如一个文件名或者通配符、网络主机等），一般基于`权限类型`来设置，有的比如java.security.AllPermission不需要权限名。`允许的操`作也和`权限类型`对应，指定了对目标可以执行的操作行为，比如读、写等。如下面的例子：

```
permission java.security.AllPermission;    //权限类型permission java.lang.RuntimePermission "stopThread";    //权限类型+权限名permission java.io.FilePermission "/tmp/foo" "read";    //权限类型+权限名+允许的操作
```

#### 标准权限有哪些

|            | 类型                                | 权限名                                                   | 操作                   | 例子                                                         |
| :--------- | :---------------------------------- | :------------------------------------------------------- | :--------------------- | :----------------------------------------------------------- |
| 文件权限   | java.io.FilePermission              | 文件名（平台依赖）                                       | 读、写、删除、执行     | 允许所有问价的读写删除执行：permission java.io.FilePermission "<< ALL FILES>>", "read,write,delete,execute";。允许对用户主目录的读：permission java.io.FilePermission "${user.home}/-", "read";。 |
| 套接字权限 | java.net.SocketPermission           | 主机名:端口                                              | 接收、监听、连接、解析 | 允许实现所有套接字操作：permission java.net.SocketPermission "*:1-", "accept,listen,connect,resolve";。允许建立到特定网站的连接：permission java.net.SocketPermission "*.abc.com:1-", "connect,resolve";。 |
| 属性权限   | java.util.PropertyPermission        | 需要访问的jvm属性名                                      | 读、写                 | 读标准Java属性：permission java.util.PropertyPermission "java.*", "read";。在sdo包中创建属性：permission java.util.PropertyPermission "sdo.*", "read,write";。 |
| 运行时权限 | java.lang.RuntimePermission         | 多种权限名[见附录A]                                      | 无                     | 允许代码初始化打印任务：permission java.lang.RuntimePermission "queuePrintJob" |
| AWT权限    | java.awt.AWTPermission              | 6种权限名[见附录B]                                       | 无                     | 允许代码充分使用robot类：permission java.awt.AWTPermission "createRobot"; permission java.awt.AWTPermission "readDisplayPixels";。 |
| 网络权限   | java.net.NetPermission              | 3种权限名[见附录C]                                       | 无                     | 允许安装流处理器：permission java.net.NetPermission "specifyStreamHandler";。 |
| 安全权限   | java.security.SecurityPermission    | 多种权限名[见附录D]                                      | 无                     |                                                              |
| 序列化权限 | java.io.SerializablePermission      | 2种权限名[见附录E]                                       | 无                     |                                                              |
| 反射权限   | java.lang.reflect.ReflectPermission | suppressAccessChecks（允许利用反射检查任意类的私有变量） | 无                     |                                                              |
| 完全权限   | java.security.AllPermission         | 无（拥有执行任何操作的权限）                             | 无                     |                                                              |

### 2. **代码源**

代码源是类所在的位置，表示为以URL地址。

### 3. **保护域**

保护域用来组合代码源和权限，这是沙箱的基本概念。保护域就在于声明了比如由代码A可以做权限B这样的事情。

### 4. **策略文件**

策略文件是控制沙箱的管理要素，一个策略文件包含一个或多个保护域的项。策略文件完成了代码权限的指定任务，策略文件包括全局和用户专属两种。

为了管理沙箱，策略文件我认为是最重要的内容。JVM可以使用多个策略文件，不过一般两个最常用。一个是全局的：`$JREHOME/lib/security/java.policy`，作用于JVM的所有实例。另一个是用户自己的，可以存储到用户的主目录下。策略文件可以使用jdk自带的policytool工具编辑。

默认的策略文件我们先参考一下：

```
// Standard extensions get all permissions by defaultgrant codeBase "file:${{java.ext.dirs}}/*" {        permission java.security.AllPermission;};// default permissions granted to all domainsgrant {        // Allows any thread to stop itself using the java.lang.Thread.stop()        // method that takes no argument.        // Note that this permission is granted by default only to remain        // backwards compatible.        // It is strongly recommended that you either remove this permission        // from this policy file or further restrict it to code sources        // that you specify, because Thread.stop() is potentially unsafe.        // See the API specification of java.lang.Thread.stop() for more        // information.        permission java.lang.RuntimePermission "stopThread";        // allows anyone to listen on dynamic ports        permission java.net.SocketPermission "localhost:0", "listen";        // permission for standard RMI registry port        permission java.net.SocketPermission "localhost:1099", "listen";        // "standard" properies that can be read by anyone        permission java.util.PropertyPermission "java.version", "read";        permission java.util.PropertyPermission "java.vendor", "read";        permission java.util.PropertyPermission "java.vendor.url", "read";        permission java.util.PropertyPermission "java.class.version", "read";        permission java.util.PropertyPermission "os.name", "read";        permission java.util.PropertyPermission "os.version", "read";        permission java.util.PropertyPermission "os.arch", "read";        permission java.util.PropertyPermission "file.separator", "read";        permission java.util.PropertyPermission "path.separator", "read";        permission java.util.PropertyPermission "line.separator", "read";        permission java.util.PropertyPermission "java.specification.version", "read";        permission java.util.PropertyPermission "java.specification.vendor", "read";        permission java.util.PropertyPermission "java.specification.name", "read";        permission java.util.PropertyPermission "java.vm.specification.version", "read";        permission java.util.PropertyPermission "java.vm.specification.vendor", "read";        permission java.util.PropertyPermission "java.vm.specification.name", "read";        permission java.util.PropertyPermission "java.vm.version", "read";        permission java.util.PropertyPermission "java.vm.vendor", "read";        permission java.util.PropertyPermission "java.vm.name", "read";};
```

策略文件的内容格式就是这样，grant授权允许操作某个权限。这个默认的策略文件就指明了jdk扩展包可以有全部权限，允许代码stop线程，允许监听1099端口等等。

另一个很重要的是参数文件——java.security，这个文件和策略文件在同一个目录下。这个参数文件定义了沙箱的一些参数。比如默认的沙箱文件是这样的（截取部分）：

```
# The default is to have a single system-wide policy file,# and a policy file in the user's home directory.policy.url.1=file:${java.home}/lib/security/java.policypolicy.url.2=file:${user.home}/.java.policy# whether or not we expand properties in the policy file# if this is set to false, properties (${...}) will not be expanded in policy# files.policy.expandProperties=true# whether or not we allow an extra policy to be passed on the command line# with -Djava.security.policy=somefile. Comment out this line to disable# this feature.policy.allowSystemProperty=true
```

policy.url.*这个属性指明了使用的策略文件，如上文所述，默认的两个位置就在这里配置，用户可以自行更改顺序和存储位置。而policy.allowSystemProperty指明是否允许用户自行通过命令行指定policy文件。

### 5. **密钥库**

保存密钥证书的地方。

## 默认沙箱

通过Java命令行启动的Java应用程序，默认不启用沙箱。要想启用沙箱，启动命令需要做如下形式的变更：

```
java -Djava.security.manager <other args>
```

沙箱启动后，安全管理器会使用两个默认的策略文件来确定沙箱启动参数。当然也可以通过命令指定：

```
java -Djava.security.policy=<URL>
```

如果要求启动时只遵循一个策略文件，那么启动参数要加个等号，如下：

```
java -Djava.security.policy==<URL>
```

## 如何使用

### 1. 限制读文件

这个例子很简单，首先写一个r.txt文件，里面的内容是“abcd”，再写个程序如下读取这个r.txt。

```
import java.io.File;import java.io.FileInputStream;import java.io.FileNotFoundException;import java.io.IOException;import java.io.InputStream;public class PolicyTest {    public static void file() {        File f = new File("D:\\github\\CDLib\\src\\main\\resources\\security\\r.txt");        InputStream is;        try {            is = new FileInputStream(f);            byte[] content = new byte[1024];            while (is.read(content) != -1) {                System.out.println(new String(content));            }        } catch (FileNotFoundException e) {            // TODO Auto-generated catch block            e.printStackTrace();        } catch (IOException e) {            // TODO Auto-generated catch block            e.printStackTrace();        }    }    public static void main(String[] args) {        // test read file.        file();    }}
```

发现输出是`abcd`。

接下来修改java启动参数，加入`-Djava.security.manager`，启动了安全沙箱。再运行，输出变成了异常

> Exception in thread "main" java.security.AccessControlException: access denied ("java.io.FilePermission" "D:\github\CDLib\src\main\resources\security\r.txt" "read")
> at java.security.AccessControlContext.checkPermission(Unknown Source)
> at java.security.AccessController.checkPermission(Unknown Source)
> at java.lang.SecurityManager.checkPermission(Unknown Source)
> at java.lang.SecurityManager.checkRead(Unknown Source)
> at java.io.FileInputStream.(Unknown Source)
> at com.taobao.cd.security.PolicyTest.main(PolicyTest.java:15)

这里已经提示了，访问被拒绝，说明了沙箱启动，同时也验证了默认沙箱——禁止本地文件访问。

再来，我们构建一个custom.policy文件如下：

```
grant {    permission java.io.FilePermission "D:\\github\\CDLib\\src\\main\\resources\\security\\*", "read";};
```

这里构建了一条安全策略——允许读取security目录下的文件。

修改启动命令，添加`-Djava.security.policy=D:\\github\\CDLib\\src\\main\\resources\\security\\custom.policy`，再执行，结果输出了`abcd`。

如上例。我们通过自定义policy文件修改了默认沙箱的安全策略，再通过启动参数开启沙箱模式。这样就可以构造我们自己想要的沙箱效果了。

### 2. 限制访问网络

通过HttpClient访问www.baidu.com

```
import java.io.File;import java.io.FileInputStream;import java.io.FileNotFoundException;import java.io.IOException;import java.io.InputStream;import com.taobao.cd.http.util.HttpUtil;public class PolicyTest {    public static void network() {        try {            String text = HttpUtil.createHtmlText("http://www.baidu.com", HttpUtil.UA);            System.out.println(text);        } catch (Exception e) {            // TODO Auto-generated catch block            e.printStackTrace();        }    }    public static void main(String[] args) {        // test use network.        network();    }}
```

开启默认沙箱后，输出如下：

> java.security.AccessControlException: access denied ("java.net.SocketPermission" "www.baidu.com" "resolve")
> at java.security.AccessControlContext.checkPermission(Unknown Source)
> at java.security.AccessController.checkPermission(Unknown Source)
> at java.lang.SecurityManager.checkPermission(Unknown Source)
> at java.lang.SecurityManager.checkConnect(Unknown Source)
> at java.net.InetAddress.getAllByName0(Unknown Source)
> at java.net.InetAddress.getAllByName(Unknown Source)
> at java.net.InetAddress.getAllByName(Unknown Source)
> at org.apache.http.impl.conn.DefaultClientConnectionOperator.resolveHostname(DefaultClientConnectionOperator.java:242)
> at org.apache.http.impl.conn.DefaultClientConnectionOperator.openConnection(DefaultClientConnectionOperator.java:130)
> at org.apache.http.impl.conn.AbstractPoolEntry.open(AbstractPoolEntry.java:149)
> at org.apache.http.impl.conn.AbstractPooledConnAdapter.open(AbstractPooledConnAdapter.java:121)
> at org.apache.http.impl.client.DefaultRequestDirector.tryConnect(DefaultRequestDirector.java:573)
> at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:425)
> at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:820)
> at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:754)
> at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:732)
> at com.taobao.cd.http.util.HttpUtil.createHtmlText(HttpUtil.java:38)
> at com.taobao.cd.security.PolicyTest.network(PolicyTest.java:15)
> at com.taobao.cd.security.PolicyTest.main(PolicyTest.java:45)

根据错误提示，知道是访问socket没有权限。那么修改下policy，指定权限：

```
grant {    permission java.net.SocketPermission "www.baidu.com:1-", "connect,resolve";};
```

在指定权限文件下再运行，得到了正常的text形式的baidu首页的页面文档。权限策略成功。

代码参考github：https://github.com/changedi/CDLib/blob/master/src/main/java/com/taobao/cd/security/PolicyTest.java

## 附录

### A

| 权限名                      | 用途说明                                                     |
| :-------------------------- | :----------------------------------------------------------- |
| accessClassInPackage.<name> | 允许代码访问指定包中的类                                     |
| accessDeclaredMembers       | 允许代码使用反射访问其他类中私有或保护的成员                 |
| createClassLoader           | 允许代码实例化类加载器                                       |
| createSecurityManager       | 允许代码实例化安全管理器，它将允许程序化的实现对沙箱的控制   |
| defineClassInPackage.<name> | 允许代码在指定包中定义类                                     |
| exitVM                      | 允许代码关闭整个虚拟机                                       |
| getClassLoader              | 允许代码访问类加载器以获得某个特定的类                       |
| getProtectionDomain         | 允许代码访问保护域对象以获得某个特定类                       |
| loadlibrary.<name>          | 允许代码装载指定类库                                         |
| modifyThread                | 允许代码调整指定的线程参数                                   |
| modifyThreadGroup           | 允许代码调整指定的线程组参数                                 |
| queuePrintJob               | 允许代码初始化一个打印任务                                   |
| readFileDescriptor          | 允许代码读文件描述符（相应的文件是由其他保护域中的代码打开的） |
| setContextClassLoader       | 允许代码为某线程设置上下文类加载器                           |
| setFactory                  | 允许代码创建套接字工厂                                       |
| setIO                       | 允许代码重定向System.in、System.out或System.err输入输出流    |
| setSecurityManager          | 允许代码设置安全管理器                                       |
| stopThread                  | 允许代码调用线程类的stop()方法                               |
| writeFileDescriptor         | 允许代码写文件描述符                                         |

### B

| 权限名                         | 用途说明                      |
| :----------------------------- | :---------------------------- |
| accessClipboard                | 允许访问系统的全局剪贴板      |
| accessEventQueue               | 允许直接访问事件队列          |
| createRobot                    | 允许代码创建AWT的Robot类      |
| listenToAllAWTEvents           | 允许代码直接监听事件分发      |
| readDisplayPixels              | 允许AWT Robot读显示屏上的像素 |
| showWindowWithoutWarningBanner | 允许创建无标题栏的窗口        |

### C

| 权限名                        | 用途说明                      |
| :---------------------------- | :---------------------------- |
| specifyStreamHandler          | 允许在URL类中安装新的流处理器 |
| setDefaultAuthenticator       | 可以安装鉴别类                |
| requestPassworkAuthentication | 可以完成鉴别                  |

### D

| 权限名                                   | 用途说明                                 |
| :--------------------------------------- | :--------------------------------------- |
| addIdentityCertificate                   | 为Identity增加一个证书                   |
| clearProviderProperties.<provider name>  | 针对指定的提供者，删除所有属性           |
| createAccessControlContext               | 允许创建一个存取控制器的上下文环境       |
| getDomainCombiner                        | 允许撤销保护域                           |
| getPolicy                                | 检索可以实现沙箱策略的类                 |
| getProperty.<prop name>                  | 读取指定的安全属性                       |
| getSignerPrivateKey                      | 由Signer对象获取私有密钥                 |
| insertProvider.<provider name>           | 将指定的提供者添加到响应的安全提供者组中 |
| loadProviderProperties.<provider name>   | 装载指定的提供者的属性                   |
| printIdentity                            | 打印Identity类内容                       |
| putAllProviderProperties.<provider name> | 更新指定的提供者的属性                   |
| putProviderProperty.<provider name>      | 为指定的提供者增加一个属性               |
| removeIdentityCertificate                | 取消Identity对象的证书                   |
| removeProvider.<provider name>           | 将指定的提供者从相应的安全提供者组中删除 |
| removeProviderProperty.<provider name>   | 删除指定的安全提供者的某个属性           |
| setIdentityInfo                          | 为某个Identity对象设置信息串             |
| setIdentityPublicKey                     | 为某个Identity对象设置公钥               |
| setPolicy                                | 设置可以实现沙箱策略的类                 |
| setProperty.<prop name>                  | 设置指定的安全属性                       |
| setSignerKeyPair                         | 在Signer对象中设置密钥对                 |
| setSystemScope                           | 设置系统所用的IdentityScope              |

### E

| 权限名                       | 用途说明                                                     |
| :--------------------------- | :----------------------------------------------------------- |
| enableSubstitution           | 允许实现ObjectInputStream类的enableResolveObject()方法和ObjectOutputStream类的enableReplaceObject()方法 |
| enableSubclassImplementation | 允许ObjectInputStream和ObjectOutputStream创建子类，子类可以覆盖readObject()和writeObject()方法 |