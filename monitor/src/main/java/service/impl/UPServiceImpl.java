package service.impl;
import dao.UsernamePasswordDao;
import model.UsernamePassword;
import service.UPService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service
public class UPServiceImpl implements UPService{
    @Resource
    private UsernamePasswordDao usernamePasswordDao;
     @Override
     public UsernamePassword queryByUsername(String userName){
        return usernamePasswordDao.queryByUsername(userName);
     }
}
