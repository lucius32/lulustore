package springstore.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String emailAddress;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private String userRole;
    @OneToMany(mappedBy = "userId", orphanRemoval = true)
    private List<Likes> likes;
    @OneToMany(mappedBy = "proId", orphanRemoval = true)
    private List<Cart> carts;

}
