package springstore.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springstore.Dto.UserDto;
import springstore.Model.Product;
import springstore.Model.User;
import springstore.Service.ServiceInterface.AdminService;
import springstore.Service.ServiceInterface.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    private final UserService userService;

    private final AdminService adminservice;
    @Autowired
    public AdminController(UserService userService, AdminService adminservice) {
        this.userService = userService;
        this.adminservice = adminservice;
    }


    @GetMapping("")
    public String adminPage(Model model){
        model.addAttribute("admin",new UserDto());
        return "AdminLogin";
    }

    @PostMapping("")
    public String adminLogin(@ModelAttribute("admin") UserDto userDto, Model model, HttpSession session){
        if(userService.adminLogin(userDto)){
            session.setAttribute("adminEmail",userDto.getEmailAddress());
            return "AdminPage";
        }
        model.addAttribute("AdminError","Invalid Email or Password");
        return "AdminLogin";

    }
    @GetMapping("/register")
    public String adminRegisterPage(Model model){
        model.addAttribute("AdminRegister",new User());
        return "Adminregister";
    }
    @PostMapping("/register")
    public String adminRegister(@ModelAttribute("AdminRegister") User user, Model model){
       if(!userService.registerAdmin(user)){
           model.addAttribute("Adminfailed","Email already exit");
           return "Adminregister";
       }
       model.addAttribute("Adminsuccess","Registered Successfully");
        return "AdminPage";
    }
@GetMapping("/EditProduct/{id}")
public  String editProductPage(@PathVariable("id") int proId, Model model, HttpSession session){
    Product product = adminservice.getProductById(proId);
    model.addAttribute("product",product);
    model.addAttribute("proId",proId);
    model.addAttribute("newProduct",new Product());
    return "editProduct";
}
    @PostMapping("/EditProduct/{id}")
    public  String editProduct(@PathVariable("id") int proId, @ModelAttribute("newProduct") Product product, Model model){
        adminservice.editProduct(proId,product);
        model.addAttribute("success","Product edited successfully");
        return "redirect:/Admin/viewAllProduct";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        adminservice.deleteProduct(id);
        return "redirect:/Admin/viewAllProduct";
    }
    @GetMapping("/addProduct")
    public String addProductview(Model model){
    model.addAttribute("productPage",new Product());
        return "addproduct";
    }
    @PostMapping("/addProduct")
    public String uploadImage(@ModelAttribute("productPage") Product product ,Model model, @RequestParam("image") MultipartFile file) throws IOException {
        adminservice.addNewProduct(file,product);
        model.addAttribute("success","Product added successfully");
        return "addproduct";
    }
    @GetMapping("/viewAllProduct")
    public String getAllProducts(Model model){
        model.addAttribute("Products", adminservice.showAllProducts());
        return "showAllProduct";
    }
}
