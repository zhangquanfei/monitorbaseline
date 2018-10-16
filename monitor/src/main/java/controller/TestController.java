package controller;

import model.UsernamePassword;
import service.UPService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;

@Controller
@RequestMapping("/userp")
public class TestController {
    @Resource
     private UPService upService;
    //认证方法测试
    @RequestMapping("/showUser")
    public String showUser(@RequestParam("username")String username,@RequestParam("password")String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (Exception e) {
            token.clear();
            //登陆失败
            return "/rz-fail.html";
        }
        return "/showUser.html";
    }
    //mybatis测试
    @RequestMapping("/mybatis")
    public String mybatis() {
        UsernamePassword up = upService.queryByUsername("zqf");
        System.out.println("username="+up.getUsername());
        System.out.println("password="+up.getPassword());
        return "/showUser.html";
    }


}
