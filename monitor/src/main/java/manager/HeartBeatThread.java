package manager;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import rpc.AddMonitorInterface;

import java.net.InetSocketAddress;

public class HeartBeatThread extends Thread{
    public static Mysql mysql = new Mysql();
    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public void run() {
        String result = "";
        while (true){
            try {
                sleep(3000);
                try {
                    AddMonitorInterface proxy = null;
                    proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                            new InetSocketAddress(host, Integer.parseInt(port)),
                            new Configuration());
                    result = proxy.addmonitor("","","","heartbeat","");
                } catch (Exception e) {
                    result = "fail";
                }

                System.out.println("结果："+result);

                if (!"heartbeatsuccess".equals(result)){

//                        ZookeeperClient zookeeperClient = new ZookeeperClient();
//                        zookeeperClient.deletezookeeper(host+":"+port);
                        mysql.deleteRegMachine(host);

                        mysql.updateMonitor("停止中",host+":"+port);
                }
                if ("heartbeatsuccess".equals(result)){
                        mysql.updateMonitor("运行中",host+":"+port);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
