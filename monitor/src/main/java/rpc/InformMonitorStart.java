package rpc;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class InformMonitorStart {

    public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {

        RPC.Builder builder = new RPC.Builder(new Configuration());

        builder.setBindAddress("192.168.99.73").setPort(10000).setProtocol(InformMonitorInterface.class).setInstance(new InformMonitorImpl());

        RPC.Server server = builder.build();

        server.start();

    }
}
