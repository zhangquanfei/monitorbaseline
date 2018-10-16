package model;

public class EventData {
    private String ip;
    private String EVENT;
    private String direction;
    private String TIME;

    public EventData(String ip, String EVENT, String direction, String TIME) {
        this.ip = ip;
        this.EVENT = EVENT;
        this.direction = direction;
        this.TIME = TIME;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
