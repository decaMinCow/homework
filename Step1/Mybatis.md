## 一、简单题

### 1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？

- XML 文件内完成逻辑判断和动态拼接 SQL 的功能

- \<if/>、\<choose/>、\<when/>、\<otherwise/>、\<trim/>、\<when/>、\<set/>、\<foreach/>、\<bind/>

- 其执行原理为，使用 OGNL 的表达式，从 SQL 参数对象中计算表达式的值,根据表达式的值动态拼接 SQL ，以此来完成动态 SQL 的功能

### 2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

- 仅支持一对一和一对多的延迟加载

- 创建 A 对象的代理对象，对方法进行处理，当获取关联的属性对象 B 是 null 时，那么就会单独执行事先保存好的查询关联 B 对象的 sql，结果写入 B 对象中，于是 A 的对象 B 属性就有值了

### 3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？

- SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。

- ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map<String, Statement>内

- BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。

### 4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？

- 一级缓存：
	- 存储结构：map
	- 作用域是：SqlSession
	- 失效场景：SqlSession 提交事务、close

- 二级缓存：
	- 存储结构：map
	- 作用域是：namespace
	- 失效场景：namespace 下的 SqlSession 提交事务，close

### 5、简述Mybatis的插件运行原理，以及如何编写一个插件？

- 动态代理对Executor、StatementHandler、ParameterHandler、ResultSetHandler进行拦截

- 创建插件类继承 Intercepror 接口，注解声明要拦截的内容，修改配置文件

## 二、编程题

请完善自定义持久层框架IPersistence，在现有代码基础上添加、修改及删除功能。【需要采用getMapper方式】

[工程地址](https://github.com/decaMinCow/homework/tree/master/Step1)