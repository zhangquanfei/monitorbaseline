package controller;


import manager.Data;
import manager.Mysql;
import manager.ZookeeperClient;
import model.*;
import org.I0Itec.zkclient.ZkClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rpc.AddMonitorInterface;
import service.MonitorService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
@RequestMapping("/monitor")
public class MonitorController {


    @Resource
    MonitorService monitorService;
    @Resource Data data;

    Mysql mysql = new Mysql();

    public static int tag = 0;
    @RequestMapping("/addmonitor")
    public String showUser(HttpServletRequest request, HttpServletResponse response) {

        String select = request.getParameter("sele");
        String[] ipAndPort = select.split(":");

        System.out.println("::::::::::::"+select);

        Map<String, Object> map = new HashMap<String, Object>();
        String directory = request.getParameter("directory");
        //String ip = request.getParameter("ip");

        map.put("directory", directory);
        map.put("ip",select);

        String event = request.getParameter("Jszzdm");
        event = event.replace("0","Create");
        event =event.replace("1","Modify");
        event =event.replace("2","Delete");
        System.out.println("event:"+event);
        map.put("event", event);

        String exclude = request.getParameter("exclude");
        map.put("exclude", exclude);

        System.out.println("directory directory directory:::"+directory);
        System.out.println("exclude exclude exclude:::"+exclude);

        AddMonitorInterface proxy = null;
        try {
            proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                    new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])),
                    new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String threadname = proxy.addmonitor(directory,exclude,event,"add","");
        System.out.println("结果："+threadname);

        //data.getMap().put(threadname, threadname);
        map.put("thread", threadname);
        map.put("state", "运行中");

        monitorService.addmonitor(map);
        List<Monitor> listMonitors =  monitorService.selectAllMonitor();

        request.setAttribute("monitor",listMonitors);

        return "/monitor/setmonitor.jsp";
    }

    @RequestMapping("/querymonitor")
    public String showUser2(HttpServletRequest request, HttpServletResponse response) {
        List<Monitor> listMonitors =  monitorService.selectAllMonitor();

        request.setAttribute("monitor",listMonitors);
        return "/monitor/setmonitor.jsp";
    }

    @RequestMapping("/deletemonitor")
    public String showUser1(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("--------------------com in deletemonitor");
        int row = Integer.parseInt(request.getParameter("position"));

        String thread = monitorService.queryThreadByRow(row);
        String ip = monitorService.queryIpByRow(row);
        //String[] ipAndPort = ip.split(":");
        String[] ipAndPort = ip.split(":");
        System.out.println("--------------------");

        System.out.println(ipAndPort[0]+" "+ipAndPort[1]);
        System.out.println(thread);


        try {

            AddMonitorInterface  proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                    new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])),
                    new Configuration());

            String result = proxy.addmonitor("","","","del",thread);
            System.out.println("结果："+result);


            //data.getMap().get(thread).stop();
        }catch (Exception e){

            int id = monitorService.queryIdByRow(row);
            monitorService.deleteMonitor(id);

            List<Monitor> listMonitors =  monitorService.selectAllMonitor();

            request.setAttribute("monitor",listMonitors);
            return "/monitor/setmonitor.jsp";
        }

        int id = monitorService.queryIdByRow(row);
        monitorService.deleteMonitor(id);

        List<Monitor> listMonitors =  monitorService.selectAllMonitor();

        request.setAttribute("monitor",listMonitors);
        return "/monitor/setmonitor.jsp";
    }

    @RequestMapping("/monitorlog")
    public String showUser4(HttpServletRequest request, HttpServletResponse response) {


        return "/monitor/index.jsp";
    }

    @RequestMapping(value="login1.action")
    public @ResponseBody
    List<String> login1(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        List<String> list = new LinkedList<String>();
//        ZkClient zkClient = new ZkClient("192.168.33.133:2181,192.168.33.134:2181,192.168.33.135:2181");
//        List<String> ss =  zkClient.getChildren("/monitor");
//        for (String s : ss){
//            String a = (String) zkClient.readData("/monitor/"+s);
//            System.out.println("zookeeper::::::::::::::::::::"+a);
//            list.add(a);
//        }
//        return list;
       // return ZookeeperClient.list;

        List<RegMachine> regMachines = mysql.queryRegMachine();
        List<String> regIPs = new LinkedList<>();
        for (RegMachine regMachine : regMachines){
            regIPs.add(regMachine.getRegIp()+":"+regMachine.getRegPort());
        }
        return regIPs;
    }


    @RequestMapping("/logmonitor")
    public String logmonitor(HttpServletRequest request, HttpServletResponse response) {
        List<LogMonitor> listlogs =  monitorService.quertyAllLogMonitor();

        if (tag == 0){
            tag = listlogs.size();
        }else {
            if (tag == listlogs.size()) {


                System.out.println("-----------无触发事件");
            } else if (tag != listlogs.size()) {
                //发送邮件。。。待完成
                System.out.println("-----------有触发事件");
                tag = listlogs.size();
            }
        }
        request.setAttribute("listlogs",listlogs);
        return "/monitor/logmonitor.jsp";
    }



    @RequestMapping("/copyfile")
    public String copyfile(HttpServletRequest request, HttpServletResponse response) {

        String select = request.getParameter("sele");
        String[] ipAndPort = select.split(":");


        String originaldir = request.getParameter("originaldir");
        String copydir = request.getParameter("copydir");



        AddMonitorInterface proxy = null;
        try {
            proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                    new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])),
                    new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = proxy.addmonitor(originaldir,copydir,"","copyfile","");
        System.out.println("结果："+result);

        if ("success".equals(result)){
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ip",select);
            map.put("originaldir",originaldir);
            map.put("copydir",copydir);
            map.put("TIME",df.format(day));
            monitorService.addcopyfile(map);
        }


        List<CopyFile> copyFiles =  monitorService.quertyAllCopyFile();

        request.setAttribute("copyFiles",copyFiles);


        return "/monitor/copyfile.jsp";
    }

    @RequestMapping("/querycopyfile")
    public String queryCopyFile(HttpServletRequest request, HttpServletResponse response) {
        List<CopyFile> copyFiles =  monitorService.quertyAllCopyFile();

        request.setAttribute("copyFiles",copyFiles);
        return "/monitor/copyfile.jsp";
    }

    @RequestMapping("/checkoutfile")
    public String checkoutFile(HttpServletRequest request, HttpServletResponse response) {
        monitorService.deleteAllCheckout();

        String select = request.getParameter("sele");
        String[] ipAndPort = select.split(":");

        System.out.println("::::::::::::"+select);

        String direction = request.getParameter("direction");
        System.out.println("::::::::::::"+direction);
//        String copyFile = "";
//        if ((new File(direction).isDirectory())){
//
//            //待写。。。
//        } else if ((new File(direction).isFile())){
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("ip",select);
//            map.put("direction",direction);
//            String copyFile1 = monitorService.queryCopyFileByIPNowFile(map);
//            System.out.println("copyFile1::::::"+copyFile1);
//            copyFile = direction.replace(direction.substring(0, direction.lastIndexOf("/")),copyFile1);
//            System.out.println("copyFile::::::"+copyFile);
//        }

        AddMonitorInterface proxy = null;
        try {
            proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                    new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])),
                    new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = proxy.addmonitor(direction,select,"","checkoutfile","");
        System.out.println("结果："+result);


        List<CheckoutData> checkoutDatas =  monitorService.quertyAllCheckout();

        request.setAttribute("checkoutdatas",checkoutDatas);
        return "/monitor/checkoutfile.jsp";
    }
    @RequestMapping("/querycheckoutfile")
    public String querycheckoutFile(HttpServletRequest request, HttpServletResponse response) {
        monitorService.deleteAllCheckout();
        List<CheckoutData> checkoutDatas =  monitorService.quertyAllCheckout();

        request.setAttribute("checkoutdatas",checkoutDatas);
        return "/monitor/checkoutfile.jsp";
    }

    @RequestMapping("/deleteupdate")
    public String deleteUpdateCopyFile(HttpServletRequest request, HttpServletResponse response) {

        String updateposition = request.getParameter("updateposition");
        String deleteposition = request.getParameter("deleteposition");

        System.out.println("deletepositiondeletepositiondeleteposition:::"+deleteposition);
        if (updateposition == null && deleteposition != null){
            System.out.println("deletepositiondeletepositiondeletepositiondeleteposition：："+"点击的是删除按钮");
            int  id = monitorService.queryCopyFileIdByRow(Integer.parseInt(deleteposition));
            CopyFile  copyFile = monitorService.queryCopyFileByRow(Integer.parseInt(deleteposition));
            String originaldir = copyFile.getOriginaldir();
            monitorService.deleteMD5DataByOri("^"+originaldir);
            monitorService.deleteCopyFileById(id);

        }

        if (deleteposition == null && updateposition != null){
            //System.out.println("updatepositionupdatepositionupdatepositionupdateposition：："+"点击的是更新按钮");
            CopyFile  copyFile = monitorService.queryCopyFileByRow(Integer.parseInt(updateposition));
            String ip = copyFile.getIp();
            String[] ipAndPort = ip.split(":");
            String originaldir = copyFile.getOriginaldir();
            String copydir = copyFile.getCopydir();
            System.out.println(ip +" "+originaldir+ " "+ copydir);

            int  id = monitorService.queryCopyFileIdByRow(Integer.parseInt(updateposition));
            monitorService.deleteMD5DataByOri("^"+originaldir);
            monitorService.deleteCopyFileById(id);

            AddMonitorInterface proxy = null;
            try {
                proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                        new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1])),
                        new Configuration());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = proxy.addmonitor(originaldir,copydir,"","copyfile","");
            System.out.println("结果："+result);

            if ("success".equals(result)){
                Date day=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("ip",ip);
                map.put("originaldir",originaldir);
                map.put("copydir",copydir);
                map.put("TIME",df.format(day));
                monitorService.addcopyfile(map);
            }
        }
        List<CopyFile> copyFiles =  monitorService.quertyAllCopyFile();

        request.setAttribute("copyFiles",copyFiles);
        return "/monitor/copyfile.jsp";
    }
}
