package service;
import model.UsernamePassword;
public interface UPService {

    UsernamePassword queryByUsername(String userName);
}
