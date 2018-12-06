package manager;


import model.RegMachine;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import rpc.AddMonitorInterface;

import java.net.InetSocketAddress;
import java.util.List;

public class HeartBeatThread extends Thread {
    public static Mysql mysql = new Mysql();



    @Override
    public void run() {
        while (true) {
            try {
                sleep(3000);
                long nowTime = System.currentTimeMillis();
                List<RegMachine> regMachines =  mysql.queryRegMachine();
                for (RegMachine regMachine : regMachines){
                    String ip = regMachine.getRegIp();
                    String port = regMachine.getRegPort();
                    System.out.println(regMachine.getHeartData());
                    long heartdata = Long.valueOf(regMachine.getHeartData());

                    System.out.println("ip: "+ip +"  heartdata: "+ heartdata+"  nowTime: "+nowTime);
                    long timeSub = (nowTime - heartdata) /1000;
                    if (timeSub > 30){
                        mysql.deleteRegMachine(ip);
                        mysql.updateMonitor("停止中", ip + ":" + port);
                    }else {
                        mysql.updateMonitor("运行中", ip + ":" + port);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
