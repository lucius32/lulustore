package springstore.Model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prodId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String productCategory;
    @Column(nullable = false)
    private double productPrice;
    @Column(nullable = false)
    private int productQuantity;
    @Column(nullable = false)
    private String productImage;
    @OneToMany(mappedBy = "proId", orphanRemoval = true)
    private List<Likes> likes;
    @OneToMany(mappedBy = "proId", orphanRemoval = true)
    private List<Cart> carts;

}