package springstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import springstore.Service.ServiceInterface.AdminService;
import springstore.Service.ServiceInterface.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    private final AdminService adminservice;
    private final CartService cartService;
@Autowired
    public HomeController(AdminService adminservice, CartService cartService) {
        this.adminservice = adminservice;
        this.cartService = cartService;
    }

    @GetMapping()
    public String Homepage(Model model, HttpSession session){
        model.addAttribute("Products",adminservice.showAllProducts());
        return "index";
    }
}
