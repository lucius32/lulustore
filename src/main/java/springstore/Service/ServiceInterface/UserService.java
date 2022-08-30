package springstore.Service.ServiceInterface;

import springstore.Dto.UserDto;
import springstore.Model.User;

public interface UserService {
    boolean  loginUser(UserDto user);
    boolean registerUser(User user);
    boolean adminLogin(UserDto user);

    boolean registerAdmin(User user);
}
