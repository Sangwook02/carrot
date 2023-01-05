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
    @Id @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @NotEmpty
    private Long images;
    @NotEmpty
    private String name;
    @NotEmpty
    private Enum<Category> category;
    @NotEmpty
    private int price;
    @NotEmpty
    private int liked;
    @NotEmpty
    private LocalDateTime time;
    @NotEmpty
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
