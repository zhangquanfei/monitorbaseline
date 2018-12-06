package model;

public class RegMachine {
    private String regIp;
    private String regPort;
    private String heartData;

    public String getHeartData() {
        return heartData;
    }

    public void setHeartData(String heartData) {
        this.heartData = heartData;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getRegPort() {
        return regPort;
    }

    public void setRegPort(String regPort) {
        this.regPort = regPort;
    }
}
