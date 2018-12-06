package manager;

import model.EventData;
import model.LogMonitor;
import service.MonitorService;

import javax.annotation.Resource;
import java.util.List;

public class EventThread extends Thread {

    Mysql mysql = new Mysql();
    public static int tag = 0;


    @Override
    public void run() {

        try {

            while (true) {
                sleep(3000);
                System.out.println("------------------------event thread");
               int size = mysql.queryAllEventData();
                if (tag == 0) {
                    tag = size;
                } else {
                    if (tag == size) {
                        System.out.println("-----------无触发事件");
                    } else if (tag != size) {
                        System.out.println("-----------有触发事件");
                       List<EventData> eventDatas =   mysql.queryEventData(tag,size-tag+1);
                       String send= "";
                      for (EventData eventData : eventDatas){
                          send = send+"<br/>"+"机器："+eventData.getIp()+" 事件："+eventData.getEVENT()+" 路径:"+eventData.getDirection()+" 时间:"+eventData.getTIME();
                      }
                      SendEmail.sendEmail("<font size=\"5\" face=\"arial\" color=\"blue\">"+send+"</font>");
                        tag = size;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
