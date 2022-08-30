package springstore.Service;

import springstore.Dto.UserDto;
import springstore.Model.User;
import springstore.Repository.UserRepository;
import springstore.Service.ServiceInterface.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean loginUser(UserDto user) {
      User users= userRepository.getUserByEmailAddressAndPassWord(user.getEmailAddress(),user.getPassword());
      if(users == null){
          return false;
      }
      else{
          user.setFirstName(users.getFirstName());
          return (users.getPassWord().equals(user.getPassword()) && users.getEmailAddress().equals(user.getEmailAddress()) && users.getUserRole().equals("customer"));
      }
    }

    @Override
    public boolean registerUser(User user) {
        User users= userRepository.getUserByEmailAddress(user.getEmailAddress());
        if(users != null){
          return false;
        }
        else {
            user.setUserRole("customer");
            userRepository.save(user);
            return true;
    }
    }

    @Override
    public boolean adminLogin(UserDto user) {
        User users= userRepository.getUserByEmailAddressAndPassWord(user.getEmailAddress(),user.getPassword());
        if(users != null && users.getUserRole().equals("admin") && (users.getPassWord().equals(user.getPassword()) && users.getEmailAddress().equals(user.getEmailAddress()))){
            return true;
        }
       return false;
    }

    @Override
    public boolean registerAdmin(User user) {
        User users= userRepository.getUserByEmailAddress(user.getEmailAddress());
        if(users != null){
            return false;
        }
        else {
            user.setUserRole("admin");
            userRepository.save(user);
            return true;
        }
    }
}

