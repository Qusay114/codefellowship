package codefellowship.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<ApplicationUser> applicationUsers = new HashSet<>();
//    public Role(){}

    public Role(String name){
        this.name = name ;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
