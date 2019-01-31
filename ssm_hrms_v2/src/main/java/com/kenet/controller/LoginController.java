package com.kenet.controller;

import com.kenet.bean.User;
import com.kenet.util.JsonMsg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/hrms")
public class LoginController {

    /*
    * 登录页面
    * */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /*
    * @param request
    * @return
    * */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg dologin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);

        /*
        * 登录调用认证
        * */
        UsernamePasswordToken token = new UsernamePasswordToken(
                username, password);

        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return JsonMsg.fail().addInfo("login_error","用户不存在或者密码错误");
        } catch (IncorrectCredentialsException ex) {
            return JsonMsg.fail().addInfo("login_error","用户不存在或者密码错误");
        } catch (AuthenticationException ex) {
            return JsonMsg.fail().addInfo("login_error","用户不存在或者密码错误");
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMsg.fail().addInfo("server_error","内部错误，请重试");
        }
//        if(!(("admin".equals(username)) && ("1234".equals(password) ))) {
//            return JsonMsg.fail().addInfo("login_error","账号和密码不匹配");
//        }
        return JsonMsg.success();
    }

    /*
    * 认证失败
    * */
    @RequestMapping(value = "/unauthorized",method = RequestMethod.GET)
    public String unauthorized() {
        return "unauthorized";
    }

    /*
    * 跳转到主页面
    * */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
    
   /**
     * 退出登录：从主页面跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try{
                subject.logout();
            }catch(Exception ex){
            }
        }
        return "redirect:/hrms/login";
    }
}
