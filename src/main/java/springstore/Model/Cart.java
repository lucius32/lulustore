package springstore.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cart{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private int cartId;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user")
 private User userId;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "prod")
private Product proId;

private int prodQuantity;

}
