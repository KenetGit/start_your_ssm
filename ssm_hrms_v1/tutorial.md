# Introduction

This's a fundamental project for 'SSM' architechtrue begginer, as we know, 'SSM' contains Spring, Spring MVC and MyBatis. If you have 
learned about Spring, you should know how to construct a Spring application especially its IoC ans AOP mechanism, and you should know 
how to configure Spring container with XML file. Spring MVC is mainly used to serve as carrier that handles all HTTP requests and maps 
them to different controllers, Servlet is the core of these tasks. JSP is the traditional view-template which is so old that we seldom 
use it now but as for many mid-scale or small-scale companies, JSP is still popular to construct a multi-page web application. As RESTful
interface has become the most popular way to connect front-ent and back-end supported by HTTP protocal, JSON is along with the tool most 
developers recommended highly, so in this SSM application we will see that how to response with JSON instead of ViewAndModel, such a tip
is so important that take coomand of its principle is also advised. MyBatis is a ORM architecture used to operate data entities with a more
convinient method. Thanks to these excellent architetures so that it is easy to construct a SSM application.

This application is focus on back-end operations,mainly about CRUD operations, some buys may exist so i'll be very appreciate to your kind 
reminding about the buy. 

### Technical Stack
- Architetures: SSM (Spring, Spring MVC and MyBatis)
- Database: MySQL
- Management Tool: Maven
- Front-end Architecture: Bootstrap
- Java tool: JDK 1.8
- Developing Tool: Intellij IDEA
- Developing Environment: Windows 10

# Body

## 1. Database design

In the project, two small tables are used.
```
use ssm_hrms;

DROP TABLE IF EXISTS `tbl_emp`;
CREATE TABLE `tbl_emp`(
	`emp_id` int(11) UNSIGNED NOT NULL auto_increment,
	`emp_name` VARCHAR(22) NOT NULL DEFAULT '',
  `emp_email` VARCHAR(256) NOT NULL DEFAULT '',
  `gender` CHAR(2) NOT NULL DEFAULT '',
   `department_id` int(11) NOT NULL DEFAULT 0,
	 PRIMARY KEY(`emp_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept`(
	`dept_id` int(11) UNSIGNED NOT NULL auto_increment,
	`dept_name` VARCHAR(255) NOT NULL DEFAULT '',
  `dept_leader` VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY(`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

## Construct and start 

### 2.1 Dependencies

Please look at the file pom.xml for dependencies used in the project and try to make your understand of each dependency, for example, 
'jackson-databind' is essential when repoense with JSON, it maps Class object to JSON object automatically. 

### 2.2 Configuration files 

secondly, new a webapp in your project and make a web.xml under the catagery of WEB-INF. Details can be found in web.xml and here some
tips are given, the Spring context is the parent container of web context, so configuration of Spring context should be put ahead of the 
the web serlet configuration

Some important filters are liste in the web.xml, please pay attention to them and learned about their effects.

```
...
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:/applicationContext.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
        <async-supported>true</async-supported>
    </servlet>
    
    ...
    
    <filter>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
 ...
```

More details please go to `resources/` for `applicationContext.xml` and `springmvc.xml`. By the way, Jetty server plugin was used, so it
is more quickly to run your app.

### 2.3 start your App

Please write a `TestController` to test whether the app server runs well.

```
@Controller
public class TestController {

    @RequestMapping("/test")
    public String index() {
        System.out.println("testing...");
        return "test";
    }
}
```

## 3. Dao layer

If TestController functions well, let us write the main part, Dao layer. MyBatis Mapper is used here and MyBatis annotation function was 
also used. When we finish Dao layer, we should spare some time to test our code.

Build your Mappers for MyBatis mapper and make a configuration file mybatis.xml for MyBatis.

For example, with annotation of MyBatis, thing looks easier. More details to be found at 'mapper/'. However, using MyBatis annotation is
not fit for some complex database operations.

```
/*==================================Delete====================================*/
    @Delete({" DELETE FROM", TABLE_NAME, "WHERE dept_id=#{deptId} "})
    int deleteDeptById(@Param("deptId") Integer deptId);

/*=================================Update====================================*/
//UPDATE runoob_tbl SET runoob_title='Learning SSM' WHERE runoob_id=3;
    @Update({" UPDATE ", TABLE_NAME, " SET dept_name=#{department.deptName},dept_leader=#{department.deptLeader} " +
            " WHERE dept_id=#{deptId}" })
    int updateDeptById(@Param("deptId") Integer deptId, @Param("department") Department department);

```

## 4. Front-end

Please look at `webapp`.

![](https://github.com/KenetGit/start_your_ssm/blob/master/ssm_hrms_v1/img/main.PNG)

## 5. Controller layer

Pay attention to some annotation such as @ResponseBody, which is used to return a JSON Object. I've taken some notes about these, they
can be found [here.](https://github.com/KenetGit/gitNotes/blob/master/SSM%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.md)

# Thanks

Thanks to [nomico271](https://github.com/nomico271/SSM_HRMS), you offer a very useful project, and i've added some improvement base on
your code, more enhance will be done such as `Right Control` or `Session`  using `Shiro` in the v2. thanks again.

