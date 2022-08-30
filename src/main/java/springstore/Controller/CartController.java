package springstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springstore.Model.Product;
import springstore.Service.ServiceInterface.CartService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Cart")
public class CartController {
private final CartService cartService;
@Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id, HttpSession session){
       if(session.getAttribute("auth") == null){
           return "redirect:/Customer/account/login";
        };
        String email = (String) session.getAttribute("auth");
        cartService.addProductToCart(id,email);
        return "redirect:/";
    }
    @GetMapping()
    public String getAllCart(Model model,HttpSession session){
        if(session.getAttribute("auth") == null){
            return "redirect:/Customer/account/login";
        }
    String email = (String) session.getAttribute("auth");
    model.addAttribute("AllCart", cartService.getAllCart(email));
    return "CartPage";
    }
    @GetMapping("buyNow")
    public String buyNow(HttpSession session){
    if(session.getAttribute("auth") == null){
        return "redirect:/Customer/account/login";
    }
    return "redirect:/Cart";
    }
    @GetMapping("/deleteCartProduct/{id}")
    public String deleteCartProduct(@PathVariable("id") int id,HttpSession session){
        String email = (String) session.getAttribute("auth");
        cartService.deleteProduct(id,email);
        return "redirect:/Cart";
    }

}
