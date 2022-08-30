package springstore.Controller;
import springstore.Dto.UserDto;
import springstore.Model.User;
import springstore.Service.ServiceInterface.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Customer")
public class CustomerController {
   private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account/login")
    public String getLoginPage(Model model){
        UserDto user  = new UserDto();
        model.addAttribute("userLoginPage",user);
        return "Login";
    }
    @PostMapping("/account/login")
    public String Login(@ModelAttribute("userLoginPage") UserDto user, HttpSession session, Model model){
   if(!userService.loginUser(user)){
       model.addAttribute("error","Invalid Email or Password");
       return "Login";
   }
   else {
       session.setAttribute("auth",user.getEmailAddress());
       session.setAttribute("FirstNameWelcome","Welcome Back "+user.getFirstName() + "!");
       return "redirect:/";
   }
    }
    @GetMapping("/account/create")
    public String getRegisterPage(Model model){
        User user  = new User();
        model.addAttribute("userSignUp",user);
        return "SignUp";
    }
    @PostMapping("/account/create")
    public String Register(@ModelAttribute("userSignUp") User user,Model model) throws InterruptedException {
        if(userService.registerUser(user)){
            model.addAttribute("success","Sign up Successful!");
            return "SignUp";
        }
        else {
            model.addAttribute("failed", "Email or password taken!");
            return "SignUp";
        }
    }
    @GetMapping("/account/logout")
    public String logUserOut(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
