package codefellowship.web;

import codefellowship.domain.ApplicationUser;
import codefellowship.infrastructure.ApplicationUserRepository;
import codefellowship.infrastructure.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService ;

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        System.out.println("before anything");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("---------username " + userDetails.getUsername());
        ApplicationUser appUser = appUserService.findAppUser(userDetails.getUsername());
        System.out.println("-------- Bio " + appUser.getBio());
        model.addAttribute("appUser" , appUser);
        model.addAttribute("posts" , appUser.getPosts());
        return "profile" ;
    }
}
