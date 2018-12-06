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


        EventThread eventThread = new EventThread();
        eventThread.start();

        HeartBeatThread heartBeatThread = new HeartBeatThread();
        heartBeatThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
