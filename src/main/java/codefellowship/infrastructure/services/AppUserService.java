package codefellowship.infrastructure.services;

import codefellowship.domain.ApplicationUser;

import java.util.List;

public interface AppUserService {
    ApplicationUser findAppUser(String username);
    ApplicationUser findAppUser(Long id) ;
    ApplicationUser createAppUser(ApplicationUser applicationUser);
    ApplicationUser deleteUser(Long id) ;

    List<ApplicationUser> findAppUsers();
}
