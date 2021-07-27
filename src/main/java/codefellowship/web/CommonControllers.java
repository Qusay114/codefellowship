package codefellowship.web;

import codefellowship.domain.ApplicationUser;
import codefellowship.domain.Role;
import codefellowship.infrastructure.ApplicationUserRepository;
import codefellowship.infrastructure.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CommonControllers {

    @Autowired
    private AppUserService appUserService ;

    @Autowired
    private BCryptPasswordEncoder encoder ;

    @GetMapping("/")
    public String getHomePage(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){

            model.addAttribute("showSignup" , true);
            model.addAttribute("showLogin" , true);
        }
        else{
            model.addAttribute("showLogout" , true);
            model.addAttribute("username" ,
            ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                    );
            model.addAttribute("showUsername" , true);
        }
        return "home";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createAppUser(@RequestParam String username , @RequestParam String password
                                    , @RequestParam String firstName , @RequestParam String lastName
                                    , @RequestParam String bio , @RequestParam String dateOfBirth
                                    , @RequestParam String rolePassword){

        ApplicationUser applicationUser = new ApplicationUser(username , encoder.encode(password));
        applicationUser.setFirstName(firstName);
        applicationUser.setLastName(lastName);
        applicationUser.setDateOfBirth(dateOfBirth);
        applicationUser.setBio(bio);
        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMIN");
        Set<Role> roles = new HashSet<>();
        if(rolePassword.equals("wallahadmin"))
            roles.add(adminRole);
        else
            roles.add(userRole);

        applicationUser.setRoles(roles);
        applicationUser = appUserService.createAppUser(applicationUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser, null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login" ;
    }

    @GetMapping("/login/error")
    public String failureLogin(Model model){
        model.addAttribute("failed" , true);
        return "login";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(){
        return "access-denied";
    }
}
