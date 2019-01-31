package com.kenet.shiro;

import com.kenet.bean.User;
import com.kenet.service.ShiroUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
