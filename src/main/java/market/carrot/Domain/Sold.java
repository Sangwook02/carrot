package market.carrot.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@Table(name = "sold")
public class Sold {
    @Id @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotEmpty
    private User user;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    @NotEmpty
    private Item item;
}
