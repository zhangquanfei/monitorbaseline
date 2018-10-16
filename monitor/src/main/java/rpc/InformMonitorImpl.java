package rpc;

import manager.WatchRecursiveRafaelNadal;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InformMonitorImpl implements InformMonitorInterface{

    @Override
    public String addmonitor1(String path) {
        return "success";
    }


}
