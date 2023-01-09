package market.carrot.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "item")
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemimages_id")
    private ItemImages itemImages;
    @NotEmpty
    private String name;
//    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Category category;
//    @NotEmpty
    private int price;
    private int liked;
//    @NotEmpty
    private LocalDateTime time;
//    @NotEmpty
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
