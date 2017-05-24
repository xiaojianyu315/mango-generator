# mango-generator
### 简介
本项目是jfaster mango分库分表中间件代码生成器
jfaster mango官方网站：

### 环境依赖
1. jdk1.7(推荐)，1.6未测试

### jar包依赖
1. freemarker2.3.x
2. mysql驱动包
### 使用方式
1. 下载mango-gererator-1.0.jar
2. 放入工程的classpath下
3. 运行如下代码：
```java
public class AppTest {
    public static void main(String[] args) {
        MangoGenerator mg = new MangoGenerator(
                "jdbc:mysql://localhost:3306/jsponge",
                "root",
                "root",
                "jsponge",
                "",
                "/Users/xiaojianyu/Downloads/");
        mg.generator();
    }
}

```


