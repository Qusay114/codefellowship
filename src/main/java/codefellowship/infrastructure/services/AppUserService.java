package codefellowship.infrastructure.services;

import codefellowship.domain.ApplicationUser;

public interface AppUserService {
    ApplicationUser findAppUser(String username);
    ApplicationUser createAppUser(ApplicationUser applicationUser);
}
