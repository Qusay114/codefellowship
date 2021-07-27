package codefellowship.infrastructure.services;

import codefellowship.domain.ApplicationUser;
import codefellowship.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AppUserService")
public class AppUserServiceImp implements AppUserService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository ;

    @Override
    public ApplicationUser findAppUser(String username) {
       return applicationUserRepository.findApplicationUserByUsername(username);
    }

    @Override
    public ApplicationUser findAppUser(Long id) {
        return applicationUserRepository.findById(id).orElseThrow() ;
    }

    @Override
    public ApplicationUser createAppUser(ApplicationUser applicationUser) {
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser deleteUser(Long id) {
        ApplicationUser applicationUser = applicationUserRepository.findById(id).orElseThrow();
        applicationUserRepository.deleteById(id);
        return applicationUser;
    }
}
