package manager;

import model.EventData;
import model.RegMachine;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Mysql {
    //驱动程序名
    static String driver = "com.mysql.jdbc.Driver";
    //MySQL配置时的用户名
    static String user = "root";
    //MySQL配置时的密码
    static String password = "root";

    //声明Connection对象
    static Connection mysql_con;
    static Statement mysql_statement;



    static {
        String mysql_url = "jdbc:mysql://192.168.99.73:3306/db_ssm?autoReconnect=true";
        //
        //com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Data source rejected establishment of connection,  message from server: "Too many connections"
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            mysql_con = DriverManager.getConnection(mysql_url,user,password);
            mysql_statement = mysql_con.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    public int queryAllEventData(){

        int i = 0;
        //遍历查询结果集
        try {

            String sql = "SELECT * FROM eventdata";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = mysql_statement.executeQuery(sql);
            while(rs.next()){
               i++;
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return i;
    }


    public List<EventData> queryEventData(int start,int longsome){

        List<EventData> eventDatas  = new LinkedList<EventData>();
        int i = 0;
        //遍历查询结果集
        try {

            String sql = "SELECT * FROM eventdata LIMIT "+start+","+longsome;
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = mysql_statement.executeQuery(sql);
            while(rs.next()){
                String ip = rs.getString("ip");
                String EVENT = rs.getString("EVENT");
                String direction = rs.getString("direction");
                String TIME = rs.getString("TIME");
                EventData eventData = new EventData(ip,EVENT,direction,TIME);
                eventDatas.add(eventData);
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return eventDatas;
    }

    public  void deleteMonitor(String ip){
        //遍历查询结果集
        try {
            PreparedStatement psql;
            //预处理删除数据
            psql = mysql_con.prepareStatement("delete from monitor where ip = ?");
            psql.setString(1,ip);
            psql.executeUpdate();
            psql.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

    public void updateMonitor(String state,String ip){
        //遍历查询结果集
        try {
            PreparedStatement psql;
            psql = mysql_con.prepareStatement("update monitor set state = ? where ip = ?");
            psql.setString(1,state);
            psql.setString(2,ip);
            psql.executeUpdate();
            psql.close();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }



    public List<RegMachine> queryRegMachine(){

        List<RegMachine> regMachines  = new LinkedList<RegMachine>();
        //遍历查询结果集
        try {
            String sql = "SELECT * FROM regMachine";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = mysql_statement.executeQuery(sql);
            while(rs.next()){
                String regIp = rs.getString("regIp");
                String regPort = rs.getString("regPort");

                RegMachine regMachine = new RegMachine();
                regMachine.setRegIp(regIp);
                regMachine.setRegPort(regPort);

                regMachines.add(regMachine);
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return regMachines;
    }

    public  void deleteRegMachine(String ip){
        //遍历查询结果集
        try {
            PreparedStatement psql;
            //预处理删除数据
            psql = mysql_con.prepareStatement("delete from regMachine where regIp = ?");
            psql.setString(1,ip);
            psql.executeUpdate();
            psql.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }
}


















