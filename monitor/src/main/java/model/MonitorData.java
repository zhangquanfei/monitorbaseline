package model;

public class MonitorData {
    private String directory;
    private String event;
    private String exclude;
    private String addOrDelete;
    private String threadName;


    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getAddOrDelete() {
        return addOrDelete;
    }

    public void setAddOrDelete(String addOrDelete) {
        this.addOrDelete = addOrDelete;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
