package codefellowship.web;

import codefellowship.domain.ApplicationUser;
import codefellowship.infrastructure.ApplicationUserRepository;
import codefellowship.infrastructure.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService ;

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser appUser = appUserService.findAppUser(userDetails.getUsername());
        model.addAttribute("appUser" , appUser);
        model.addAttribute("posts" , appUser.getPosts());
        model.addAttribute("showLogout" , true);
        model.addAttribute("username" , appUser.getUsername());
        model.addAttribute("showUsername" , true);
        return "profile" ;
    }

    @GetMapping("/users/{id}")
    public String getUsersPage(Model model , @PathVariable Long id){
        model.addAttribute("appUser" , appUserService.findAppUser(id));
        model.addAttribute("posts" , appUserService.findAppUser(id).getPosts());
        return "user" ;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("appUsers" , appUserService.findAppUsers());
        return "users" ;
    }



    @PostMapping("/users/{id}")
    public RedirectView deleteUser(Model model , @PathVariable Long id){
        appUserService.deleteUser(id);
        return new RedirectView("/");
    }

    @PostMapping("/users/following/{id}")
    public RedirectView addFollowing(@PathVariable Long id){
        ApplicationUser applicationUser = appUserService.findAppUser(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser applicationUser1 = appUserService.findAppUser(userDetails.getUsername());
        applicationUser1.addFollowing(applicationUser);
        return new RedirectView("/users") ;
    }

    @GetMapping("/test")
    public ResponseEntity<ApplicationUser> test(){
        ApplicationUser applicationUser = new ApplicationUser("qusay0000" ,"0000" );
        applicationUser.setFirstName("Qusay");
        applicationUser.setLastName("Al-Amarat");
        return new ResponseEntity<>(applicationUser , HttpStatus.ACCEPTED);
    }
}
