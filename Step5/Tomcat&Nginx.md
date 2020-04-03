## 一、编程题

### 1、开发Minicat V4.0，在已有Minicat基础上进⼀步扩展，模拟出webapps部署效果 磁盘上放置⼀个webapps⽬录，webapps中可以有多个项⽬，⽐如demo1,demo2,demo3... 具体的项⽬⽐如demo1中有serlvet（也即为：servlet是属于具体某⼀个项⽬的servlet），这样的话在 Minicat初始化配置加载，以及根据请求url查找对应serlvet时都需要进⼀步处理

[工程地址](https://github.com/decaMinCow/homework/tree/master/Step5/Minicat) 

## 二、简答题

### 1、请详细描述 Tomcat 体系结构（图⽂并茂）
![循环调用 UML 说明](./tomcat.jpg)

架构诠释：

1. Server(服务器)是Tomcat构成的顶级构成元素，所有一切均包含在Server中，Server的实现类StandardServer可以包含一个到多个Services,Service的实现类为StandardService调用了容器(Container)接口，其实是调用了Servlet Engine(引擎)，而且StandardService类中也指明了该Service归属的Server;

2. Container: 引擎(Engine)、主机(Host)、上下文(Context)和Wraper均继承自Container接口，所以它们都是容器。但是，它们是有父子关系的，在主机(Host)、上下文(Context)和引擎(Engine)这三类容器中，引擎是顶级容器，直接包含是主机容器，而主机容器又包含上下文容器，所以引擎、主机和上下文从大小上来说又构成父子关系,虽然它们都继承自Container接口。

3. 连接器(Connector)将Service和Container连接起来，首先它需要注册到一个Service，它的作用就是把来自客户端的请求转发到Container(容器)，这就是它为什么称作连接器的原因。

从功能的角度将Tomcat源代码分成5个子模块，分别是:

Jsper模: 这个子模块负责jsp页面的解析、jsp属性的验证，同时也负责将jsp页面动态转换为java代码并编译成class文件。在Tomcat源代码中，凡是属于org.apache.jasper包及其子包中的源代码都属于这个子模块;

Servlet和Jsp模块: 这个子模块的源代码属于javax.servlet包及其子包，如我们非常熟悉的javax.servlet.Servlet接口、javax.servet.http.HttpServlet类及javax.servlet.jsp.HttpJspPage就位于这个子模块中;

Catalina模块: 这个子模块包含了所有以org.apache.catalina开头的java源代码。该子模块的任务是规范了Tomcat的总体架构，定义了Server、Service、Host、Connector、Context、Session及Cluster等关键组件及这些组件的实现，这个子模块大量运用了Composite设计模式。同时也规范了Catalina的启动及停止等事件的执行流程。从代码阅读的角度看，这个子模块应该是我们阅读和学习的重点。

Connector模块: 如果说上面三个子模块实现了Tomcat应用服务器的话，那么这个子模块就是Web服务器的实现。所谓连接器(Connector)就是一个连接客户和应用服务器的桥梁，它接收用户的请求，并把用户请求包装成标准的Http请求(包含协议名称，请求头Head，请求方法是Get还是Post等等)。同时，这个子模块还按照标准的Http协议，负责给客户端发送响应页面，比如在请求页面未发现时，connector就会给客户端浏览器发送标准的Http 404错误响应页面。

Resource模块: 这个子模块包含一些资源文件，如Server.xml及Web.xml配置文件。严格说来，这个子模块不包含java源代码，但是它还是Tomcat编译运行所必需的。