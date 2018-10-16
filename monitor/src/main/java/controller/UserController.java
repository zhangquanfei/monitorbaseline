package controller;


import manager.EventThread;
import model.User;
import model.UsernamePassword;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MonitorService;
import service.impl.MonitorServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    MonitorService monitorService;


    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = (String) request.getParameter("password");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        log.info(username + " " + password);
        try {
           UsernamePassword up =  monitorService.queryByUsernameAndPassword(map);
           if(up != null) {
              // List<BoardDao> b =  board.selectAllBoard();
              // request.setAttribute("board", b);
               //return "/person.jsp";


              return "/monitor/index.jsp";
           }
        } catch (Exception e) {
            log.info("错误信息为："+e.getMessage());
        }
        return "/WEB-INF/jsp/error.jsp";

    }


    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = (String) request.getParameter("password");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        log.info(username + " " + password);

        String rt = "/WEB-INF/jsp/registersuccess.jsp";
        try {
            monitorService.insertUsernamePassword(map);
        } catch (Exception e) {
            log.info("错误信息为："+e.getMessage());
            rt = "/WEB-INF/jsp/registererror.jsp";
        }
        return rt;
    }


    @RequestMapping("/inspect")
    public void inspect(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        System.out.println("comr in ---");

        String userName = request.getParameter("user");
        UsernamePassword up =  monitorService.queryByUsername(userName);

        String tip = "";
        if (up != null){
            tip = "用户名已存在";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(tip);
        pw.flush();
        pw.close();

    }


}