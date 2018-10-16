package manager;

import model.LogMonitor;
import model.RegMachine;
import org.I0Itec.zkclient.ZkClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import rpc.AddMonitorInterface;
import service.MonitorService;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class HeartBeat implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Mysql mysql = new Mysql();
        EventThread eventThread = new EventThread();
        eventThread.start();

        System.out.println("HeartBeatHeartBeatHeartBeatHeartBeatHeartBeatHeartBeat");
      //  ZkClient zkClient = ZookeeperClient.zkClient;
      //  List<String> ss =  zkClient.getChildren("/monitor");
        List<RegMachine> regMachines = mysql.queryRegMachine();
        for (RegMachine s : regMachines){
//            System.out.println(s);
//            String zdata = (String) zkClient.readData("/monitor/"+s);
//            String[] hostAndPort = zdata.split(":");

            HeartBeatThread heartBeatThread = new HeartBeatThread();

            heartBeatThread.setHost(s.getRegIp());
            heartBeatThread.setPort(s.getRegPort());
            heartBeatThread.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
