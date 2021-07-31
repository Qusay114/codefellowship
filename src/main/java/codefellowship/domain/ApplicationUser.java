package codefellowship.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true)
    private String username ;

    private String password ;
    private String firstName ;
    private String lastName ;
    private String dateOfBirth ;
    @Column(columnDefinition = "text")
    private String bio ;

    @OneToMany(mappedBy="applicationUser")
    private List<Post> posts ;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "appuser_role",
            joinColumns = @JoinColumn(name = "appuser_id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "userapp_userapp" ,
            joinColumns = @JoinColumn(name="from_id") ,
            inverseJoinColumns = @JoinColumn(name = "to_id")
    )
    private Set<ApplicationUser> following = new HashSet<>() ;

    @ManyToMany
    private Set<ApplicationUser> followers = new HashSet<>() ;

    public ApplicationUser(){}
    public ApplicationUser(String username , String password){
        this.username = username ;
        this.password = password ;
    }
    public ApplicationUser(String username , String password, String firstName , String lastName , String dateOfBirth ){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.dateOfBirth = dateOfBirth ;
        this.username = username ;
        this.password = password ;
    }
    public ApplicationUser(String username , String password, String firstName , String lastName , String dateOfBirth , String bio ){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.dateOfBirth = dateOfBirth ;
        this.bio = bio ;
        this.username = username ;
        this.password = password ;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public Set<ApplicationUser> getFollowers() {
        return followers;
    }

    public Set<ApplicationUser> addFollowing(ApplicationUser applicationUser){
        this.following.add(applicationUser);
        return this.following ;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (Role role : this.roles)
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return  simpleGrantedAuthorities ;
    }

    @Override
    public String getPassword() {
        return this.password ;
    }

    @Override
    public String getUsername() {
        return this.username ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return true ;
    }
}
