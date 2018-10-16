package manager;

import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.*;

@Repository
public class Data {
    public  Map<String, String> map ;

    public Map<String,WatchEvent.Kind<Path>> map1;
    public Data(){
        this.map =  new HashMap<String, String>();
        this.map1 = new HashMap<String,WatchEvent.Kind<Path>>();
    }

    public Map<String, WatchEvent.Kind<Path>> getMap1() {
        return map1;
    }

//    public void setMap1(String key, WatchEvent.Kind<Path> event) {
//        this.map1.put(key,event);
//    }

    public  Map<String, String> getMap() {
        return map;
    }

//    public void setMap(Map<String, String> map) {
//        this.map = map;
//    }
}
