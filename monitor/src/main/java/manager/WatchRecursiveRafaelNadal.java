package manager;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.nio.file.FileSystems;   
import java.nio.file.FileVisitResult;   
import java.nio.file.Files;   
import java.nio.file.LinkOption;   
import java.nio.file.Path;   
import java.nio.file.Paths;   
import java.nio.file.SimpleFileVisitor;   
import java.nio.file.StandardWatchEventKinds;   
import java.nio.file.WatchEvent;   
import java.nio.file.WatchEvent.Kind;   
import java.nio.file.WatchKey;   
import java.nio.file.WatchService;   
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;

public class WatchRecursiveRafaelNadal implements ServletContextListener {


    // 频道1
    private static final String CHANNEL1 = "res1";
    // 频道2
    private static final String CHANNEL2 = "res2";

    // 通过频道1推送给前台的变量1
    private static int number1 = 0;
    // 通过频道2推送给前台的变量2
    private static int number2 = 100;


    public static String sss = "";





    private WatchService watchService;   
    private final Map<WatchKey, Path> directories = new HashMap<>();

    private void registerPath(Path path,List< WatchEvent.Kind<Path>> events) throws IOException {
        if(events.size() == 1){
            //register the received path
            WatchKey key = path.register(watchService, events.get(0));
            //store the key and path
            directories.put(key, path);
        }
        if(events.size() == 2){
            //register the received path
            WatchKey key = path.register(watchService, events.get(0),events.get(1));
            //store the key and path
            directories.put(key, path);
        }
        if(events.size() == 3){
            //register the received path
            WatchKey key = path.register(watchService, events.get(0),events.get(1),events.get(2));
            //store the key and path
            directories.put(key, path);
        }

//        //register the received path
//        WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
//                StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
//        directories.put(key, path);

    }   
    private void registerTree(Path start,final String exclude,List< WatchEvent.Kind<Path>> events) throws IOException {
   
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {   
   
            @Override   
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)   
                    throws IOException {   
               
            	//ss�����·�����ڼ�����Χ֮�� C:/a/w/aaa;C:/a/w/aaasss;C:/a/w/q
            	//String[] ss = exclude.split(";");
            	String[] ss = {"C:\\a\\w","C:\\a\\x"};


            	boolean b = false;

                    for (int i = 0; i < ss.length; i++) {
                        if (dir.toString().equals(ss[i])){
                            b = true;
                        }
                        if (dir.toString().length() > ss[i].length()) {
                                if (dir.toString().contains(ss[i])){
                                    b = true;
                                }
                        }
                    }
                    if (b == false){
                        System.out.println("register:"+dir);
                        registerPath(dir, events);
                        b = false;
                    }


//                for(int i =0;i<ss.length;i++){
//
//                    if (dir.toString().equals(ss[i])){
//                            //过滤
//                    }else {
//
//                        if (dir.toString().length() > ss[i].length()) {
//
//                            char[] excludes = ss[i].toCharArray();
//                            char[] starts = dir.toString().toCharArray();
//                            Boolean  bool = false;
//                            for (int j = 0; j < excludes.length; j++) {
//                                if (starts[j] != excludes[j]) {
//                                    bool = true;
//                                }
//                            }
//                            if (bool == true) {
//                                System.out.println("aaa");
//                                registerPath(dir, events);
//                                bool = false;
//                            }
//                        }else {
//                            System.out.println("ppp");
//                            registerPath(dir, events);
//                        }
//                    }
//                }



            	//System.out.println("==="+dir.toString());
//                if(!Arrays.asList(ss).contains(dir.toString()))
//                {
//                System.out.println("Registering:" + dir);
//                    WatchRecursiveRafaelNadal.sss = "Registering:" + dir;
//                registerPath(dir,events);
//                }



                return FileVisitResult.CONTINUE;   
            }   
        });   
   
    }

    public void watchRNDir(Path start,String exclude,List< Kind<Path>> events) throws IOException, InterruptedException {
   
        watchService = FileSystems.getDefault().newWatchService();   
   
        registerTree(start,exclude,events);
   
        //start an infinite loop   
        while (true) {   
   
            //retrieve and remove the next watch key   
            final WatchKey key = watchService.take();

//            if (data.getMap().get("a") != null){
//                break;
//            }
   
            //get list of events for the watch key   
            for (WatchEvent<?> watchEvent : key.pollEvents()) {   
   
                //get the kind of event (create, modify, delete)   
                final Kind<?> kind = watchEvent.kind();   
   
                //get the filename for the event   
                final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;   
                final Path filename = watchEventPath.context();   
   
                //handle OVERFLOW event   
                if (kind == StandardWatchEventKinds.OVERFLOW) {   
                    continue;   
                }   
   
                //handle CREATE event   
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    final Path directory_path = directories.get(key);   
                    final Path child = directory_path.resolve(filename);   
                    System.out.println(kind + "-> " + child);
                    sss = kind + "-> " + child;
                    if (Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {   
                        registerTree(child,exclude,events);
                    }
                }

                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    final Path directory_path = directories.get(key);   
                    final Path child = directory_path.resolve(filename);  
                    System.out.println(kind + "-> " + child);
                    sss = kind + "-> " + child;
                }
                
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    final Path directory_path = directories.get(key);
                    final Path child = directory_path.resolve(filename);
                    System.out.println(kind + "-> " + child);


                    sss = kind + "-> " + child;





                }

            }

            //reset the key   
            boolean valid = key.reset();   
   
            //remove the key if it is not valid   
            if (!valid) {   
                directories.remove(key);   
   
                //there are no more keys registered   
                if (directories.isEmpty()) {   
                    break;   
                }   
            }   
        }   
        watchService.close();   
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // CometContext ： Comet4J上下文，负责初始化配置、引擎对象、连接器对象、消息缓存等。
        CometContext cc = CometContext.getInstance();

        // 注册频道，即标识哪些字段可用当成频道，用来作为向前台传送数据的“通道”
        cc.registChannel(CHANNEL1);
        cc.registChannel(CHANNEL2);

        Thread myThread = new Thread(new WatchRecursiveRafaelNadal.SendToClientThread(),"SendToClientThread");
        // 下面的内部类的方法是个死循环，设置helloAppModule线程为“守护线程”，则当jvm只剩“守护线程”时(主线程结束)，该线程也会结束。
        myThread.setDaemon(true);
        // 开始线程
        myThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * 内部类线程类
     */
    class SendToClientThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // CometEngine ： 引擎，负责管理和维持连接，并能够必要的发送服务
                CometEngine engine = CometContext.getInstance().getEngine();

                // 参数的意思：通过什么频道（CHANNEL1）发送什么数据（number1++），前台可用可用频道的值（result1）来获取某频道发送的数据

                if(!"".equals(WatchRecursiveRafaelNadal.sss)) {
                    engine.sendToAll(CHANNEL1, WatchRecursiveRafaelNadal.sss);
                    engine.sendToAll(CHANNEL2, number2++);
                    WatchRecursiveRafaelNadal.sss="";
                }
            }
        }
    }
}
   
   
 
      
 

