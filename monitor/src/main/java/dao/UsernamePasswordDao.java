package dao;
import model.UsernamePassword;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UsernamePasswordDao {
    UsernamePassword queryByUsernameAndPassword(Map<String,Object> map);
    UsernamePassword queryByUsername(String userName);
    void insertUsernamePassword(Map<String,Object> map);
}
