package codefellowship.infrastructure;

import codefellowship.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findApplicationUserByUsername(username);

        if(applicationUser == null)
            throw new UsernameNotFoundException(username + "NOT FOUND!");
        return applicationUser ;
    }
}
