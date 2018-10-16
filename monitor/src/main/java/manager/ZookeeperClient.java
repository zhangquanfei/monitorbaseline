package manager;

import org.I0Itec.zkclient.ZkClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperClient {

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    public static List<String> list = new LinkedList<String>();
    public static ZkClient zkClient = null;
    public ZookeeperClient() {
        SAXReader reader = new SAXReader();
        Document doc = null;
        String path = ZookeeperClient.class.getResource("/zookeeper-core.xml").getPath();
        try {
            doc = reader.read(new File(path));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //2.得到根标签
        Element rootElem = doc.getRootElement();
        System.out.println(rootElem.getName());



        Element zookeeperElem = rootElem.element("zookeeper");
        String zookeeper = zookeeperElem.getStringValue().trim();
        System.out.println(zookeeperElem.getName()+" "+zookeeper);

        zkClient = new ZkClient(zookeeper);
        List<String> ss =  zkClient.getChildren("/monitor");
        for (String s : ss){
            String a = (String) zkClient.readData("/monitor/"+s);
            System.out.println("zookeeper::::::::::::::::::::"+a);
            list.add(a);
        }
    }

    @Test
    public void aaa(){
        String ss = "dd"+1;
        System.out.println(ss);
    }

    public void deletezookeeper(String data){

        List<String> ss =  zkClient.getChildren("/monitor");
        for (String s : ss){
            System.out.println(s);
            String zdata = (String) zkClient.readData("/monitor/"+s);
            if (data.equals(zdata)){
                zkClient.delete("/monitor/"+s);
            }

        }
    }

    public static List<String> list1 = new LinkedList<>();
    public static void main(String[] args){


        //String aa = "qwqqqqqqqqqqqqwqqq";
        //String bb = aa.replaceAll("w","");
       // System.out.println(aa.replaceAll("w","1"));




        long heartTime = 1536769639369L;
        long nowTime = 1529825531461L;
        System.out.println(( nowTime- heartTime)/1000);

//



        ZkClient zkClient = new ZkClient("192.168.100.31:2181,192.168.100.32:2181,192.168.100.33:2181");
        final String as = "/monitor/192.168.100.33";
       // final String as = "/monitor/192.168.99.73";
       // final String as = "/monitor/192.168.110.90";
       //    zkClient.deleteRecursive(as);
           //zkClient.delete("/monitor/server1");
        List<String> ss =  zkClient.getChildren("/monitor");
//        for (String s : ss){
//            final String as = "/monitor/192.168.99.73";
//            zkClient.deleteRecursive(as);
////            System.out.println(s);
////            System.out.println((long)zkClient.readData("/monitor/"+s+ "/heart"));
//        }

        for (String s : ss){
            System.out.println(s);
            System.out.println((long)zkClient.readData("/monitor/"+s+ "/heart"));
        }

//        long a = 1536683017722L;
//        System.out.println("--1536682811439----"+System.currentTimeMillis());
//        Long timeSub = (System.currentTimeMillis() - a) / 1000;
//        System.out.println(timeSub);

    }

    @Test
    public  void zkClientTest() {
        ZkClient zkClient = new ZkClient("192.168.33.133:2181,192.168.33.134:2181,192.168.33.135:2181");

        //zkClient.delete("/monitor/server1");
        List<String> ss =  zkClient.getChildren("/monitor");
        for (String s : ss){
            System.out.println(s);
            //System.out.println((String) zkClient.readData("/monitor/"+s+ "/heart"));
        }

//        String node2 = "/app2";
//
//        if (!zkClient.exists(node2)) {
//            //创建node2节点
//            zkClient.createPersistent(node2, "hello zk");
//        }
//
//        //读取node2节点的数据
//        System.out.println((String) zkClient.readData(node2));
////        zkClient.createPersistent("/app2/app3", "hello zk1");
////        System.out.println((String) zkClient.readData("/app2/app3"));
//
//        System.out.println(zkClient.exists(node2));
//
//        //获取node2节点下面的所有子节点
//       List<String> s =  zkClient.getChildren(node2);
//        System.out.println(s.get(0));
//
//        //删除node2节点
//        zkClient.delete(node2);

    }
}
