package springstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springstore.Service.ServiceInterface.LikesService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Likes")
public class likesController {
    private final LikesService likesService;
@Autowired
    public likesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @GetMapping("{id}")
    public String likeProduct(@PathVariable("id") int id, HttpSession session){
        if(session.getAttribute("auth") == null){
            return "redirect:/Customer/account/login";
        }
        String userId1 = (String) session.getAttribute("auth");
        likesService.likeProduct(userId1,id);
        return "redirect:/";
    }
    @GetMapping()
    public String getAllCart(Model model, HttpSession session){
        if(session.getAttribute("auth") == null){
            return "redirect:/Customer/account/login";
        }
        String email = (String) session.getAttribute("auth");
        model.addAttribute("allLikes", likesService.getAlllikes(email));
        return "likesPage";
    }
    @GetMapping("/deletelikedProduct/{id}")
    public String deleteCartProduct(@PathVariable("id") int id,HttpSession session){
        String email = (String) session.getAttribute("auth");
        likesService.deleteProduct(id,email);
        return "redirect:/Likes";
    }

}
