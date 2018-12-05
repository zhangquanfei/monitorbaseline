package dao;

import model.CheckoutData;
import model.CopyFile;
import model.LogMonitor;
import model.Monitor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Repository
public interface MonitorDao {
    void addmonitor(Map<String,Object> map);
    List<Monitor> selectAllMonitor();
    void deleteMonitor(int id);
    int queryIdByRow(int row);
    String queryIpByRow(int row);
    String queryThreadByRow(int row);

    List<LogMonitor> quertyAllLogMonitor();

    void addcopyfile(Map<String,Object> map);
    List<CopyFile> quertyAllCopyFile();

    String queryCopyFileByIPNowFile(Map<String,Object> map);

    List<CheckoutData> quertyAllCheckout();

    void deleteAllCheckout();

    int queryCopyFileIdByRow(int row);
    void deleteCopyFileById(int id);
    CopyFile queryCopyFileByRow(int row);
    void deleteMD5DataByOri(String originaldir);
}
