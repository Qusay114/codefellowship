package codefellowship.web;

import codefellowship.domain.ApplicationUser;
import codefellowship.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository ;

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser appUser = applicationUserRepository.findApplicationUserByUsername(userDetails.getUsername());
        model.addAttribute("appUser" , appUser);
        model.addAttribute("posts" , appUser.getPosts());
        return "profile" ;
    }
}
