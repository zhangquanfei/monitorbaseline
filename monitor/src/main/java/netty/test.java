package netty;

import manager.ZookeeperClient;

import java.util.HashSet;
import java.util.Set;

public class test {
    public static void main(String[] args){
       // NettyClient nettyClient = new NettyClient();
        //Object o = nettyClient.client();
       // System.out.println("1212"+o);


        //ZookeeperClient.list1.add("c");

        Set<String> listA= new HashSet<>();
        listA.add("Tom1");
        listA.add("Tom2");
        listA.add("Tom3");
        Set<String> listB= new HashSet<>();
        listB.add("Tom4");

        Set<String> listC= new HashSet<>();
        listC.addAll(listA);
        listA.retainAll(listB);

        System.out.println("--------");
        for (String a: listA){
            System.out.println(a);
        }
        System.out.println("--------");
        for (String a: listC){
            System.out.println(a);
        }
        if(listA.size()>0){
            System.out.println("这两个集合有相同的交集");
        }else{
            System.out.println("这两个集合没有相同的交集");
        }


    }
}
