# mango-generator
### 简介

jfaster mango官方网站：[http://www.jfaster.org/]

本项目是jfaster mango分库分表中间件代码生成器.
此工具可以方便的生成mango需要的数据对象(DO)和数据访问层对象(DAO)

此工具还提供了一个分库分表sql生成的工具，它可以根据单个sql文件生成具体规则的sql脚本（比如N个库，每个库N张表）


### 环境依赖
1. jdk1.7(推荐)，1.6未测试
2. maven

### 注意事项
1. 项目将不采用发行jar包的形式，如果您想使用，您可以下载本项目源码，运行具体类的main方法使用，也是很方便的
2. 此工具默认你的表是有主键的，如果没有设置主键selectByPrimaryKey、updateByPrimaryKey方法将会默认使用"id"字段作为这些方法的参数

### V1.0能做什么，不能做什么
1. 对库进行DO、DAO生成（可指定单个表，不指定则生成整个库）
2. DAO生成由于jfaster mango还没有trim函数，暂不能生成类似mybatis的insertSelective和updateByPrimaryKeySelective的方法，这个会在添加了trim标签以后增加
3. 此版本DAO只做了未分表的生成，对于sharding的注解未支持，需要自行添加，会在后续添加上
4. 还能做的就是对jfaster mango尽一些绵薄之力，希望对各位有些帮助

### 遇到问题怎么办
1. 遇到问题直接在github上面提bug，然后在群里（445124187）说一下，@ゞ安❤分-
2. 或者直接在群里直接@ゞ安❤分-

### 以下是代码生成的使用方式
1. 下载项目代码
2. test目录下有AppTest类，运行如下代码：
```java
public class AppTest {
    public static void main(String[] args) {
        MangoGenerator mg = new MangoGenerator(
                "jdbc:mysql://localhost:3306/jsponge", //数据库连接
                "root", //数据库用户名
                "root",//数据库密码
                "jsponge",//数据库库名
                "",//要生成的表名，不写则生成整个数据库，写了则只生成单个表
                "/Users/xiaojianyu/Downloads/");//生成的DO和DAO文件位置
        mg.generator();
    }
}

```
4. 生成文件预览结果

ServerGatherRecord.java

```java
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/**
* 对应数据库表名: server_gather_record
*/
public class ServerGatherRecord implements Serializable{

    /**
    * 数据库字段: id
    * 字段描述: 
    */
    private Long id;

    /**
    * 数据库字段: server_id
    * 字段描述: 服务器id
    */
    private Long serverId;

    /**
    * 数据库字段: gather_type
    * 字段描述: 采集类型，1-内存；2-cpu；3-磁盘
    */
    private Integer gatherType;

    /**
    * 数据库字段: gather_time
    * 字段描述: 采集时间
    */
    private Date gatherTime;

    /**
    * 数据库字段: total
    * 字段描述: 总量
    */
    private Long total;

    /**
    * 数据库字段: free
    * 字段描述: 空闲的
    */
    private Long free;

    /**
    * 数据库字段: used
    * 字段描述: 已使用的
    */
    private Long used;

    /**
    * 数据库字段: create_time
    * 字段描述: 创建时间
    */
    private Date createTime;

    /**
    * 数据库字段: update_time
    * 字段描述: 更新时间
    */
    private Date updateTime;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setGatherType(Integer gatherType) {
        this.gatherType = gatherType;
    }

    public Integer getGatherType() {
        return gatherType;
    }

    public void setGatherTime(Date gatherTime) {
        this.gatherTime = gatherTime;
    }

    public Date getGatherTime() {
        return gatherTime;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setFree(Long free) {
        this.free = free;
    }

    public Long getFree() {
        return free;
    }

    public void setUsed(Long used) {
        this.used = used;
    }

    public Long getUsed() {
        return used;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}
```
ServerGatherRecordDao.java

```java
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.ReturnGeneratedId;

@DB(
        table = "server_gather_record"
)
public interface ServerGatherRecordDao {

    String BASE_COLUMNS = " id, server_id, gather_type, gather_time, total, free, used, create_time, update_time ";
    String VALUES_COLUMNS = " :id, :serverId, :gatherType, :gatherTime, :total, :free, :used, :createTime, :updateTime ";

    @ReturnGeneratedId
    @SQL("insert into #table(" + BASE_COLUMNS + ") values(" + VALUES_COLUMNS + ")")
    long insert(ServerGatherRecord object);

    @SQL("select " + BASE_COLUMNS + " from #table where id = :1")
    ServerGatherRecord selectByPrimaryKey(Long id);

    @SQL(
          " update #table set "
        + " server_id = :serverId, "
        + " gather_type = :gatherType, "
        + " gather_time = :gatherTime, "
        + " total = :total, "
        + " free = :free, "
        + " used = :used, "
        + " create_time = :createTime, "
        + " update_time = :updateTime "
        + " where id = :id"
    )
    boolean updateByPrimaryKey(ServerGatherRecord object);

    @SQL("delete from #table where id = :1")
    boolean deletesByPrimaryKey(Long id);
}
```

### 分库分表sql生成的规则适用场景
1. 以用户id（userId）为例，userId % 表总数 == 表索引；userId % 表总数 / 库总数 == 库索引

### 以下是分库分表sql生成器的使用方式 
1. 找到org.jfaster.generator.ShardTableSqlGenerator类，此类就是分库分表sql脚本生成器代码，就一个main方法，运行即可，前提是记得修改常量的路径
```java
    /**
     * 逻辑表路径
     */
    private static final String logic_table_input_file = "/Users/xiaojianyu/IdeaProjects/mango-generator/db/table_logic.sql";
    /**
     * 分库分表全部总表的输出路径
     */
    private static final String all_sql_output_file = "/Users/xiaojianyu/IdeaProjects/mango-generator/db/table_logic_all.sql";
    /**
     * 分库分表独立表的输出路径
     */
    private static final String shard_sql_output_path = "/Users/xiaojianyu/IdeaProjects/mango-generator/db/logic_tables/";
    /**
     * 库前缀，用于生成USE，切换库命令
     */
    private static final String db_prefix = "lc_";
    /**
     * 库的数量
     */
    private static final int db_count = 2;
    /**
     * 每个库表的数量
     */
    private static final int every_db_table_count = 2;

```

2. 找到db/table_logic.sql,在里面编写你的普通sql，多个表使用"==="3个等号分割，比如：
```sql

CREATE TABLE lc_a (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

===

CREATE TABLE lc_b (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';


```

3. 上面定义了2张表，运行ShardTableSqlGenerator类main方法后即可生成分库分表的sql脚本，这里演示的是分2个库，每个库有2张表的情况，会成功3个文件，一个是包含了全部sql命令的文件，另外2个是每张表对应的sql命令的文件

table_logic_all.sql 是全部sql的脚本，适合统一创建库表

```sql
USE lc_0;

CREATE TABLE lc_a_0 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



CREATE TABLE lc_a_1 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



USE lc_1;

CREATE TABLE lc_a_2 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



CREATE TABLE lc_a_3 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



USE lc_0;


CREATE TABLE lc_b_0 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';





CREATE TABLE lc_b_1 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';




USE lc_1;


CREATE TABLE lc_b_2 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';





CREATE TABLE lc_b_3 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';





```

lc_a.sql

```sql
USE lc_0;

CREATE TABLE lc_a_0 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



CREATE TABLE lc_a_1 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



USE lc_1;

CREATE TABLE lc_a_2 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';



CREATE TABLE lc_a_3 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';




```

lc_b.sql
```sql
USE lc_0;


CREATE TABLE lc_b_0 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';





CREATE TABLE lc_b_1 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';




USE lc_1;


CREATE TABLE lc_b_2 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';





CREATE TABLE lc_b_3 (
  id bigint(20) NOT NULL COMMENT 'id',
  login_name varchar(200) NOT NULL COMMENT '登录名',
  login_pwd varchar(200) DEFAULT NULL COMMENT '登录密码',
  member_id bigint(20) NOT NULL COMMENT '用户id',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY login_name (login_name) USING BTREE,
  KEY member_id (member_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';





```