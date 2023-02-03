package market.carrot.Domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemimages_id")
    private ItemImages itemImages;
//    @NotEmpty
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
    // 판매중인지 여부
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
