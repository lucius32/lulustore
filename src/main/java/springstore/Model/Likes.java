package springstore.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private int likesId;

        @ManyToOne(fetch = FetchType.LAZY)
        private Product proId;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "user")
        private User userId;

}
