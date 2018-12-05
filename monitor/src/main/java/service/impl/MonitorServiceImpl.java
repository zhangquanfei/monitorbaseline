package service.impl;

import dao.MonitorDao;
import dao.UsernamePasswordDao;
import model.*;
import org.springframework.stereotype.Service;
import service.MonitorService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class MonitorServiceImpl implements MonitorService {
    @Resource
    MonitorDao monitorDao;

    @Resource
    UsernamePasswordDao usernamePasswordDao;

    @Override
    public UsernamePassword queryByUsernameAndPassword(Map<String, Object> map) {
        return usernamePasswordDao.queryByUsernameAndPassword(map);
    }

    @Override
    public UsernamePassword queryByUsername(String userName) {
        return usernamePasswordDao.queryByUsername(userName);
    }

    @Override
    public void insertUsernamePassword(Map<String, Object> map) {
            usernamePasswordDao.insertUsernamePassword(map);
    }

    @Override
    public void addmonitor(Map<String, Object> map) {
        monitorDao.addmonitor(map);
    }

    @Override
    public List<Monitor> selectAllMonitor() {
        return monitorDao.selectAllMonitor();
    }

    @Override
    public void deleteMonitor(int id) {
        monitorDao.deleteMonitor(id);
    }

    @Override
    public int queryIdByRow(int row) {
        return monitorDao.queryIdByRow(row);
    }

    @Override
    public String queryIpByRow(int row) {
        return monitorDao.queryIpByRow(row);
    }

    @Override
    public String queryThreadByRow(int row) {
        return monitorDao.queryThreadByRow(row);
    }

    @Override
    public List<LogMonitor> quertyAllLogMonitor() {
        return monitorDao.quertyAllLogMonitor();
    }

    @Override
    public void addcopyfile(Map<String, Object> map) {
         monitorDao.addcopyfile(map);
    }

    @Override
    public List<CopyFile> quertyAllCopyFile() {
        return monitorDao.quertyAllCopyFile();
    }

    @Override
    public String queryCopyFileByIPNowFile(Map<String, Object> map) {
        return monitorDao.queryCopyFileByIPNowFile(map);
    }

    @Override
    public List<CheckoutData> quertyAllCheckout() {
        return monitorDao.quertyAllCheckout();
    }

    @Override
    public void deleteAllCheckout() {
        monitorDao.deleteAllCheckout();
    }

    @Override
    public int queryCopyFileIdByRow(int row) {
        return monitorDao.queryCopyFileIdByRow(row);
    }

    @Override
    public void deleteCopyFileById(int id) {
        monitorDao.deleteCopyFileById(id);
    }

    @Override
    public CopyFile queryCopyFileByRow(int row) {
        return monitorDao.queryCopyFileByRow(row);
    }

    @Override
    public void deleteMD5DataByOri(String originaldir) {
        monitorDao.deleteMD5DataByOri(originaldir);
    }


}
