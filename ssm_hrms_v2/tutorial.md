## Tutorial

### 1. Add Entity
When use Shiro, we should add up more entities for `User`, `Role` and `Permission`, here we only use 'User' for mocking.

```
public class User {

    private String username;
    private String password;

    private String salt;
    ...
```

### 2. Confiration

#### 2.1 ShiroConfig.xml

Configuration for Shiro, we put them independently in a single file, shiro-config.xml, and import this file in ApplicationContext.xml
Some important annotations have been writed so you can easily understand.

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/util
       http://www.springframework.org/schema/util/spring-util.xsd">

    <!--1. 配置一个 securityManager -->

    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="myRealm" />
        <property name="cacheManager" ref="cacheManager"/>
    </bean>

    <!--1.1 需要引用的自定义的Realm-->
    <bean id="myRealm" class="com.kenet.shiro.MyRealm"/>

    <!--2. LifecycleBeanPostProcessor: 管理shiro bean的生命周期-->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>

    <!--3.1 基于表单的身份验证过滤器-->
    <bean id="formAuthenticationFilter"
          class="org.apache.shiro.web.filter.authc.FormAuthenticationFilter">
        <property name="usernameParam" value="username"/>
        <property name="passwordParam" value="password"/>
        <property name="loginUrl" value="/login.jsp"/>
    </bean>

    <!--3.2 Shiro的web过滤器 -->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <!--shiro的处理，都由securityManger安排 -->
        <property name="securityManager" ref="securityManager"/>
        <!--登录成功，跳转到loginUrl-->
        <property name="loginUrl" value="/hrms/login"/>
        <property name="successUrl" value="/hrms/main"/>
        <!-- 验证不通过执行的请求或者跳转 -->
        <property name="unauthorizedUrl" value="/hrms/unauthorized"/>

        <property name="filters">
            <util:map>
                <entry key="authc" value-ref="formAuthenticationFilter"/>
            </util:map>
        </property>
        <!-- 具体的拦截路径和拦截的方式和角色和权限的范围 -->
        <property name="filterChainDefinitions">
            <value>
                /hrms/login=authc
                /hrms/main=authc
                /hrms/emp/*=authc
                /hrms/dept/*=authc
            </value>
        </property>
    </bean>

    <!--4. 配置启用Shiro的注解功能 -->
    <!--4.1 DefaultAdvisorAutoProxyCreator是用来扫描上下文，寻找所有的Advistor(通知器），
        将这些Advisor应用到所有符合切入点的Bean中，所以必须在lifecycleBeanPostProcessor创建之后创建
     -->
    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
          depends-on="lifecycleBeanPostProcessor">
        <property name="proxyTargetClass" value="true"></property>
    </bean>
    <!--4.2 定义aop切面，用于代理如@RequiresPermissions注解的控制器，进行权限控制-->
    <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager"/>
    </bean>

    <!-- 5. 配置缓存管理器 -->
    <bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
        <!-- 指定 ehcache 的配置文件 -->
        <property name="cacheManagerConfigFile" value="classpath:ehcache-shiro.xml"/>
    </bean>

</beans>
```
#### 2.2 CacheManager for Shiro

To have a better use of Shiro, add your own cacheManaer.

```
<ehcache>
    <diskStore path="java.io.tmpdir/shiro-spring-sample"/>

    <defaultCache
            maxElementsInMemory="10000"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            overflowToDisk="false"
            diskPersistent="false"
            diskExpiryThreadIntervalSeconds="120"
    />

    <cache name="shiro-activeSessionCache"
           maxElementsInMemory="10000"
           eternal="true"
           overflowToDisk="true"
           diskPersistent="true"
           diskExpiryThreadIntervalSeconds="600"/>

    <cache name="org.apache.shiro.realm.SimpleAccountRealm.authorization"
           maxElementsInMemory="100"
           eternal="false"
           timeToLiveSeconds="600"
           overflowToDisk="false"/>

</ehcache>
```

### 3. Self-defined Realm

```
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private ShiroUserService shiroUserService;
    /*
    * 授权,暂时不开发
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /*
    * 用户认证
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        try {
            // 手动强制转换Object-->String
            String username = (String) authenticationToken.getPrincipal();
            // query user
            User user = shiroUserService.queryUserByName(username);

            System.out.println("---doGetAuthenticationInfo");
            if (user == null) return null;

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    username,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());

            return simpleAuthenticationInfo;
        } catch (Exception  e) {
            System.out.println("身份验证失败"+e.getMessage());
            return null;
        }
    }
}

```
and add a ShiroUserService for mocking.

```
@Service
public class ShiroUserService {

    public User queryUserByName(String username) {
        User user = new User();
        /*
        * query userInfo from database
        * */
        user.setPassword("1234");
        user.setUsername(username);
        user.setSalt("1234567");
        return user;
    }
}

```

## Run

Run this v2 in your IDEA, you will find some difference compared to v1, all URL that can be visited only when user have loginned can be
visited directly in v1 but not in this v2, so we have finish an easy version for authentication and authorization.
