package codefellowship.web;

import codefellowship.domain.ApplicationUser;
import codefellowship.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class CommonControllers {

    @Autowired
    private ApplicationUserRepository applicationUserRepository ;
    @Autowired
    private BCryptPasswordEncoder encoder ;

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createAppUser(@RequestParam String username , @RequestParam String password
                                    , @RequestParam String firstName , @RequestParam String lastName
                                    , @RequestParam String bio , @RequestParam String dateOfBirth){

        ApplicationUser applicationUser = new ApplicationUser(username , encoder.encode(password));
        applicationUser.setFirstName(firstName);
        applicationUser.setLastName(lastName);
        applicationUser.setDateOfBirth(dateOfBirth);
        applicationUser.setBio(bio);
        applicationUser = applicationUserRepository.save(applicationUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser , null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login" ;
    }
}
