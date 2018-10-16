package rpc;


import manager.ZookeeperClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public  static void main(String[] args) throws IOException {
//        InputStream in = Test.class.getClassLoader().getResourceAsStream("zookeeper-core.xml");
//        InputStreamReader isr=new InputStreamReader (in);
//        BufferedReader br = new BufferedReader(isr);
//        String line2="";
//        while ((line2=br.readLine())!=null) {
//            System.out.println("------------------------");
//
//            if (line2.contains("<zookeeper>")){
//
//                String[] ss = line2.split("zookeeper");
//                for (int i=0;i<ss.length;i++)
//                System.out.println(ss[i]);
//            }
//        }


System.out.println(Test.class.getResource("/zookeeper-core.xml").getPath());
String ss = Test.class.getResource("/zookeeper-core.xml").getPath();
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new File(ss));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //2.得到根标签
        Element rootElem = doc.getRootElement();
        System.out.println(rootElem.getName());



        Element zookeeperElem = rootElem.element("zookeeper");
        String zookeeper = zookeeperElem.getStringValue().trim();
        System.out.println(zookeeperElem.getName()+" "+zookeeper);

//        ZookeeperClient zookeeperClient =new ZookeeperClient();
//        List<String> list   = zookeeperClient.list;
//
//        for (String s : list){
//            System.out.println(s);
//        }
//        InformMonitorInterface proxy = null;
//        try {
//            proxy = RPC.getProxy(InformMonitorInterface.class, 1L,
//                    new InetSocketAddress("192.168.99.73", 10000),
//                    new Configuration());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String result = proxy.addmonitor1("hahahehhdhabdhb");
//        System.out.println("结果："+result);

        AddMonitorInterface proxy = null;
        String result = "fail";
        try {
            proxy = RPC.getProxy(AddMonitorInterface.class, 1L,
                    new InetSocketAddress("192.168.33.134", 10000),
                    new Configuration());

             result = proxy.addmonitor("","","","heartbeat","");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("结果："+result);
        }

        System.out.println("结果："+result);

//        RpcMonitorThread rpcMonitorThread = new RpcMonitorThread();
//        rpcMonitorThread.setEvent("Create,Modify,Delete");
//        rpcMonitorThread.setPath("/home/quanfei");
//        rpcMonitorThread.setExcludes("/home/quanfei/app");
//        rpcMonitorThread.start();


//
//        List< WatchEvent.Kind<Path>> sq = new LinkedList<WatchEvent.Kind<Path>>();
//        sq.clear();
//        sq.add(StandardWatchEventKinds.ENTRY_CREATE);
//        sq.add(StandardWatchEventKinds.ENTRY_MODIFY);
//        sq.add(StandardWatchEventKinds.ENTRY_DELETE);
//
//        RpcMonitorThread rpcMonitorThread = new RpcMonitorThread();
////        rpcMonitorThread.setEvents(sq);
////        rpcMonitorThread.setPath("/home/quanfei");
////        rpcMonitorThread.setExcludes("/home/quanfei/app");
//        rpcMonitorThread.start();
    }
}
