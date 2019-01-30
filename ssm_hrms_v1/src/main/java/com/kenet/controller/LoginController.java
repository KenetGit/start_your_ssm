package com.kenet.controller;

import com.kenet.util.JsonMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
        if(!"admin".equals(username) && (!"1234".equals(password) )) {
            return JsonMsg.fail().addInfo("login_error","账号和密码不匹配");
        }
        return JsonMsg.success();
    }

    /*
    * 跳转到主页面
    * */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
