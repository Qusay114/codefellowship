package codefellowship.web;

import codefellowship.domain.Post;
import codefellowship.infrastructure.ApplicationUserRepository;
import codefellowship.infrastructure.PostRepository;
import codefellowship.infrastructure.services.AppUserService;
import codefellowship.infrastructure.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class PostController {

    @Autowired
    private AppUserService appUserService ;
    @Autowired
    private PostService postService ;

    @PostMapping("/addpost")
    public RedirectView addNewPost(@RequestParam String body){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String createdAt = localDateTime.format(dateTimeFormatter);
        Post post = new Post(body , createdAt);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setApplicationUser(appUserService.findAppUser(userDetails.getUsername()));
        postService.createPost(post);
        return new RedirectView("/profile");
    }
}
